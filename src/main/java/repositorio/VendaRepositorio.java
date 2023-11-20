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
import modelo.Venda;

public class VendaRepositorio {
	private EntityManagerFactory emf;
	private EntityManager em;

	public VendaRepositorio() {
		emf = Persistence.createEntityManagerFactory("come-que-ta-bom");
		em = emf.createEntityManager();
	}

	public Venda inserir(Venda venda) {
		em.getTransaction().begin();
		em.persist(venda);
		em.getTransaction().commit();
		return venda;
	}

	public Venda getByCodigo(int codigo) {
		return em.find(Venda.class, codigo);
	}

	public Venda[] getVendas() {
		List<Venda> produtosList = em.createQuery("SELECT p FROM Produto p", Venda.class).getResultList();
		Venda[] produtosArray = produtosList.toArray(new Venda[produtosList.size()]);
		return produtosArray;

	}
}
