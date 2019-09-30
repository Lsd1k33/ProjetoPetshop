package br.edu.senai.model.dao;

import br.edu.senai.connection.ConnectionFactory;
import br.edu.senai.model.bean.Agenda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgendaDAO {

    ConnectionFactory connection;

    public AgendaDAO() {
        connection = new ConnectionFactory();
    }

    public void insert(Agenda agenda) {
        PreparedStatement stmt = null;

        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("insert into agenta VALUES (?,?,?,?)");
            stmt.setString(1, agenda.getHorario());
            stmt.setBoolean(2, agenda.isDisponivel());
            stmt.executeUpdate();
            connection.confirm();
        } catch (SQLException ex) {
            Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.closeConnection(stmt);
        }
    }

    public void update(Agenda agenda) {
        PreparedStatement stmt = null;
        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("update into agenda set horario = ?"
                    + ", diponivel = ? where cod_agenda = ? ");
            stmt.setString(1, agenda.getHorario().toUpperCase());
            stmt.setBoolean(2, agenda.isDisponivel());
            stmt.executeUpdate();
            connection.confirm();
        } catch (SQLException ex) {
            Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.closeConnection(stmt);
        }

    }

    public void delete(Agenda agenda) {
        PreparedStatement stmt = null;
        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("delete from agenda where horario = ?");
            stmt.setString(1, agenda.getHorario());
            stmt.executeUpdate();
            connection.confirm();
        } catch (SQLException e) {
            Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            connection.closeConnection(stmt);
        }
    }

    public List<Agenda> read() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Agenda> agendas = new ArrayList<>();

        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("select *from agenda");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Agenda agenda = new Agenda();
                agenda.setDisponivel(rs.getBoolean("disponivel"));
                agenda.setHorario(rs.getString("horario"));
                agendas.add(agenda);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.closeConnection();
            try {
                rs.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return agendas;
    }
}
