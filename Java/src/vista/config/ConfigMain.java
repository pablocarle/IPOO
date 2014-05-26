package vista.config;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class ConfigMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel configFormPanel;

	private JPanel altaTragamonedas;
	private JPanel altaPremio;
	private JPanel bajaPremio;
	private JPanel cargaCredito;
	
	public ConfigMain() {
		setTitle("Tragamonedas V1.0 - Configuracion");
		setBounds(100, 100, 487, 309);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
		setBounds(0, 0, 1280, 1024);
		
		JButton btnAltaTragamonedas = new JButton("Alta Tragamonedas");
		btnAltaTragamonedas.setBounds(12, 12, 187, 25);
		btnAltaTragamonedas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Mostrar formulario de alta de tragamonedas
				configFormPanel = new AltaTragamonedaForm();
				configFormPanel.setBounds(12, 86, 461, 172);
				
				getContentPane().add(configFormPanel);
				configFormPanel.setVisible(true);
				repaint();
			}
		});
		getContentPane().add(btnAltaTragamonedas);
		
		JButton btnAltaPremio = new JButton("Alta Premios");
		btnAltaPremio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnAltaPremio.setBounds(12, 49, 187, 25);
		getContentPane().add(btnAltaPremio);
		
		JButton btnCargaDeCredito = new JButton("Carga de Credito");
		btnCargaDeCredito.setBounds(300, 12, 173, 25);
		getContentPane().add(btnCargaDeCredito);
		
		JButton btnBajaPremios = new JButton("Baja Premios");
		btnBajaPremios.setBounds(300, 49, 173, 25);
		getContentPane().add(btnBajaPremios);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(22, 86, 451, 4);
		getContentPane().add(separator);
//		
//		configFormPanel = new JPanel();
//		configFormPanel.setBounds(12, 86, 461, 172);
//		configFormPanel.setBackground(Color.BLUE);
//		getContentPane().add(configFormPanel);

	}
	
	private void initGUI() {
		
		
	}
	
	private void initEvents() {
		
	}
}
