/**
 * 
 */
package br.com.rpires;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.rpires.dao.IProdutoDAO;
import br.com.rpires.dao.ProdutoDaoMock;
import br.com.rpires.domain.Produto;
import br.com.rpires.exceptions.DAOException;
import br.com.rpires.exceptions.TipoChaveNaoEncontradaException;
import br.com.rpires.services.IProdutoService;
import br.com.rpires.services.ProdutoService;

/**
 * @author rodrigo.pires
 *
 */
public class ProdutoServiceTest {

	private IProdutoService produtoService;
	
	private Produto produto;
	
	public ProdutoServiceTest() {
		IProdutoDAO dao = new ProdutoDaoMock();
		produtoService = new ProdutoService(dao);
	}
	
	@Before
	public void init() {

		produto = new Produto();

		produto.setCodigo("A1");
		produto.setDescricao("Produto 1");
		produto.setNome("Produto 1");
		produto.setValor(BigDecimal.TEN);

		// NOVO CAMPO
		produto.setQuantidade(10);
	}
	
	@Test
	public void pesquisar() throws DAOException {

		Produto produtor =
				this.produtoService.consultar(produto.getCodigo());

		Assert.assertNotNull(produtor);

		// TESTA NOVO CAMPO
		Assert.assertEquals(
				Integer.valueOf(10),
				produtor.getQuantidade());
	}
	
	@Test
	public void salvar()
			throws TipoChaveNaoEncontradaException, DAOException {

		Boolean retorno = produtoService.cadastrar(produto);

		Assert.assertTrue(retorno);

		// TESTA NOVO CAMPO
		Assert.assertEquals(
				Integer.valueOf(10),
				produto.getQuantidade());
	}
	
	@Test
	public void excluir() throws DAOException {

		produtoService.excluir(produto.getCodigo());
	}
	
	@Test
	public void alterarCliente()
			throws TipoChaveNaoEncontradaException, DAOException {

		produto.setNome("Rodrigo Pires");

		// ALTERA NOVO CAMPO
		produto.setQuantidade(20);

		produtoService.alterar(produto);
		
		Assert.assertEquals(
				"Rodrigo Pires",
				produto.getNome());

		// TESTA ALTERAÇÃO
		Assert.assertEquals(
				Integer.valueOf(20),
				produto.getQuantidade());
	}
}