/**
 * 
 */
package br.com.dsouza.dao;

import br.com.dsouza.dao.generic.IGenericDAO;
import br.com.dsouza.domain.Venda;
import br.com.dsouza.exceptions.DAOException;
import br.com.dsouza.exceptions.TipoChaveNaoEncontradaException;

/**
 * @author rodrigo.pires
 *
 */
public interface IVendaDAO extends IGenericDAO<Venda, String> {

	public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;
	
	public void cancelarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;
}
