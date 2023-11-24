/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servico;

import java.util.Scanner;
import modelo.Cliente;
import repositorio.ClienteRepositorio;

public class ClienteServico {
	private ClienteRepositorio repositorio;
	private Scanner entrada;

	public ClienteServico(Cliente[] clientes) {
		this.repositorio = new ClienteRepositorio();
		this.entrada = new Scanner(System.in);
	}

	public void desenvolvimentoCliente() {
		int opcCliente = 0;
		do {
			System.out.println("===========Menu de Opções de cliente===========");
			System.out.println("1- Inserir Cliente");
			System.out.println("2- Deletar Cliente");
			System.out.println("3- Alterar Cliente");
			System.out.println("Digite Zero para voltar ao menu anterior");
			opcCliente = entrada.nextInt();
			Cliente cliente = new Cliente();
			switch (opcCliente) {
			case 0:
				break;
			case 1: // Cadastro de clientes
				cliente = new Cliente();
				System.out.print("Nome do Cliente: ");
				cliente.setNome(entrada.next());
				System.out.print("Endereço: ");
				cliente.setEndereço(entrada.next());
				System.out.print("Cidade: ");
				cliente.setCidade(entrada.next());
				// recebe informações e chama metodo para salvar dados
				this.inserir(cliente);
				System.out.println("Registro salvo com sucesso!");
				return;
			case 2:// apagar cliente
				System.out.println("Informe o codigo do cliente: ");
				int codigoDeletar = entrada.nextInt();
				// recebe codigo do cliente e chama metodo para buscar o cliente a ser apagado
				cliente = this.getCliente(codigoDeletar);
				if (cliente != null) {
					this.deletar(cliente);
				}
				System.out.println("Registro apagado com sucesso!");
				return;
			case 3:// atualizar dados
				System.out.println("Informe o codigo do cliente: ");
				int codigoAtualizar = entrada.nextInt();
				// recebe codigo do cliente e chama metodo para buscar o cliente a ser
				// atualizado
				cliente = new Cliente();
				cliente.setCodigo(codigoAtualizar);
				System.out.print("Novo nome do Cliente: ");
				cliente.setNome(entrada.next());
				System.out.print("Novo endereço do Cliente: ");
				cliente.setEndereço(entrada.next());
				System.out.print("Nova cidade do Cliente: ");
				cliente.setCidade(entrada.next());
				// recebe todos os dados do cliente e atualiza no BD
				cliente = this.atualizar(cliente);
				if (cliente != null) {// caso retorne cliente indica que ele foi atualizado
					System.out.println("Registro atualizado com sucesso!"); // mostra sucesso
				}
				break;
			default:
				System.out.println("Digite uma opção válida");
			}
		} while (opcCliente != 0);
	}

	public Cliente inserir(Cliente cliente) {
		return repositorio.inserir(cliente);
	}

	public Cliente atualizar(Cliente cliente) {
		return repositorio.atualizar(cliente);
	}

	public Cliente getCliente(int codigo) {
		return repositorio.getByCodigo(codigo);
	}

	public Cliente deletar(Cliente cliente) {
		return repositorio.deletar(cliente);
	}

	public void listaClientes() {
		System.out.println("Deseja realmente imprimir o relatório (S\\N)");
		String resposta = entrada.next();
		if (resposta.equalsIgnoreCase("S")) {
			Cliente[] clientes = repositorio.getClientes();
			for (Cliente cliente : clientes) {
				System.out.println(cliente.toString());
			}
		}
	}

}
