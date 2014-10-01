package tablero;

import java.util.Random;
import java.util.Vector;

public class Partida {
	
	private static final int AGUA = -1, TOCADO = -2, HUNDIDO = -3;
	
	/**
	 * El mar se representa mediante una matriz de casillas
	 * En una casilla no tocada con un barco se guarda el indice del barco en el vector de barcos
	 * El resto de valores posibles (AGUA, TOCADO y HUNDIDO) se representan mediante 
	 * constantes enteras negativas.
	 */
	private int mar[][] = null;				// matriz que contendra el mar y los barcos en distintos estados
	private int numFilas, 					// numero de filas del tablero
				numColumnas;				// numero de columnas del tablero
	private Vector<Barco> barcos = null;	// vector dinamico de barcos
	private int numBarcos, 					// numero de barcos de la partida
	            quedan,  					// numero de barcos no hundidos
	            disparos; 					// numero de disparos efectuados
	
	/**
	 * Contructor por defecto. No hace nada
	 */
	public Partida() {}
	
	/**
	 * Constructor de una partida
	 * @param	nf	numero de filas del tablero
	 * @param   nf  numero de columnas del tablero
	 * @param   nc  numero de barcos
	 */
	public Partida(int nf, int nc, int nb) {
		numFilas = nf;
		numColumnas = nc;
		mar = new int[numFilas][numColumnas];
		numBarcos = nb;
		quedan = numBarcos;
		barcos = new Vector<Barco>();
		for(int i = 0; i < nf; i++){
			for(int j = 0; j < nc; j++){
				mar[i][j] = -1;
			}
		}
		
		ponBarcos();
	}
	
	
	
	public int getNumBarcos() {
		return numBarcos;
	}


	public int getQuedan() {
		return quedan;
	}

	public void setQuedan(int quedan) {
		this.quedan = quedan;
	}

	public int getDisparos() {
		return disparos;
	}

	public void setDisparos(int disparos) {
		this.disparos = disparos;
	}

	/**
	 * Dispara sobre una casilla y devuelve el resultado
	 * @param	f	fila de la casilla
	 * @param   c   columna de la casilla
	 * @return		resultado de marcar la casilla: AGUA, ya TOCADO, ya HUNDIDO, identidad del barco recien hundido
	 */	
    public int pruebaCasilla(int f, int c) {
    	int devuelvo = 0;

//    	SI ES MENOR QUE 0 SE DEVUELVE LO QUE YA HAY, XK HAY AGUA O YA SE HA PROBADO
    	if(mar[f][c] < 0){
    		devuelvo = mar[f][c];	
    	}
    	
//    	SI SE TOCA UN BARCO, SE TOCA
    	else if(mar[f][c] >= 0){
    		int indiceBarco = mar[f][c];
    		Barco barco = barcos.get(indiceBarco);
//    		SI FALTA UNA POR TOCAR PARA HUNDIR EL BARCO, SE HUNDE
    		if((barco.getTamanyo() - barco.getTocadas()) == 1){
    			devuelvo = indiceBarco;
    			hundirBarco(barco);
    		}
    		else{
    			mar[f][c] = -2;
    			devuelvo = -2;
    			barco.aumentaTocadas();
    		}
    		
    	} 
    	return devuelvo;
    }//Fin del pruebaCasilla
    

	/**
	 * Devuelve una cadena con los datos de un barco dado: filIni, colIni, orientacion, tamanyo
	 * Los datos se separan con el caracter especial '#'
	 * @param	idBarco	indice del barco en el vector de barcos
	 * @return	        cadena con los datos del barco
	 */	
	public String getBarco(int idBarco) {

		return barcos.get(idBarco).toString();
	}
	
	/**
	 * Devuelve un vector de cadenas con los datos de todos los barcos
	 * @return	vector de cadenas, una por barco con la informacion de getBarco
	 */	
	public String[] getSolucion() {
		
		String[] vector = new String[barcos.size()];
		for(int i = 0; i < barcos.size(); i++){
			vector[i] = barcos.get(i).toString();
		}
		
		return vector;
	}
    

