package br.edu.senai.model.bean;

public class Produto {
    private String descricao;
    private Float preco_venda;
    private Float preco_compra;
    private String tipo_produto; 

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getPreco_venda() {
        return preco_venda;
    }

    public void setPreco_venda(Float preco_venda) {
        this.preco_venda = preco_venda;
    }

    public Float getPreco_compra() {
        return preco_compra;
    }

    public void setPreco_compra(Float preco_compra) {
        this.preco_compra = preco_compra;
    }

    public String getTipo() {
        return tipo_produto;
    }

    public void setTipo(String tipo) {
        this.tipo_produto = tipo;
    }
    
}
