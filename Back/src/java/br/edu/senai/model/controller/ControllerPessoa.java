
package br.edu.senai.model.controller;

import br.edu.senai.model.bean.Pessoa;
import br.edu.senai.model.dao.PessoaDAO;
import java.util.List;

public class ControllerPessoa {
   
    public boolean addContado(Pessoa obj) {
        //ListContato.add(obj);
        PessoaDAO pdao = new PessoaDAO();
        if (pdao.insert(obj)) {
            return false;
        } else {
            return true;
        }
    }
    public List<Pessoa> getPessoa(){
        PessoaDAO pdao = new PessoaDAO();
        return pdao.read();
    }
    public boolean update (int cod_pessoa, Pessoa obj){
        boolean achou = false;
        PessoaDAO pdao = new PessoaDAO();
        Pessoa p = pdao.read(cod_pessoa);
                
        achou = true;
    }
}
