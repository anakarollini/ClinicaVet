package Clinica.pi.ClinicaVeterinaria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Clinica.pi.ClinicaVeterinaria.models.Clinica;
import Clinica.pi.ClinicaVeterinaria.repositories.ClinicaRepository;

@Controller
public class ClinicaController {
	
	@Autowired
	private ClinicaRepository cr;
	
	@RequestMapping("/clinica/form")
	public String form() {
		return "clinica/formClinica";
	}
	
    @PostMapping("/veterinario")
	public String adicionar(Clinica clinica) {
    
    	System.out.println(clinica);
    	cr.save(clinica);
 
    
    	
		return "clinica/veterinario-adicionado";
	}

}
