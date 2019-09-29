package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author gianlucampos
 */
public class Conexao {

    private static final String BANCO = "jdbc:postgresql://localhost:5432/dbwebservice";
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String USUARIO = "postgres";    
    private static final String SENHA = "postgres";
    private static Connection con = null;


    public static Connection getConexao() {
        if (con == null) {
            try {
                Class.forName(DRIVER);
                con = DriverManager.getConnection(BANCO, USUARIO, SENHA);
            } catch (ClassNotFoundException ex) {
                System.out.println("Não encontrou o driver" + ex.getMessage());
            } catch (SQLException ex) {
                System.out.println("Erro ao conectar: " + ex.getMessage());
            }
        }
        return con;
    }

    public static PreparedStatement getPreparedStatement(String sql) {
        if (con == null) {
            con = getConexao();
        }
        try {
            return con.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println("Erro de sql: " + e.getMessage());
        }
        return null;
    }

}
