package ClinicaStore;

import java.io.Serializable;

public abstract class Animal implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	private int idade;
	private String dono;
	protected String especie;

	public Animal(String nome, int idade, String dono) {
		this.nome = nome;
		this.idade = idade;
		this.dono = dono;
	}

	public String toString() {
		String retorno = "";
		retorno += "Nome: " + this.nome + "\n";
		retorno += "Idade: " + this.idade + " anos\n";
		retorno += "Dono: " + this.dono + "\n";
		retorno += "Especie: " + this.especie + "\n";
		retorno += "Barulho: " + soar() + "\n";
		return retorno;
	}

	public abstract String soar();

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
