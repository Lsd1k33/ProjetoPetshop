
package br.edu.senai.model.bean;


public class Fornecedor extends Pessoa {
    private String nomEmpresa;
    private int cnpj;

    public String getNomEmpresa() {
        return nomEmpresa;
    }

    public void setNomEmpresa(String nomEmpresa) {
        this.nomEmpresa = nomEmpresa;
    }

    public int getCnpj() {
        return cnpj;
    }

    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }
    
}