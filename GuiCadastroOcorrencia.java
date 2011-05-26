package markup;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class GuiCadastroOcorrencia extends JFrame {

	/**
	 * 
	 */
	static final String URL = "jdbc:mysql://192.168.1.12/icallbox";
	static final String USER = "diallink";
	static final String PASSWORD = "4321" ;
		
	private static final long serialVersionUID = 1L;
	JLabel jlData, jlHora, jlCliente, jlCpf, jlNrCartao, jlEmpresa, jlClassificacao;
	JLabel jlFila, jlOperador, jlTelefone, jlObservacao, jlOcorrencia; 
	static JComboBox cbFila, cbClassificacao;
	static JTextField tfCliente, tfCpf, tfNrCartao, tfEmpresa, tfClassificacao, tfTelefone;
	static JTextArea taObservacao;
	static JScrollPane scroll_obs;
	static Date hoje; 
	static String Data, Hora, Telefone, Fila, UniqueId, Operador, idCliente, cpfCliente;
	static String fileOutPath;
	static int idCont, idEmpresa;
	static String[] lista_ocorrencia ;
	JButton btSalvar, btLimpar;
	JCheckBox ckOcorrencia;
	
	
	
	private OcorrenciaDAO ocorrenciaDAO;
	
    SimpleDateFormat data_formatter = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat hora_formatter = new SimpleDateFormat("HH:mm:ss");	
    

    
    
    
public void inicializarComponentes(){
    	//*****
		hoje = new java.util.Date();
		Data = data_formatter.format(hoje);
	    Hora = hora_formatter.format(hoje);
	    //*****
    	
    	jlData 		= new JLabel("Data: " + Data);
    	jlHora 		= new JLabel("Hora: " + Hora);
    	jlCliente 	= new JLabel("Cliente: ");
    	jlCpf 		= new JLabel("CPF: ");
    	jlNrCartao 	= new JLabel("NrCartão: ");
    	jlEmpresa 	= new JLabel("Empresa: ");
    	jlClassificacao = new JLabel("Classificação: ");
    	jlFila 		= new JLabel("Fila: ");
    	jlOperador 	= new JLabel("Operador: " + Operador, JLabel.CENTER);
    	jlTelefone 	= new JLabel("Telefone: ");
    	jlObservacao = new JLabel("Observação: ");
    	
    	tfCliente 		= new JTextField();
    	tfCpf 			= new JTextField();
    	tfNrCartao 		= new JTextField();
    	tfEmpresa 		= new JTextField();
    	tfClassificacao = new JTextField();
    	tfCliente 		= new JTextField();
    	tfTelefone	    = new JTextField();
    	
    	taObservacao    = new JTextArea();
    	scroll_obs 		= new JScrollPane(taObservacao);
    	
    	cbFila			= new JComboBox();
    	cbFila.addItem("CashCard");
    	cbFila.addItem("TrocaPremios");
    	cbFila.setSelectedItem(Fila);
          
    	
    	cbClassificacao = new JComboBox();
    	
    	ckOcorrencia = new JCheckBox("Ocorrencia para Markup", false);
    	
    	
    	 btSalvar    = new JButton();
    	 btSalvar.setText("Salvar Ocorrência");
         
    	 btLimpar    = new JButton();
    	 btLimpar.setText("Limpar Campos");
    	
    	 ocorrenciaDAO = new OcorrenciaDAO();
    	    
    	 
    	 
    	 
    	 Container pane = getContentPane();
         pane.setLayout(new BorderLayout(20,20));   
         pane.add(getHeader(), BorderLayout.NORTH);
         pane.add(getFields(), BorderLayout.CENTER);
         pane.add(getButtonPanel(),BorderLayout.SOUTH ) ;     
         pane.add(getWest(),BorderLayout.WEST ) ;
         
         
         if (!ocorrenciaDAO.bd.getConnection()) {
             JOptionPane.showMessageDialog(null,"Falha na conexao, Sistema sera fechado !");
             System.exit(0);
             
         }
         
    }        
    
protected JComponent getHeader() {
	JPanel inner = new JPanel();
	inner.setLayout(new GridLayout(2, 1, 0, 0));
	
	JLabel label = new JLabel("Cadastro de Ocorrências", JLabel.CENTER);
    label.setFont(new Font("Courier", Font.BOLD, 24));
    inner.add(label);
    inner.add(jlOperador);
    return inner;
 	}      

protected JComponent getButtonPanel() {
    JPanel inner = new JPanel();
    inner.setLayout(new GridLayout(1, 3, 50, 0)); // linha, coluna, distancia
    
    inner.add(ckOcorrencia);
    inner.add(btSalvar);
    inner.add(btLimpar);    
    return inner;
    }    

protected JComponent getWest() {
	JPanel inner = new JPanel();
	inner.setLayout(new GridLayout(20, 1, 0, 0));
	inner.add(jlData);
	inner.add(jlHora);

	return inner;
}



/*
 *      fill = fill (horizontal);  
        gridx = column;  
        gridy = row;  
        gridwidth = width;  // ocupação colunas
        gridheight = height; // ocupação linhas 
    */

protected JComponent getFields() {
    JPanel inner = new JPanel();
    
    inner.setLayout(new java.awt.GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    
    c.insets = new Insets(4,4,4,4);  
    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.WEST;  
    
    
    c.gridx = 0; //column
    c.gridy = 1; //row
    c.gridwidth = 1;
    c.gridheight = 1;
    inner.add((jlFila),c);
    
    c.gridx = 1; //column
    c.gridy = 1; //row
    c.gridwidth = 3;
    c.gridheight = 1;
    c.weightx = 0.5;
    c.fill = GridBagConstraints.NONE;
    inner.add((cbFila),c);
    
    //**********************
    // LINHA 02
    //**********************
  
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0; //column
    c.gridy = 2; //row
    c.gridwidth = 1;
    c.gridheight = 1;
    inner.add((jlTelefone),c);
    
    
    c.gridx = 1; //column
    c.gridy = 2; //row
    c.gridwidth = 1;
    c.gridheight = 1;
    inner.add((tfTelefone),c);
    tfTelefone.setText(Telefone);
    
    //**********************
    // LINHA 03
    //**********************
   
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0; //column
    c.gridy = 3; //row
    c.gridwidth = 1;
    c.gridheight = 1;
    inner.add((jlCpf),c);
    
    c.gridx = 1; //column
    c.gridy = 3; //row
    c.gridwidth = 3;
    c.gridheight = 1;
    inner.add((tfCpf),c);
    
  //**********************
  // LINHA 04
  //**********************

  
    c.gridx = 0; //column
    c.gridy = 4; //row
    c.gridwidth = 1;
    c.gridheight = 1;
    inner.add((jlCliente),c);
    
    c.gridx = 1; //column
    c.gridy = 4; //row
    c.gridwidth = 4;
    c.gridheight = 1;
    c.weightx = 1; // ocupa linha inteira
    inner.add((tfCliente),c);

    //**********************
    // LINHA 05
    //**********************    
    
   
    c.gridx = 0; //column
    c.gridy = 5; //row
    c.gridwidth = 1;
    c.gridheight = 1;
    inner.add((jlNrCartao),c);
    
    c.gridx = 1; //column
    c.gridy = 5; //row
    c.gridwidth = 1;
    c.gridheight = 1;
    inner.add((tfNrCartao),c);
    
    
    //**********************
    // LINHA 06
    //**********************    
   
    
    c.gridx = 0; //column
    c.gridy = 6; //row
    c.gridwidth = 1;
    c.gridheight = 1;
    inner.add((jlEmpresa),c);
    
    c.gridx = 1; //column
    c.gridy = 6; //row
    c.gridwidth = 1;
    c.gridheight = 1;
    inner.add((tfEmpresa),c);
    
    //**********************
    // LINHA 07
    //**********************    
       
    c.fill = GridBagConstraints.WEST;
    c.gridx = 0; //column
    c.gridy = 7; //row
    c.gridwidth = 1;
    c.gridheight = 1;
    inner.add((jlClassificacao),c);
    
    c.gridx = 1; //column
    c.gridy = 7; //row
    c.gridwidth = 1;
    c.gridheight = 1;
    inner.add((cbClassificacao),c);
    
    carregaOcorrencias();
    
				
    //**********************
    // LINHA 08
    //**********************  	
    
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0; //column
    c.gridy = 8; //row
    c.gridwidth = 1;
    c.gridheight = 1;
    inner.add((jlObservacao),c);
    
    c.gridx = 1; //column
    c.gridy = 8; //row
    c.gridwidth = 1;
    c.gridheight = 4;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH; 
    inner.add((scroll_obs),c);
    
   
    
    return inner;
}

//*********************
//*********************  FIM DAS TELAS
//*********************

public static void LerArquivoTxt() {
/*
CONFIGURACAO DO INTEGRADOR - ICALL MANAGER
========================================== 
CalleridID(Num)  = 1121484657%10001
CalleridID(Name) = #FILA_DE_TESTE#Padrao#TELEFONE#NULL#AKIVA-1306340230.12044
Data		 	 = %25/05/2011 13:15:02
Ramal 		 	 = %20003
Agente			 = %roberta
Fila			 = %10001
Operação         = %TESTE
PA               = %
FilaName         = %FILA_DE_TESTE
FilaSkill        = %Padrao
 
LINHA[0] = 1121484657%10001#
LINHA[1] = FILA_DE_TESTE#
LINHA[2] = Padrao#
LINHA[3] = TELEFONE#
LINHA[4] = NULL#
LINHA[5] = AKIVA-1306341233.12107%25/05/2011 13:31:45%20003%roberta%10001%OPERAÇÃO TESTE%%FILA_DE_TESTE%Fila_de_Teste
 *
 */
		
	try {
        
	
		fileOutPath = System.getProperty("user.home") + System.getProperty("file.separator") + 
        "Desktop" + System.getProperty("file.separator") + "CALLS" + System.getProperty("file.separator") + "INCOMING.CFG" ;
		fileOutPath = fileOutPath.replace("\\","/");
		
		BufferedReader in = new BufferedReader(new FileReader(fileOutPath));
            String str;
            while (in.ready()) {
                str = in.readLine();
                
                //UniqueId     = str.substring(beginIndex, endIndex)0 ;
                String[] linha = str.split("#");
                String campoParte1 = linha[0].toString(); // fone%nr.fila
                //String campoParte2 = linha[1].toString(); //nome da fila
                String campoParte3 = linha[2].toString(); // Skill -> Descricao Fila
                //String campoParte4 = linha[3].toString(); // Origem Contato
                //String campoParte5 = linha[4].toString(); // null ???
                String campoParte6 = linha[5].toString(); // UniqueID%Data%Cod.Opera%NomeOpera%nrFila%NomeOpera%??%NomeFila%Skill
                
                
                String[] campos 	= campoParte1.split("%");
                Telefone 	    	= campos[0].toString();
              //  Fila	        	= campos[1].toString();
                Fila                = campoParte3;
                
                campos = campoParte6.split("%");
                UniqueId 			= campos[0].toString();
               // String cDataHora 	= campos[1].toString();
               // String cCodOpera 	= campos[2].toString();
                Operador 			= campos[3].toString();
               
                
            }
            in.close();
    } catch (IOException e) {
    }
}
        
public static void  carregaOcorrencias() {
	cbClassificacao.removeAllItems();
	
	Fila = cbFila.getSelectedItem().toString();
	System.out.println("Opção Escolhida:" + Fila);
	
		if (Fila.equals("CashCard")) { // cash card
			String oco[] = {
					"Duvida- Consulta de saldo",
					"Duvida- Informacoes para Compra",
					"Duvida- Informacoes para saque",
					"Duvida- Informacoes sobre desbloqueio",
					"Duvida- Informacoes sobre entrega de senha",
					"Duvida- Informacoes sobre entrega do cartao",
					"Duvida- Informacoes sobre Senha",
					"Ocorrencia-Auditoria, dinheiro não saiu do caixa",
					"Ocorrencia-Desbloqueio Codificado",
					"Ocorrencia-Desbloqueio Lote",
					"Ocorrencia-Desbloqueio Manual",
					"Ocorrencia-Perda, nao recebeu cartao (troca de cartao)",
					"Ocorrencia-Perda, nao recebeu senha (troca de cartao)",
					"Outros - Ativo",
					"Outros- 2ª via de cartão",
					"Outros- Central MultiClube",
					"Outros- Informações à Terceiros",
					"Outros- Solicitacoes Gerais Azulai",
					"Outros-Engano/Mudo/Caiu Ligação",
					"Outros- Teste" };
			lista_ocorrencia = oco;
			//System.out.println(lista_ocorrencia.length);
		}
		
		if (Fila.equals("TrocaPremios")) { // troda premios
			String[] oco = {
					"Duvida- Consulta de pontuacao",
					"Duvida- Informacoes para efetuar cadastro",
					"Duvida- Informacoes para resgatar e-cash",
					"Duvida- Informacoes para resgatar produtos",
					"Duvida- Informacoes sobre código de acesso",
					"Duvida- Informacoes sobre entrega de produto",
					"Duvida- Problema no site",
					"Ocorrencia-Informacoes sobre codigo de acesso",
					"Ocorrencia-Perda, nao recebeu cartao troca de cartao",
					"Ocorrencia-Perda, nao recebeu codigo de acesso (troca de cartao)",
					"Ocorrencia-Problema no site",
					"Outros- 2ª via de cartão",
					"Outros- Informações à Terceiros",
					"Outros-Central MultiClube",
					"Outros-Ativo",
					"Outros-Engano/Mudo/Caiu Ligação",
					"Outros-Solicitacoes Gerais Azulai",
					"Outros-Teste"
			};
			lista_ocorrencia = oco;
		}  
		
		for (int i = 0; i < lista_ocorrencia.length; i++){
	    	cbClassificacao.addItem(lista_ocorrencia[i]);
		}
} 
    
public static void retornaDadosCliente() {
		
		try {
			Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "SELECT a.id_cont, b.nome, b.cpf, c.campo2, c.campo3 " +
			             "FROM icall_contato_telefone as a, icall_contato_pfisica as b, icall_contato_additional as c " +
			             "where a.id_cont = b.id_cont " +
			             "and c.id_cont = a.id_cont " +
			             "and a.numero = ? ";				
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, Telefone);
						
			ResultSet resultset = statement.executeQuery();
						
			while ( resultset.next() ) {
				String campo6 = resultset.getString("id_cont");
				String campo2 = resultset.getString("nome");
				String campo3 = resultset.getString("cpf");
				String campo4 = resultset.getString("campo2");
				String campo5 = resultset.getString("campo3");
				
						tfCliente.setText(campo2);
						tfCpf.setText(campo3);
						tfEmpresa.setText(campo4);
						tfNrCartao.setText(campo5);
						idCliente = campo6;
			} // final while
			
			resultset.close();
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}		 
 }

public static void verifica_cpf() {
	
	try {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		String sql = "SELECT a.cpf, a.nome, b.campo3, b.campo2, c.numero, a.id_cont from " +
		             "icall_contato_pfisica as a, icall_contato_additional as b, icall_contato_telefone as c " +
		             "where a.id_cont = b.id_cont " +
		             "and c.id_cont = a.id_cont " +
		             "and a.cpf = ? ";				
				
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, tfCpf.getText());
					
		ResultSet resultset = statement.executeQuery();
		
		while ( resultset.next() ) {
			String campo1 = resultset.getString("cpf");
			String campo2 = resultset.getString("nome");
			String campo3 = resultset.getString("campo3");
			String campo4 = resultset.getString("campo2");
			String campo5 = resultset.getString("numero");
			String campo6 = resultset.getString("id_cont");	
					tfCliente.setText(campo2);
					tfCpf.setText(campo1);
					tfEmpresa.setText(campo4);
					tfNrCartao.setText(campo3);
					if (tfTelefone.getText().equals("")){
						tfTelefone.setText(campo5);
					}
					idCliente = campo6;		
		}			
		resultset.close();
		statement.close();
		connection.close();
		
	} catch (SQLException e) {
		System.out.println(e.getMessage());
		
	}
}

