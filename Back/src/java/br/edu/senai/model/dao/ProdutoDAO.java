package br.edu.senai.model.dao;

import br.edu.senai.connection.ConnectionFactory;
import br.edu.senai.model.bean.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutoDAO {

    ConnectionFactory connection;

    public ProdutoDAO() {
        connection = new ConnectionFactory();
    }

    public void insert(Produto produto) {
        PreparedStatement stmt = null;
        try {
            connection.getConection();
            stmt = connection.getConection().prepareStatement("insert into produto values(?,?,?,?)");
            stmt.setString(1, produto.getDescricao());
            stmt.setString(2, produto.getTipo());
            stmt.setFloat(3, produto.getPreco_compra());
            stmt.setFloat(4, produto.getPreco_venda());
        } catch (SQLException e) {

            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            connection.closeConnection(stmt);
        }
    }

    public void update(Produto produto) {
        PreparedStatement stmt = null;
        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("update into produto set descricao = ?"
                    + ", tipo = ?, preco_compra = ?, preco_venda = ? where cod_produto = ? ");
            stmt.setString(1, produto.getDescricao().toUpperCase());
            stmt.setString(2, produto.getTipo().toUpperCase());
            stmt.setFloat(3, produto.getPreco_compra());
            stmt.setFloat(4, produto.getPreco_venda());
            stmt.executeUpdate();
            connection.confirm();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.closeConnection(stmt);
        }
    }
     public void delete(Produto produto) {
        PreparedStatement stmt = null;
        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("delete from produto where descricao = ?");
            stmt.setString(1, produto.getDescricao().toUpperCase());
            stmt.executeUpdate();
            connection.confirm();
        } catch (SQLException e) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            connection.closeConnection(stmt);
        }
    }

    public List<Produto> read() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();

        try {
            connection.openConnection();
            stmt = connection.getConection().prepareStatement("select *from produto");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco_compra(rs.getFloat("preco_compra"));
                produto.setPreco_venda(rs.getFloat("preco_venda"));
                produto.setTipo(rs.getString("tipo"));
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.closeConnection();
            try {
                rs.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return produtos;
    }
}
