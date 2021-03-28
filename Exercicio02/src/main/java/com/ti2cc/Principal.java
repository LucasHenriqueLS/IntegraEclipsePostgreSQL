package com.ti2cc;

import java.util.*;

public class Principal {

	public static Scanner sc = new Scanner(System.in);

	public static void menu(){
		System.out.println("**********************************");
		System.out.println("****     MENU DE OPCOES       ****");
		System.out.println("**********************************");
		System.out.println("1) Listar");
		System.out.println("2) Inserir");
		System.out.println("3) Excluir");
		System.out.println("4) Atualizar");
		System.out.println("5) Sair");
	}
	
	public static void main(String[] args) {
		
		DAO dao = new DAO();
		
		dao.conectar();

		int opcao = -1;

		Fruta[] frutas = dao.getFrutas();

		do{
			menu();
			System.out.println("Digite uma Opcao");
			opcao = sc.nextInt();
			switch (opcao){
			case 1:
				//Mostrar frutas
				System.out.println("==== Mostrar frutas === ");		
				for(int i = 0; i < frutas.length; i++) {
					System.out.println(frutas[i].toString());
				}
				break;
	
			case 2:
				int codigoTemp, quantidadeTemp;
				String nomeTemp;
				System.out.println("Digite o código da fruta que será inserida");
				codigoTemp = sc.nextInt();
				System.out.println("Digite o nome da fruta que será inserida");
				nomeTemp = sc.nextLine();
				System.out.println("Digite a quantidade da fruta que será inserida");
				quantidadeTemp = sc.nextInt();
				//Inserir um elemento na tabela
				Fruta fruta = new Fruta(codigoTemp, nomeTemp, quantidadeTemp);
				if(dao.inserirFruta(fruta) == true) {
					System.out.println("Inserção com sucesso -> " + fruta.toString());
				}
				break;
			
			case 3:
				int codigoDaEntrada;
				System.out.println("Digite o código da fruta que será excluída");
				codigoDaEntrada = sc.nextInt();
				//Excluir fruta
				for(int i = 0; i < frutas.length; i++){
					if(frutas[i].getCodigo() == codigoDaEntrada){
						dao.excluirFruta(frutas[i].getCodigo());
					}
				}
				break;
			
			case 4:
				int segCodigoDaEntrada;
				System.out.println("Digite o código da fruta que será atualizada");
				segCodigoDaEntrada = sc.nextInt();
				for(int i = 0; i < frutas.length; i++){
					if(frutas[i].getCodigo() == segCodigoDaEntrada){
						//Atualizar fruta
						System.out.println("Escolha qual elemento da fruta selecionada será atualizado");
						System.out.println("1) Nome");
						System.out.println("2) Quantidade");
						int opcao2;
						opcao2 = sc.nextInt();
						if(opcao2 == 1){
							System.out.println("Digite o novo nome");
							String novoNome;
							novoNome = sc.nextLine();
							frutas[i].setNome(novoNome);
							dao.atualizarFruta(frutas[i]);
						}else if(opcao2 == 2){
							System.out.println("Digite a nova quantidade");
							int novaQuantidade;
							novaQuantidade = sc.nextInt();
							frutas[i].setQuantidade(novaQuantidade);
							dao.atualizarFruta(frutas[i]);
						}else{
							System.out.println("Opção inválida, encerrando atualização...");
							i = frutas.length;
						}
					}
				}
				break;
	
			case 5:
				System.out.println("Programa sendo encerrado... ");
				break;
	
			default:
				System.out.println("Opcao Invalida... ");
				break;
			}
		}while(opcao != 5);
		
		dao.close();
	}
}