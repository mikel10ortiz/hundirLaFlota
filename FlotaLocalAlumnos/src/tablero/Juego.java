package tablero;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Juego {
	
	/**
	 * Implementa el juego 'Hundir la flota' mediante una interfaz grafica (GUI)
	 */
	
	/** Estados posibles de las casillas del tablero */
	private static final int AGUA = -1, TOCADO = -2, HUNDIDO = -3;
	
	/** Parametros por defecto de una partida */
	private static final int NUMFILAS=8, NUMCOLUMNAS=8, NUMBARCOS=6;

	private Partida partida = null;     // Objeto con los datos de la partida en juego
	private JFrame frame = null;        // Tablero de juego
	private JLabel estado = null;       // Texto en el panel de estado
	private JButton buttons[][] = null; // Botones asociados a las casillas de la partida
	
	/** Atributos de la partida en juego */
	private int numFilas, numColumnas, numBarcos, quedan, disparos;
	
	/**
	 * Programa principal. Crea y lanza un nuevo juego
	 * @param args	no se utiliza
	 */
	public static void main(String[] args) {
		Juego juego = new Juego();
		juego.ejecuta();
	} // end main
	
	/**
	 * Lanza una nueva hebra que establece los atributos del juego y dibuja la interfaz grafica: tablero
	 */
	private void ejecuta() {
        // POR IMPLEMENTAR
		buttons = new JButton[NUMFILAS][NUMCOLUMNAS];
		dibujaTablero();
	} // end ejecuta
	
	/**
	 * Dibuja el tablero de juego y crea la partida inicial
	 */
	private void dibujaTablero() {
        // POR IMPLEMENTAR
		frame = new JFrame("Hundir la flota");
		Container contenedor = frame.getContentPane();
		JMenuBar barraMenu = new JMenuBar();
		contenedor.add(barraMenu);
		JMenu menuOpciones = new JMenu("Opciones");
		JMenuItem itemIniciar = new JMenuItem("Iniciar partida");
		barraMenu.add(menuOpciones);
		menuOpciones.add(itemIniciar);
		JMenuItem itemLimpiar = new JMenuItem("Limpiar tablero");
		menuOpciones.add(itemLimpiar);
		JMenuItem itemSalir = new JMenuItem("Salir del juego");
		menuOpciones.add(itemSalir);
		
		for(int i = 0; i < NUMFILAS; i++){
			for(int j = 0; j < NUMCOLUMNAS; j++){
				JButton boton = new JButton();
				boton.addActionListener(new ButtonListener());
				boton.setActionCommand(Integer.toString(i) + "," + Integer.toString(j));
				boton.setPreferredSize(new Dimension(25, 25));
				buttons[i][j] = boton;
			}
		}
		
		JPanel panelTablero = new JPanel();
		
	} // end dibujaTablero
	
	/**
	 * Anyade el menu de opciones del juego
	 */
	private void anyadeMenu() {
        // POR IMPLEMENTAR
	} // end anyadeMenu

	/**
	 * Anyade el panel con las casillas del mar y sus etiquetas.
	 * Cada casilla sera un boton con su correspondiente escuchador
	 * @param nf	numero de filas
	 * @param nc	numero de columnas
	 */
	private void anyadeGrid(int nf, int nc) {
        // POR IMPLEMENTAR		
	} // end anyadeGrid
	

	/**
	 * Anyade el panel de estado al tablero
	 * @param cadena	cadena inicial del panel de estado
	 */
	private void anyadePanelEstado(String cadena) {	
        // POR IMPLEMENTAR
	} // end anyadePanel Estado
	
	/**
	 * Cambia la cadena mostrada en el panel de estado
	 * @param cadenaEstado	nuevo estado
	 */
	private void cambiaEstado(String cadenaEstado) {
        // POR IMPLEMENTAR
	} // end cambiaEstado
	
	/**
	 * Muestra la solucion de la partida y marca la partida como finalizada
	 */
	private void muestraSolucion() {
        // POR IMPLEMENTAR
	} // end muestraSolucion
	
	/**
	 * Limpia las casillas del tablero
	 */
	private void limpiaTablero() {
        // POR IMPLEMENTAR
	} // end limpiaTablero

	
/******************************************************************************************/
/*********************  CLASE INTERNA MenuListener ****************************************/
/******************************************************************************************/
	
	/**
	 * Clase interna que escucha el menu de Opciones del tablero
	 * 
	 */
	private class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
	        // POR IMPLEMENTAR	
		} // end actionPerformed
		
	} // end class MenuListener
	

/******************************************************************************************/
/*********************  CLASE INTERNA ButtonListener **************************************/
/******************************************************************************************/
	/**
	 * Clase interna que escucha cada uno de los botones del tablero
	 * Para poder identificar el boton que ha generado el evento se pueden usar las propiedades
	 * de los componentes, apoyandose en los metodos putClientProperty y getClientProperty
	 */
	private class ButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
	        // POR IMPLEMENTAR
		} // end actionPerformed

		
	} // end class ButtonListener
	
	

} // end class Juego