public static void verifica_telefone() {
	
	try {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		String sql = "SELECT a.cpf, a.nome, b.campo3, b.campo2, c.numero, a.id_cont from " +
		             "icall_contato_pfisica as a, icall_contato_additional as b, icall_contato_telefone as c " +
		             "where a.id_cont = b.id_cont " +
		             "and c.id_cont = a.id_cont " +
		             "and c.numero = ? ";				
				
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, tfTelefone.getText());
					
		ResultSet resultset = statement.executeQuery();
		
		while ( resultset.next() ) {
			String campo1 = resultset.getString("cpf");
			String campo2 = resultset.getString("nome");
			String campo3 = resultset.getString("campo3");
			String campo4 = resultset.getString("campo2");
			String campo5 = resultset.getString("numero");
			String campo6 = resultset.getString("id_cont");
			
					tfCliente.setText(campo2);
					tfCpf.setText(campo1);
					tfEmpresa.setText(campo4);
					tfNrCartao.setText(campo3);
					tfTelefone.setText(campo5);
					idCliente = campo6;			
		}			
		resultset.close();
		statement.close();
		connection.close();
		
	} catch (SQLException e) {
		System.out.println(e.getMessage());
		
	}
}

public void limpaCampos() {
	tfCliente.setText("");
	tfCpf.setText("");
	tfEmpresa.setText("");
	tfNrCartao.setText("");
	taObservacao.setText("");
}
	