	/********************************    METODOS PRIVADOS  ********************************************/
    
	
	/**
	 * Coloca los barcos en el tablero
	 */	
	private void ponBarcos() {
		/* Por defecto colocamos un barco de tamaño 4, uno de tamaño 3, otro de tamaño 2 y tres barcos de tamaño 1 */
		barcos.add( ponBarco(0, 4) );
		barcos.add( ponBarco(1, 3) );
		barcos.add( ponBarco(2, 2) );
		barcos.add( ponBarco(3, 1) );
		barcos.add( ponBarco(4, 1) );
		barcos.add( ponBarco(5, 1) );
	}
	
	
	/**
	 * Busca hueco para un barco y lo coloca en el tablero.
	 * @param  id   indice del barco en el vector de barcos
	 * @param  tam  tamanyo del barco
	 * @return      un barco guardado como un objeto Barco
	 */	
	private Barco ponBarco(int id, int tam) {
        char orientacion=' ';
        boolean ok = false;
        int fila = 0, col = 0;
        Random random = new Random(); // Para generar aleatoriamente la orientacion y posicion de los barcos
        
        // Itera hasta que encuentra hueco para colocar el barco cumpliendo las restricciones
        while (!ok) {
        	// Primero genera aleatoriamente la orientacion del barco
            if (random.nextInt(2) == 0) { // Se dispone horizontalmente
            	// Ahora genera aleatoriamente la posicion del barco
                col = random.nextInt(numColumnas + 1 - tam); // resta tam para asegurar que cabe
                fila = random.nextInt(numFilas);

                // Comprueba si cabe a partir de la posicion generada con mar o borde alrededor
                if (librePosiciones(fila, col, tam+1, 'H')) {
                	// Coloca el barco en el mar
                    for (int i = 0; i < tam; i++) {
                        mar[fila][col+i] = id;
                    }
                    ok = true;
                    orientacion = 'H';
                }
            }
            else { //Se dispone verticalmente
                fila = random.nextInt(numFilas + 1 - tam);
                col = random.nextInt(numColumnas);
                if (librePosiciones(fila, col, tam+1, 'V')) {
                    for (int i = 0; i < tam; i++) {
                        mar[fila + i][col] = id;
                    }
                    ok = true;
                    orientacion = 'V';
                }
            } // end if H o V
        } // end while
        return new Barco(fila, col, orientacion, tam);
	}
	
	/**
	 * Comprueba si hay hueco para un barco a partir de una casilla inicial.
	 * Los barcos se colocan dejando una casilla de hueco con los otros.
	 * Pueden pegarse a los bordes.
	 * @param  fila   fila de la casilla inicial
	 * @param  col    columna de la casilla inicial
	 * @param  tam    tamanyo del barco + 1 para dejar hueco alrededor
	 * @param  ori    orientacion del barco: 'H' o 'V'
	 * @return        true si se encuentra hueco, false si no.
	 */	
    private boolean librePosiciones(int fila, int col, int tam, char ori) {
    	int i;
        if (ori == 'H') {
        	i = ( (col > 0) ? -1 : 0 );
        	// Comprueba que "cabe" horizontalmente a partir de la columna dada.
        	// Esto implica que:
        	// haya 'tam' casillas vacias (con mar) en la fila 'fila'
        	// y que quede rodeado por el mar o por un borde
        	while ( (col+i < numColumnas) && (i<tam) && (mar[fila][col+i] == AGUA) && 
        			( (fila == 0) || (mar[fila-1][col+i] == AGUA) )  &&
        			( (fila == numFilas-1) || (mar[fila+1][col+i] == AGUA) )         			
        		  ) i++;
        }
        else { // ori == 'V'
        	i = ( (fila > 0) ? -1 : 0 );
        	while ( (fila+i < numFilas) &&  (i<tam) && (mar[fila+i][col] == AGUA) &&
        			( (col == 0) || (mar[fila+i][col-1] == AGUA) )  &&
        			( (col == numColumnas-1) || (mar[fila+i][col+1] == AGUA) )  
        		  ) i++;
        }
        // Ha encontrado un hueco cuando ha generado el barco totalmente rodeado de agua o
        boolean resultado = (i == tam);
        // lo ha generado horizontal pegado al borde derecho o
        resultado = resultado || ( (ori == 'H') && (col+i == numColumnas) );
        // lo ha generado vertical pegado al borde inferior.
        resultado = resultado || ( (ori == 'V') && (fila+i == numFilas) );
        return resultado;
    }
    
    /*
     * METODOS PRIVADOS AUXILIARES CREADOS POR NOSOTROS
     */
    public void aumentarDisparos(){
    	disparos++;
    }
    
    public void restarQuedan(){
    	quedan--;
    }
    
    
    /*
     * METODOS PRIVADOS AUXILIARES CREADOS POR NOSOTROS
     */
    private void hundirBarco(Barco barco){
    	barco.aumentaTocadas();
		
//		HUNDIR BARCO
		if(barco.getOrientacion() == 'H'){
			for(int i = 0; i < barco.getTamanyo(); i++){
				mar[barco.getFilaInicial()][barco.getColumnaInicial() + i] = -3;
			}
		} else {
			for(int i = 0; i < barco.getTamanyo(); i++){
				mar[barco.getFilaInicial() + i][barco.getColumnaInicial()] = -3;
			}
		}
    }
} // end class Partida
