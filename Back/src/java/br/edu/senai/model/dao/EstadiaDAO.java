package br.edu.senai.model.dao;

import br.edu.senai.model.bean.Estadia;
import br.edu.senai.connection.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EstadiaDAO {

    ConnectionFactory connection;

    public EstadiaDAO() {
        connection = new ConnectionFactory();
    }

    public void insert(Estadia estadia) {
        PreparedStatement stmt = null;
        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("insert into estadia values (?,?,?)");
            stmt.setString(1,estadia.getData_entrada());
            stmt.setString(2,estadia.getData_saida());
            stmt.setString(3, estadia.getObersevacao());
            stmt.executeUpdate();
            connection.confirm();
        } catch (SQLException e) {
            Logger.getLogger(EstadiaDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            connection.closeConnection(stmt);
        }
    }

    public void update(Estadia estadia) {
        PreparedStatement stmt = null;
        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("update into estadia set date_entrada = ?"
                    + ", data_saida = ?, observacao = ? where cod_agenda = ? ");
            stmt.setString(1,estadia.getData_entrada());
            stmt.setString(2,estadia.getData_saida());
            stmt.setString(3,estadia.getObersevacao().toUpperCase());
            stmt.executeUpdate();
            connection.confirm();
        } catch (SQLException ex) {
            Logger.getLogger(EstadiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.closeConnection(stmt);
        }
    }

    public void delete(Estadia estadia) {
        PreparedStatement stmt = null;
        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("delete from estadia where data_entrada = ?");
            stmt.setString(1,estadia.getData_entrada());
            stmt.executeUpdate();
            connection.confirm();
        } catch (SQLException e) {
            Logger.getLogger(EstadiaDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            connection.closeConnection(stmt);
        }
    }
    public List<Estadia> read() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Estadia> estadias = new ArrayList<>();

        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("select *from estadia");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Estadia estadia = new Estadia();
                estadia.setData_entrada(rs.getString("data_entrada"));
                estadia.setData_saida(rs.getString("data_saida"));
                estadia.setObersevacao(rs.getString("observacao"));
                estadias.add(estadia);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EstadiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.closeConnection();
            try {
                rs.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(EstadiaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return estadias;
    }
}
