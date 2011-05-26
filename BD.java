/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markup;

import java.sql.*;

/**
 *
 * @author roberta.soares
 */
public class BD {
    public Connection connection = null ;
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String DBNAME = "diallink";
    private final String URL = "jdbc:mysql://192.168.1.12/" + DBNAME;
    private final String LOGIN = "diallink";
    private final String SENHA = "4321";
    
    public boolean getConnection(){
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, LOGIN, SENHA);
            System.out.println("Conectou");
            return true;
        } catch (ClassNotFoundException erro) {
            System.out.println("Driver não encontrado !" + erro.toString());
            return false;
        } catch (SQLException erro) {
            System.out.println("Falha ao Conectar !" + erro.toString());
            return false;
        }
    }
    
    public void close() {
        try {
            connection.close();
            System.out.println("Desconectou");
        } catch (SQLException erro) {}
    }
    
    
}