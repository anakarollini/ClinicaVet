package Clinica.pi.ClinicaVeterinaria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Clinica.pi.ClinicaVeterinaria.models.Clinica;
import Clinica.pi.ClinicaVeterinaria.models.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{
	
	List<Paciente> findByClinica(Clinica clinica);

}
