/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Produto;

public class ProdutoRepositorio {
	private EntityManagerFactory emf;
	private EntityManager em;

	public ProdutoRepositorio(Produto[] produtos) {
		emf = Persistence.createEntityManagerFactory("come-que-ta-bom");
		em = emf.createEntityManager();
	}

	public Produto inserir(Produto produto) {
		em.getTransaction().begin();
		em.persist(produto);
		em.getTransaction().commit();
		return produto;
	}

	public Produto atualizar(Produto produto) {
		em.getTransaction().begin();
		em.merge(produto);
		em.getTransaction().commit();
		return produto;
	}

	public void deletar(int indice) {
		em.getTransaction().begin();
		Produto produto = em.find(Produto.class, indice);
		if (produto != null) {
			em.remove(produto);
		}
		em.getTransaction().commit();
	}

	public Produto getByCodigo(int codigo) {
		return em.find(Produto.class, codigo);
	}

	public Produto[] getProdutos() {
		List<Produto> produtosList = em.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
		Produto[] produtosArray = produtosList.toArray(new Produto[produtosList.size()]);
		return produtosArray;
	}

}