public void salvarOcorrencia(){ // Grava no Banco DialLink
	String url = "jdbc:mysql://192.168.1.12/diallink";
	String user = "diallink";
	String password = "4321" ;
	
	
	System.out.println(
	           " Hora: " + Hora + 
	           " Cliente " + tfCliente.getText() +
	           " CPF: " + tfCpf.getText()+
	           " NrCartao: " + tfNrCartao.getText() +
	           " Empresa: " + tfEmpresa.getText() +
	           " Classifica: " + cbClassificacao.getSelectedItem().toString() +
	           " Fila: " + cbFila.getSelectedItem().toString() +
	           " Operador: " + Operador +
	           " Telefone: " + tfTelefone.getText()+
	           " Observacao: " + taObservacao.getText() +
	           " UniqueID: " + UniqueId + 
	           " idCliente: " + idCliente 
	           );
	
	
	
	
	
	try {
		Connection connection = DriverManager.getConnection(url, user, password);

		String sql = "insert into markup_ocorrencias (data, hora, cliente, cpf, nrCartao, empresa, classificacao, fila, operador, telefone, observacao, uniqueID, id_clientes, id_classificacao, ocorrencia_markup) " +
	    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		
		PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dataUtil = null;

		try { 
			dataUtil = simpleDateFormat.parse(Data);
		} catch (ParseException ex) {
		    ex.printStackTrace();
		}

		// convertendo Java.Util.Date para Java.Sql.Date
		java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime()); 
	
		
		String ocorrencia_markup = "N";
		if (ckOcorrencia.isSelected()) {
			ocorrencia_markup = "S";
		} 
		
		
		stmt.setDate  (1, dataSql);
		stmt.setString(2, Hora);
		stmt.setString(3, tfCliente.getText());
		stmt.setString(4, tfCpf.getText());
		stmt.setString(5, tfNrCartao.getText());
		stmt.setString(6, tfEmpresa.getText());
		stmt.setString(7, cbClassificacao.getSelectedItem().toString());
		stmt.setString(8, cbFila.getSelectedItem().toString());
		stmt.setString(9, Operador);
		stmt.setString(10, tfTelefone.getText());
		stmt.setString(11, taObservacao.getText());
		stmt.setString(12, UniqueId);
		stmt.setInt(13, Integer.parseInt(idCliente));
		stmt.setInt(14, 1); // id_classificacao
		stmt.setString(15, ocorrencia_markup); // id_classificacao
			
		
		
		
		stmt.execute();
		stmt.close();
	
		System.out.println("Ocorrencia Gravada !");
		connection.close();
	
   } catch (SQLException e) {
	  System.out.println(e.getMessage());
}		 


	
}


