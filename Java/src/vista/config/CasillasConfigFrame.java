package vista.config;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import vista.TragamonedasView;
import modelo.Fruta;
import controlador.Sistema;

public class CasillasConfigFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private JList<Fruta> listaOrigen;
	private JList<Fruta> listaDestino;
	
	private JButton pasarDestino;
	private JButton quitarDestino;
	
	private Sistema sistema;
	private JButton btnAceptar;
	private JButton btnCancelar;

	/**
	 * Create the frame.
	 */
	public CasillasConfigFrame(Sistema sistema, TragamonedasView tragamonedas) {
		this.sistema = sistema;
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		initGUI();
		initEvents();
	}
	
	private void initGUI() {

		listaOrigen = new JList<Fruta>();
		listaOrigen.setBounds(12, 39, 158, 170);
		contentPane.add(listaOrigen);
//		Lista origen se inicializa con todas las frutas disponibles en el sistema
		listaOrigen.setListData((Vector<Fruta>)sistema.obtenerListaFrutas());
		
		pasarDestino = new JButton(">");
		pasarDestino.setBounds(182, 35, 44, 25);
		contentPane.add(pasarDestino);
		
		listaDestino = new JList<Fruta>();
		listaDestino.setBounds(238, 39, 143, 170);
		contentPane.add(listaDestino);
		
		quitarDestino = new JButton("<");
		quitarDestino.setBounds(182, 72, 44, 25);
		contentPane.add(quitarDestino);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(53, 221, 117, 25);
		contentPane.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(238, 221, 117, 25);
		contentPane.add(btnCancelar);
	}
	
	private void initEvents() {
		pasarDestino.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listaDestino.setListData(listaOrigen.getSelectedValuesList().toArray(new Fruta[0]));
				
			}
		});
		
		quitarDestino.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listaDestino.setListData(new Fruta[0]);		
			}
		});
		
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
