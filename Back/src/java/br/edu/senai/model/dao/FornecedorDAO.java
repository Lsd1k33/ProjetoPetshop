
package br.edu.senai.model.dao;

import br.edu.senai.connection.ConnectionFactory;
import br.edu.senai.model.bean.Fornecedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FornecedorDAO {
    
    ConnectionFactory connection;

    public FornecedorDAO() {
        connection = new ConnectionFactory();
    }

    public void insert(Fornecedor fornecedor) {
        PreparedStatement stmt = null;
        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("insert into fornecedor values (?,?)");
            stmt.setInt(1,fornecedor.getCnpj());
            stmt.setString(2,fornecedor.getNomEmpresa());
            stmt.executeUpdate();
            connection.confirm();
        } catch (SQLException e) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            connection.closeConnection(stmt);
        }
    }

    public void update(Fornecedor fornecedor) {
        PreparedStatement stmt = null;
        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("update into fornecedor set nomeEmpresa = ?"
                    + ", Cnpj = ? where cod_fornecedor = ? ");
            stmt.setString(1,fornecedor.getNomEmpresa());
            stmt.setInt(2,fornecedor.getCnpj());
            stmt.executeUpdate();
            connection.confirm();
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.closeConnection(stmt);
        }
    }

    public void delete(Fornecedor fornecedor) {
        PreparedStatement stmt = null;
        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("delete from fornecedor where cnpj = ?");
            stmt.setInt(1,fornecedor.getCnpj());
            stmt.executeUpdate();
            connection.confirm();
        } catch (SQLException e) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            connection.closeConnection(stmt);
        }
    }
    public List<Fornecedor> read() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Fornecedor> fornecedores = new ArrayList<>();

        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("select *from fornecedor");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setCnpj(rs.getInt("cnpj"));
                fornecedor.setNomEmpresa(rs.getString("nomeEmpresa"));
                fornecedores.add(fornecedor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.closeConnection();
            try {
                rs.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fornecedores;
    }
}
