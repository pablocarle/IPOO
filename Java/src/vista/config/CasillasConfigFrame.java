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

	/**
	 * Create the frame.
	 */
	public CasillasConfigFrame(Sistema sistema, TragamonedasView tragamonedas) {
		this.sistema = sistema;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		initGUI();
		initEvents();
	}
	
	private void initGUI() {

		listaOrigen = new JList<Fruta>();
		listaOrigen.setBounds(22, 39, 96, 11);
		contentPane.add(listaOrigen);
//		Lista origen se inicializa con todas las frutas disponibles en el sistema
		listaOrigen.setListData((Vector<Fruta>)sistema.obtenerListaFrutas());
		
		pasarDestino = new JButton(">");
		pasarDestino.setBounds(182, 35, 44, 25);
		contentPane.add(pasarDestino);
		
		listaDestino = new JList<Fruta>();
		listaDestino.setBounds(293, 39, 96, 11);
		contentPane.add(listaDestino);
	}
	
	private void initEvents() {
		pasarDestino.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listaDestino.add(listaOrigen.getSelectedValue());
				
			}
		});
	}
}
