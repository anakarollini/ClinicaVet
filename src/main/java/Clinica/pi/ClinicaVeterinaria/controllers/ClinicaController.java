package Clinica.pi.ClinicaVeterinaria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import Clinica.pi.ClinicaVeterinaria.models.Clinica;
import Clinica.pi.ClinicaVeterinaria.repositories.ClinicaRepository;

@Controller
@RequestMapping("/clinica")
public class ClinicaController {

	@Autowired
	private ClinicaRepository cr;

	@GetMapping("/form")
	public String form() {
		return "clinica/formClinica";
	}

	@PostMapping
	public String adicionar(Clinica clinica) {

		System.out.println(clinica);
		cr.save(clinica);

		return "clinica/veterinario-adicionado";
	}

	@GetMapping
	public ModelAndView listar() {
		List<Clinica> clinicas = cr.findAll();
		ModelAndView mv = new ModelAndView("clinica/lista");
		mv.addObject("clinica", clinicas);
		return mv;

	}

}
