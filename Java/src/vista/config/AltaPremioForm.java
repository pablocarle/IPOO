package vista.config;

import controlador.Sistema;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import vista.MainFrame;

public class AltaPremioForm extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField valorPremioText;
	private JTextField nroMaquinaText;
	private JTextArea  combinacionText;
	private JButton btnBanana;
	private JButton btnFrutilla;
	private JButton btnManzana;
	private JButton btnPera;
	private JButton btnSandia;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private Sistema sistema;

	public static void main(String[] args) {
		AltaPremioForm inst = new AltaPremioForm();
		inst.setVisible(true);
	}
	
	public AltaPremioForm(){
	}
	public AltaPremioForm(Sistema s) {
		super();
		initGUI();
		sistema = s;
	}
	
	public void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				setLayout(null);
				
				JLabel lblnroMaquina = new JLabel("Nro Tragamonedas");
				lblnroMaquina.setBounds(12, 30, 202, 15);
				add(lblnroMaquina);
		
				JLabel lblvalorPremio = new JLabel("Valor del Premio");
				lblvalorPremio.setBounds(220, 30, 202, 15);
				add(lblvalorPremio);
				
		        combinacionText=new JTextArea();
		        combinacionText.setBounds(80,70,300,100);
		        add(combinacionText);
		
				valorPremioText = new JTextField();
				valorPremioText.setBounds(324, 28, 114, 19);
				add(valorPremioText);
				valorPremioText.setColumns(10);
				
				nroMaquinaText = new JTextField();
				nroMaquinaText.setBounds(140, 28, 20, 19);
				add(nroMaquinaText);
				nroMaquinaText.setColumns(10);
				
				btnBanana = new JButton("Banana");
				//btnBanana.setIcon(new javax.swing.ImageIcon("banana.jpg")); 
				btnBanana.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						   combinacionText.append("Banana\n");
                           combinacionText.setLineWrap(true);
					}
				});	
				btnBanana.setBounds(10, 200, 80, 25);
				add(btnBanana);
				
				btnFrutilla = new JButton("Frutilla");
				//btnBanana.setIcon(new javax.swing.ImageIcon("banana.jpg"));
				btnFrutilla.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						   combinacionText.append("Frutilla\n");
                           combinacionText.setLineWrap(true);
					}
				});	
				btnFrutilla.setBounds(100, 200, 80, 25);
				add(btnFrutilla);
				
				btnManzana = new JButton("Manzana");
				//btnBanana.setIcon(new javax.swing.ImageIcon("banana.jpg")); 
				btnManzana.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						   combinacionText.append("Manzana\n");
                           combinacionText.setLineWrap(true);
					}
				});	
				btnManzana.setBounds(190, 200, 80, 25);
				add(btnManzana);
				
				btnPera = new JButton("Pera");
				//btnBanana.setIcon(new javax.swing.ImageIcon("banana.jpg")); 
				btnPera.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						   combinacionText.append("Pera\n");
                           combinacionText.setLineWrap(true);
					}
				});	
				btnPera.setBounds(280, 200, 80, 25);
				add(btnPera);
				
				btnSandia = new JButton("Sandia");
				//btnBanana.setIcon(new javax.swing.ImageIcon("banana.jpg")); 
				btnSandia.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						   combinacionText.append("Sandia\n");
                           combinacionText.setLineWrap(true);
					}
				});	
				btnSandia.setBounds(370, 200, 80, 25);
				add(btnSandia);
				
				btnAceptar = new JButton("Aceptar");
				btnAceptar.addActionListener(new ActionListener() {
			
					public void actionPerformed(ActionEvent arg0) {
						   float valorPremio = Float.parseFloat(valorPremioText.getText());
			    		   int Nromaquina = Integer.parseInt(nroMaquinaText.getText());
			    		   String[] texto = combinacionText.getText().split("\n");
						   List<String> combinacionPremio = Arrays.asList(texto);

                           sistema.altaPremio(Nromaquina, combinacionPremio, valorPremio);
					}
				});	
				btnAceptar.setBounds(100, 300, 117, 25);
				add(btnAceptar);
		
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
			
					public void actionPerformed(ActionEvent arg0) {
//						Cerrar la ventana y volver al menu principal
						getParent().setVisible(false);
						JFrame mainPage = new MainFrame();
						mainPage.setVisible(true);
					}
				});	
				btnCancelar.setBounds(242, 300, 117, 25);
				add(btnCancelar);
				setSize(500, 400);
			}
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
}