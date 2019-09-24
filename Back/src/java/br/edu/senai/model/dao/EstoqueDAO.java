package br.edu.senai.model.dao;

import br.edu.senai.connection.ConnectionFactory;

import br.edu.senai.model.bean.Estoque;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EstoqueDAO {

    ConnectionFactory connection;

    public EstoqueDAO() {
        connection = new ConnectionFactory();
    }

    public void insert(Estoque estoque) {
        PreparedStatement stmt = null;
        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("insert into estoque VALUES (?,?)");
            stmt.setInt(1, estoque.getQuantidade());
            stmt.setDate(2, (Date) estoque.getData_saida());
            stmt.executeUpdate();
            connection.confirm();
        } catch (SQLException e) {
            Logger.getLogger((EstoqueDAO.class.getName())).log(Level.SEVERE, null, e);
        } finally {
            connection.closeConnection(stmt);
        }
    }

    public void update(Estoque estoque) {
        PreparedStatement stmt = null;
        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("update into estoque set data_saida = ? ");
            stmt.setDate(1, (Date) estoque.getData_saida());
            stmt.setInt(2, estoque.getQuantidade());
            stmt.executeUpdate();
            connection.confirm();
        } catch (Exception e) {
            Logger.getLogger(EstoqueDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            connection.closeConnection(stmt);
        }
    }

    public void delete(Estoque estoque) {
        PreparedStatement stmt = null;
        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("delete from where data_saida = ?");
            stmt.setDate(1, (Date) estoque.getData_saida());

        } catch (SQLException e) {
            Logger.getLogger(EstoqueDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            connection.closeConnection(stmt);
        }
    }

    public List<Estoque> read() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Estoque> estoques = new ArrayList<>();

        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("select *from banhoTosa");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Estoque estoque = new Estoque();
                estoque.setData_saida(rs.getDate("data_saida"));
                estoque.setQuantidade(rs.getInt("quantidade"));
                estoques.add(estoque);
            }
        } catch (SQLException e) {
            Logger.getLogger(EstoqueDAO.class.getName()).log(Level.SEVERE, null, e);

        } catch (Exception e) {
            Logger.getLogger(EstoqueDAO.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            connection.closeConnection();
            try {
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                Logger.getLogger(EstoqueDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return estoques;
    }
}
