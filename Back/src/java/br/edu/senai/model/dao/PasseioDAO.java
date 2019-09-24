
package br.edu.senai.model.dao;

import br.edu.senai.connection.ConnectionFactory;
import br.edu.senai.model.bean.Passeio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class PasseioDAO {
    
    ConnectionFactory connection;

    public PasseioDAO() {
        connection = new ConnectionFactory();
    }

    public void insert(Passeio passeio ){
        PreparedStatement stmt = null;
        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("insert into passeio VALUES (?,?,?)");
            stmt.setString(1, passeio.getObservacao());
            stmt.setString(2, passeio.getHoraio().toUpperCase());
            stmt.setInt(3, passeio.getQuantidadeAnimal());
            stmt.executeUpdate();
            connection.confirm();
        } catch (SQLException e) {
            Logger.getLogger((PasseioDAO.class.getName())).log(Level.SEVERE, null, e);
        } finally {
            connection.closeConnection(stmt);
        }
    }
    public void update(Passeio passeio){
         PreparedStatement stmt = null;
         try {
             connection.openConnection();
             stmt = connection.getConection().prepareStatement("update into passeio set horairo = ? ");
             stmt.setString(1,passeio.getHoraio().toUpperCase());
             stmt.setString(2, passeio.getObservacao().toUpperCase());
             stmt.setInt(3 ,passeio.getQuantidadeAnimal());
             stmt.executeUpdate();
             connection.confirm();
         } catch (SQLException e) {
             Logger.getLogger(PasseioDAO.class.getName()).log(Level.SEVERE, null, e);
         }
          finally{
             connection.closeConnection(stmt);
         }   
     }
    public void delete (Passeio passeio){
         PreparedStatement stmt = null;
         try {
             connection.openConnection();
             stmt = connection.getConection().prepareStatement("delete from where horario = ?");
             stmt.setString(1, passeio.getHoraio().toUpperCase());
             
         } catch (SQLException e) {
             Logger.getLogger(PasseioDAO.class.getName()).log(Level.SEVERE,null, e);
         }
          finally{
             connection.closeConnection(stmt);
         }   
             
     }
    public List<Passeio> read(){
        PreparedStatement stmt= null;
        ResultSet rs = null;
        List<Passeio> passeios = new ArrayList<>();
        
        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("select *from banhoTosa");
            rs = stmt.executeQuery();
            while(rs.next()){
               Passeio passeio = new Passeio();
               passeio.setHoraio(rs.getString("horario"));
               passeio.setObservacao(rs.getString("observacao"));
               passeio.setQuantidadeAnimal(rs.getInt("quantidadeAnimal"));
            passeios.add(passeio);
            }
        } catch (SQLException e) {
            Logger.getLogger(PasseioDAO.class.getName()).log(Level.SEVERE, null, e);
            
        
        } catch (Exception e) {
            Logger.getLogger(PasseioDAO.class.getName()).log(Level.SEVERE, null, e);
            
        }finally{
            connection.closeConnection();
            try{
                rs.close();
                stmt.close();
            }catch(SQLException e){
            Logger.getLogger(PasseioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        }
         return passeios;   
    }
}
