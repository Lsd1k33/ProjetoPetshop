/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.senai.model.bean;


public class Cliente extends Pessoa{
    
    private int cpf;
    private String pessoaSec;

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getPessoaSec() {
        return pessoaSec;
    }

    public void setPessoaSec(String pessoaSec) {
        this.pessoaSec = pessoaSec;
    }

    
}
