package Clinica.pi.ClinicaVeterinaria.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Clinica.pi.ClinicaVeterinaria.models.Clinica;

@Controller
public class ClinicaController {
	
	@RequestMapping("/clinica/form")
	public String form() {
		return "formClinica";
	}
	
    @PostMapping("/veterinario")
	public String adicionar(Clinica clinica) {
    
    	System.out.println(clinica);
    	
    	
    	
		return "veterinario-adicionado";
	}

}
