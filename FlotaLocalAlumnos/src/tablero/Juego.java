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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Juego {
	
	/**
	 * Implementa el juego 'Hundir la flota' mediante una interfaz grafica (GUI)
	 * HAGO UNA PRUEBA GUITHUBafdasdfaasdfasdfa
	 */
	
	/** Estados posibles de las casillas del tablero */
	private static final int AGUA = -1, TOCADO = -2, HUNDIDO = -3;
	private static final Color AZUL = new Color(0,0,255);
	private static final Color ROJO = new Color(255,0,0);
	private static final Color AMARILLO = new Color(255,255,0);
	
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
				frame = new JFrame("Hundir la flota");	
				buttons = new JButton[NUMFILAS][NUMCOLUMNAS];	
				dibujaTablero();
				frame.pack();
				frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				
			}
		});
	} // end ejecuta
	
	/**
	 * Dibuja el tablero de juego y crea la partida inicial
	 */
	private void dibujaTablero() {
        // POR IMPLEMENTAR
		buttons = new JButton[NUMFILAS][NUMCOLUMNAS];
		for(int i = 0; i < NUMFILAS; i++){
			for(int j = 0; j < NUMCOLUMNAS; j++){
				JButton boton = new JButton();
				boton.putClientProperty("fila", i);
				boton.putClientProperty("columna", j);
//				boton.setActionCommand(Integer.toString(i) + "," + Integer.toString(j));
				boton.setPreferredSize(new Dimension(40, 40));
				boton.addActionListener(new ButtonListener());
				boton.setBackground(new Color(255,255,255));
				buttons[i][j] = boton;
			}
		}
		
		anyadeMenu();
		anyadeGrid(NUMFILAS, NUMCOLUMNAS);
		anyadePanelEstado("Inicio de partida");
		partida = new Partida(NUMFILAS, NUMCOLUMNAS, NUMBARCOS);
		
	} // end dibujaTablero
	
	/**
	 * Anyade el menu de opciones del juego
	 */
	private void anyadeMenu() {
        // POR IMPLEMENTAR
		JMenuBar barraMenu = new JMenuBar();
		frame.setJMenuBar(barraMenu);
		JMenu menuOpciones = new JMenu("Opciones");
		JMenuItem itemIniciar = new JMenuItem("Nueva partida");
		itemIniciar.setActionCommand("nueva");
		itemIniciar.addActionListener(new MenuListener());
		barraMenu.add(menuOpciones);
		menuOpciones.add(itemIniciar);
		JMenuItem itemSolucion = new JMenuItem("Mostrar solucion");
		itemSolucion.setActionCommand("solucion");
		menuOpciones.add(itemSolucion);
		itemSolucion.addActionListener(new MenuListener());
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
					lbIndice.setHorizontalAlignment(SwingConstants.CENTER);
					lbIndice.setVerticalAlignment(SwingConstants.BOTTOM);
					panelTablero.add(lbIndice);
				}
				if(i > 0 && j == 0){
					lbIndice = new JLabel(letras[i - 1]);
					lbIndice.setHorizontalAlignment(SwingConstants.RIGHT);
					panelTablero.add(lbIndice);
				}
				if(i > 0 && j == NUMCOLUMNAS + 1){
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
		estado = new JLabel(cadena);
		panelEstado.add(estado);
	} // end anyadePanel Estado
	
	/**
	 * Cambia la cadena mostrada en el panel de estado
	 * @param cadenaEstado	nuevo estado
	 */
	private void cambiaEstado(String cadenaEstado) {
        // POR IMPLEMENTAR
		estado.setText(cadenaEstado);
	} // end cambiaEstado
	
	/**
	 * Muestra la solucion de la partida y marca la partida como finalizada
	 */
	private void muestraSolucion() {
        // POR IMPLEMENTAR
		String[] solucion = partida.getSolucion();
//		RECORRER EL VECTOR Y MOSTRAR LA SOLUCION
		cambiaEstado("PARTIDA FINALIZADA");
	} // end muestraSolucion
	
	/**
	 * Limpia las casillas del tablero
	 */
	private void limpiaTablero() {
        // POR IMPLEMENTAR
		partida = new Partida(NUMFILAS, NUMCOLUMNAS, NUMBARCOS);
		pintarBotones();
		cambiaEstado("Inicio de partida");
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
	        /*
	         * RESPUESTA AL BOTON DEL MENÚ NUEVA PARTIDA, SE CREA UNA PARTIDA Y SE LIMPIA EL TABLERO 
	         */
			if(e.getActionCommand().equals("nueva")){
				partida = new Partida(NUMFILAS, NUMCOLUMNAS, NUMBARCOS);
				limpiaTablero();
			}
			
			/*
	         * RESPUESTA AL BOTON DEL MENÚ SALIR, CIERRA EL PROGRAMA 
	         */
			if(e.getActionCommand().equals("salir")){
				System.exit(0);
			}
			
			/*
	         * RESPUESTA AL BOTON DEL MENÚ SOLUCION 
	         */
			if(e.getActionCommand().equals("solucion")){
				
				deshabilitarBotones();

				/*
		         * PINTAMOS EL TABLERO PROBANDO TODAS LAS CASILLAS Y PINTANDO MAR EN AZUL Y BARCO EN ROJO
		         */
				for(int i = 0; i < NUMFILAS; i++){
					for(int j = 0; j < NUMCOLUMNAS; j++){
						int casilla = partida.pruebaCasilla(i, j);
						if(casilla == AGUA){
							buttons[i][j].setBackground(AZUL);
							buttons[i][j].setBorder(null);
						}
						else if(casilla >= 0 || casilla == HUNDIDO || casilla == TOCADO){
							buttons[i][j].setBackground(ROJO);
							buttons[i][j].setBorder(null);
						}
					}
				}
				
				cambiaEstado("PARTIDA FINALIZADA");
				
				JOptionPane.showMessageDialog(null, "HAS MOSTRADO LA SOLUCIÓN\nPARTIDA FINALIZADA");
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
			/*
	         * COGEMOS LOS DATOS DEL BOTÓN Y PROBAMOS CASILLA, DEPENDE EL RETORNO PINTAMOS DE UN COLOR U OTRO 
	         */		
			JButton botonPulsado = (JButton)e.getSource();
			int fila = (int) botonPulsado.getClientProperty("fila");
			int columna = (int)botonPulsado.getClientProperty("columna");
			
			
			int resultadoCasilla = partida.pruebaCasilla(fila, columna);
			if(resultadoCasilla == AGUA){
				botonPulsado.setBackground(AZUL);
				botonPulsado.setBorder(null);
			}
			else if(resultadoCasilla == TOCADO){
				botonPulsado.setBackground(AMARILLO);
				botonPulsado.setBorder(null);
			}
			else if(resultadoCasilla == HUNDIDO){
				botonPulsado.setBackground(ROJO);
				botonPulsado.setBorder(null);
			}
			else if(resultadoCasilla >= 0){
				/*
		         * SI EL RESULTADO ES UN NUMERO DE BARCO OBTENEMOS TODA SU INFORMACION
		         * PARA PINTAR TODO EL BARCO DE ROJO
		         */
				int filaInicial;
				int colInicial;
				char orientacion;
				int tam;
				String infoBarco = partida.getBarco(resultadoCasilla);
				String[] infoArray = infoBarco.split("#");
				
				filaInicial = Integer.parseInt(infoArray[0]);
				colInicial = Integer.parseInt(infoArray[1]);
				orientacion = infoArray[2].charAt(0);
				tam = Integer.parseInt(infoArray[3]);

				switch(orientacion){
				case 'H':
					for(int i = 0; i < tam; i++){
						buttons[filaInicial][colInicial + i].setBackground(ROJO);
						buttons[filaInicial][colInicial + i].setBorder(null);
					}
					break;
				case 'V':
					for(int i = 0; i < tam; i++){
						buttons[filaInicial + i][colInicial].setBackground(ROJO);
						buttons[filaInicial + i][colInicial].setBorder(null);	
					}
					break;
				}
				
				partida.restarQuedan(); // RESTAMOS UNO A QUEDAN
			}
			
			partida.aumentarDisparos();
			String strEstado = "Intentos:" + partida.getDisparos() + " " + "Barcos restantes:" + partida.getQuedan();
			cambiaEstado(strEstado);
			
			/*
	         * SI NO QUEDAN BARCOS, TERMINAMOS LA PARTIDA DESHABILITANDO TODOS LOS BOTONES 
	         */
			if(partida.getQuedan() == 0){
				deshabilitarBotones();
				
				JOptionPane.showMessageDialog(null, "Terminó la partida");
				
			}
		} // end actionPerformed

		
	} // end class ButtonListener
	
	/*
	 * 
	 * 
     * **********************  METODOS PRIVADOS PARA MEJORAR EL CODIGO **************************** 
     * 
     * 
     * 
     */

	private void pintarBotones(){
		for(int i = 0; i < NUMFILAS; i++){
			for(int j = 0; j < NUMCOLUMNAS; j++){
				buttons[i][j].setBackground(new Color(255,255,255));
				buttons[i][j].setEnabled(true);

			}
		}
	}
	
	private void deshabilitarBotones(){
		for(int i = 0; i < NUMFILAS; i++){
			for(int j = 0; j < NUMCOLUMNAS; j++){
				buttons[i][j].setEnabled(false);

			}
		}
	}
} // end class Juego
