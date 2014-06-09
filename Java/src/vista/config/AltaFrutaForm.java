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
import controlador.Sistema;

public class AltaFrutaForm extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Sistema sistema;
	
	private JTextField txtNombre;
	private JTextField txtArchivo;
	
	private JButton btnAceptar;
	private JButton btnCancelar;

	/**
	 * Create the panel.
	 */
	public AltaFrutaForm(Sistema sistema) {
		
		this.sistema = sistema;
		setLayout(null);
		
		initGUI();
		
		initEvents();

	}

	private void initEvents() {
		
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (sistema.agregarFruta(txtNombre.getText(), txtArchivo.getText())) {
					JOptionPane.showMessageDialog(null, "Fruta " + txtNombre.getText() + " generada correctamente");
				} else {
					JOptionPane.showMessageDialog(null, "La fruta ya existe");
				}
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame configFrame = (JFrame)getParent().getParent().getParent().getParent();
				configFrame.dispose();
				JFrame mainPage = new MainFrame(sistema);
				mainPage.setVisible(true);
			}
		});
		
	}

	private void initGUI() {
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(12, 12, 70, 15);
		add(lblNombre);
		
		JLabel lblArchivoImagen = new JLabel("Archivo Imagen");
		lblArchivoImagen.setBounds(12, 39, 107, 15);
		add(lblArchivoImagen);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(324, 10, 114, 19);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		txtArchivo = new JTextField();
		txtArchivo.setBounds(324, 37, 114, 19);
		add(txtArchivo);
		txtArchivo.setColumns(10);
		
		JButton btnAceptar = new JButton("Cancelar");
		btnAceptar.setBounds(226, 90, 117, 25);
		add(btnAceptar);
		
		JButton btnCancelar = new JButton("Aceptar");

		btnCancelar.setBounds(97, 90, 117, 25);
		add(btnCancelar);
	}
}
