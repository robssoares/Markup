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
public class EmpresaDAO {
    
    public Empresa empresa;
    public BD bd;
    
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String men, sql;
    
    public static final byte INCLUSAO = 1;
    public static final byte ALTERACAO = 2;
    public static final byte EXCLUSAO = 3;

    public EmpresaDAO() {
        bd = new BD();
        empresa = new Empresa();
    }

    
     public boolean localizar(){
        sql = "select idEmpresa, nomeEmpresa from markup_empresas where idEmpresa = ?";
        try {
            statement = bd.connection.prepareStatement(sql);
            statement.setInt(1, empresa.getIdEmpresa());
            resultSet = statement.executeQuery();
            resultSet.first();
            
            empresa.setIdEmpresa(resultSet.getInt(1));
            empresa.setNomeEmpresa(resultSet.getString(2));
            return true;
        } catch (SQLException erro) {
            return false;
        }
    }
    
    
 public String atualizar(int operacao){
        men = "Operacao realizada dom sucesso !";
        try {
            if (operacao ==INCLUSAO){
                sql = "insert into markup_empresas(idEmpresa, nomeEmpresa) values(?,?)";
                statement = bd.connection.prepareStatement(sql);
                statement.setInt(1, empresa.getIdEmpresa());                 
                statement.setString(2, empresa.getNomeEmpresa());                
            }
            else if (operacao ==ALTERACAO){
                sql = "update markup_empresas set nomeEmpresa = ? " +
                      "where idEmpresa = ? ";
                statement = bd.connection.prepareStatement(sql);
                statement.setInt(2, empresa.getIdEmpresa());
                statement.setString(1, empresa.getNomeEmpresa());
            }
            else if (operacao ==EXCLUSAO) {
                sql = "delete from  markup_empresas where idEmpresa=?";
                statement = bd.connection.prepareStatement(sql);
                statement.setInt(1, empresa.getIdEmpresa());
            }
            if (statement.executeUpdate() ==0)
                men = "falha na operacao ";
        }catch (SQLException erro) {
            men = "Falha na operacao !" + erro.toString();
        }
        return men;
    
    }    
    
 

}
