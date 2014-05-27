package vista;

import controlador.Sistema;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import vista.config.ConfigMain;
import vista.juego.JuegoMain;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lblTragamonedasV;
	private JButton btnConfiguracion;
	private JButton btnJugar;
	private Sistema sistema;

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {
		setResizable(false);
		setBounds(100, 100, 343, 197);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		Usamos coordenadas fijas
		getContentPane().setLayout(null);
		setTitle("Tragamonedas V1.0");
		
		initGUI();
		sistema = new Sistema();
		
		initEvents();

	}
	
	private void initGUI() {
		lblTragamonedasV = new JLabel("Tragamonedas V1.0");
		lblTragamonedasV.setBounds(100, 12, 192, 15);
		getContentPane().add(lblTragamonedasV);
		
		btnConfiguracion = new JButton("Configuracion");
		
		btnConfiguracion.setBounds(87, 63, 170, 25);
		getContentPane().add(btnConfiguracion);
		
		btnJugar = new JButton("Jugar");
		
		btnJugar.setBounds(87, 117, 170, 25);
		getContentPane().add(btnJugar);
	}
	
	private void initEvents() {
		btnConfiguracion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				Abrir ventana de configuracion
				JFrame configMain = new ConfigMain(sistema);
				configMain.setVisible(true);
				dispose();
			}
		});
		
		btnJugar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				Abrir ventana de juego
				JFrame juegoMain = new JuegoMain();
				juegoMain.setVisible(true);
				dispose();
			}
		});
	}
}
