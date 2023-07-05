package com.sabrigulseven.appointment.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sabrigulseven.appointment.model.Appointment;
import com.sabrigulseven.appointment.model.Patient;
import com.sabrigulseven.appointment.model.Physiotherapist;
import com.sabrigulseven.appointment.model.Treatment;
import com.sabrigulseven.appointment.service.AppointmentService;
import com.sabrigulseven.appointment.service.EmailSenderService;
import com.sabrigulseven.appointment.service.PatientService;
import com.sabrigulseven.appointment.service.PhysiotherapistService;
import com.sabrigulseven.appointment.service.TreatmentService;
import com.sabrigulseven.appointment.util.ScheduleConstants;

@Controller
@RequestMapping("/physiotherapist")
public class PhysiotherapistController {


	private final PhysiotherapistService physiotherapistService;
	private final AppointmentService appointmentService;
	private final PatientService patientService;
	private final TreatmentService treatmentService;
	private final EmailSenderService emailSenderService;

	 public PhysiotherapistController(PhysiotherapistService physiotherapistService, AppointmentService appointmentService,
                                     PatientService patientService, TreatmentService treatmentService,
                                     EmailSenderService emailSenderService) {
        this.physiotherapistService = physiotherapistService;
        this.appointmentService = appointmentService;
        this.patientService = patientService;
        this.treatmentService = treatmentService;
        this.emailSenderService = emailSenderService;
    }
	
	@GetMapping("/appointments")
	public String getAppointments(HttpSession session, Model model) {
		Physiotherapist physiotherapist = (Physiotherapist) session.getAttribute("physiotherapist");
		if (physiotherapist != null) {
			List<Appointment> appointmentsToday = appointmentService.getTodaysAppointmentList(physiotherapist);
			List<Appointment> appointmentsUpcoming = appointmentService.getUpcomingAppointmentsForPhysiotherapist(physiotherapist);
			model.addAttribute("appointmentsToday", appointmentsToday);
			model.addAttribute("appointmentsUpcoming", appointmentsUpcoming);
			return "physiotherapist/appointments";
		} else {
			return "redirect:/login";
		}
	}
	
	@GetMapping("/schedule")
	public String getPhysiotherapistSchedule(HttpSession session, Model model) {
		Physiotherapist physiotherapist = (Physiotherapist) session.getAttribute("physiotherapist");
		if (physiotherapist == null) {
			return "redirect:/login";
		}
	    model.addAttribute(new Appointment());
	    model.addAttribute("todayDate",LocalDate.now());
	    model.addAttribute("startHour",ScheduleConstants.START_TIME);
	    model.addAttribute("endHour",ScheduleConstants.END_TIME);
	    model.addAttribute("appointmentDuration", ScheduleConstants.APPOINTMENT_DURATION*60);
	    return "physiotherapist/schedule";
	}

	@PostMapping("/schedule")
	public String savePhysiotherapistSchedule(
			@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
	        @RequestParam("startTime") @DateTimeFormat(pattern = "HH:mm") LocalTime startTime,
	        @RequestParam("endTime") @DateTimeFormat(pattern = "HH:mm") LocalTime endTime,
	        HttpSession session) {

		Physiotherapist physiotherapist = (Physiotherapist) session.getAttribute("physiotherapist");
		if (physiotherapist == null) {
			return "redirect:/login";
		}
		List<Appointment> appointments = appointmentService.generateAppointments(physiotherapist, date, startTime, endTime);
		appointmentService.saveAll(appointments);
		return "redirect:/physiotherapist/schedule";
	}

	@GetMapping("/patient/{id}")
	public String getPatientView(HttpSession session, @PathVariable long id, Model model) {
		Physiotherapist physiotherapist = (Physiotherapist) session.getAttribute("physiotherapist");
		if (physiotherapist == null) {
			return "redirect:/login";
		}
		Patient patient = patientService.getPatientById(id);
		if(patient!= null) {
			model.addAttribute("patient", patient);
			model.addAttribute("appointments", appointmentService.getAll(patient));
			model.addAttribute("treatments", treatmentService.getAll());
			
			return "physiotherapist/patient";
		}
		return "redirect:/physiotherapist/patients";
	}
	
	@PostMapping("/patient/sendmail")
	public String sendEmail(@RequestParam("patientId") Long patientId,
	                        @RequestParam("treatmentId") Long treatmentId) {
	    Patient patient = patientService.getPatientById(patientId);
	    Treatment treatment = treatmentService.getById(treatmentId);
	    if (patient != null && treatment != null) {
	        emailSenderService.sendTreatmentMail(patient.getEmailAddress(), treatment.getPdfPath());
	        return "redirect:/physiotherapist/patient/"+patientId;
	    } 
		return "redirect:/";    
	}

	@GetMapping("/patients")
	public String getPatientList(HttpSession session, Model model) {
		Physiotherapist physiotherapist = (Physiotherapist) session.getAttribute("physiotherapist");
		if (physiotherapist == null) {
			return "redirect:/login";
		}
		List<Patient> patients=physiotherapistService.getPatientsOfPhysiotherapist(physiotherapist);
		model.addAttribute("patients", patients);
		
		return "physiotherapist/patients";
	}
	
	@GetMapping("/patient/sendmail")
    public String sendEmail(Model model) {
		
        return "/physiotherapist/sendmail";
    }
}
