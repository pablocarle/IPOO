package vista.config;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vista.MainFrame;
import vista.UserMessageView;
import controlador.Sistema;

public class CargaCreditoForm extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTragamonedasNbr;
	private JTextField txtCreditoACargar;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private Sistema sistema;

	public CargaCreditoForm(Sistema sistema) {
		this.sistema = sistema;
		setLayout(null);
		
		initGUI();
		initEvents();
		
	}
	
	private void initGUI(){
		JLabel lblTragamonedas = new JLabel("Tragamonedas");
		lblTragamonedas.setBounds(12, 12, 123, 15);
		add(lblTragamonedas);
		
		txtTragamonedasNbr = new JTextField();
		txtTragamonedasNbr.setBounds(324, 10, 114, 19);
		add(txtTragamonedasNbr);
		txtTragamonedasNbr.setColumns(10);
		
		JLabel lblCreditoACargar = new JLabel("Credito a cargar");
		lblCreditoACargar.setBounds(12, 59, 123, 15);
		add(lblCreditoACargar);
		
		txtCreditoACargar = new JTextField();
		txtCreditoACargar.setBounds(324, 57, 114, 19);
		add(txtCreditoACargar);
		txtCreditoACargar.setColumns(10);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(224, 135, 117, 25);
		add(btnCancelar);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(95, 135, 117, 25);
		add(btnAceptar);
	}
	
	private void initEvents(){
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
		
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Integer nbrMaquina = Integer.parseInt(txtTragamonedasNbr.getText());
					Float creditoAdicional = Float.parseFloat(txtCreditoACargar.getText());
					UserMessageView mensaje = sistema.cargarCredito(nbrMaquina, creditoAdicional);
					JOptionPane.showMessageDialog(null, mensaje.getMensaje());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Ocurrio un error de carga de credito adiciona, revise los datos ingresados");
				}
			}
		});
	}
}
