package br.com.rpires.domain;

import anotacao.ColunaTabela;
import anotacao.Tabela;
import anotacao.TipoChave;

@Tabela("TB_ESTOQUE")
public class Estoque implements Persistente {

    @ColunaTabela(dbName = "id", setJavaName = "setId")
    private Long id;

    @TipoChave("getCodigoProduto")
    @ColunaTabela(dbName = "codigo_produto", setJavaName = "setCodigoProduto")
    private String codigoProduto;

    @ColunaTabela(dbName = "quantidade", setJavaName = "setQuantidade")
    private Integer quantidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}