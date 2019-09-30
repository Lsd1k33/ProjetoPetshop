package br.edu.senai.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {

    Connection connection;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String BANCO = "";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/" + BANCO;
    private static final String USER = "root";
    private static final String PASS = "";

    public Connection getConection() {
        return connection;
    }

    public boolean openConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASS);
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean confirm(){
        try{
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean cancel(){
        try{
            connection.rollback();
        } catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean closeConnection(PreparedStatement stmt) {
        closeConnection();

        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean closeConnection(PreparedStatement stmt, ResultSet rs) {
        closeConnection(stmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
