package repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Cliente;


public class ClienteRepositorio {
	private EntityManagerFactory emf;
	private EntityManager em;

	public ClienteRepositorio() {
		emf = Persistence.createEntityManagerFactory("come-que-ta-bom");
		em = emf.createEntityManager();
	}

	public Cliente inserir(Cliente cliente) {
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
		return cliente;
	}

	public Cliente atualizar(Cliente cliente) {
		em.getTransaction().begin();
		em.merge(cliente);
		em.getTransaction().commit();
		return cliente;
	}

	public Cliente deletar(Cliente cliente) {
	    em.getTransaction().begin();
	    Cliente clienteEncontrado = em.find(Cliente.class, cliente.getCodigo());
	    if (clienteEncontrado != null) {
	        em.remove(clienteEncontrado);
	    }
	    em.getTransaction().commit();
	    return clienteEncontrado;
	}


	public Cliente getByCodigo(int codigo) {
		return em.find(Cliente.class, codigo);
	}

	public Cliente[] getClientes() {
		List<Cliente> clientesList = em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
		Cliente[] clientesArray = clientesList.toArray(new Cliente[clientesList.size()]);
		return clientesArray;
	}
}