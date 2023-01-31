package Clinica.pi.ClinicaVeterinaria.models;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@NotBlank
	private String idade;
	@NotBlank
	private String especie;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataAtendimento;
	@NotNull
	private LocalTime horaAtendimento;

	@ManyToOne
	private Clinica clinica;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public LocalDate getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(LocalDate data) {
		this.dataAtendimento = data;
	}

	public LocalTime getHoraAtendimento() {
		return horaAtendimento;
	}

	public void setHoraAtendimento(LocalTime hora) {
		this.horaAtendimento = hora;
	}

	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}

	@Override
	public String toString() {
		return "Paciente [id=" + id + ", nome=" + nome + ", idade=" + idade + ", especie=" + especie
				+ ", dataAtendimento=" + dataAtendimento + ", horaAtendimento=" + horaAtendimento + ", clinica="
				+ clinica + "]";
	}

	

}
