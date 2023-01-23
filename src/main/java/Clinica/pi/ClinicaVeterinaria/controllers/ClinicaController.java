package Clinica.pi.ClinicaVeterinaria.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClinicaController {
	
	@RequestMapping("/clinica/form")
	public String form() {
		return "formClinica";
	}

}
