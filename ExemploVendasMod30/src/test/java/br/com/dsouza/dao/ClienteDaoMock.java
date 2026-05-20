package br.com.dsouza.dao;

import java.util.Collection;

import br.com.dsouza.dao.IClienteDAO;
import br.com.dsouza.domain.Cliente;
import br.com.dsouza.exceptions.TipoChaveNaoEncontradaException;

public class ClienteDaoMock implements IClienteDAO {

	@Override
	public Boolean cadastrar(Cliente entity) throws TipoChaveNaoEncontradaException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void excluir(Long valor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterar(Cliente entity) throws TipoChaveNaoEncontradaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cliente consultar(Long valor) {

		Cliente cliente = new Cliente();

		cliente.setCpf(valor);
		cliente.setNome("Rodrigo");
		cliente.setCidade("São Paulo");
		cliente.setEnd("End");
		cliente.setEstado("SP");
		cliente.setNumero(10);
		cliente.setTel(1199999999L);

		// NOVO CAMPO
		cliente.setEmail("rodrigo@email.com");

		return cliente;
	}

	@Override
	public Collection<Cliente> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}


}
