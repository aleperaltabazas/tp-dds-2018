package DDS.SGE.Persistencia;

import DDS.SGE.*;
import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Repositorios.RepositorioAdministradores;
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
		cliente = new Cliente("Un nombre", "Un apellido", Cliente.TipoDni.DNI, "1111", "4444", "Un domicilio",
				LocalDateTime.now(), new ArrayList<Dispositivo>());
		em = EntityManagerHelper.entityManager();
	}

	@After
	public void after() {
		EntityManagerHelper.rollback();
	}

	@Test
	public void testPersistirAUnClienteDesdeElRepositorioYDespuesTraerlo() {
		RepositorioClientes.agregarCliente(cliente);
		Cliente persistido = em.find(Cliente.class, cliente.getId());

		assertEquals(persistido, cliente);
	}

	@Test
	public void testPersistirUnClienteYTraerloPorUsername() {
		Cliente otroCliente = new Cliente("Usuario", "pass");
		RepositorioClientes.agregarCliente(otroCliente);

		Cliente persistido = RepositorioClientes.findByUsername(otroCliente.getUsername()).get();
		assertEquals(persistido, otroCliente);

	}

	@Test
	public void testPersistirUnAdministrador() {
		Administrador administrador = new Administrador("Admin", "admin");
		RepositorioAdministradores.agregarAdministrador(administrador);
		Administrador persistido = RepositorioAdministradores.findByID(administrador.getId());

		assertEquals(administrador, persistido);
	}

}
