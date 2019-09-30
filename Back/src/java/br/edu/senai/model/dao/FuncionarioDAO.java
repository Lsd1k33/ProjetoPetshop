package br.edu.senai.model.dao;

import br.edu.senai.connection.ConnectionFactory;
import br.edu.senai.model.bean.Funcionario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FuncionarioDAO {

    ConnectionFactory connection;

    public FuncionarioDAO() {
        connection = new ConnectionFactory();
    }

    public void insert(Funcionario funcionario) {
        PreparedStatement stmt = null;
        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("insert into funcionario values (?,?,?,?)");
            stmt.setInt(1, funcionario.getCpf());
            stmt.setString(2, funcionario.getDataContratar());
            stmt.setString(3, funcionario.getAreaAtuacao());
            stmt.setBoolean(4, funcionario.isStatus());

            stmt.executeUpdate();
            connection.confirm();
        } catch (SQLException e) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            connection.closeConnection(stmt);
        }
    }

    public void update(Funcionario funcionario) {
        PreparedStatement stmt = null;
        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("update into funcionario set cpf = ?"
                    + ", dataContratar = ?, areaAtuacao = ?, status = ? where cod_funcionario = ? ");
            stmt.setInt(1, funcionario.getCpf());
            stmt.setString(2, funcionario.getDataContratar());
            stmt.setString(3, funcionario.getAreaAtuacao());
            stmt.setBoolean(4, funcionario.isStatus());

            stmt.executeUpdate();
            connection.confirm();
        } catch (SQLException ex) {
            Logger.getLogger(EstadiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.closeConnection(stmt);
        }
    }

    public void delete(Funcionario funcionario) {
        PreparedStatement stmt = null;
        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("delete from funcionario where cpf = ?");
            stmt.setInt(1, funcionario.getCpf());
            stmt.executeUpdate();
            connection.confirm();
        } catch (SQLException e) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            connection.closeConnection(stmt);
        }
    }

    public List<Funcionario> read() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("select *from estadia");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setDataContratar(rs.getString("dataContratar"));
                funcionario.setCpf(rs.getInt("cpf"));
                funcionario.setStatus(rs.getBoolean("status"));
                funcionario.setAreaAtuacao(rs.getString("areaAtuacao"));

                funcionarios.add(funcionario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.closeConnection();
            try {
                rs.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return funcionarios;
    }
}
