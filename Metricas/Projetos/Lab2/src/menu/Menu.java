package menu;

import java.util.Scanner;

import agenda.Agenda;
import util.Util;

public class Menu {
	
	private static Scanner sc = new Scanner(System.in);
	private static Agenda agenda = new Agenda();
	
	public static void main(String[] args) {
		String entrada;
		do {
			System.out.print("(C)adastrar Contato" + Util.QUEBRA_DE_LINHA + 
					"(L)istar Contatos" + Util.QUEBRA_DE_LINHA +
					"(E)xibir Contato" + Util.QUEBRA_DE_LINHA +
					"(S)air" + Util.QUEBRA_DE_LINHA +
					Util.QUEBRA_DE_LINHA +
					"Opção> ");		
			entrada = sc.nextLine();
			switch (entrada) {
				case "C":
					cadastrarContato();
					break;
				case "L":
					listarContato();
					break;
				case "E":
					exibirContato();
					break;
				case "S":
					break;
				default:
					System.out.println("OPÇÃO INVÁLIDA!");
					break;
			}
		} while(!entrada.equals("S"));
	}

	private static void exibirContato() {
		System.out.print("Contato> ");
		int posicao = Integer.parseInt(sc.nextLine());
		
		try {
			agenda.getContato(posicao);
			System.out.println(agenda.getContato(posicao) + Util.QUEBRA_DE_LINHA);
		} catch (IllegalArgumentException e) {
			System.out.println("POSIÇÃO INVÁLIDA!" + Util.QUEBRA_DE_LINHA);
		}
	}

	private static void listarContato() {
		System.out.print(agenda.listarContatos() + Util.QUEBRA_DE_LINHA);
	}

	private static void cadastrarContato() {
		System.out.print("Posição: ");
		int posicao = Integer.parseInt(sc.nextLine());
		
		System.out.print("Nome: ");
		String nome = sc.nextLine();
		
		System.out.print("Sobrenome: ");
		String sobrenome = sc.nextLine();
		
		System.out.print("Telefone: ");
		String telefone = sc.nextLine();
		
		try {
			agenda.cadastrarContato(posicao, nome, sobrenome, telefone);
			System.out.println("CADASTRO REALIZADO!");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println(Util.QUEBRA_DE_LINHA);
		}
	}

}
