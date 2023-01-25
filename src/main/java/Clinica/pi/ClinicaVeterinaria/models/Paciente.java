package Clinica.pi.ClinicaVeterinaria.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String idade;
	private String especie;
	private String dataAtendimento;
	private String horaAtendimento;

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

	public String getData() {
		return dataAtendimento;
	}

	public void setData(String data) {
		this.dataAtendimento = data;
	}

	public String getHora() {
		return horaAtendimento;
	}

	public void setHora(String hora) {
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
