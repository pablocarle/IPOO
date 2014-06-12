package vista.config;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import modelo.Fruta;
import controlador.Sistema;

public class CasillasConfigTabPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JList<Fruta> listaOrigen;
	private JList<Fruta> listaDestino;
		
	private JButton pasarDestino;
	private JButton quitarDestino;
	
	private JButton btnAceptar;
	private JButton btnCancelar;
	
	private Sistema sistema;
	private Container ventanaOrigen;
	private int codigoTragamonedas;

	/**
	 * Create the panel.
	 * @param ventanaOrigen 
	 * @param codigoTragamonedas 
	 */
	public CasillasConfigTabPane(Sistema sistema, Container ventanaOrigen, int codigoTragamonedas) {

		this.ventanaOrigen = ventanaOrigen;
		this.sistema = sistema;
		this.codigoTragamonedas = codigoTragamonedas;
		
		setLayout(null);
		
		initGUI();
		initEvents();
		
	}
	
	private void initGUI() {
		listaOrigen = new JList<Fruta>();
		listaOrigen.setBounds(12, 39, 158, 170);
		this.add(listaOrigen);
//		Lista origen se inicializa con todas las frutas disponibles en el sistema
		listaOrigen.setListData((Vector<Fruta>)sistema.obtenerListaFrutas());
		
		pasarDestino = new JButton(">");
		pasarDestino.setBounds(182, 35, 44, 25);
		this.add(pasarDestino);
		
		listaDestino = new JList<Fruta>();
		listaDestino.setBounds(238, 39, 143, 170);
		this.add(listaDestino);
		
		quitarDestino = new JButton("<");
		quitarDestino.setBounds(182, 72, 44, 25);
		this.add(quitarDestino);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(53, 221, 117, 25);
		this.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(238, 221, 117, 25);
		this.add(btnCancelar);
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
				listaDestino.setListData(new Fruta[1]);	
			}
		});
		
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				Validar que todas las casillas esten cargadas
				boolean canContinue = true;
				
				JTabbedPane tabPanePadre = (JTabbedPane) getParent();
				
				for (int i = 0; i < tabPanePadre.getTabCount(); i++) {
					
					CasillasConfigTabPane unTabPane = (CasillasConfigTabPane) tabPanePadre.getComponent(i);
					List<Fruta> frutasSeleccionasUnaCasilla = unTabPane.obtenerConfigCasilla();
					if (frutasSeleccionasUnaCasilla.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Debe cargar la configuracion de la casilla " + (i+1));
						canContinue = false;
						break;
					}
				}
				
				if (canContinue) {
					List<List<Fruta>> listaCasillas = new ArrayList<List<Fruta>>();
					for (int i = 0; i < tabPanePadre.getTabCount(); i++) {
						
						CasillasConfigTabPane unTabPane = (CasillasConfigTabPane) tabPanePadre.getComponent(i);
						List<Fruta> frutasSeleccionasUnaCasilla = unTabPane.obtenerConfigCasilla();
						listaCasillas.add(frutasSeleccionasUnaCasilla);
						
					}
					
					if (sistema.iniciarCasillas(codigoTragamonedas, listaCasillas)) {
						JOptionPane.showMessageDialog(null, "Tragamonedas creado correctamente con codigo " + codigoTragamonedas);
//						Cerrar la ventana
						JFrame casillasConfigFrame = (JFrame) getParent().getParent().getParent().getParent();
						casillasConfigFrame.dispose();
						ventanaOrigen.setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(null, "Ocurrio un problema creando el tragamonedas");
						sistema.eliminarTragamonedas(codigoTragamonedas);
					}
				}
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				Cerrar la ventana
				JFrame casillasConfigFrame = (JFrame) getParent().getParent().getParent().getParent();
				casillasConfigFrame.dispose();
				sistema.eliminarTragamonedas(codigoTragamonedas); //Siempre existe
				ventanaOrigen.setEnabled(true);
			}
		});
	}
	
	public List<Fruta> obtenerConfigCasilla() {
		for (int i = 0; i < listaDestino.getComponentCount(); i++) {
			listaDestino.setSelectedIndex(i);
		}
		return listaDestino.getSelectedValuesList();
	}
}
