/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markup;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author robssoares
 */

public class GuiCadastroEmpresa extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel jlIdEmpresa, jlNomeEmpresa;
    JButton btGravar, btAlterar, btExcluir, btNovo, btLocalizar, btCancelar, btSair;
    static JTextField tfIdEmpresa, tfNomeEmpresa;
    private EmpresaDAO empresaDAO;
    
    public GuiCadastroEmpresa(){
         inicializarComponentes(); 
         definirEventos();
         
    }
    
    
    public void inicializarComponentes(){
        jlIdEmpresa = new JLabel("Codigo: ");
        jlNomeEmpresa = new JLabel("Empresa: ");
        
        tfIdEmpresa = new JTextField(10);
        tfNomeEmpresa = new JTextField(60);
        
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
        pane.setLayout(new BorderLayout(20,20));  // espa√ßamento entre as √°reas
           
        
        pane.add(getHeader(), BorderLayout.NORTH);
        pane.add(getFields(), BorderLayout.CENTER);
        pane.add(getButtonPanel(),BorderLayout.SOUTH ) ;     
       
        
        
        
        empresaDAO = new EmpresaDAO();
        
        if (!empresaDAO.bd.getConnection()) {
        JOptionPane.showMessageDialog(null,"Falha na conexao, Sistema ser√° fechado !");
        System.exit(0);
        
    }
    
}
  
    private JComponent getFields() {
 
        JPanel inner = new JPanel();
        inner.setLayout(new java.awt.GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        

        
        //LABEL + TEXTFIELD //+ LABEL + TEXTFIELD // LABEL + TEXTFIELD
      //  c.fill = GridBagConstraints.BOTH;  
        c.insets = new Insets(4,4,4,4);  
        c.fill = GridBagConstraints.HORIZONTAL;  
        c.anchor = GridBagConstraints.CENTER;  
        c.gridwidth = 1;  
        inner.add((jlIdEmpresa),c);
        
        c.weightx = 1;  
        c.gridwidth = 1;  
        c.weightx = 0.2;  
        inner.add((tfIdEmpresa),c);      
        
       
        c.fill = GridBagConstraints.HORIZONTAL;  
        c.weightx = 0;  
        c.gridwidth = 1;  
        inner.add((jlNomeEmpresa),c); 
        
        c.weightx = 1;  
        c.gridwidth = 1;  
        inner.add((tfNomeEmpresa),c);   
       
 
        return inner;
        
    
    }     
       
    private JComponent getButtonPanel() {
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

    private JComponent getHeader() {
    JLabel label = new JLabel("Cadastro de Empresa", JLabel.CENTER);
    label.setFont(new Font("Courier", Font.BOLD, 24));
    return label;
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
    
    
    public void limpaCampos(){
    tfIdEmpresa.setText("");
    tfNomeEmpresa.setText("");
    tfIdEmpresa.requestFocus();
    setBotoes(true, true, false, false, false, false);
    }
    

    public void definirEventos(){
    
    btSair.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent e) {
        empresaDAO.bd.close();
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
        if (tfIdEmpresa.getText().equals("") || tfNomeEmpresa.getText().equals("")) {
               JOptionPane.showMessageDialog(null, "N„o pode haver campos em branco !");
               tfIdEmpresa.requestFocus();
               return;
             }
           empresaDAO.empresa.setIdEmpresa((Integer.parseInt(tfIdEmpresa.getText() )));
           empresaDAO.empresa.setNomeEmpresa(tfNomeEmpresa.getText());
                      
           JOptionPane.showMessageDialog(null, empresaDAO.atualizar(EmpresaDAO.INCLUSAO));
           limpaCampos();
       }
    });
   
   
    btAlterar.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent e) {
         empresaDAO.empresa.setIdEmpresa(Integer.parseInt(tfIdEmpresa.getText() ));
         empresaDAO.empresa.setNomeEmpresa(tfNomeEmpresa.getText());
                  
         JOptionPane.showMessageDialog(null, empresaDAO.atualizar(EmpresaDAO.ALTERACAO));
         limpaCampos();
     }
    }); 
   
   btExcluir.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent e) {
        empresaDAO.empresa.setIdEmpresa((Integer.parseInt(tfIdEmpresa.getText() )));
        empresaDAO.localizar();
        int n = JOptionPane.showConfirmDialog(null, empresaDAO.empresa.getNomeEmpresa(),
                                              "Excluir a Empresa ? ", JOptionPane.YES_NO_OPTION);
     
        if (n== JOptionPane.YES_NO_OPTION) {
            JOptionPane.showMessageDialog(null, empresaDAO.atualizar(EmpresaDAO.EXCLUSAO));
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
    empresaDAO.empresa.setIdEmpresa(Integer.parseInt(tfIdEmpresa.getText()));
    
    if (empresaDAO.localizar()) {
        
        tfIdEmpresa.setText(String.valueOf(empresaDAO.empresa.getIdEmpresa()));
        tfNomeEmpresa.setText(empresaDAO.empresa.getNomeEmpresa());
        setBotoes(true, true, false, false, false, false);
    }
    else {
        JOptionPane.showMessageDialog(null,"Empresa n„o encontrada !");
        limpaCampos();
    }
}    
   
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
         
        
    GuiCadastroEmpresa janela = new GuiCadastroEmpresa();
    janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela.setVisible(true);
    janela.setSize(800, 600);
    janela.setLocation(75, 75); 

    
    }    
    
    
    
}  
    
  