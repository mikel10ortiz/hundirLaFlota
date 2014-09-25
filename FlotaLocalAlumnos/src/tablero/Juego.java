package tablero;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Juego {
	
	/**
	 * Implementa el juego 'Hundir la flota' mediante una interfaz grafica (GUI)
	 * HAGO UNA PRUEBA GUITHUBafdasdfaasdfasdfa
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
		SwingUtilities.invokeLater(new Runnable() {
			

			@Override
			public void run() {
				partida = new Partida(NUMFILAS, NUMCOLUMNAS, NUMBARCOS);
				buttons = new JButton[NUMFILAS][NUMCOLUMNAS];
				dibujaTablero();				
			}
		});
	} // end ejecuta
	
	/**
	 * Dibuja el tablero de juego y crea la partida inicial
	 */
	private void dibujaTablero() {
        // POR IMPLEMENTAR
		frame = new JFrame("Hundir la flota");	
		
		for(int i = 0; i < NUMFILAS; i++){
			for(int j = 0; j < NUMCOLUMNAS; j++){
				JButton boton = new JButton();
				boton.addActionListener(new ButtonListener());
				boton.putClientProperty("fila", i);
				boton.putClientProperty("columna", j);
//				boton.setActionCommand(Integer.toString(i) + "," + Integer.toString(j));
				boton.setPreferredSize(new Dimension(25, 25));
				boton.addActionListener(new ButtonListener());
				buttons[i][j] = boton;
			}
		}
		
		anyadeMenu();
		anyadeGrid(NUMFILAS, NUMCOLUMNAS);
		anyadePanelEstado("Inicio de partida");
		
		frame.pack();
		frame.setVisible(true);
		
	} // end dibujaTablero
	
	/**
	 * Anyade el menu de opciones del juego
	 */
	private void anyadeMenu() {
        // POR IMPLEMENTAR
		JMenuBar barraMenu = new JMenuBar();
		frame.setJMenuBar(barraMenu);
		JMenu menuOpciones = new JMenu("Opciones");
		JMenuItem itemIniciar = new JMenuItem("Iniciar partida");
		itemIniciar.setActionCommand("nueva");
		itemIniciar.addActionListener(new MenuListener());
		barraMenu.add(menuOpciones);
		menuOpciones.add(itemIniciar);
		JMenuItem itemLimpiar = new JMenuItem("Limpiar tablero");
		menuOpciones.add(itemLimpiar);
		JMenuItem itemSalir = new JMenuItem("Salir del juego");
		itemSalir.setActionCommand("salir");
		itemSalir.addActionListener(new MenuListener());
		menuOpciones.add(itemSalir);
	} // end anyadeMenu

	/**
	 * Anyade el panel con las casillas del mar y sus etiquetas.
	 * Cada casilla sera un boton con su correspondiente escuchador
	 * @param nf	numero de filas
	 * @param nc	numero de columnas
	 */
	private void anyadeGrid(int nf, int nc) {
        // POR IMPLEMENTAR	
		JPanel panelTablero = new JPanel();
		panelTablero.setLayout(new GridLayout(NUMFILAS + 1, NUMCOLUMNAS + 2));
		frame.getContentPane().add(panelTablero);
		//DIBUJA
		JLabel lbIndice = new JLabel();
		String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
		
		for(int i = 0; i < NUMFILAS + 1; i++){
			for(int j = 0; j < NUMCOLUMNAS + 2; j++){
				if(i == 0 && (j == 0 || j == NUMCOLUMNAS + 1)){
					lbIndice = new JLabel(" ");
					panelTablero.add(lbIndice);
				}
				if(i == 0 && j > 0 && j < NUMCOLUMNAS + 1){
					lbIndice = new JLabel(Integer.toString(j - 1));
					lbIndice.setAlignmentX(Component.CENTER_ALIGNMENT);
					panelTablero.add(lbIndice);
				}
				if(i > 0 && (j == 0 || j == NUMCOLUMNAS + 1)){
					lbIndice = new JLabel(letras[i - 1]);
					panelTablero.add(lbIndice);
				}
				
				if(i > 0 && j > 0 && j < NUMCOLUMNAS + 1){
					panelTablero.add(buttons[i - 1][j - 1]);
				}
			}
		}
	} // end anyadeGrid
	

	/**
	 * Anyade el panel de estado al tablero
	 * @param cadena	cadena inicial del panel de estado
	 */
	private void anyadePanelEstado(String cadena) {	
        // POR IMPLEMENTAR
		JPanel panelEstado = new JPanel();
		frame.getContentPane().add(panelEstado, BorderLayout.SOUTH);
		JLabel lbEstado = new JLabel(cadena);
		panelEstado.add(lbEstado);
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
		String[] solucion = partida.getSolucion();
//		RECORRER EL VECTOR Y MOSTRAR LA SOLUCION
		estado.setText("PARTIDA FINALIZADA");
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
			if(e.getActionCommand().equals("nueva")){
				ejecuta();
			}
			if(e.getActionCommand().equals("salir")){
				System.exit(0);
			}
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
			JButton botonPulsado = (JButton)e.getSource();
			int fila = (int) botonPulsado.getClientProperty("fila");
			int columna = (int)botonPulsado.getClientProperty("columna");
			
//			JOptionPane.showMessageDialog(null, "fila " + fila + " " + "columna " + columna);
			
			int resultadoCasilla = partida.pruebaCasilla(fila, columna);
			if(resultadoCasilla == AGUA){
				botonPulsado.setBackground(new Color(0,0,255));
			}
			else if(resultadoCasilla == TOCADO){
				botonPulsado.setBackground(new Color(255,112,40));
			}
			else if(resultadoCasilla == HUNDIDO){
				botonPulsado.setBackground(new Color(255,0,0));
			}
			else if(resultadoCasilla > 0){
				JOptionPane.showMessageDialog(null, "entra");
				int filaInicial;
				int colInicial;
				char orientacion;
				int tam;
				String infoBarco = partida.getBarco(resultadoCasilla);
				String cadenaCogida;
				String cadenaResto;
				
				cadenaCogida = infoBarco.substring(0,infoBarco.indexOf('#'));
				cadenaResto = infoBarco.substring(infoBarco.indexOf('#') + 1);
				filaInicial = Integer.parseInt(cadenaCogida);
				
				cadenaCogida = cadenaResto.substring(0,cadenaResto.indexOf('#'));
				cadenaResto = cadenaResto.substring(1, cadenaResto.indexOf('#') + 1);
				colInicial = Integer.parseInt(cadenaCogida);
					
				cadenaCogida = cadenaResto.substring(0,cadenaResto.indexOf('#'));
				cadenaResto = cadenaResto.substring(cadenaResto.indexOf('#'), cadenaResto.length());
				orientacion = cadenaCogida.charAt(0);
				
				tam = Integer.parseInt(cadenaResto);
			
				switch(orientacion){
				case 'H':
					for(int i = 0; i < tam; i++){
						buttons[filaInicial][colInicial + i].setBackground(new Color(255, 0, 0));						
					}
					break;
				case 'V':
					for(int i = 0; i < tam; i++){
						buttons[filaInicial + i][colInicial].setBackground(new Color(255, 0, 0));						
					}
					break;
				}
			}
		} // end actionPerformed

		
	} // end class ButtonListener
	
	

} // end class Juego
