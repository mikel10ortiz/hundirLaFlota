package tablero;

public class Barco {	
	/**
	 * Clase para guardar la informacion de un barco en una partida de 'Hundir la flota'
	 */

	private int filaInicial,	// coordenadas iniciales del barco
				columnaInicial; 
	private char orientacion;	// 'H': horizontal; 'V':vertical
	private int	tamanyo,		// numero de casillas que ocupa
				tocadas;		// numero de casillas tocadas
	
	/**
	 * Constructor por defecto. No hace nada
	 */
	public Barco() { }
	
	/**
	 * Constructor con argumentos
	 * @param f				fila del barco
	 * @param c				columna del barco
	 * @param orientacion	orientacion (vertical u horizontal)
	 * @param tamanyo		tamanyo del barco
	 * @param tocadas		numero de celdas tocadas
	 */
	public Barco(int f, int c, char orientacion, int tamanyo) {
        // POR IMPLEMENTAR
	}
	
	
	@Override
	/**
	 * Codifica en formato String los datos del barco
	 * @return cadena con los datos del barco separados por '#'
	 */
	public String toString() {
		return filaInicial + "#" + columnaInicial + "#" + orientacion + "#" + tamanyo;
	}
	
	/******************************************************************************************/
	/*****************************     GETTERS y SETTERS    ***********************************/
	/******************************************************************************************/
	
	// POR IMPLEMENTAR


} // end class Barco
