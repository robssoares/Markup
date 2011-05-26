/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markup;
import java.sql.*;

/**
 *
 * @author robssoares
 */
public class ClienteDAO {
    
    public Cliente cliente;
    public BD bd;
    
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String men, sql;
   
    public static final byte INCLUSAO = 1;
    public static final byte ALTERACAO = 2;
    public static final byte EXCLUSAO = 3;

    public ClienteDAO() {
        bd = new BD();
        cliente = new Cliente();
    }
    
   public boolean localizar(){
        sql = "select idmarkup_clientes, cpf, nome, telefone, " +
        	  "nrcartao, idmarkup_empresas  " +
        	  "from markup_clientes where idmarkup_clientes = ?";
        try {
            statement = bd.connection.prepareStatement(sql);
            statement.setInt(1, cliente.getIdmarkup_clientes());
            resultSet = statement.executeQuery();
            resultSet.first();
                    
            
            
            cliente.setIdmarkup_clientes(resultSet.getInt(1));
            cliente.setCpf(resultSet.getString(2));
            cliente.setNome(resultSet.getString(3));
            
            //cliente.setDtNascto(resultSet.getDate(4));
            
            cliente.setTelefone(resultSet.getString(4));
            cliente.setNrCartao(resultSet.getString(5));
            //cliente.setDtDesbloqueioUra(resultSet.getDate(7));
            cliente.setIdmarkup_empresas(resultSet.getInt(6));
            
            return true;
        } catch (SQLException erro) {
            return false;
        }
    }
    
    
   
 public String atualizar(int operacao){
        men = "Operacao realizada dom sucesso !";
        try {
            if (operacao ==INCLUSAO){
                //sem dataNascto, dtdesbloqueioura
                sql = "insert into markup_clientes(cpf, nome, dtNascto, telefone, nrCartao, idmarkup_empresas) " + 
                      "values(?,?,?,?,?,?)"  ;
                statement = bd.connection.prepareStatement(sql);
                statement.setString(1, cliente.getCpf());
                statement.setString(2, cliente.getNome());
                
                //statement.setDate(3, cliente.getDtNascto());
                 java.sql.Date dataSql = new java.sql.Date(cliente.getDtNascto().getTime()); 
                
                statement.setDate(3, dataSql);
                statement.setString(4, cliente.getTelefone());
                statement.setString(5, cliente.getNrCartao());
                statement.setInt(6, cliente.getIdmarkup_empresas());
                
            }
            else if (operacao ==ALTERACAO){
                sql = "update markup_clientes set cpf = ?, nome = ?, telefone = ?, nrCartao = ?, idmarkup_empresas = ? " +
                      "where idmarkup_clientes = ? ";
                statement = bd.connection.prepareStatement(sql);
                statement.setString(1, cliente.getCpf());
                statement.setString(2, cliente.getNome());
                statement.setString(3, cliente.getTelefone());
                statement.setString(4, cliente.getNrCartao());
                statement.setInt(5, cliente.getIdmarkup_empresas());
                statement.setInt(6, cliente.getIdmarkup_clientes());                
            }
            else if (operacao ==EXCLUSAO) {
                sql = "delete from  markup_clientes where idmarkup_clientes=?";
                statement = bd.connection.prepareStatement(sql);
                statement.setInt(1, cliente.getIdmarkup_clientes());
            }
            if (statement.executeUpdate() ==0)
                men = "falha na operacao ";
        }catch (SQLException erro) {
            men = "Falha na operacao !" + erro.toString();
        }
        return men;
    
    }       
   
   
   
    
}
