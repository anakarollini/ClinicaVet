package Clinica.pi.ClinicaVeterinaria.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import Clinica.pi.ClinicaVeterinaria.models.Clinica;
import Clinica.pi.ClinicaVeterinaria.models.Paciente;
import Clinica.pi.ClinicaVeterinaria.repositories.ClinicaRepository;
import Clinica.pi.ClinicaVeterinaria.repositories.PacienteRepository;

@Controller
@RequestMapping("/clinica")
public class ClinicaController {

	@Autowired
	private ClinicaRepository cr;
	@Autowired
	private PacienteRepository pr;

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

	@GetMapping("/{id}")
	public ModelAndView detalhar(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Clinica> opt = cr.findById(id);

		if (opt.isEmpty()) {
			md.setViewName("redirect:/clinica");
			return md;
		}
		md.setViewName("clinica/detalhes");
		Clinica clinica = opt.get();
		md.addObject("clinica", clinica);
		
		List<Paciente> pacientes = pr.findByClinica(clinica);
		md.addObject("pacientes", pacientes);

		return md;

	}
	
	@PostMapping("/{idClinica}")
	public String salvarPaciente(@PathVariable Long idClinica, Paciente paciente) {
		
		System.out.println("Id do veterinário: " + idClinica);
		System.out.println(paciente);
		
		Optional<Clinica> opt = cr.findById(idClinica);
		
		if(opt.isEmpty()) {
			return "redirect:/clinica";
		}
		
		Clinica clinica = opt.get();
		paciente.setClinica(clinica);
		
		pr.save(paciente);
		
		return "redirect:/clinica/{idClinica}";
	}

}
