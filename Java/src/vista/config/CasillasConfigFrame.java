package vista.config;

import java.awt.Container;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import vista.TragamonedasView;
import controlador.Sistema;

public class CasillasConfigFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Container ventanaOrigen;
	private TragamonedasView tragamonedas;
	
	private List<CasillasConfigTabPane> configsCasillas = new Vector<CasillasConfigTabPane>();
	
	
	
	private Sistema sistema;

	/**
	 * Create the frame.
	 */
	public CasillasConfigFrame(Sistema sistema, TragamonedasView tragamonedas, Container ventanaOrigen) {
		this.sistema = sistema;
		this.ventanaOrigen = ventanaOrigen;
		this.tragamonedas = tragamonedas;
		
		initGUI();
	}
	
	private void initGUI() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 397);
		setResizable(false);
		
		JTabbedPane pane = new JTabbedPane();
		pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		for (int i = 0; i < tragamonedas.getCantidadCasillas() ; i++) {
			CasillasConfigTabPane panelCasilla = new CasillasConfigTabPane(sistema, ventanaOrigen, tragamonedas.getCodigoTragamoneda()); //TODO Definir argumentos
			
			configsCasillas.add(panelCasilla);
			
			pane.addTab("Casilla " + (i+1), panelCasilla);
		}
		
		setContentPane(pane);
		
	}
}
