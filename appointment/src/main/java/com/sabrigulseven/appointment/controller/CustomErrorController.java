package com.sabrigulseven.appointment.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // get the error status
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            // handle different error status codes
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "redirect:/"; // anasayfaya yönlendir
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/500"; // özel hata sayfasına yönlendir
            }
        }
        return "error"; // varsayılan hata sayfasına yönlendir
    }

}


