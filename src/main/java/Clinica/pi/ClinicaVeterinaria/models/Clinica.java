package Clinica.pi.ClinicaVeterinaria.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Clinica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@NotBlank
	private String crmv;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataPlantao;
	
	
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
	public String getCrmv() {
		return crmv;
	}
	public void setCrmv(String crmv) {
		this.crmv = crmv;
	}
	public LocalDate getDataPlantao() {
		return dataPlantao;
	}
	public void setDataPlantao(LocalDate dataPlantao) {
		this.dataPlantao = dataPlantao;
	}
	@Override
	public String toString() {
		return "Clinica [id=" + id + ", nome=" + nome + ", crmv=" + crmv + ", dataPlantao=" + dataPlantao + "]";
	}
	
}
