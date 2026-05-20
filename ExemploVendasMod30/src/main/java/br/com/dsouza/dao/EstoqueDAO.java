package br.com.dsouza.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.dsouza.dao.generic.GenericDAO;
import br.com.dsouza.domain.Estoque;

public class EstoqueDAO extends GenericDAO<Estoque, Long> implements IEstoqueDAO {

	public EstoqueDAO() {
		super();
	}

	@Override
	public Class<Estoque> getTipoClasse() {
		return Estoque.class;
	}

	@Override
	public void atualiarDados(Estoque entity, Estoque entityCadastrado) {
		entityCadastrado.setCodigoProduto(entity.getCodigoProduto());
		entityCadastrado.setQuantidade(entity.getQuantidade());
	}

	@Override
	protected String getQueryInsercao() {
		StringBuilder sb = new StringBuilder();

		sb.append("INSERT INTO TB_ESTOQUE ");
		sb.append("(ID, CODIGO_PRODUTO, QUANTIDADE) ");
		sb.append("VALUES (nextval('sq_estoque'), ?, ?)");

		return sb.toString();
	}

	@Override
	protected void setParametrosQueryInsercao(
			PreparedStatement stmInsert,
			Estoque entity) throws SQLException {

		stmInsert.setString(1, entity.getCodigoProduto());
		stmInsert.setInt(2, entity.getQuantidade());
	}

	@Override
	protected String getQueryExclusao() {
		return "DELETE FROM TB_ESTOQUE WHERE ID = ?";
	}

	@Override
	protected void setParametrosQueryExclusao(
			PreparedStatement stmExclusao,
			Long valor) throws SQLException {

		stmExclusao.setLong(1, valor);
	}

	@Override
	protected String getQueryAtualizacao() {
		StringBuilder sb = new StringBuilder();

		sb.append("UPDATE TB_ESTOQUE ");
		sb.append("SET CODIGO_PRODUTO = ?, ");
		sb.append("QUANTIDADE = ? ");
		sb.append("WHERE ID = ?");

		return sb.toString();
	}

	@Override
	protected void setParametrosQueryAtualizacao(
			PreparedStatement stmUpdate,
			Estoque entity) throws SQLException {

		stmUpdate.setString(1, entity.getCodigoProduto());
		stmUpdate.setInt(2, entity.getQuantidade());
		stmUpdate.setLong(3, entity.getId());
	}

	@Override
	protected void setParametrosQuerySelect(
			PreparedStatement stmSelect,
			Long valor) throws SQLException {

		stmSelect.setLong(1, valor);
	}
}