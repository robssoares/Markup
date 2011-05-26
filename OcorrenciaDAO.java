package markup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class OcorrenciaDAO {

	public Ocorrencia ocorrencia;
    public BD bd;
	
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String men, sql;
    
    public static final byte INCLUSAO = 1;
    public static final byte ALTERACAO = 2;
    public static final byte EXCLUSAO = 3;
    
    
    public OcorrenciaDAO() {
        bd = new BD();
        ocorrencia = new Ocorrencia();
    }
    
    public boolean localizar(){
        sql = "select idmarkup_ocorrencias, cliente from markup_ocorrencias  where idmarkup_ocorrencias = ?";
        try {
            statement = bd.connection.prepareStatement(sql);
            statement.setInt(1, ocorrencia.getIdmarkup_ocorrencias());
            resultSet = statement.executeQuery();
            resultSet.first();
            
            ocorrencia.setIdmarkup_ocorrencias(resultSet.getInt(1));
            ocorrencia.setCliente(resultSet.getString(2));
            
            return true;
        } catch (SQLException erro) {
            return false;
        }
    }
    

    public String atualizar(int operacao){
        men = "Operacao realizada dom sucesso !";
        try {
            if (operacao ==INCLUSAO){
                sql = "insert into markup_ocorrencias(data, hora, cliente, cpf, nrCartao, " +
                	  " empresa, classificacao, fila, operador, telefone, observacao, uniqueId)" +
                	  " values(?,?,?,?,?,?,?,?,?,?,?,?)";
                
                statement = bd.connection.prepareStatement(sql);
                statement.setDate		(1, (java.sql.Date) ocorrencia.getData());                 
                statement.setDate		(2, (java.sql.Date) ocorrencia.getHora());   
                statement.setString		(3, ocorrencia.getCliente());
                statement.setString		(4, ocorrencia.getCpf());
                statement.setString		(5, ocorrencia.getNrCartao());
                statement.setString		(6, ocorrencia.getEmpresa());
                statement.setString		(7, ocorrencia.getClassificacao());
                statement.setString		(8, ocorrencia.getFila());
                statement.setString     (9, ocorrencia.getOperador());
                statement.setString     (10, ocorrencia.getTelefone());
                statement.setString     (11, ocorrencia.getObservacao());
                statement.setString     (12, ocorrencia.getUniqueId());
            }
           
            else if (operacao ==EXCLUSAO) {
                sql = "delete from  markup_ocorrencias where idmarkup_ocorrencias=?";
                statement = bd.connection.prepareStatement(sql);
                statement.setInt(1, ocorrencia.getIdmarkup_ocorrencias());
            }
            if (statement.executeUpdate() ==0)
                men = "falha na operacao ";
        }catch (SQLException erro) {
            men = "Falha na operacao !" + erro.toString();
        }
        return men;
    
    }    
    
    
}

    
    
	

