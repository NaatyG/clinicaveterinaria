package ClinicaStore;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ClinicaStore {
	private ArrayList<Animal> animal;

	public ClinicaStore() {
		this.animal = new ArrayList<Animal>();
	}

	public String[] leValores(String[] dadosIn) {
		String[] dadosOut = new String[dadosIn.length];

		for (int i = 0; i < dadosIn.length; i++) {
			String input = JOptionPane.showInputDialog("Entre com " + dadosIn[i] + ": ");

			if (input == null) {
				throw new RuntimeException("Entrada cancelada pelo usuário.");
			}

			dadosOut[i] = input;
		}

		return dadosOut;
	}

	public Animal leAnimal() {

		String[] valores = new String[3];
		String[] nomeVal = { "Nome", "Idade", "Dono" };
		valores = leValores(nomeVal);

		int idade = this.retornaInteiro(valores[1]);

		Animal animal = new Animal(valores[0], idade, valores[2]);
		return animal;
	}

	public Cavalo leCavalo() {

		String[] valores = new String[3];
		String[] nomeVal = { "Nome", "Idade", "Dono" };
		valores = leValores(nomeVal);

		int idade = this.retornaInteiro(valores[1]);

		Cavalo cavalo = new Cavalo(valores[0], idade, valores[2]);
		return cavalo;
	}

	public Ganso leGanso() {

		String[] valores = new String[3];
		String[] nomeVal = { "Nome", "Idade", "Dono" };
		valores = leValores(nomeVal);

		int idade = this.retornaInteiro(valores[1]);

		Ganso ganso = new Ganso(valores[0], idade, valores[2]);
		return ganso;
	}

	private boolean intValido(String s) {
		try {
			Integer.parseInt(s); // M�todo est�tico, que tenta tranformar uma string em inteiro
			return true;
		} catch (NumberFormatException e) { // N�o conseguiu tranformar em inteiro e gera erro
			return false;
		}
	}

	public int retornaInteiro(String entrada) { // retorna um valor inteiro
		int numInt;

		// Enquanto n�o for poss�vel converter o valor de entrada para inteiro,
		// permanece no loop
		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
		}
		return Integer.parseInt(entrada);
	}

	public void salvaAnimal(ArrayList<Animal> animal) {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream("c:\\temp\\petStore.dat"));
			for (int i = 0; i < animal.size(); i++)
				outputStream.writeObject(animal.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Não foi possível criar o arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally { // Close the ObjectOutputStream
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

	@SuppressWarnings("finally")
	public ArrayList<Animal> recuperaAnimal() {
		ArrayList<Animal> animalTemp = new ArrayList<Animal>();

		ObjectInputStream inputStream = null;

		try {
			inputStream = new ObjectInputStream(new FileInputStream("c:\\temp\\petStore.dat"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Animal) {
					animalTemp.add((Animal) obj);
				}
			}
		} catch (EOFException ex) { // Quando chegar no final do arquivo
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Arquivo de animais não existe!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally { // Fecha o arquivo
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return animalTemp;
		}
	}

	public void menuClinicaStore() {

		String menu = "";
		String entrada;
		int opc1, opc2;

		do {
			menu = "Clínica Veterinária\n\n" +
					"1. Entrar com novo Animal\n" +
					"2. Listar Animais\n" +
					"3. Excluir Animais\n" +
					"4. Salvar Animais\n" +
					"5. Recuperar Dados\n" +
					"9. Sair";
			entrada = JOptionPane.showInputDialog(menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch (opc1) {
				case 1:// Entrar dados
					menu = "Escolha o animalzinho\n" +
							"Opções:\n" +
							"1. Ganso\n" +
							"2. Cavalo\n" +
							"3. Voltar\n";

					entrada = JOptionPane.showInputDialog(menu + "\n\n");
					opc2 = this.retornaInteiro(entrada);

					switch (opc2) {
						case 1:
							Ganso ganso = this.leGanso();
							animal.add((Animal) ganso);
							break;
						case 2:
							Cavalo cavalo = this.leCavalo();
							animal.add((Animal) cavalo);
							break;
						case 3:
							break;

						default:
							JOptionPane.showMessageDialog(null, "Opção inválida!");
					}
					break;

				case 2: // Exibir dados
					if (animal.size() == 0) {
						JOptionPane.showMessageDialog(null, "Nenhum animal salvo");
						break;
					}
					String dados = "";
					for (int i = 0; i < animal.size(); i++) {
						dados += animal.get(i).toString() + "-----------------------------\n";
					}
					JOptionPane.showMessageDialog(null, dados);
					break;

				case 3: // Limpar Dados
					if (animal.size() == 0) {
						JOptionPane.showMessageDialog(null, "Nenhum animal salvo");

					} else {
						String dado = "";
						for (int i = 0; i < animal.size(); i++) {
							dado += i + " - " + animal.get(i).toString() + "\n";
						}
						int posicao = Integer.parseInt(JOptionPane
								.showInputDialog("Digite a posição do animal que deseja excluir: \n" + dado));
						animal.remove(posicao);
					}
					break;

				case 4: // Grava Dados
					if (animal.size() == 0) {
						JOptionPane.showMessageDialog(null, "Nenhum animal salvo.");
						break;
					}
					this.salvaAnimal(animal);
					JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
					break;
				case 5: // Recupera Dados
					animal = recuperaAnimal();
					if (animal.size() == 0) {
						JOptionPane.showMessageDialog(null, "Não há dados para apresentar.");
						break;
					}
					JOptionPane.showMessageDialog(null, "Dados RECUPERADOS com sucesso!");
					break;
				case 9:
					JOptionPane.showMessageDialog(null, "Obrigada por utilizar nosso App!");
					break;
			}
		} while (opc1 != 9);
	}

	public static void main(String[] args) {

		ClinicaStore pet = new ClinicaStore();
		pet.menuClinicaStore();

	}

}