public boolean clienteExiste(){ // verifica se cliente existe no Banco Icall
		
	/*
	 * Se cliente NOVO (buscar por CPF) -> Gravar dados nas tabelas:
	 *    * icall_contato_pfisica
	 *    * icall_contato_telefone
	 *    * icall_contato_additional
	 */
	
	try {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		String sql = "select cpf from icall_contato_pfisica where cpf = ?";
	
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, tfCpf.getText());
					
		ResultSet resultset = statement.executeQuery();
		resultset.first();
		cpfCliente = resultset.getString("cpf");
	    
	    resultset.close();
	    statement.close();
	    connection.close();
	
    } catch (SQLException e) {
  	  System.out.println(e.getMessage());
  	  return false;
    }

    if (!tfCpf.getText().equals(cpfCliente)) {  // cliente nao cadastrado
      return false;
    } else {
    	return true; // cliente cadastrado
    }
	
}


public int buscarEmpresas() {  // banco diallink
	String url = "jdbc:mysql://192.168.1.12/diallink";
	String user = "diallink";
	String password = "4321" ;
	
	try {
		Connection connection = DriverManager.getConnection(url, user, password);

		String sql = "select idEmpresa from markup_empresas where nomeEmpresa = ? " ;
		
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
		statement.setString(1, tfEmpresa.getText());
		
		ResultSet resultset = statement.executeQuery();
		resultset.first();
		idEmpresa = resultset.getInt("idEmpresa");
	    
	    resultset.close();
	    statement.close();
	    connection.close();
	
    } catch (SQLException e) {
  	  System.out.println(e.getMessage());
    }	
	return idEmpresa;	
		
}




