/**
 * 
 */
package br.com.dsouza.services;

import br.com.dsouza.dao.IProdutoDAO;
import br.com.dsouza.domain.Produto;
import br.com.dsouza.services.generic.GenericService;

/**
 * @author rodrigo.pires
 *
 */
public class ProdutoService extends GenericService<Produto, String> implements IProdutoService {

	public ProdutoService(IProdutoDAO dao) {
		super(dao);
	}

}
