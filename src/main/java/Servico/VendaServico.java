/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servico;

import java.time.LocalDate;
import java.util.Scanner;
import modelo.Cliente;
import modelo.Produto;
import modelo.Venda;
import modelo.Venda;
import repositorio.ProdutoRepositorio;
import repositorio.VendaRepositorio;

public class VendaServico {

	private VendaRepositorio repositorio;
	private ClienteServico clienteServico;
	private ProdutoServico produtoServico;
	Scanner entrada;

	public VendaServico(Venda vendas[], Cliente clientes[], Produto[] produtos) {
		this.repositorio = new VendaRepositorio(vendas);
		this.clienteServico = new ClienteServico(clientes);
		this.produtoServico = new ProdutoServico(produtos);
		this.entrada = new Scanner(System.in);

	}

	// desenvolvimento da venda, metodo para fazer registro de uma nova venda
	public void desenvolvimentoVenda() {
		Venda venda = new Venda();
		System.out.println("===========Registro de venda===========");
		System.out.println("Digite o código do cliente que efetuou a compra");
		int codigoCliente = entrada.nextInt();
		// recebe codigo do cliente e tentar buscar no array de clientes
		Cliente cliente = clienteServico.getCliente(codigoCliente);
		if (cliente == null) {// caso retorne null indica que cliente não foi cadastrado
			System.out.println("Cliente não encontrado");
			return;
		}
		System.out.println("Digite o código do produto comprado");
		int codigoProduto = entrada.nextInt();
		// recebe codigo do produto e tenta buscar o produto no array de produtos salvos
		Produto produto = produtoServico.getProduto(codigoProduto);
		if (produto == null) {// caso retorne null indica que produto não foi cadastrado
			System.out.println("Produto não encontrado");
			return;
		}
		venda.setCliente(cliente);// insere o cliente na venda
		venda.setProduto(produto);// insere o produto na venda
		venda.setDataVenda(LocalDate.now());// pega data ATUAL e insere na venda
		this.inserir(venda);// insere a venda
		System.out.println("Venda registrada com sucesso!");// sucesso de venda
	}

	// assim como demais metodos de inserir, valida a posição do array e insere na
	// primeira posição nula
	public Venda inserir(Venda venda) {
		return repositorio.inserir(venda);
	}

	public Venda getVenda(int codigo) {
		return repositorio.getByCodigo(codigo);
	}

	public void listarVendas() {
		System.out.println("Deseja realmente imprimir o relatório (S\\N)");
		String resposta = entrada.next();
		if (resposta.equalsIgnoreCase("S")) {
			Venda[] vendas = repositorio.getVendas();
			for (Venda venda : vendas) {
				System.out.println(venda.toString());
			}
		}

	}

	// metodo para gerar nota fiscal
	public void notaFiscal() {
		System.out.println("Digite o código do cliente: ");
		int codigoCliente = entrada.nextInt();
		// busca cliente pelo codigo
		Cliente cliente = clienteServico.getCliente(codigoCliente);
		if (cliente == null) {// caso cliente nulo indica que ele não existe na base de dados
			System.out.println("Cliente não encontrado!");
			return;
		}
		// verifica se TODOS os campos do cliente foi preenchido, caso não foi mostra
		// mensagem
		if ((cliente.getCidade() == null || cliente.getEndereço() == null || cliente.getNome() == null)) {
			System.out.println("TODOS os dados do Cliente devem ser preenchidos!");
			return;
		}
		// inicio da nota mostrando os dados do cliente
		System.out.println("NOTA FISCAL");
		System.out.println("=================");
		System.out.println("Nome: " + cliente.getNome());
		System.out.println("Endereço: " + cliente.getCidade());
		System.out.println("Cidade: " + cliente.getCidade());
		System.out.println("=================");
		Venda[] vendas = this.repositorio.getVendas();// busca todas vendas do cliente
		Float valorTotal = 0f;// variavel para somar os valores dos produtos
		System.out.println("=================");
		System.out.println(" ===Refeições===");
		System.out.println("=================");
		for (int i = 0; i < vendas.length; i++) {// vare vendas
			if (vendas[i] != null && vendas[i].getProduto() != null && vendas[i].getCliente() != null) {// verifica se
																										// na posicao o
																										// cliente e o
																										// produto foi
																										// preenchido
				if (vendas[i].getCliente().getCodigo() == cliente.getCodigo()) {// verifica se a venda é do cliente
																				// selecionado pelo usuario
					if (vendas[i].getProduto().getValor() != null && vendas[i].getProduto().getDescricao() != null) {// verifica
																														// se
																														// o
																														// produto
																														// comprado
																														// tem
																														// valor
																														// e
																														// nome
																														// (so
																														// mostra
																														// se
																														// isso
																														// for
																														// preenchido)
						System.out.println("Produto: " + vendas[i].getProduto().getDescricao() + " \t Valor: "
								+ vendas[i].getProduto().getValor());// printa dados do produto
						valorTotal += vendas[i].getProduto().getValor();// soma valor do produto no total
					}
				}
			}
		}
		System.out.println("=================");
		System.out.println("TOTAL: " + valorTotal);// mostra valor total dos produtos comprados
		System.out.println("=================");
	}

	/// 12
	public void maiorValorVenda() {
		boolean existeVendasCliente = false;
		System.out.println("Digite o código do cliente: ");
		int codigo = entrada.nextInt();
		Float valor = Float.MIN_VALUE;
		Venda[] vendas = this.repositorio.getVendas();// busca todas vendas do cliente
		for (int i = 0; i < vendas.length; i++) {
			if (vendas[i] != null) {
				if (vendas[i].getCliente().getCodigo() == codigo) {
					if (vendas[i].getProduto().getValor() > valor) {
						existeVendasCliente = true;
						valor = vendas[i].getProduto().getValor();
					}
				}
			}
		}
		if (existeVendasCliente) {
			System.out.println("Maior valor de venda " + valor);
		} else {
			System.out.println("Não existem vendas cadastradas para esse cliente");
		}
	}

/// 12
	public void menorValorVenda() {
		boolean existeVendasCliente = false;
		System.out.println("Digite o código do cliente: ");
		int codigo = entrada.nextInt();
		Float valor = Float.MAX_VALUE;
		Venda[] vendas = this.repositorio.getVendas();// busca todas vendas do cliente
		for (int i = 0; i < vendas.length; i++) {
			if (vendas[i] != null) {
				if (vendas[i].getCliente().getCodigo() == codigo) {
					if (vendas[i].getProduto().getValor() < valor) {
						existeVendasCliente = true;
						valor = vendas[i].getProduto().getValor();
					}
				}
			}
		}
		if (existeVendasCliente) {
			System.out.println("Menor valor de venda " + valor);
		} else {
			System.out.println("Não existem vendas cadastradas para esse cliente");
		}
	}

	/// 13
	public void totalVendaCliente() {
		boolean existeVendasCliente = false;
		System.out.println("Digite o código do cliente: ");
		int codigo = entrada.nextInt();
		Float valor = 0F;
		Venda[] vendas = this.repositorio.getVendas();// busca todas vendas do cliente
		for (int i = 0; i < vendas.length; i++) {
			if (vendas[i] != null) {
				if (vendas[i].getCliente().getCodigo() == codigo) {
					existeVendasCliente = true;
					valor += vendas[i].getProduto().getValor();
				}
			}
		}
		if (existeVendasCliente) {
			System.out.println("Total vendas " + valor);
		} else {
			System.out.println("Não existem vendas cadastradas para esse cliente");
		}

	}
}
