package com.sabrigulseven.appointment.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sabrigulseven.appointment.model.Admin;
import com.sabrigulseven.appointment.model.Patient;
import com.sabrigulseven.appointment.model.Physiotherapist;
import com.sabrigulseven.appointment.model.User;
import com.sabrigulseven.appointment.service.AuthService;

@Controller
public class AuthController {

	private final AuthService adminService;
    private final AuthService patientService;
    private final AuthService physiotherapistService;

    public AuthController(@Qualifier("adminServiceImpl") AuthService adminService,
                           @Qualifier("patientServiceImpl") AuthService patientService,
                           @Qualifier("physiotherapistServiceImpl") AuthService physiotherapistService) {
        this.adminService = adminService;
        this.patientService = patientService;
        this.physiotherapistService = physiotherapistService;
    }
	
	@GetMapping("/login")
	public String showLoginForm(HttpSession session) {
		Patient patient = (Patient) session.getAttribute("patient");
		if (patient != null) {
			return "/";
		}
		Physiotherapist physiotherapist = (Physiotherapist) session.getAttribute("physiotherapist");
		if (physiotherapist != null) {
			return "/";
		}
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin != null) {
			System.out.println("admin session");
			return "/";
		}
		return "login";
	}

	@PostMapping("/login")
    public String login(@RequestParam("identityNumber") long identityNumber, @RequestParam("password") String password, HttpSession session) {
        User user = adminService.loginUser(identityNumber, password);
        if (user instanceof Admin) {
            session.setAttribute("userType", "admin");
            session.setAttribute("admin", user);
            return "redirect:/"; // Redirect to the home page	
        }

        user = physiotherapistService.loginUser(identityNumber, password);
        if (user instanceof Physiotherapist) {
            session.setAttribute("userType", "physiotherapist");
            session.setAttribute("physiotherapist", user);
            return "redirect:/"; // Redirect to the home page		
        }

        user = patientService.loginUser(identityNumber, password);
        if (user instanceof Patient) {
            session.setAttribute("userType", "patient");
            session.setAttribute("patient", user);
            return "redirect:/"; // Redirect to the home page
        }

        return "redirect:/login?error=invalid"; // Redirect to the login page with an error message
    }

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
