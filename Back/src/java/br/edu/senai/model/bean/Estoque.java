package br.edu.senai.model.bean;

import java.util.Date;

public class Estoque {
private Date data_saida;
private int quantidade;

    public Date getData_saida() {
        return data_saida;
    }

    public void setData_saida(Date data_saida) {
        this.data_saida = data_saida;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
