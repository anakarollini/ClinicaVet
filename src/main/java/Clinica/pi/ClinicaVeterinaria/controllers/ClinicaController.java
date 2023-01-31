package Clinica.pi.ClinicaVeterinaria.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Clinica.pi.ClinicaVeterinaria.models.Clinica;
import Clinica.pi.ClinicaVeterinaria.models.Paciente;
import Clinica.pi.ClinicaVeterinaria.repositories.ClinicaRepository;
import Clinica.pi.ClinicaVeterinaria.repositories.PacienteRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/clinica")
public class ClinicaController {

	@Autowired
	private ClinicaRepository cr;
	@Autowired
	private PacienteRepository pr;

	@GetMapping("/form")
	public String form(Clinica clinica) {
		return "clinica/formClinica";
	}

	@PostMapping
	public String salvar(@Valid Clinica clinica, BindingResult result, RedirectAttributes atrAttributes) {

		if(result.hasErrors()) {
			return form(clinica);
		}
		
		System.out.println(clinica);
		cr.save(clinica);
		atrAttributes.addFlashAttribute("mensagem", "Veterinário salvo com sucesso!");

		return "redirect:/clinica";
	}

	@GetMapping
	public ModelAndView listar() {
		List<Clinica> clinicas = cr.findAll();
		ModelAndView mv = new ModelAndView("clinica/lista");
		mv.addObject("clinica", clinicas);
		return mv;

	}

	@GetMapping("/{id}")
	public ModelAndView detalhar(@PathVariable Long id, Paciente paciente) {
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
	public ModelAndView salvarPaciente(@PathVariable Long idClinica, @Valid Paciente paciente, BindingResult result, RedirectAttributes atrAttributes) {

		if(result.hasErrors()) {
			return detalhar(idClinica, paciente);
		}
		
		System.out.println("Id do veterinário: " + idClinica);
		System.out.println(paciente);

		Optional<Clinica> opt = cr.findById(idClinica);

		if (opt.isEmpty()) {
			return new ModelAndView("redirect:/clinica");
		}

		Clinica clinica = opt.get();
		paciente.setClinica(clinica);

		pr.save(paciente);
		atrAttributes.addFlashAttribute("mensagem", "Paciente salvo com sucesso!");

		return new ModelAndView("redirect:/clinica");
	}

	@GetMapping("/{id}/selecionar")
	public ModelAndView selecionarClinica(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Clinica> opt = cr.findById(id);

		if (opt.isEmpty()) {
			md.setViewName("redirect:/clinica");
			return md;

		}

		Clinica clinica = opt.get();
		md.setViewName("clinica/formClinica");
		md.addObject("clinica", clinica);
		return md;

	}
	
	@GetMapping("/{idClinica}/pacientes/{idPaciente}/selecionar")
	public ModelAndView selecionarPaciente(@PathVariable Long idClinica, @PathVariable Long idPaciente) {
		 
		ModelAndView md = new ModelAndView();
		Optional<Clinica> optClinica = cr.findById(idClinica); 
		Optional<Paciente> optPaciente = pr.findById(idPaciente);    
		
		if(optClinica.isEmpty() || optPaciente.isEmpty()) {
			md.setViewName("redirect:/clinica");
			return md;
			
		}
		
		Clinica clinica = optClinica.get();
		 Paciente paciente = optPaciente.get();
		 
		 if(clinica.getId() != paciente.getClinica().getId()) {
			 md.setViewName("redirect:/clinica");
			 return md;
			 
		 }
		 md.setViewName("clinica/detalhes");
		md.addObject("paciente", paciente);
		md.addObject("clinica", clinica);
		md.addObject("pacientes", pr.findByClinica(clinica));
		return md;
	}

	@GetMapping("/{id}/remover")
	public String apagarClinica(@PathVariable Long id, RedirectAttributes atrAttributes) {

		Optional<Clinica> opt = cr.findById(id);

		if (!opt.isEmpty()) {

			Clinica clinica = opt.get();

			List<Paciente> pacientes = pr.findByClinica(clinica);

			pr.deleteAll(pacientes);

			cr.delete(clinica);
			atrAttributes.addFlashAttribute("mensagem", "Veterinário removido com sucesso!");

		}

		return "redirect:/clinica";
	}
	
	@GetMapping("/{idClinica}/pacientes/{idPaciente}/remover")
	public String apagarPaciente(@PathVariable Long idClinica, @PathVariable Long idPaciente, RedirectAttributes atrAttributes) {
		
		Optional<Paciente> opt = pr.findById(idPaciente);
		if(!opt.isEmpty()) {
			
			Paciente paciente = opt.get();
			pr.delete(paciente);
			atrAttributes.addFlashAttribute("mensagem", "Paciente removido com sucesso!");
		}
		
		return "redirect:/clinica/{idPaciente}";
	}
	
	

}
