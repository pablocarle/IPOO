package vista.config;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vista.MainFrame;

public class AltaTragamonedaForm extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField precioJugadaText;
	private JTextField recaudacionIniText;
	private JTextField recaudacionMinText;
	private JTextField cantCasillasText;
	private JButton btnAceptar;
	private JButton btnCancelar;

	/**
	 * Create the panel.
	 */
	public AltaTragamonedaForm() {
		setLayout(null);
		
		JLabel lblPrecioDeJugada = new JLabel("Precio de Jugada");
		lblPrecioDeJugada.setBounds(12, 30, 202, 15);
		add(lblPrecioDeJugada);
		
		JLabel lblRecaudacionInicial = new JLabel("Recaudacion Inicial");
		lblRecaudacionInicial.setBounds(12, 57, 135, 15);
		add(lblRecaudacionInicial);
		
		JLabel lblRecaudacionMinima = new JLabel("Recaudacion Minima");
		lblRecaudacionMinima.setBounds(12, 84, 144, 15);
		add(lblRecaudacionMinima);
		
		JLabel lblCantidadDeCasillas = new JLabel("Cantidad de casillas");
		lblCantidadDeCasillas.setBounds(12, 111, 144, 15);
		add(lblCantidadDeCasillas);
		
		precioJugadaText = new JTextField();
		precioJugadaText.setBounds(324, 28, 114, 19);
		add(precioJugadaText);
		precioJugadaText.setColumns(10);
		
		recaudacionIniText = new JTextField();
		recaudacionIniText.setBounds(324, 55, 114, 19);
		add(recaudacionIniText);
		recaudacionIniText.setColumns(10);
		
		recaudacionMinText = new JTextField();
		recaudacionMinText.setBounds(324, 82, 114, 19);
		add(recaudacionMinText);
		recaudacionMinText.setColumns(10);
		
		cantCasillasText = new JTextField();
		cantCasillasText.setToolTipText("Catidad de casillas para la maquina");
		cantCasillasText.setBounds(324, 109, 114, 19);
		add(cantCasillasText);
		cantCasillasText.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(97, 164, 117, 25);
		add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				Cerrar la ventana y volver al menu principal
				getParent().setVisible(false);
				JFrame mainPage = new MainFrame();
				mainPage.setVisible(true);
			}
		});
		btnCancelar.setBounds(242, 164, 117, 25);
		add(btnCancelar);
		
	}
}
