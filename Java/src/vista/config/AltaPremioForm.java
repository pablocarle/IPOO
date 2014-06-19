package vista.config;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class AltaPremioForm extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField valorPremioText;
	private JTextField nroMaquinaText;
	private JTextArea  combinacionText;
	private JButton btnBuscar;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JButton btnLimpiar;
	private JButton btnPoner;
	private JButton btnSacar;
	private JList ListadoFrutas;
	private JList<String> ListadoCasillas;
	private Sistema sistema;
	private Vector<Fruta> Listado;
	DefaultListModel ListaMod1 = new DefaultListModel();
	DefaultListModel ListaMod2 = new DefaultListModel();

	public AltaPremioForm(final Sistema sistema) {
		super();
		this.sistema = sistema;
		initGUI();
	}

	public void initGUI() {
	
		setLayout(null);

		JLabel lblnroMaquina = new JLabel("Nro Tragamonedas");
		lblnroMaquina.setBounds(80, 30, 202, 15);
		add(lblnroMaquina);
		
		JLabel lblvalorPremio = new JLabel("Valor del Premio");
		lblvalorPremio.setBounds(100, 250, 202, 15);
		add(lblvalorPremio);
		
		ListadoFrutas = new JList();
		ListadoFrutas.setBounds(80,70,100,140);
		ListadoFrutas.setEnabled(true);
		add(ListadoFrutas);
		
		ListadoCasillas = new JList();
		ListadoCasillas.setBounds(270,70,100,140);
		ListadoCasillas.setEnabled(true);
		add(ListadoCasillas);
		
		valorPremioText = new JTextField();
		valorPremioText.setBounds(200,250, 114, 19);
		add(valorPremioText);
		valorPremioText.setColumns(10);

		nroMaquinaText = new JTextField();
		nroMaquinaText.setBounds(220, 28, 20, 19);
		add(nroMaquinaText);
		nroMaquinaText.setColumns(10);

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
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(270, 30, 80, 15);
		add(btnBuscar);
		
		btnPoner = new JButton("Poner");
		btnPoner.setBounds(185,100, 80, 15);
		add(btnPoner);
		
		btnSacar = new JButton("Sacar");
		btnSacar.setBounds(185,150, 80, 15);
		add(btnSacar);
		
		initEvents();

	}
	
	private void initEvents() {
		btnCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
//				Cerrar la ventana y volver al menu principal
				JFrame configFrame = (JFrame)getParent().getParent().getParent().getParent();
				configFrame.dispose();
				JFrame mainPage = new MainFrame(sistema);
				mainPage.setVisible(true);
			}
		});
		
		btnAceptar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				float valorPremio;
				int Nromaquina;
				Vector<String> combinacionPremio = new Vector<String>();
				try {
					valorPremio = Float.parseFloat(valorPremioText.getText());
					Nromaquina = Integer.parseInt(nroMaquinaText.getText());
					
					for(int i=0;i<ListaMod2.size();i++){
						combinacionPremio.addElement(ListaMod2.elementAt(i).toString());
					}
					
					UserMessageView vistaRetorno = sistema.altaPremio(Nromaquina, combinacionPremio, valorPremio);
					
					JOptionPane.showMessageDialog(null, vistaRetorno.getMensaje());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Ocurrio un error al dar de alta el premio, revise los datos", "Error", JOptionPane.ERROR_MESSAGE);
				}
//				limpiar();
			}
		});	
		
		btnLimpiar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ListaMod2.removeAllElements();
				ListadoCasillas.setModel(ListaMod2);		
			}
		});
		
		btnBuscar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) { 
				ListaMod1.removeAllElements();
				ListadoFrutas.setModel(ListaMod1);
				Listado = (Vector<Fruta>) sistema.obtenerListaFrutas();
				for (int i =0;i<Listado.size();i++){
					ListaMod1.addElement(Listado.get(i).getNombre());
				}
				ListadoFrutas.setModel(ListaMod1);

			};
		});	
		
		btnPoner.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) { 
				String Seleccion = (String) ListadoFrutas.getSelectedValue();
				ListaMod2.addElement(Seleccion);
				ListadoCasillas.setModel(ListaMod2);
			}
		});	
		
		btnSacar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int selec = ListadoCasillas.getSelectedIndex();
				if (selec >= 0){
					ListaMod2.removeElementAt(selec);
				}
			}
		});	

	}
	
	private void limpiar() {
		ListaMod2.removeAllElements();
		ListadoCasillas.setModel(ListaMod2);
	}
}
