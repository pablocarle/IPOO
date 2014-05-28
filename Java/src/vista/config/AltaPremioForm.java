package vista.config;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import vista.MainFrame;
import vista.UserMessageView;
import controlador.Sistema;

public class AltaPremioForm extends JPanel {
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
	private JButton btnLimpiar;
	private Sistema sistema;


	public AltaPremioForm(final Sistema sistema) {
		super();
		initGUI();
		this.sistema = sistema;
		
	}

	public void initGUI() {
	
		setLayout(null);

		JLabel lblnroMaquina = new JLabel("Nro Tragamonedas");
		lblnroMaquina.setBounds(12, 30, 202, 15);
		add(lblnroMaquina);

		JLabel lblvalorPremio = new JLabel("Valor del Premio");
		lblvalorPremio.setBounds(220, 30, 202, 15);
		add(lblvalorPremio);

		combinacionText = new JTextArea();
		combinacionText.setBounds(80,70,300,100);
//			Deshabilitamos para que solo puedan cargarse frutas con los botones
		combinacionText.setEnabled(false);
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
		btnBanana.setBounds(10, 200, 80, 25);
		add(btnBanana);

		btnFrutilla = new JButton("Frutilla");
		//btnBanana.setIcon(new javax.swing.ImageIcon("banana.jpg"));
		btnFrutilla.setBounds(100, 200, 80, 25);
		add(btnFrutilla);

		btnManzana = new JButton("Manzana");
		//btnBanana.setIcon(new javax.swing.ImageIcon("banana.jpg")); 
		btnManzana.setBounds(190, 200, 85, 25);
		add(btnManzana);

		btnPera = new JButton("Pera");
		//btnBanana.setIcon(new javax.swing.ImageIcon("banana.jpg")); 
		btnPera.setBounds(290, 200, 80, 25);
		add(btnPera);

		btnSandia = new JButton("Sandia");
		//btnBanana.setIcon(new javax.swing.ImageIcon("banana.jpg")); 
		btnSandia.setBounds(380, 200, 80, 25);
		add(btnSandia);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(100, 300, 117, 25);
		add(btnAceptar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(242, 300, 117, 25);
		add(btnCancelar);
		setSize(500, 400);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(390, 107, 89, 23);
		add(btnLimpiar);
		
		initEvents();

	}
	
	private void initEvents() {
		btnCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
//				Cerrar la ventana y volver al menu principal
				JFrame configFrame = (JFrame)getParent().getParent().getParent().getParent();
				configFrame.dispose();
				JFrame mainPage = new MainFrame();
				mainPage.setVisible(true);
			}
		});
		
		btnAceptar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				float valorPremio;
				int Nromaquina;
				List<String> combinacionPremio;
				try {
					valorPremio = Float.parseFloat(valorPremioText.getText());
					Nromaquina = Integer.parseInt(nroMaquinaText.getText());
					String[] texto = combinacionText.getText().split("\n");
					combinacionPremio = Arrays.asList(texto);
					
					UserMessageView vistaRetorno = sistema.altaPremio(Nromaquina, combinacionPremio, valorPremio);
					
					JOptionPane.showMessageDialog(null, vistaRetorno.getMensaje());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Ocurrio un error al dar de alta el premio, revise los datos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});	
		
		btnLimpiar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				combinacionText.setText("");				
			}
		});
		
		btnSandia.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) { 
				combinacionText.append("Sandia\n");
				combinacionText.setLineWrap(true);
			}
		});	
		
		btnBanana.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				combinacionText.append("Banana\n");
				combinacionText.setLineWrap(true);
			}
		});	

		btnFrutilla.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				combinacionText.append("Frutilla\n");
				combinacionText.setLineWrap(true);
			}
		});	

		btnPera.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				combinacionText.append("Pera\n");
				combinacionText.setLineWrap(true);
			}
		});	
		
		btnManzana.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				combinacionText.append("Manzana\n");
				combinacionText.setLineWrap(true);
			}
		});	
	}
}
