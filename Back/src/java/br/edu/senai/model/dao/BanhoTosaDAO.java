package br.edu.senai.model.dao;
import br.edu.senai.connection.ConnectionFactory;
import br.edu.senai.model.bean.BanhoTosa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class BanhoTosaDAO {
    ConnectionFactory  connection;
    public BanhoTosaDAO(){
        connection = new ConnectionFactory();
    }
    public void insert (BanhoTosa banhoTosa){
        PreparedStatement stmt= null;
        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("insert into banhoTosa VALUES (?,?)");
            stmt.setString(1, banhoTosa.getProcedimento().toUpperCase());
            stmt.setString(2,banhoTosa.getObservacao().toUpperCase());
            stmt.executeUpdate();
            connection.confirm();
        } catch (SQLException e) {
            Logger.getLogger((BanhoTosaDAO.class.getName())).log(Level.SEVERE,null,e);
        }finally{
            connection.closeConnection(stmt);
        }
    }
     public void update(BanhoTosa banhoTosa){
         PreparedStatement stmt = null;
         try {
             connection.openConnection();
             stmt = connection.getConection().prepareStatement("update into banhoTosa set procedimento = ? ");
             stmt.setString(1, banhoTosa.getProcedimento().toUpperCase());
             stmt.setString(2, banhoTosa.getObservacao().toUpperCase());
             stmt.executeUpdate();
             connection.confirm();
         } catch (SQLException e) {
             Logger.getLogger(BanhoTosaDAO.class.getName()).log(Level.SEVERE, null, e);
         }
          finally{
             connection.closeConnection(stmt);
         }   
     }   
     public void delete (BanhoTosa banhoTosa){
         PreparedStatement stmt = null;
         try {
             connection.openConnection();
             stmt = connection.getConection().prepareStatement("delete from banhoTosa where procedimento = ?");
             stmt.setString(1, banhoTosa.getProcedimento().toUpperCase());
             
         } catch (SQLException e) {
             Logger.getLogger(BanhoTosaDAO.class.getName()).log(Level.SEVERE,null, e);
         }
          finally{
             connection.closeConnection(stmt);
         }   
             
     }
    public List<BanhoTosa> read(){
        PreparedStatement stmt= null;
        ResultSet rs = null;
        List<BanhoTosa> banhoTosas = new ArrayList<>();
        
        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("select *from banhoTosa");
            rs = stmt.executeQuery();
            while(rs.next()){
               BanhoTosa banhoTosa = new BanhoTosa();
               banhoTosa.setProcedimento(rs.getString("procedimento"));
               banhoTosa.setObservacao(rs.getString("observacao"));
            banhoTosas.add(banhoTosa);
            }
        } catch (SQLException e) {
            Logger.getLogger(BanhoTosaDAO.class.getName()).log(Level.SEVERE, null, e);
            
        
        } catch (Exception e) {
            Logger.getLogger(BanhoTosaDAO.class.getName()).log(Level.SEVERE, null, e);
            
        }finally{
            connection.closeConnection();
            try{
                rs.close();
                stmt.close();
            }catch(SQLException e){
            Logger.getLogger(BanhoTosaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        }
         return banhoTosas;   
    }
     
}