public int retornaIdCont() {
	
	try {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		String sql = "select id_cont from icall_contato_pfisica order by id_cont desc";
	
		PreparedStatement statement = connection.prepareStatement(sql);
							
		ResultSet resultset = statement.executeQuery();
		resultset.first();
		idCont = resultset.getInt("id_cont");
		resultset.close();
	    statement.close();
	    connection.close();
	
    } catch (SQLException e) {
  	  System.out.println(e.getMessage());
    }
	return idCont;
}


public void salvarIcall(){ // Grava no Banco Icall
	
	try {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

//**** CONTATO_PFISICA
		
		String sql = "insert into icall_contato_pfisica (id_cont, nome,	data_nasc, cpf,	rg,	sexo) " +
	    "VALUES (?,?,?,?,?,?)" ;
		
		PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dataBranco = "01/01/1900";
		Date dataUtil = null;

		try { 
			dataUtil = simpleDateFormat.parse(dataBranco);
		} catch (ParseException ex) {
		    ex.printStackTrace();
		}

		// convertendo Java.Util.Date para Java.Sql.Date
		java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime()); 
	
		int nrIdCliente = retornaIdCont();
		nrIdCliente++;
		
		idCliente = String.valueOf(nrIdCliente);
				
		stmt.setInt   (1, nrIdCliente ); // idcont
		stmt.setString(2, tfCliente.getText());
		stmt.setDate  (3, dataSql);
		stmt.setString(4, tfCpf.getText());
		stmt.setString(5, "");
		stmt.setString(6, "");
							
		//stmt.execute();
		stmt.close();
		
//**** CONTATO_TELEFONE		
		try { 
			dataUtil = simpleDateFormat.parse(Data);
		} catch (ParseException ex) {
		    ex.printStackTrace();
		}

		// convertendo Java.Util.Date para Java.Sql.Date
		dataSql = new java.sql.Date(dataUtil.getTime()); 
		
		
		String sql2 = "insert into icall_contato_telefone (id_cont, cad_data, numero, tipo, local) " +
	    "VALUES (?,?,?,?,?)" ;
		
		PreparedStatement stmt2 = (PreparedStatement) connection.prepareStatement(sql2);	
		stmt2.setInt    (1, nrIdCliente ); // idcont
		stmt2.setDate   (2, dataSql);
		stmt2.setString (3, tfTelefone.getText());
		stmt2.setString (4, "");
		stmt2.setString (5, "");
						
		//stmt2.execute();
		stmt2.close();
	
//**** CONTATO_ADDITIONAL		
		
		int id_empresa = buscarEmpresas();
		
		
		String sql3 = "insert into icall_contato_additional (id_oper, id_cont, campo1, campo2, campo3) " +
	    "VALUES (?,?,?,?,?)" ;
		
		PreparedStatement stmt3 = (PreparedStatement) connection.prepareStatement(sql3);	
		stmt3.setInt    (1, 2 ); 
		stmt3.setInt    (2, nrIdCliente );
		stmt3.setString (3, String.valueOf(id_empresa)); // id empresa
		stmt3.setString (4, tfEmpresa.getText());
		stmt3.setString (5, tfNrCartao.getText());
						
		//stmt3.execute();
		stmt3.close();
			
		System.out.println("Ocorrencia Icall Gravada !");
		connection.close();
	
   } catch (SQLException e) {
	  System.out.println(e.getMessage());
}		 
	
}



