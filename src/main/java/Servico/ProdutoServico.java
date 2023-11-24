/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servico;

import java.util.Scanner;

import modelo.Produto;
import modelo.Venda;
import repositorio.ProdutoRepositorio;

public class ProdutoServico {
	private ProdutoRepositorio repositorio;
	Scanner entrada;

	public ProdutoServico(Produto[] produtos) {
		this.repositorio = new ProdutoRepositorio(produtos);
		this.entrada = new Scanner(System.in);
	}


	public ProdutoServico(Venda[] produtos) {
		
	}


	public void desenvolvimentoProduto() {
		int opcProduto = 0;
		do {
			System.out.println("===========Menu de Opções de produto===========");
			System.out.println("1- Inserir Produto");
			System.out.println("2- Deletar Produto");
			System.out.println("3- Alterar Produto");
			System.out.println("Digite Zero para voltar ao menu anterior");
			opcProduto = entrada.nextInt();
			Produto produto = new Produto();
			switch (opcProduto) {
			case 0:
				break;
			case 1:
				produto = new Produto();
				System.out.print("Valor: ");
				produto.setValor(entrada.nextFloat());
				System.out.print("Descrição: ");
				produto.setDescricao(entrada.next());
				this.inserir(produto);
				System.out.println("Registro salvo com sucesso!");
				return;
			case 2:
				System.out.println("Informe o codigo do produto: ");
				int codigoDeletar = entrada.nextInt();
				produto = this.getProduto(codigoDeletar);
				if (produto != null) {
					this.deletar(produto);
				}
				System.out.println("Registro apagado com sucesso!");
				return;
			case 3:
				System.out.println("Informe o codigo do produto: ");
				int codigoAtualizar = entrada.nextInt();
				produto = new Produto();
				produto.setCodigo(codigoAtualizar);
				System.out.print("Novo valor do Produto: ");
				produto.setValor(entrada.nextFloat());
				System.out.print("Nova descrição do Produto: ");
				produto.setDescricao(entrada.next());
				produto = this.atualizar(produto);
				if (produto != null) {
					System.out.println("Registro atualizado com sucesso!");
				}
				break;
			default:
				System.out.println("Digite uma opção válida");
			}
		} while (opcProduto != 0);
	}

	

	public Produto inserir(Produto produto) {
		return repositorio.inserir(produto);
	}

	public Produto atualizar(Produto produto) {
		return repositorio.atualizar(produto);
	}

	public Produto getProduto(int codigo) {
		return repositorio.getByCodigo(codigo);
	}

	public Produto deletar(Produto produto) {
		return repositorio.deletar(produto);
	}

	public void listaProduto() {
		System.out.println("Deseja realmente imprimir o relatório (S\\N)");
		String resposta = entrada.next();
		if (resposta.equalsIgnoreCase("S")) {
			Produto[] produtos = repositorio.getProdutos();
			for (Produto produto : produtos) {
				System.out.println(produto.toString());
			}
		}
	}

}