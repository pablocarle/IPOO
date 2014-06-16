package vista.config;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.Fruta;
import vista.MainFrame;
import vista.UserMessageView;
import controlador.Sistema;

/**
 * TODO revisar tema de baja de premio en {@link Sistema}
 * 
 * @author IPOO
 *
 */
public class BajaPremioForm extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	private JButton btnBuscar;
	private Sistema sistema;
	private JList<String> ListadoPremios;
	private Vector<Fruta> Listado;
	DefaultListModel ListaMod1 = new DefaultListModel();

	public BajaPremioForm(final Sistema sistema) {
		super();
		this.sistema = sistema;
		initGUI();
	}

	public void initGUI() {
	
		setLayout(null);
	
		JLabel lblnroMaquina = new JLabel("Nro Tragamonedas");
		lblnroMaquina.setBounds(80, 30, 202, 15);
		add(lblnroMaquina);
		
		nroMaquinaText = new JTextField();
		nroMaquinaText.setBounds(220, 28, 20, 19);
		add(nroMaquinaText);
		nroMaquinaText.setColumns(10);
		
		ListadoPremios = new JList();
		ListadoPremios.setBounds(40,70,400,200);
		ListadoPremios.setEnabled(true);
		add(ListadoPremios);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(100, 300, 117, 25);
		add(btnAceptar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(242, 300, 117, 25);
		add(btnCancelar);
		setSize(500, 400);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(270, 30, 80, 15);
		add(btnBuscar);
		
		initEvents();

	}
	
	private void initEvents() {
		btnCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
//				Cerrar la ventana y volver al menu principal
				limpiar();
				JFrame configFrame = (JFrame)getParent().getParent().getParent().getParent();
				configFrame.dispose();
				JFrame mainPage = new MainFrame(sistema);
				mainPage.setVisible(true);
			}
		});
		
		btnAceptar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int nroMaquina;
				List<String> combinacionPremio;
				try {
					nroMaquina = Integer.parseInt(nroMaquinaText.getText());
					if (ListadoPremios.getSelectedIndex() >= 0){
						String[] texto = ListadoPremios.getSelectedValue().toString().split("    ");
						combinacionPremio = Arrays.asList(texto);
						UserMessageView vistaRetorno = sistema.bajaPremio(nroMaquina, combinacionPremio);
						List<String> lista=sistema.consultarPremios(nroMaquina);
						if (lista != null){
							limpiar();
							for (int i =0;i<lista.size();i++){
								ListaMod1.addElement(lista.get(i));
							}
							ListadoPremios.setModel(ListaMod1);
						}
						JOptionPane.showMessageDialog(null, vistaRetorno.getMensaje());
					}else{
						JOptionPane.showMessageDialog(null, "Para dar de baja debe seleccionar un elemento", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Ocurrio un error al dar de alta el premio, revise los datos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});	
		
		btnBuscar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int nroMaquina;	
				try{
					nroMaquina = Integer.parseInt(nroMaquinaText.getText());				
					List<String> lista=sistema.consultarPremios(nroMaquina);
					if (lista==null||lista.isEmpty()){
						JOptionPane.showMessageDialog(null, "No existen premios cargados para esta maquina", "Aviso", JOptionPane.WARNING_MESSAGE);
					}else{
						for (int i =0;i<lista.size();i++){
							ListaMod1.addElement(lista.get(i));
						}
						ListadoPremios.setModel(ListaMod1);
					}
				}catch (NumberFormatException a){
					JOptionPane.showMessageDialog(null, "Error en el ingreso del numero de maquina", "Error", JOptionPane.ERROR_MESSAGE);
				};
			}
		});
	}
	
	private void limpiar() {
		ListaMod1.removeAllElements();
		ListadoPremios.setModel(ListaMod1);
	}
}