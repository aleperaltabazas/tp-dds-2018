package DDS.SGE.Persistencia;

import DDS.SGE.*;
import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Repositorios.RepositorioClientes;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCliente {
	Cliente cliente;
	EntityManager em;

	@Before
	public void initialize() {
		cliente = new Cliente("Un nombre", "Un apellido", Cliente.TipoDni.DNI, "1111", "4444", "Un domicilio", LocalDateTime.now(), new ArrayList<Dispositivo>());
		em = EntityManagerHelper.entityManager();
	}
	
	@After
	public void after() {
		EntityManagerHelper.rollback();
	}
	
	@Test
	public void testPersistirAUnClienteDesdeElRepositorioYDespuesTraerlo() {
		RepositorioClientes.persistir(cliente);
		Cliente persistido = em.find(Cliente.class, cliente.getId());
		
		assertEquals(persistido, cliente);
	}
	
	@Test
	public void testPersistirUnClienteYTraerloPorUsername() {
		Cliente otroCliente = new Cliente("Usuario", "pass");
		RepositorioClientes.persistir(otroCliente);
		Cliente persistido = RepositorioClientes.instancia.findByUsername(otroCliente.getUsername());
		
		assertEquals(persistido, otroCliente);
	}

}
