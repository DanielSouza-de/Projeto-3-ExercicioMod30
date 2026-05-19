package br.com.rpires.dao;

import br.com.rpires.dao.generic.GenericDAO;
import br.com.rpires.domain.Estoque;

public class EstoqueDAO extends GenericDAO<Estoque, String> implements IEstoqueDAO {

    public EstoqueDAO() {
        super();
    }
}