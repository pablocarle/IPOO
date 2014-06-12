package vista.config;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vista.MainFrame;
import vista.TragamonedasView;
import controlador.Sistema;
import controlador.exceptions.TragamonedasCreacionException;

public class AltaTragamonedaForm extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField precioJugadaText;
	private JTextField recaudacionIniText;
	private JTextField recaudacionMinText;
	private JTextField cantCasillasText;
	
	private JCheckBox defaultLoad;
	
	private JLabel lblPrecioDeJugada;
	private JLabel lblRecaudacionInicial;
	private JLabel lblRecaudacionMinima;
	private JLabel lblCantidadDeCasillas;
	
	private JButton btnAceptar;
	private JButton btnCancelar;
	private Sistema sistema;

	/**
	 * Create the panel.
	 */
	public AltaTragamonedaForm(final Sistema sistema) {
		
		this.sistema = sistema;
		setLayout(null);
		
		initGUI();
		initEvents();
		
	}
	
	private void initGUI() {
//		Labels
		lblPrecioDeJugada = new JLabel("Precio de Jugada");
		lblPrecioDeJugada.setBounds(12, 30, 202, 15);
		add(lblPrecioDeJugada);
		
		lblRecaudacionInicial = new JLabel("Recaudacion Inicial");
		lblRecaudacionInicial.setBounds(12, 57, 135, 15);
		add(lblRecaudacionInicial);
		
		lblRecaudacionMinima = new JLabel("Recaudacion Minima");
		lblRecaudacionMinima.setBounds(12, 84, 144, 15);
		add(lblRecaudacionMinima);
		
		lblCantidadDeCasillas = new JLabel("Cantidad de casillas");
		lblCantidadDeCasillas.setBounds(12, 111, 144, 15);
		add(lblCantidadDeCasillas);
		
//		Campos de texto
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
		
//		Check de carga default del tragamonedas
		defaultLoad = new JCheckBox("Carga default");
		defaultLoad.setBounds(324, 136, 114, 19);
		add(defaultLoad);
		
//		Botones
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(97, 160, 117, 25);
		add(btnAceptar);
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(242, 160, 117, 25);
		add(btnCancelar);
	}
	
	private void initEvents() {
//		Aceptar intenta crear un nuevo tragamonedas
		btnAceptar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				//llama a modelo para crear tragamonedas
				TragamonedasView vistaRetorno;
				
				try	{
					
					if (defaultLoad.isSelected()) {
						vistaRetorno = sistema.crearTragamonedas(Float.parseFloat(precioJugadaText.getText()), Float.parseFloat(recaudacionIniText.getText()), Float.parseFloat(recaudacionMinText.getText()), Integer.parseInt(cantCasillasText.getText()), true );
						JOptionPane.showMessageDialog(null, "Se genero correctamente el tragamonedas con codigo: " + vistaRetorno.getCodigoTragamoneda());
					} else {
						vistaRetorno = sistema.crearTragamonedas(Float.parseFloat(precioJugadaText.getText()), Float.parseFloat(recaudacionIniText.getText()), Float.parseFloat(recaudacionMinText.getText()), Integer.parseInt(cantCasillasText.getText()), false );
						
//						Mostrar ventana de configuracion de casillas, deshabilitar esta
						Container frame = getParent().getParent().getParent().getParent();
						frame.setEnabled(false);
						new CasillasConfigFrame(sistema, vistaRetorno, frame).setVisible(true);;
					}
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Formato de numero incorrecto, verifique los datos ingresados");
				} catch (TragamonedasCreacionException e) {			
					JOptionPane.showMessageDialog(null, "Ocurrio un error de creacion de tragamonedas, revise los datos ingresados");
				}
				limpiarCampos();		
						
			}
		});
		
		
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				Cerrar la ventana y volver al menu principal
				JFrame configFrame = (JFrame)getParent().getParent().getParent().getParent();
				configFrame.dispose();
				JFrame mainPage = new MainFrame(sistema);
				mainPage.setVisible(true);
			}
		});
	}
	
	private void limpiarCampos() {
		precioJugadaText.setText("");
		recaudacionIniText.setText("");
		recaudacionMinText.setText("");
		cantCasillasText.setText("");	
	}
}
