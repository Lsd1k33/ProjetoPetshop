
package br.edu.senai.model.dao;
import br.edu.senai.connection.ConnectionFactory;
import br.edu.senai.model.bean.Pessoa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PessoaDAO {
    ConnectionFactory connection ;
    public  PessoaDAO(){
        connection = new ConnectionFactory();
    }
     public void insert (Pessoa pessoa){
         PreparedStatement stmt = null;
                try {
             connection.getConection();
             stmt = connection.getConection().prepareStatement("insert into pessoa values(?,?,?,?)");
             stmt.setString(1, pessoa.getNome().toUpperCase());
             stmt.setString(2, pessoa.getEmail().toUpperCase());
             stmt.setString(3, pessoa.getTipo().toUpperCase());
             stmt.setInt(4, pessoa.getTelefone());
             stmt.executeUpdate();
             connection.confirm();
                }catch (SQLException e) {
            Logger.getLogger((PessoaDAO.class.getName())).log(Level.SEVERE,null,e);
        }finally{
            connection.closeConnection(stmt);
        }
     }   
        public void update(Pessoa pessoa){
         PreparedStatement stmt = null;
         try {
             connection.openConnection();
             stmt = connection.getConection().prepareStatement("update into pessoa set nome = ?, email = ?,"
                     + " tipo = ?, telefone = ? where cod_pessoa  ");
             stmt.setString(1, pessoa.getNome().toUpperCase());
             stmt.setString(2, pessoa.getEmail().toUpperCase());
             stmt.setString(3, pessoa.getTipo().toUpperCase());
             stmt.setInt(4, pessoa.getTelefone());
             stmt.executeUpdate();
             connection.confirm();
         } catch (SQLException e) {
             Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, e);
         }
          finally{
             connection.closeConnection(stmt);
         }   
     }   
     public void delete (Pessoa pessoa){
         PreparedStatement stmt = null;
         try {
             connection.openConnection();
             stmt = connection.getConection().prepareStatement("delete from pessoa where nome = ?");
             stmt.setString(1, pessoa.getNome().toUpperCase());
             
         } catch (SQLException e) {
             Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE,null, e);
         }
          finally{
             connection.closeConnection(stmt);
         }   
             
     }
      public List<Pessoa> read(){
        PreparedStatement stmt= null;
        ResultSet rs = null;
        List<Pessoa> pessoas = new ArrayList<>();
        
        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("select *from pessoa");
            rs = stmt.executeQuery();
            while(rs.next()){
               Pessoa pessoa = new Pessoa();
               pessoa.setNome(rs.getString("nome"));
               pessoa.setEmail(rs.getString("email"));
               pessoa.setTipo(rs.getString("tipo"));
               pessoa.setTelefone(rs.getInt("telefone"));
            pessoas.add(pessoa);
            }
        } catch (SQLException e) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, e);
            
        
        } catch (Exception e) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, e);
            
        }finally{
            connection.closeConnection();
            try{
                rs.close();
                stmt.close();
            }catch(SQLException e){
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        }
         return pessoas;   
    }

  

    
}
        
        
