package ClinicaStore;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ClinicaStore1 {

	private ArrayList<Animal> animal;

	public ClinicaStore1() {
		this.animal = new ArrayList<Animal>();
	}

	public void adicionaAnimal(Animal mani) {
		this.animal.add(mani);
	}

	public void listarAnimais() {
		for (Animal mani : animal) {
			System.out.println(mani.toString());
		}
		System.out.println("Total = " + this.animal.size() + " animais listados com sucesso!\n");
	}

	public void excluirAnimal(Animal mani) {
		if (this.animal.contains(mani)) {
			this.animal.remove(mani);
			System.out.println("[Animal " + mani.toString() + "excluido com sucesso!]\n");
		} else
			System.out.println("Animal inexistente!\n");
	}

	public void excluirAnimais() {
		animal.clear();
		System.out.println("Animais excluidos com sucesso!\n");
	}

	public void gravarAnimais() {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream("c:\\temp\\petStore.dat"));
			for (Animal mani : animal) {
				outputStream.writeObject(mani);
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void recuperarAnimais() {
		ObjectInputStream inputStream = null;
		try {
			inputStream = new ObjectInputStream(new FileInputStream("c:\\temp\\animais.dat"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Cavalo)
					this.animal.add((Cavalo) obj);
				else if (obj instanceof Ganso)
					this.animal.add((Ganso) obj);
			}
		} catch (EOFException ex) { // when EOF is reached
			System.out.println("Fim do arquivo!");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
					System.out.println("Animais recuperados com sucesso!\n");
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		ClinicaStore1 pet = new ClinicaStore1();

		Cavalo mustang = new Cavalo("Mustang", 3, "Maria");
		Cavalo pegasos = new Cavalo("Pégasos", 7, "Maria");
		Ganso bertie = new Ganso("Bertie", 2, "Jose");
		Ganso fluffy = new Ganso("Fluffy", 5, "Jose");
		pet.adicionaAnimal(mustang);
		pet.adicionaAnimal(pegasos);
		pet.adicionaAnimal(rex);
		pet.adicionaAnimal(toto);
		pet.listarAnimais();
		pet.gravarAnimais();
		pet.excluirAnimal(mustang);
		pet.listarAnimais();
		pet.excluirAnimais();
		pet.listarAnimais();
		pet.recuperarAnimais();
		pet.listarAnimais();
	}

}
