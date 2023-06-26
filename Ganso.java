package ClinicaStore;

public class Ganso extends Animal {

	private static final long serialVersionUID = 1L;

	public String soar() {
		return "Grasnar";
	}

	public Ganso(String nome, int idade, String dono) {
		super(nome, idade, dono);
		this.especie = "Ganso";
	}

	public String getNome() {
		return this.nome;
	}

	public int getIdade() {
		return this.idade;
	}

	public String getDono() {
		return this.dono;
	}

	public String getEspecie() {
		return this.especie;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public void setDono(String dono) {
		this.dono = dono;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}
}
