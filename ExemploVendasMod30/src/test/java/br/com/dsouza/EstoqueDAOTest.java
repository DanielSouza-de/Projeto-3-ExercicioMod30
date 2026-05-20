/**
 * 
 */
package br.com.dsouza;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import br.com.dsouza.dao.EstoqueDAO;
import br.com.dsouza.dao.IEstoqueDAO;
import br.com.dsouza.domain.Estoque;
import br.com.dsouza.exceptions.DAOException;
import br.com.dsouza.exceptions.MaisDeUmRegistroException;
import br.com.dsouza.exceptions.TableException;
import br.com.dsouza.exceptions.TipoChaveNaoEncontradaException;

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
				estoqueDao.excluir(est.getId());
			} catch (DAOException e) {
				e.printStackTrace();
			}
		});
	}

	private Estoque criarEstoque(String codigoProduto)
			throws TipoChaveNaoEncontradaException, DAOException {

		Estoque estoque = new Estoque();

		estoque.setId(System.currentTimeMillis());
		estoque.setCodigoProduto(codigoProduto);
		estoque.setQuantidade(100);

		estoqueDao.cadastrar(estoque);

		return estoque;
	}

	private void excluir(Long valor) throws DAOException {
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
				this.estoqueDao.consultar(estoque.getId());

		Assert.assertNotNull(estoqueDB);

		Assert.assertEquals(
				Integer.valueOf(100),
				estoqueDB.getQuantidade());

		excluir(estoqueDB.getId());
	}

	@Test
	public void salvar()
			throws TipoChaveNaoEncontradaException, DAOException {

		Estoque estoque = criarEstoque("P2");

		Assert.assertNotNull(estoque);

		Assert.assertEquals(
				Integer.valueOf(100),
				estoque.getQuantidade());

		excluir(estoque.getId());
	}

	@Test
	public void excluir()
			throws DAOException,
			TipoChaveNaoEncontradaException,
			MaisDeUmRegistroException,
			TableException {

		Estoque estoque = criarEstoque("P3");

		Assert.assertNotNull(estoque);

		excluir(estoque.getId());

		Estoque estoqueBD =
				this.estoqueDao.consultar(estoque.getId());

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
				this.estoqueDao.consultar(estoque.getId());

		assertNotNull(estoqueBD);

		Assert.assertEquals(
				Integer.valueOf(200),
				estoqueBD.getQuantidade());

		excluir(estoque.getId());

		Estoque estoqueBD1 =
				this.estoqueDao.consultar(estoque.getId());

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

			excluir(est.getId());
		}

		list = estoqueDao.buscarTodos();

		assertTrue(list != null);
		assertTrue(list.size() == 0);
	}
}