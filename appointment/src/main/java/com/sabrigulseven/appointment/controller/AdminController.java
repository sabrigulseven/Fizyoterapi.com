package com.sabrigulseven.appointment.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sabrigulseven.appointment.model.Admin;
import com.sabrigulseven.appointment.model.Appointment;
import com.sabrigulseven.appointment.model.Patient;
import com.sabrigulseven.appointment.model.Physiotherapist;
import com.sabrigulseven.appointment.model.Treatment;
import com.sabrigulseven.appointment.service.AppointmentService;
import com.sabrigulseven.appointment.service.PatientService;
import com.sabrigulseven.appointment.service.PhysiotherapistService;
import com.sabrigulseven.appointment.service.TreatmentService;
import com.sabrigulseven.appointment.util.FileUtility;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final PhysiotherapistService physiotherapistService;
    private final PatientService patientService;
    private final AppointmentService appointmentService;
    private final TreatmentService treatmentService;
	private final FileUtility fileUtility;

    public AdminController(PhysiotherapistService physiotherapistService,
                                PatientService patientService,
                                AppointmentService appointmentService,
                                TreatmentService treatmentService,
								FileUtility fileUtility) {
        this.physiotherapistService = physiotherapistService;
        this.patientService = patientService;
        this.appointmentService = appointmentService;
        this.treatmentService = treatmentService;
		this.fileUtility = fileUtility;
    }
	
	@GetMapping(value = "/physiotherapists")
	public String getphysiotherapistAdminPage(Model model, HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin == null) {
			return "redirect:/login";
		}
		model.addAttribute("physiotherapist", new Physiotherapist());
		List<Physiotherapist> physiotherapists = physiotherapistService.getAll();
		model.addAttribute("physiotherapists", physiotherapists);
		return "admin/physiotherapistoperations";
	}

	@PostMapping("/physiotherapists/save")
	public String registerPhysiotherapist(@Valid @ModelAttribute("physiotherapist") Physiotherapist physiotherapist,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "redirect:/admin/physiotherapists";
		}
		physiotherapistService.save(physiotherapist);
		return "redirect:/admin/physiotherapists";
	}

	@PostMapping("/physiotherapists/edit/{id}")
	public String updatePhysiotherapist(@PathVariable long id, Physiotherapist updatedPhysiotherapist) {
		physiotherapistService.updatePhysiotherapist( updatedPhysiotherapist);
		return "redirect:/admin/physiotherapists";
	}

	@PostMapping("/physiotherapists/delete/{id}")
	public String deletePhysiotherapist(@PathVariable Long id) {
		physiotherapistService.deleteById(id);
		return "redirect:/admin/physiotherapists";
	}

	@GetMapping("/patients")
	public String getPatientAdminPage(Model model, HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin == null) {
			return "redirect:/login";
		}
		model.addAttribute("patient", new Patient());
		List<Patient> patients = patientService.findAll();
		model.addAttribute("patients", patients);
		return "admin/patientoperations";
	}

	@PostMapping("/patients/save")
	public String registerPatient(@Valid @ModelAttribute("patient") Patient patient, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "redirect:/admin/patients";
		}
		patientService.save(patient);
		return "redirect:/admin/patients";
	}

	@PostMapping("/patients/edit/{id}")
	public String updatePatient(@PathVariable Long id, Patient updatedPatient) {
	 	patientService.update(updatedPatient);
		return "redirect:/admin/patients";
	}

	@PostMapping("/patients/delete/{id}")
	public String deletePatient(@PathVariable Long id) {
		patientService.deleteById(id);		
		return "redirect:/admin/patients";
	}

	@GetMapping("/appointments")
	public String getAppointmentAdminPage(Model model, HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin == null) {
			return "redirect:/login";
		}
		List<Physiotherapist> physiotherapists = physiotherapistService.getAll();
		model.addAttribute("physiotherapists", physiotherapists);

		return "admin/appointmentoperations";
	}

	@PostMapping("/appointments")
	public String filterAppointments(
	        @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	        @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
	        @RequestParam(value = "physiotherapist", required = false) Long physiotherapistId, Model model) {
	    
	    List<Appointment> appointments = appointmentService.getAppointmentList(physiotherapistId, startDate, endDate);
	    List<Physiotherapist> physiotherapists = physiotherapistService.getAll();

	    model.addAttribute("appointments", appointments);
	    model.addAttribute("physiotherapists", physiotherapists);
	    if (physiotherapistId != null) {
	        model.addAttribute("selectedPhysiotherapistId", physiotherapistId);
	    }
	    return "admin/appointmentoperations";
	}

	@GetMapping("/treatments")
    public String getTreatmentList(Model model,HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin == null) {
			return "redirect:/login";
		}
        List<Treatment> treatments = treatmentService.getAll();
        model.addAttribute("treatments", treatments);
        return "admin/treatments";
    }

    @GetMapping("/treatments/view/{id}")
    public void viewTreatment(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
        Treatment treatment = treatmentService.getById(id);
        if (treatment != null) {
			String filePath = fileUtility.getUploadPath() + File.separator + treatment.getPdfPath();
			fileUtility.sendFileResponse(filePath, response);
        }
        
    }

    @GetMapping("/treatments/delete/{id}")
    public String deleteTreatment(@PathVariable("id") Long id) {
        Treatment treatment = treatmentService.getById(id);
        if (treatment != null) {
            String filePath = fileUtility.getUploadPath() + File.separator + treatment.getPdfPath();
        	fileUtility.deleteFile(filePath);
            treatmentService.delete(treatment);	
        }
        return "redirect:/admin/treatments";
    }

    @PostMapping("/treatments/add")
    public String addTreatment(@RequestParam("name") String name,
                               @RequestParam("file") MultipartFile file) throws IOException {
    	if (fileUtility.isPdfFile(file)) {
			fileUtility.saveFile(file);
			Treatment treatment = new Treatment();
			treatment.setDescription(name);
			treatment.setPdfPath(file.getOriginalFilename());
			treatmentService.save(treatment);
		}
        return "redirect:/admin/treatments";
    }

}
