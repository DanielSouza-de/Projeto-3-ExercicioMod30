/**
 * 
 */
package br.com.rpires;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import br.com.rpires.dao.EstoqueDAO;
import br.com.rpires.dao.IEstoqueDAO;
import br.com.rpires.domain.Estoque;
import br.com.rpires.exceptions.DAOException;
import br.com.rpires.exceptions.MaisDeUmRegistroException;
import br.com.rpires.exceptions.TableException;
import br.com.rpires.exceptions.TipoChaveNaoEncontradaException;

/**
 * @author Daniel
 *
 */
public class EstoqueDAOTest {
	
	private IEstoqueDAO estoqueDao;

	public EstoqueDAOTest() {
		estoqueDao = new EstoqueDAO();
	}
	
	@After
	public void end() throws DAOException {

		Collection<Estoque> list = estoqueDao.buscarTodos();

		list.forEach(est -> {
			try {
				estoqueDao.excluir(est.getCodigoProduto());
			} catch (DAOException e) {
				e.printStackTrace();
			}
		});
	}

	private Estoque criarEstoque(String codigoProduto)
			throws TipoChaveNaoEncontradaException, DAOException {

		Estoque estoque = new Estoque();

		estoque.setId(1L);
		estoque.setCodigoProduto(codigoProduto);
		estoque.setQuantidade(100);

		estoqueDao.cadastrar(estoque);

		return estoque;
	}
	
	private void excluir(String valor) throws DAOException {
		this.estoqueDao.excluir(valor);
	}
	
	@Test
	public void pesquisar()
			throws MaisDeUmRegistroException,
			TableException,
			DAOException,
			TipoChaveNaoEncontradaException {

		Estoque estoque = criarEstoque("P1");

		Assert.assertNotNull(estoque);

		Estoque estoqueDB =
				this.estoqueDao.consultar(estoque.getCodigoProduto());

		Assert.assertNotNull(estoqueDB);

		Assert.assertEquals(
				Integer.valueOf(100),
				estoqueDB.getQuantidade());

		excluir(estoqueDB.getCodigoProduto());
	}
	
	@Test
	public void salvar()
			throws TipoChaveNaoEncontradaException, DAOException {

		Estoque estoque = criarEstoque("P2");

		Assert.assertNotNull(estoque);

		Assert.assertEquals(
				Integer.valueOf(100),
				estoque.getQuantidade());

		excluir(estoque.getCodigoProduto());
	}
	
	@Test
	public void excluir()
			throws DAOException,
			TipoChaveNaoEncontradaException,
			MaisDeUmRegistroException,
			TableException {

		Estoque estoque = criarEstoque("P3");

		Assert.assertNotNull(estoque);

		excluir(estoque.getCodigoProduto());

		Estoque estoqueBD =
				this.estoqueDao.consultar(estoque.getCodigoProduto());

		assertNull(estoqueBD);
	}
	
	@Test
	public void alterarEstoque()
			throws TipoChaveNaoEncontradaException,
			DAOException,
			MaisDeUmRegistroException,
			TableException {

		Estoque estoque = criarEstoque("P4");

		estoque.setQuantidade(200);

		estoqueDao.alterar(estoque);

		Estoque estoqueBD =
				this.estoqueDao.consultar(estoque.getCodigoProduto());

		assertNotNull(estoqueBD);

		Assert.assertEquals(
				Integer.valueOf(200),
				estoqueBD.getQuantidade());
		
		excluir(estoque.getCodigoProduto());

		Estoque estoqueBD1 =
				this.estoqueDao.consultar(estoque.getCodigoProduto());

		assertNull(estoqueBD1);
	}
	
	@Test
	public void buscarTodos()
			throws DAOException,
			TipoChaveNaoEncontradaException {

		criarEstoque("P5");
		criarEstoque("P6");

		Collection<Estoque> list = estoqueDao.buscarTodos();

		assertTrue(list != null);
		assertTrue(list.size() == 2);

		for (Estoque est : list) {

			Assert.assertNotNull(est.getQuantidade());

			excluir(est.getCodigoProduto());
		}
		
		list = estoqueDao.buscarTodos();

		assertTrue(list != null);
		assertTrue(list.size() == 0);
	}
}