
package br.edu.senai.model.bean;


public class Funcionario extends Pessoa{
    private int cpf;
    private String areaAtuacao;
    private String dataContratar;
    private boolean status;

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public String getDataContratar() {
        return dataContratar;
    }

    public void setDataContratar(String dataContratar) {
        this.dataContratar = dataContratar;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
