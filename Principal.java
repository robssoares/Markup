package markup;

import java.awt.event.*;

import javax.swing.*;

public class Principal  {

	private JFrame frame = null;  
	private JMenuBar menuBar;
	//private JMenu menu, submenu ;
	private JMenu menuCadastro;
	private JMenuItem menuItemEmpresa, MenuItemCliente;
	GuiCadastroEmpresa guiCadastroEmpresa;
	GuiCadastroCliente guiCadastroCliente;	
	
	
	
	public void actionMenu() {
	
//		menuCadastro.addItemListener(new ItemListener() {
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//				if (e.getSource() == menuCadastro) {
//					System.out.println("Menu Cadastro");
//				}				
//			}
//		});
		
		menuItemEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == menuItemEmpresa) {
					System.out.println("Menu Item Empresa");
					 // guiCadastroEmpresa.main(null);
				}			
			}
		});
		
		MenuItemCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == MenuItemCliente) {
					System.out.println("Menu Item Cliente");
					//guiCadastroCliente.main(null);
				}			
			}
		});
		
		
	}
	
	public JMenuBar criaMenu() {
		menuBar = new JMenuBar();  // cria a barra de menus
		menuCadastro = new JMenu("Cadastro");
		menuCadastro.setMnemonic(KeyEvent.VK_C);
		menuBar.add(menuCadastro);

//		//****
		menuItemEmpresa = new JMenuItem("Empresas",
                KeyEvent.VK_E);
		menuCadastro.add(menuItemEmpresa);
//		//****
//				
		menuCadastro.addSeparator();
//		
//		//****
		MenuItemCliente = new JMenuItem("Clientes",
                KeyEvent.VK_C);
		menuCadastro.add(MenuItemCliente);
//		//****
//		
//		menu.addSeparator();
//		
//		//****
//		submenu = new JMenu("Submenu");
//		submenu.setMnemonic(KeyEvent.VK_S);
//		
//		menuItem = new JMenuItem("MenuItem1");
//		submenu.add(menuItem);
//
//		menuItem = new JMenuItem("MenuItem2");
//		submenu.add(menuItem);
//		
//		menu.add(submenu);
//		
//		
//		menuRelatorio = new JMenu("Relatório");
//		menuBar.add(menuRelatorio);
//		
		
		return menuBar;
	}
	
	private void exibeTela(){  
		//Cria e seta a janela.  
        frame = new JFrame("Cadastro OCorrências");  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setJMenuBar(this.criaMenu());  
        
        //ajusta o tamanho da janela  
        frame.setSize(900,520);  
        //centraliza o frame na tela  
        frame.setLocationRelativeTo(null);  
        //mostra a janela.  
        frame.setVisible(true); 
        
        guiCadastroEmpresa = new GuiCadastroEmpresa();
        guiCadastroCliente = new GuiCadastroCliente();
        
	}
	
	
	
	
	
	public Principal() {
		exibeTela();
		actionMenu();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Principal();
	}

}
