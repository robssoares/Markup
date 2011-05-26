/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markup;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

/**
 *
 * @author robssoares
 */
public class GuiCadastroCliente extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel jlIdCliente, jlCpf, jlNome, jlDtNascto, jlTelefone, jlNrCartao, jlDtDesbloqueioUra, jlIdMarkupEmpresas;
    JButton btGravar, btAlterar, btExcluir, btNovo, btLocalizar, btCancelar, btSair;
    JComboBox cbEmpresas;
    static JTextField tfIdCliente, tfCpf, tfNome, tfDtNascto, tfTelefone, tfNrCartao, tfDtDesbloqueioUra, tfIdMarkupEmpresas; ;
    private ClienteDAO clienteDAO;
    
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    
 public GuiCadastroCliente(){
         inicializarComponentes(); 
         definirEventos();
    }
   
     
 public void inicializarComponentes(){
        jlIdCliente = new JLabel("Codigo: ");
        jlNome = new JLabel("Cliente: ");
        jlCpf= new JLabel("Cpf: ");
        jlDtNascto = new JLabel("DtNascto:");
        jlTelefone = new JLabel("Telefone:");
        jlNrCartao = new JLabel ("NrCart�o:");
        jlDtDesbloqueioUra = new JLabel("Data Desbloqueio Ura:");
        jlIdMarkupEmpresas = new JLabel("ID Empresa:");
        
        tfIdCliente = new JTextField(10);
        tfNome = new JTextField(60);
        tfCpf = new JTextField(15);
        tfDtNascto = new JTextField(15);
        tfTelefone = new JTextField(15);
        tfNrCartao = new JTextField(15);
        tfDtDesbloqueioUra = new JTextField(15);
        tfIdMarkupEmpresas = new JTextField(5);
        
        cbEmpresas = new JComboBox();
        
        
        
        
        
        btGravar    = new JButton();
        btGravar.setText("Gravar");
        
        btAlterar    = new JButton();
        btAlterar.setText("Alterar");
        
        btExcluir    = new JButton();
        btExcluir.setText("Excluir");        
        
        btNovo    = new JButton();
        btNovo.setText("Novo");                
        
        btLocalizar    = new JButton();
        btLocalizar.setText("Localizar");                        
        
        btCancelar    = new JButton();
        btCancelar.setText("Cancelar");     
        
        btSair    = new JButton();
        btSair.setText("Sair");     
        
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout(20,20));  // espaçamento entre as áreas
           
        
        pane.add(getHeader(), BorderLayout.NORTH);
        pane.add(getFields(), BorderLayout.CENTER);
        pane.add(getButtonPanel(),BorderLayout.SOUTH ) ;     
            
        clienteDAO = new ClienteDAO();
        
        if (!clienteDAO.bd.getConnection()) {
        JOptionPane.showMessageDialog(null,"Falha na conexao, Sistema será fechado !");
        System.exit(0);
        
    }
    
}     
     
     
 protected JComponent getHeader() {
    JLabel label = new JLabel("Cadastro de Clientes", JLabel.CENTER);
    label.setFont(new Font("Courier", Font.BOLD, 24));
    return label;
    }    
   
 protected JComponent getButtonPanel() {
    JPanel inner = new JPanel();
    inner.setLayout(new GridLayout(1, 7, 5, 0));
    inner.add(btNovo);
    inner.add(btLocalizar);
    inner.add(btGravar);
    inner.add(btAlterar);
    inner.add(btExcluir);
    inner.add(btCancelar);
    inner.add(btSair);
    return inner;
    }    
   
    
 protected JComponent getFields() {
 
        JPanel inner = new JPanel();
        inner.setLayout(new java.awt.GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        

        
   // id + nome    
        c.insets = new Insets(4,4,4,4);  
        c.fill = GridBagConstraints.HORIZONTAL;  
        c.anchor = GridBagConstraints.CENTER;  
        c.gridwidth = 1;  
        inner.add((jlIdCliente),c);
        
        c.weightx = 1;  
        c.gridwidth = 1;  
        c.weightx = 0.2;  
        inner.add((tfIdCliente),c);      
        
       
        c.fill = GridBagConstraints.HORIZONTAL;  
        c.weightx = 0;  
        c.gridwidth = 1;  
        inner.add((jlNome),c); 
        
        c.weightx = 1;  
        c.gridwidth = GridBagConstraints.REMAINDER;  // preenche o resto da linha
        inner.add((tfNome),c);   
   
    // cpf + dtnascto + telefone
            
        c.fill = GridBagConstraints.BOTH;  
        c.insets = new Insets(4,4,4,4);  
  
        c.fill = GridBagConstraints.NONE;  
        c.anchor = GridBagConstraints.NORTHWEST;  
        
        c.gridy = 1; // muda linha
        c.weightx = 0;  
        c.gridwidth = 1;  
        inner.add((jlCpf),c);
          
        c.weightx = 1;  
        c.gridwidth = 1;  
        c.fill = GridBagConstraints.BOTH;  
        inner.add((tfCpf),c);      
  
       
        c.fill = GridBagConstraints.NONE;  
        c.weightx = 0;  
        c.gridwidth = 1;  
        inner.add((jlDtNascto),c); 
          
        c.weightx = 1;  
        c.fill = GridBagConstraints.BOTH;  
        c.gridwidth = 1;//GridBagConstraints.REMAINDER;  
        inner.add((tfDtNascto),c);   
 
        
        c.fill = GridBagConstraints.NONE;  
        c.weightx = 0;  
        c.gridwidth = 1;  
        inner.add((jlTelefone),c); 
          
        c.weightx = 1;  
        c.fill = GridBagConstraints.BOTH;  
        c.gridwidth = GridBagConstraints.REMAINDER;  
        inner.add((tfTelefone),c);   
        
        
        
         // idEmpresa + nrCartao + dtDesbloqueioURa
        
        c.fill = GridBagConstraints.BOTH;  
        c.insets = new Insets(4,4,4,4);  
        c.fill = GridBagConstraints.NONE;  
        c.anchor = GridBagConstraints.NORTHWEST;  
        
        c.gridy = 2; // muda linha
        c.weightx = 0;  
        c.gridwidth = 1;  
        inner.add((jlIdMarkupEmpresas),c);
          
        c.weightx = 1;  
        c.gridwidth = 1;  
        c.fill = GridBagConstraints.BOTH;  
        inner.add((tfIdMarkupEmpresas),c);      
       
        //combobox
       
        //
        
        
        c.fill = GridBagConstraints.NONE;  
        c.weightx = 0;  
        c.gridwidth = 1;  
        inner.add((jlNrCartao),c); 
          
        c.weightx = 1;  
        c.fill = GridBagConstraints.BOTH;  
        c.gridwidth = 1;//GridBagConstraints.REMAINDER;  
        inner.add((tfNrCartao),c);   
 
        
        c.fill = GridBagConstraints.NONE;  
        c.weightx = 0;  
        c.gridwidth = 1;  
        inner.add((jlDtDesbloqueioUra),c); 
          
        c.weightx = 1;  
        c.fill = GridBagConstraints.BOTH;  
        c.gridwidth = GridBagConstraints.REMAINDER;  
        inner.add((tfDtDesbloqueioUra),c);   
        
 
        return inner;
        
    
    }     
   

 public void setBotoes(boolean bNovo, boolean bLocalizar, boolean bGravar, 
                      boolean bAlterar, boolean bExcluir, boolean bCancelar ) {
    btNovo.setEnabled(bNovo);
    btLocalizar.setEnabled(bLocalizar);
    btGravar.setEnabled(bGravar);
    btAlterar.setEnabled(bAlterar);
    btExcluir.setEnabled(bExcluir);
    btCancelar.setEnabled(bCancelar);
    }
     
    
 public void limpaCampos() {
       tfIdCliente.setText("");
       tfNome.setText("");
       tfCpf.setText("");
       tfDtNascto.setText("");
       tfTelefone.setText("");
       tfNrCartao.setText("");
       tfDtDesbloqueioUra.setText("");
       tfIdMarkupEmpresas.setText("");
      
       setBotoes(true, true, false, false, false, false);
    }
   
 public void definirEventos(){
    
    btSair.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent e) {
        clienteDAO.bd.close();
        System.exit(0);
     }
    });
  
    btNovo.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent e) {
        limpaCampos();
        setBotoes(false, false, true, false, false, true);
     }
    });
    
   btCancelar.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent e) {
        limpaCampos();        
     }
    }); 
   
   btGravar.addActionListener(new ActionListener(){
    
       public void actionPerformed(ActionEvent e) {
        if (tfCpf.getText().equals("") || tfNome.getText().equals("") ||  tfNrCartao.getText().equals("")) {
               JOptionPane.showMessageDialog(null, "Não pode haver campos em branco !");
               tfIdCliente.requestFocus();
               return;
             }
        
               
           clienteDAO.cliente.setCpf(tfCpf.getText());
           clienteDAO.cliente.setNome(tfNome.getText());
       
          Date dataUtil = null; 
          try { 
		 dataUtil = simpleDateFormat.parse(tfDtNascto.getText()) ;
	      } catch (ParseException ex) {
		ex.printStackTrace();
	      }
          java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime()); 
               
           clienteDAO.cliente.setDtNascto(dataSql);
           
           clienteDAO.cliente.setTelefone(tfTelefone.getText());
           clienteDAO.cliente.setNrCartao(tfNrCartao.getText());
           clienteDAO.cliente.setIdmarkup_empresas(Integer.parseInt(   tfIdMarkupEmpresas.getText()   ));
           JOptionPane.showMessageDialog(null, clienteDAO.atualizar(ClienteDAO.INCLUSAO));
           limpaCampos();
       }
    });
   
   
    btAlterar.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent e) {
         
           clienteDAO.cliente.setIdmarkup_clientes(Integer.parseInt(tfIdCliente.getText() ));
           clienteDAO.cliente.setCpf(tfCpf.getText());
           clienteDAO.cliente.setNome(tfNome.getText());
           clienteDAO.cliente.setTelefone(tfTelefone.getText());
           clienteDAO.cliente.setNome(tfNrCartao.getText());
           clienteDAO.cliente.setIdmarkup_empresas(Integer.parseInt(tfIdMarkupEmpresas.getText()  ));
                  
         JOptionPane.showMessageDialog(null, clienteDAO.atualizar(ClienteDAO.ALTERACAO));
         limpaCampos();
     }
    }); 
   
   btExcluir.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent e) {
        clienteDAO.cliente.setIdmarkup_clientes((Integer.parseInt(tfIdCliente.getText() )));
        clienteDAO.localizar();
        int n = JOptionPane.showConfirmDialog(null, clienteDAO.cliente.getNome(),
                                              "Excluir o Cliente ? ", JOptionPane.YES_NO_OPTION);
     
        if (n== JOptionPane.YES_NO_OPTION) {
            JOptionPane.showMessageDialog(null, clienteDAO.atualizar(ClienteDAO.EXCLUSAO));
            limpaCampos();
        }
     }
    }); 
   
   
    btLocalizar.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent e) {
        atualizaCampos();
        setBotoes(true, true, true, true, true, true);
        
     }
    }); 
   
}      
    
 public void atualizaCampos(){
   clienteDAO.cliente.setIdmarkup_clientes(Integer.parseInt(tfIdCliente.getText()));
      
    if (clienteDAO.localizar()) {
        
       // tfIdCliente.setText(String.valueOf(clienteDAO.cliente.getIdmarkup_clientes()));
        tfNome.setText(clienteDAO.cliente.getNome());
       
       //Converter Data para String formatado em DD/MM/YYYY
       //tfDtNascto.setText(clienteDAO.cliente.getDtNascto().toString());
       //String dtTemp = simpleDateFormat.format(clienteDAO.cliente.getDtNascto());
       //tfDtNascto.setText(dtTemp);  
        
        tfCpf.setText(clienteDAO.cliente.getCpf());
        tfTelefone.setText(clienteDAO.cliente.getTelefone());
        tfNrCartao.setText(clienteDAO.cliente.getNrCartao());
      
        //Converter Data para String formatado em DD/MM/YYYY
        //dtTemp = "";
        //dtTemp = simpleDateFormat.format(clienteDAO.cliente.getDtDesbloqueioUra());
        //tfDtDesbloqueioUra.setText(dtTemp);  
        
        tfIdMarkupEmpresas.setText(String.valueOf(clienteDAO.cliente.getIdmarkup_empresas()));        
        
        setBotoes(true, true, false, false, false, false);
        
    }
    else {
        JOptionPane.showMessageDialog(null,"Cliente n�o encontrado!");
        limpaCampos();
    }
}      
  
  
    
    
 public static void main(String[] args) {
        // TODO code application logic here
         
        
    GuiCadastroCliente janela = new GuiCadastroCliente();
    janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela.setVisible(true);
    janela.setSize(800, 600);
    janela.setLocation(75, 75); 

    
    }        
     
    
}