public static void delFileCFG() {
	File file = new File(fileOutPath);
	file.delete();
 
    // criando um novo arquivo
	try {
        File f = new File(fileOutPath);
        f.createNewFile();
    } catch (IOException e) {
        System.out.println("Erro ao criar arquivo!");
        e.printStackTrace();
    }
	
}






public void definirEventos() {
	btLimpar.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			limpaCampos();
			tfTelefone.requestFocus();
		}		
	}); 
	btSalvar.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			if ( (tfTelefone.getText().isEmpty() ) || (tfCliente.getText().isEmpty() || (tfCpf.getText().isEmpty())) ) {
				JOptionPane.showMessageDialog(null, "Campos Obrigatórios não preenchidos", "ATENÇÃO !", JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, " Ocorrência Gravada !" ) ;
				
				if (!clienteExiste()) {
					salvarIcall();
				} 
				
				// Salvar Ocorrencia depois de verificar se Cliente Existe,
				// pois se NAO existir, vai gerar o idCliente
				
				salvarOcorrencia();	
				delFileCFG();
				System.exit(0);
			}
		}		
	}); 
	
	
	
	
	cbFila.addItemListener(new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
		   carregaOcorrencias();			
		}
	});
	
	tfCpf.addFocusListener(new FocusListener() {
		public void focusLost(FocusEvent e) {
		  if (e.getSource() == tfCpf) {
			  verifica_cpf();
		  }			
		}
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
		}
	});
	
	
       tfTelefone.addFocusListener(new FocusListener() {
		public void focusLost(FocusEvent e) {
		  if (e.getSource() == tfTelefone) {
			  if (tfTelefone.getText().length() > 9) {
				  verifica_telefone();
			  }			  
		  }			
		}
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
		}
	});

       
}


public GuiCadastroOcorrencia(){
    	LerArquivoTxt();
    	inicializarComponentes(); 
    	retornaDadosCliente();
        definirEventos();
   }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GuiCadastroOcorrencia janela = new GuiCadastroOcorrencia();
	    janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    /*
	    vai depender só da sua implementação do WindowClosing se vai fechar o JFrame ou não   

	    janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  
	    
	    addWindowListener(new WindowAdapter() {  
          public void windowClosing(WindowEvent evt) {  
          if (JOptionPane.showConfirmDialog(null,"Deseja sair")==JOptionPane.OK_OPTION){  
            salvarDados();  }  
          }  
        }); 
	     */
	    
	    janela.setVisible(true);
	    janela.setSize(800, 600);
	    janela.setLocation(75, 75); 
	    janela.setTitle("Atendimento Markup");
		
	    
	    
	}

}
