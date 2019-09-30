
package br.edu.senai.model.dao;
import br.edu.senai.model.bean.Cliente;
import br.edu.senai.connection.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO {
    ConnectionFactory connection;
    public ClienteDAO(){
        connection = new ConnectionFactory();
    }
    public void insert(Cliente cliente){
        PreparedStatement stmt = null;
        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("insert into cliente values (?,?)");
            stmt.setString(1, cliente.getPessoaSec());
            stmt.setInt(2, cliente.getCpf());
        } catch (Exception e) {
        Logger.getLogger(EstadiaDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            connection.closeConnection(stmt);
        }
    }
    
    public void update(Cliente cliente) {
        PreparedStatement stmt = null;
        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("update into cliente set cpf = ?"
                    + ", pessoaSec = ? where cod_cliente = ? ");
            stmt.setInt(1,cliente.getCpf());
            stmt.setString(2, cliente.getPessoaSec());
            stmt.executeUpdate();
            connection.confirm();
        } catch (SQLException ex) {
            Logger.getLogger(EstadiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.closeConnection(stmt);
        }
    }

    public void delete(Cliente cliente) {
        PreparedStatement stmt = null;
        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("delete from cliente where cpf = ?");
            stmt.setInt(1,cliente.getCpf());
            stmt.executeUpdate();
            connection.confirm();
        } catch (SQLException e) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            connection.closeConnection(stmt);
        }
    }
    public List<Cliente> read() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("select *from cliente");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCpf(rs.getInt("cpf"));
                cliente.setPessoaSec(rs.getString("pessoaSec"));
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.closeConnection();
            try {
                rs.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return clientes;
    }
}
    

