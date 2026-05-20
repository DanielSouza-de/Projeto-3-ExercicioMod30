/**
 * 
 */
package br.com.dsouza.services;

import br.com.dsouza.domain.Cliente;
import br.com.dsouza.exceptions.DAOException;
import br.com.dsouza.exceptions.TipoChaveNaoEncontradaException;
import br.com.dsouza.services.generic.IGenericService;

/**
 * @author rodrigo.pires
 *
 */
public interface IClienteService extends IGenericService<Cliente, Long> {

//	Boolean cadastrar(Cliente cliente) throws TipoChaveNaoEncontradaException;
//
	Cliente buscarPorCPF(Long cpf) throws DAOException;
//
//	void excluir(Long cpf);
//
//	void alterar(Cliente cliente) throws TipoChaveNaoEncontradaException;

}
