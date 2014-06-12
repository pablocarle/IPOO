package vista.config;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import controlador.Sistema;

public class ConfigMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel altaTragamonedas;
	private JPanel altaPremio;
	private JPanel bajaPremio;
	private JPanel fruteria;
	
	private JButton btnAltaTragamonedas;
	private JButton btnAltaPremio;
	private JButton btnBajaPremios;
	private JButton btnFruteria;
	
	private Sistema sistema;
	
	public ConfigMain(Sistema sistema) {
		super();
		this.sistema = sistema;
		setTitle("Tragamonedas V1.0 - Configuracion");
		
//		Ubicacion y tamaño de la ventana (JFrame) de configuracion, esta ventana
		setBounds(100, 100, 500, 600);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
		
		initGUI();

		initEvents();
	}
	
	private void initGUI() {
//		Inicializa los JPane que tiene disponibles de configuracion (todos con el mismo size)
		altaTragamonedas = new AltaTragamonedaForm(sistema);
		altaTragamonedas.setBounds(12, 110, 761, 700);
		altaTragamonedas.setVisible(true);
		altaPremio = new AltaPremioForm(sistema);
		altaPremio.setBounds(12, 110, 761, 700);
		altaPremio.setVisible(false);
		bajaPremio = new BajaPremioForm(sistema);
		bajaPremio.setBounds(12, 110, 761, 700);
		bajaPremio.setVisible(false);
		fruteria = new AltaFrutaForm(sistema);
		fruteria.setBounds(12, 110, 761, 700);
		fruteria.setVisible(false);
		
		btnAltaTragamonedas = new JButton("Alta Tragamonedas");
		btnAltaTragamonedas.setBounds(12, 12, 187, 25);
		btnAltaPremio = new JButton("Alta Premios");
		btnAltaPremio.setBounds(12, 49, 187, 25);
		btnFruteria = new JButton("Fruteria");
		btnFruteria.setBounds(300, 12, 173, 25);
		btnBajaPremios = new JButton("Baja Premios");
		btnBajaPremios.setBounds(300, 49, 173, 25);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(22, 86, 451, 4);
		
		getContentPane().add(separator);
		getContentPane().add(btnBajaPremios);
		getContentPane().add(btnFruteria);
		getContentPane().add(btnAltaPremio);
		getContentPane().add(btnAltaTragamonedas);
		
		getContentPane().add(altaTragamonedas);
		getContentPane().add(altaPremio);
		getContentPane().add(bajaPremio);
		getContentPane().add(fruteria);
	}
	
	private void initEvents() {
		btnAltaTragamonedas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Mostrar formulario de alta de tragamonedas
//				configFormPanel = new AltaTragamonedaForm(sistema);
//				configFormPanel.setBounds(12, 110, 761, 222);
				altaTragamonedas.setVisible(true);
				altaPremio.setVisible(false);
				bajaPremio.setVisible(false);
				fruteria.setVisible(false);
				repaint();
			}
		});
		
		btnAltaPremio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Mostrar formulario de alta de premios
				altaTragamonedas.setVisible(false);
				altaPremio.setVisible(true);
				bajaPremio.setVisible(false);
				fruteria.setVisible(false);
			}
		});
		
		btnFruteria.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				Mostrar formulario de carga de credito
				altaTragamonedas.setVisible(false);
				altaPremio.setVisible(false);
				bajaPremio.setVisible(false);
				fruteria.setVisible(true);
			}
		});
		
		btnBajaPremios.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				Mostrar formulario de baja de premios
				altaTragamonedas.setVisible(false);
				altaPremio.setVisible(false);
				bajaPremio.setVisible(true);
				fruteria.setVisible(false);
			}
		});
	}
}
