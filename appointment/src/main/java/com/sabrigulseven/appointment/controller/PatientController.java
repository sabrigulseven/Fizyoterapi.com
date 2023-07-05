package com.sabrigulseven.appointment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sabrigulseven.appointment.model.Appointment;
import com.sabrigulseven.appointment.model.Patient;
import com.sabrigulseven.appointment.model.Physiotherapist;
import com.sabrigulseven.appointment.service.AppointmentService;
import com.sabrigulseven.appointment.service.PatientService;
import com.sabrigulseven.appointment.service.PhysiotherapistService;

@Controller
@RequestMapping("/patient")
public class PatientController {
	
	private final PatientService patientService;
	private final AppointmentService appointmentService;
	private final PhysiotherapistService physiotherapistService;
	
	public PatientController(PatientService patientService, AppointmentService appointmentService, PhysiotherapistService physiotherapistService) {
		this.patientService = patientService;
		this.appointmentService = appointmentService;
		this. physiotherapistService = physiotherapistService;
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model, HttpSession session) {
		Patient patient = (Patient) session.getAttribute("patient");
		if(patient != null) {
			return "/";
		}
		model.addAttribute("patient", new Patient());
		return "patient/register";
	}
	
	@PostMapping("/register")
	public String registerPatient(@Valid @ModelAttribute("patient") Patient patient, BindingResult bindingResult) {
	    if (bindingResult.hasErrors()) {
	        return "patient/register";
	    }
	    patientService.save(patient);
	    return "redirect:/login";
	}
	
	@GetMapping("/appointment")
	public String showAppointmentPage(HttpSession session, Model model) {
		Patient patient = (Patient) session.getAttribute("patient");
		if (patient == null) {
	        // Oturum açmış bir hastanın olmaması durumu
	        return "redirect:/login"; // Giriş sayfasına yönlendir
	    }
		
		model.addAttribute("physiotherapists", physiotherapistService.getAll());
		return "patient/appointment";
		 
	}
	
	@GetMapping("/appointment/book")
	public String bookAppointment(@RequestParam("physiotherapistId") long physiotherapistId, HttpSession session, Model model) {
		Patient patient = (Patient) session.getAttribute("patient");
		if (patient == null) {
	        // Oturum açmış bir hastanın olmaması durumu
	        return "redirect:/login"; // Giriş sayfasına yönlendir
	    }

		Physiotherapist physiotherapist = physiotherapistService.getPhysiotherapistById(physiotherapistId);	   
		if(physiotherapist != null) {
			model.addAttribute("physiotherapist", physiotherapist);
			model.addAttribute("appointments", appointmentService.getAvailableAppointmentsForPhysiotherapist(physiotherapist));
		} else {
			return "redirect:/patient/appointment";
		}		
		return "patient/appointmentbook";
		 
	}
	
	@PostMapping("/appointment/book/save")
	public String saveBookedAppointment(@RequestParam("appointmentId") long appointmentId, HttpSession session) {
	    Patient patient = (Patient) session.getAttribute("patient");
	    if (patient == null) {
	        // Oturum açmış bir hastanın olmaması durumu
	        return "redirect:/login"; // Giriş sayfasına yönlendir
	    }

	    Optional<Appointment> optionalAppointment = appointmentService.getById(appointmentId);
	    if (!optionalAppointment.isPresent()) {
	        // Hatalı randevu kimliği - Randevu bulunamadı
	        return "redirect:/patient/appointment/book?physiotherapistId=\" + appointment.getPhysiotherapist().getId();"; // Hata sayfasına yönlendir
	    }

	    Appointment appointment = optionalAppointment.get();
	    if (appointmentService.getAppointment(patient, appointment.getPhysiotherapist(), appointment.getDate()) != null) {
	    	// Hastanın aynı gün içerisinde aynı fizyoterapist ile randevusu var 
	        return "redirect:/patient/appointment/book?physiotherapistId=" + appointment.getPhysiotherapist().getId(); // Hata sayfasına yönlendir
	    }

	    appointment.setPatient(patient);
	    appointmentService.save(appointment);
	    return "redirect:/patient/appointments"; // Randevular sayfasına yönlendir
	}


	@GetMapping("/appointments")
	public String showAppointmentsPage(HttpSession session, Model model) {
		Patient patient = (Patient) session.getAttribute("patient");
		if (patient == null) {
	        // Oturum açmış bir hastanın olmaması durumu
	        return "redirect:/login"; // Giriş sayfasına yönlendir
	    }
		
		List<Appointment> appointmentsToday = appointmentService.getTodaysAppointmentList(patient);
		List<Appointment> appointmentsUpcoming = appointmentService.getUpcomingAppointmentsForPatient(patient);
		List<Appointment> appointmentsTodayAndUpcoming = new ArrayList<>();
		appointmentsTodayAndUpcoming.addAll(appointmentsToday);
		appointmentsTodayAndUpcoming.addAll(appointmentsUpcoming);
		
		List<Appointment> appointmentsPast = appointmentService.getPastAppoinmentsForPatient(patient);
		model.addAttribute("appointmentsFuture", appointmentsTodayAndUpcoming);
		model.addAttribute("appointmentsPast", appointmentsPast);
		return "patient/appointments";
		
	}
	
	@PostMapping("/appointment/cancel/{id}")
	public String cancelAppointment(@PathVariable Long id, HttpSession session) {
		Patient patient = (Patient) session.getAttribute("patient");
		if (patient == null) {
	        // Oturum açmış bir hastanın olmaması durumu
	        return "redirect:/login"; // Giriş sayfasına yönlendir
	    }
		Appointment appointment = appointmentService.getById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid appointment Id:" + id));
		appointment.setPatient(null);
		appointment.setAttendance(false);

		appointmentService.save(appointment);
		return "redirect:/patient/appointments";
	}
}

