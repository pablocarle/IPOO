package vista.juego;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;

import vista.MainFrame;
import vista.UserMessageView;
import controlador.Sistema;
import controlador.exceptions.MaquinaNoEncontradaException;

public class JuegoMain extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	private JTextField txtCreditoActual;
	private JTextField txtCreditoACargar;
	private JTextField txtTragamonedasNbr;
	
	private JButton btnBuscar;
	private JButton btnSalir;
	private JButton btnIncrementarCredito;
	private JButton btnJugar;
	private JButton btnCobrarCredito;
	
	private Sistema sistema;
	
	public JuegoMain(Sistema sistema) {
		super();
		setTitle("Tragamonedas V1.0 - Jugar");	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		Ubicacion y tamaño de la ventana (JFrame) de configuracion, esta ventana
		setBounds(100, 100, 640, 480);

		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setResizable(false);

		initGUI();
		initEvents();
		
	}
	
	private void initGUI() {

        //--------------------------------
		//Seccion de Numero de maquina y credito disponible
        //--------------------------------
		
		JLabel lblMaquina = new JLabel("Maquina");
		lblMaquina.setBounds(10, 7, 80, 21);
		
		txtTragamonedasNbr = new JTextField();
		txtTragamonedasNbr.setBounds(74, 7, 67, 20);
		txtTragamonedasNbr.setColumns(10);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(151, 6, 89, 23);
		
		JLabel lblCreditoActual = new JLabel("Credito Disponible");
		lblCreditoActual.setBounds(328, 1, 125, 33);
		
		txtCreditoActual = new JTextField();
		txtCreditoActual.setBounds(452, 7, 131, 20);
		txtCreditoActual.setColumns(10);
		txtCreditoActual.setEditable(false);
		
        //--------------------------------
		//Seccion destinada a las imagenes de las frutas ( FALTA VER COMO HACER ESO )
        //--------------------------------
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 44, 578, 2);
		
		
        //--------------------------------
		//Botones de juego, Cobrar credito y Salir
        //--------------------------------
		btnJugar = new JButton("Jugar");
		btnJugar.setBounds(10, 355, 94, 23);
		
		btnCobrarCredito = new JButton("Cobrar Credito");
		btnCobrarCredito.setBounds(182, 355, 166, 23);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(499, 355, 94, 23);
		

		

        //--------------------------------
		//Seccion de credito a incrementar
        //--------------------------------
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 384, 570, 2);
		
		JLabel lblIncrementarCredito = new JLabel("Credito a cargar");
		lblIncrementarCredito.setBounds(20, 392, 131, 14);
		
		JLabel lblCredito = new JLabel("Credito");
		lblCredito.setBounds(10, 421, 65, 14);
		
		txtCreditoACargar = new JTextField();
		txtCreditoACargar.setBounds(85, 419, 110, 18);
		txtCreditoACargar.setColumns(10);
		
		btnIncrementarCredito = new JButton("Incrementar Credito");
		btnIncrementarCredito.setBounds(217, 417, 211, 23);


		//Agrega objetos a la ventana
		contentPane.add(lblMaquina);
		contentPane.add(txtTragamonedasNbr);
		contentPane.add(lblCreditoActual);
		contentPane.add(txtCreditoActual);

		contentPane.add(separator_1);
		contentPane.add(btnJugar);
		contentPane.add(btnCobrarCredito);
		contentPane.add(btnSalir);

		contentPane.add(separator);
		contentPane.add(lblCredito);
		contentPane.add(txtCreditoACargar);
		contentPane.add(lblIncrementarCredito);
		contentPane.add(btnIncrementarCredito);
		
		contentPane.add(btnBuscar);
		
		
	}
	
	private void initEvents() {
       
		btnBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Integer nbrMaquina = Integer.parseInt(txtTragamonedasNbr.getText());
					txtCreditoActual.setText(sistema.obtenerCredito(nbrMaquina).toString()   );
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Ocurrio un error de carga de credito adiciona, revise los datos ingresados");
				} catch (MaquinaNoEncontradaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				repaint();

			}
		});

		btnSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				Cerrar la ventana y volver al menu principal
				JFrame configFrame = (JFrame)getParent().getParent();//.getParent().getParent();
				configFrame.dispose();
				JFrame mainPage = new MainFrame(sistema);
				mainPage.setVisible(true);
				
			}
		});
		
		
		btnIncrementarCredito.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Integer nbrMaquina = Integer.parseInt(txtTragamonedasNbr.getText());
					Float creditoAdicional = Float.parseFloat(txtCreditoACargar.getText());
					UserMessageView mensaje = sistema.cargarCredito(nbrMaquina, creditoAdicional);
					JOptionPane.showMessageDialog(null, mensaje.getMensaje());
					txtCreditoActual.setText(sistema.obtenerCredito(nbrMaquina).toString()   );
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Ocurrio un error de carga de credito adiciona, revise los datos ingresados");
				} catch (MaquinaNoEncontradaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				repaint();

			}
		});
		
		btnCobrarCredito.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Integer nbrMaquina = Integer.parseInt(txtTragamonedasNbr.getText());					
					UserMessageView mensaje = sistema.cobrarCredito(nbrMaquina);
					JOptionPane.showMessageDialog(null, mensaje.getMensaje());
					txtCreditoActual.setText("0");
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Ocurrio un error en el cobro del credito disponible, revise los datos ingresados");
				}
				repaint();

			}
		});

		//-----------------------------------------------------------------
		// El jugar debiera devolver la combinacion y el premio no ??
		//-----------------------------------------------------------------
		btnJugar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Integer nbrMaquina = Integer.parseInt(txtTragamonedasNbr.getText());					
					sistema.jugarConMaquina(nbrMaquina);
					JOptionPane.showMessageDialog(null, "Jugar");

					/*
					//Confirmacion de premio
					int seguro =JOptionPane.showConfirmDialog(null, "Confirma el premio?"); 
					if (seguro == 0)
						xxxxxx
					*/	
				
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Ocurrio un error al jugar, revise los datos ingresados");
				}

			}
		});
	
	
	}
}
