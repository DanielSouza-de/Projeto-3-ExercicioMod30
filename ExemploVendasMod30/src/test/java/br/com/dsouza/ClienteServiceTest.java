/**
 * 
 */
package br.com.dsouza;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.dsouza.dao.ClienteDaoMock;
import br.com.dsouza.dao.IClienteDAO;
import br.com.dsouza.domain.Cliente;
import br.com.dsouza.exceptions.DAOException;
import br.com.dsouza.exceptions.TipoChaveNaoEncontradaException;
import br.com.dsouza.services.ClienteService;
import br.com.dsouza.services.IClienteService;

/**
 * @author rodrigo.pires
 *
 */
public class ClienteServiceTest {
	
	private IClienteService clienteService;
	
	private Cliente cliente;
	
	public ClienteServiceTest() {
		IClienteDAO dao = new ClienteDaoMock();
		clienteService = new ClienteService(dao);
	}
	
	@Before
	public void init() {

		cliente = new Cliente();

		cliente.setCpf(12312312312L);
		cliente.setNome("Rodrigo");
		cliente.setCidade("São Paulo");
		cliente.setEnd("End");
		cliente.setEstado("SP");
		cliente.setNumero(10);
		cliente.setTel(1199999999L);

		cliente.setEmail("rodrigo@email.com");
	}
	
	@Test
	public void pesquisarCliente() throws DAOException {

		Cliente clienteConsultado =
				clienteService.buscarPorCPF(cliente.getCpf());

		Assert.assertNotNull(clienteConsultado);

		Assert.assertEquals(
				"rodrigo@email.com",
				clienteConsultado.getEmail());
	}
	
	@Test
	public void salvarCliente()
			throws TipoChaveNaoEncontradaException, DAOException {

		Boolean retorno = clienteService.cadastrar(cliente);
		
		Assert.assertTrue(retorno);

		Assert.assertEquals(
				"rodrigo@email.com",
				cliente.getEmail());
	}
	
	@Test
	public void excluirCliente() throws DAOException {

		clienteService.excluir(cliente.getCpf());
	}
	
	@Test
	public void alterarCliente()
			throws TipoChaveNaoEncontradaException, DAOException {

		cliente.setNome("Rodrigo Pires");

		cliente.setEmail("pires@email.com");

		clienteService.alterar(cliente);
		
		Assert.assertEquals(
				"Rodrigo Pires",
				cliente.getNome());

		Assert.assertEquals(
				"pires@email.com",
				cliente.getEmail());
	}
}