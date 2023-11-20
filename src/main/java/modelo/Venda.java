/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Venda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;

	private LocalDate dataVenda;

	public Venda() {

	}

	public Venda(int codigo, Cliente cliente, Produto produto, LocalDate dataVenda) {
		super();
		this.codigo = codigo;
		this.cliente = cliente;
		this.produto = produto;
		this.dataVenda = dataVenda;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}

	@Override
	public String toString() {
		String dataFormatada = dataVenda.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		return "Venda - Codigo: " + codigo + " cliente: " + cliente.getCodigo() + " Produto: " + produto.getCodigo()
				+ " Data: " + dataFormatada;
	}
}
