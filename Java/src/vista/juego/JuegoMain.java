package vista.juego;

import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;

import vista.JugadaView;
import vista.MainFrame;
import vista.UserMessageView;
import controlador.Sistema;
import controlador.exceptions.MaquinaNoEncontradaException;
import controlador.exceptions.TragamonedasException;

public class JuegoMain extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	//private JPanel contentPane;

	private JTextField txtCreditoActual;
	private JTextField txtCreditoACargar;
	private JTextField txtTragamonedasNbr;
	private JTextField txtPrecioJugada;
	private List<ImageIcon> imagenesFrutas;
	
	private JButton btnBuscar;
	private JButton btnSalir;
	private JButton btnIncrementarCredito;
	private JButton btnJugar;
	private JButton btnCobrarCredito;
	
	private JLabel lblImagen0;
	private JLabel lblImagen1;
	private JLabel lblImagen2;
	private JLabel lblImagen3;
	
	private Sistema sistema;
	
	public JuegoMain(Sistema sistema) {
		super();
		this.sistema = sistema;
		setTitle("Tragamonedas V1.0 - Jugar");	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		Ubicacion y tamaño de la ventana (JFrame) de configuracion, esta ventana
		setBounds(100, 100, 707, 524);

		//contentPane = new JPanel();
		//setContentPane(contentPane);
		getContentPane().setLayout(null);

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
		
		
		JLabel lblPrecioJugada = new JLabel("Costo Jugada");
		lblPrecioJugada.setBounds(431, 10, 110, 14);

		txtPrecioJugada = new JTextField();
		txtPrecioJugada.setBounds(551, 7, 86, 20);
		txtPrecioJugada.setColumns(10);
		txtPrecioJugada.setEditable(false);

		
        //--------------------------------
		//Seccion destinada a las imagenes de las frutas ( FALTA VER COMO HACER ESO )
        //--------------------------------
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(15, 61, 649, 2);

		lblImagen0 = new JLabel("");
		lblImagen0.setBounds(10, 73, 144, 141);
		getContentPane().add(lblImagen0);

		lblImagen1 = new JLabel("");
		lblImagen1.setBounds(164, 73, 144, 141);
		getContentPane().add(lblImagen1);

		lblImagen2 = new JLabel("");
		lblImagen2.setBounds(318, 73, 131, 141);
		getContentPane().add(lblImagen2);

		lblImagen3 = new JLabel("");
		lblImagen3.setBounds(459, 74, 131, 140);
		getContentPane().add(lblImagen3);
		
		
        //--------------------------------
		//Botones de juego, Cobrar credito y Salir
        //--------------------------------
		btnJugar = new JButton("Jugar");
		btnJugar.setBounds(10, 355, 94, 23);
		btnJugar.setEnabled(false);
		
		btnCobrarCredito = new JButton("Cobrar Credito");
		btnCobrarCredito.setBounds(182, 355, 166, 23);
		btnCobrarCredito.setEnabled(false);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(570, 355, 94, 23);
		

		

        //--------------------------------
		//Seccion de credito a incrementar y credito disponible
        //--------------------------------
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 384, 654, 2);
		
		JLabel lblCreditoActual = new JLabel("Credito Disponible");
		lblCreditoActual.setBounds(10, 421, 131, 21);
		
		txtCreditoActual = new JTextField();
		txtCreditoActual.setBounds(141, 421, 110, 20);
		txtCreditoActual.setColumns(10);
		txtCreditoActual.setEditable(false);

		JLabel lblCredito = new JLabel("Credito Adicional");
		lblCredito.setBounds(10, 453, 131, 20);
		
		txtCreditoACargar = new JTextField();
		txtCreditoACargar.setBounds(141, 454, 110, 18);
		txtCreditoACargar.setColumns(10);
		txtCreditoACargar.setEditable(false);
		
		btnIncrementarCredito = new JButton("Incrementar Credito");
		btnIncrementarCredito.setBounds(284, 452, 211, 23);
		btnIncrementarCredito.setEnabled(false);


		//Agrega objetos a la ventana
		getContentPane().add(lblMaquina);
		getContentPane().add(txtTragamonedasNbr);
		getContentPane().add(lblCreditoActual);
		getContentPane().add(txtCreditoActual);

		getContentPane().add(separator_1);
		getContentPane().add(btnJugar);
		getContentPane().add(btnCobrarCredito);
		getContentPane().add(btnSalir);

		getContentPane().add(separator);
		getContentPane().add(lblCredito);
		getContentPane().add(txtCreditoACargar);
		getContentPane().add(btnIncrementarCredito);
		
		getContentPane().add(btnBuscar);
		
		getContentPane().add(lblPrecioJugada);
		getContentPane().add(txtPrecioJugada);
		

		
		
	}
	
	private void initEvents() {
       
		btnBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Integer nbrMaquina = Integer.parseInt(txtTragamonedasNbr.getText());
					txtCreditoActual.setText(sistema.obtenerCredito(nbrMaquina).toString()   );
					txtPrecioJugada.setText(sistema.obtenerPrecioJugada(nbrMaquina).toString()   );
					
					//habilita botones si se encuentra la maquina
					txtCreditoACargar.setEditable(true);
					btnIncrementarCredito.setEnabled(true);
					btnCobrarCredito.setEnabled(true);
					btnJugar.setEnabled(true);
					
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Ocurrio un error de carga de credito adiciona, revise los datos ingresados");
				} catch (MaquinaNoEncontradaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}

			}
		});

		btnSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				Cerrar la ventana y volver al menu principal
				dispose();
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
					txtCreditoACargar.setText("0");
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Ocurrio un error de carga de credito adiciona, revise los datos ingresados");
				} catch (MaquinaNoEncontradaException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}

			}
		});
		
		btnCobrarCredito.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
					try {
						Integer nbrMaquina = Integer.parseInt(txtTragamonedasNbr.getText());					
						UserMessageView mensaje = sistema.cobrarCredito(nbrMaquina);
						JOptionPane.showMessageDialog(null, mensaje.getMensaje());
						txtCreditoActual.setText(sistema.obtenerCredito(nbrMaquina).toString() );
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Ocurrio un error en el cobro del credito disponible, revise los datos ingresados");
					} catch (MaquinaNoEncontradaException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					
				

			}
		});

		//-----------------------------------------------------------------
		// El jugar debiera devolver la combinacion y el premio no ??
		//-----------------------------------------------------------------
		btnJugar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {


				     try {
						Integer nbrMaquina;
						try {
							nbrMaquina = Integer.parseInt(txtTragamonedasNbr.getText());
							try {
								JugadaView jugada= sistema.jugarConMaquina(nbrMaquina);
								List<String> urlImagenes= sistema.obtenerURLFrutas(jugada);
								imagenesFrutas= actualizarFrutas(urlImagenes);
								mostrarFrutas(imagenesFrutas);
								
								//Confirmacion de premio
								if (jugada.getTienePremio()){
									JOptionPane.showMessageDialog(null, "Confirmar premio "+jugada.getPremioValor().toString()); 
								}
								txtCreditoActual.setText(sistema.obtenerCredito(nbrMaquina).toString()   );
								UserMessageView mensaje = sistema.validarEstadoMaquina(nbrMaquina);
								if (mensaje!=null){
									JOptionPane.showMessageDialog(null,mensaje.getMensaje());
								}
							} catch (TragamonedasException ey) {
								JOptionPane.showMessageDialog(null,ey.getMessage());
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null,e1.getMessage());
						}					
	
					
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Ocurrio un error al jugar, revise los datos ingresados");
					}

			}

		});
	
	
	}
	private List<ImageIcon> actualizarFrutas(List<String> urlImagenes) {
		List<ImageIcon> nuevaLista= new Vector<ImageIcon>();
		int i;
		
		for (i=0; i<urlImagenes.size();i++){
			nuevaLista.add(new ImageIcon(urlImagenes.get(i)));
		}
		return nuevaLista;
	}
	
	private void mostrarFrutas(List<ImageIcon> imagenesFrutas){
		int i;
		

		for (i=0; i<imagenesFrutas.size();i++){
			switch (i){
			case 0:
				lblImagen0.setIcon(imagenesFrutas.get(i));
				break;
			case 1:
				lblImagen1.setIcon(imagenesFrutas.get(i));
				break;
			case 2:
				lblImagen2.setIcon(imagenesFrutas.get(i));
				break;
			case 3:
				lblImagen3.setIcon(imagenesFrutas.get(i));
				break;
			default :
				//label
				break;		   
			}
		}
		
		
	}
	



}
