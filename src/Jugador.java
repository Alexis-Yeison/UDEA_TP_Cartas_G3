import java.util.Random;

import javax.swing.JPanel;

public class Jugador {

    private int TOTAL_CARTAS = 10;
    private int MARGEN = 10;
    private int DISTANCIA = 40;

    private Carta[] cartas = new Carta[TOTAL_CARTAS];
    private Random r = new Random(); // Suerte del jugador

    public void repartir(){
        for(int i = 0; i<TOTAL_CARTAS; i++){
            cartas[i] = new Carta(r);
        }
    }

    public void mostrar(JPanel pnl){
        pnl.removeAll();


        int pos = MARGEN + (TOTAL_CARTAS - 1) * DISTANCIA;

        for(Carta carta : cartas){
            carta.mostrar(pnl, pos, MARGEN);
            pos -= DISTANCIA;
        }

        pnl.repaint();
    }

    public String getGrupos(){
        String msg = "No se encontraron grupos";

        int[] contadores = new int[NombreCarta.values().length];
        for(Carta carta : cartas){
            contadores[carta.getNombre().ordinal()] ++;
        }

        boolean hayGrupos = false;
        for(int contador : contadores){
            if(contador >= 2){
                hayGrupos = true;
                break;
            }
        }

        if(hayGrupos){
            msg = "Se encontraron los siguientes grupos:\n";
            int index = 0;
            for(int contador : contadores){
                if(contador >= 2){
                    msg += Grupo.values()[contador] + " DE " + NombreCarta.values()[index] + "\n";
                }
                index ++;
            }
        }

        return msg;
    }

    /* Se define el nuevo método getEscalera dentro
     * de la clase jugador, se inicializa con un String vacío donde
     * se guadará la información recolectada
     */
    public String getEscalera(){
        
        /* Se crea una matriz que tendrá tamaño 4x13
         * que es el lenght de la lista en Pinta.java y NombreCarta.java
         * [4] filas -> (PICAS, TREBOLES, CORAZONES, DIAMANTES)
         * [13] columnas -> (AS, UNO,..., KING)
         */
        int[][] cartaPorPinta = new int[Pinta.values().length][NombreCarta.values().length];
        for(Carta carta : cartas){
            /* Se recorre la lista de cartas del jugador
             * cada carta tiene un nombre y una pinta que devolverá un valor según su ordinal
             * si el jugador tiene esta carta, en la matriz de cartaPorPinta su valor
             * cambiará a 1 (teniendo en cuenta que todos los valores iniciales son 0)
             */
            cartaPorPinta[carta.getPinta().ordinal()][carta.getNombre().ordinal()] = 1;
        }

        // Valor booleano para verificar si hay escalera
        String msg = "Se encontraron las siguientes escaleras:\n";
        boolean hayEscaleras = false;
        for(int i = 0; i < Pinta.values().length; i++){ 
            // Se itera sobre cada fila de la matriz
            int contador = 0; // contador de cartas encontradas
            String escalera = ""; // almacenará la secuencia de las cartas en orden

            for(int j = 0; j < NombreCarta.values().length; j++){ 
                /* Se recorre la matriz cartaPorPinta que verificará si el valor
                 * en esa posición es igual a 1 (significa que tiene esta carta)
                 * se aumenta el contador en 1 y se agrega el nombre de la carta en
                 * la variable escalera
                 */
                if(cartaPorPinta[i][j] == 1){
                    contador ++;
                    escalera += NombreCarta.values()[j] + " ";
                /* En cada iteracion se verificará que el valor de la posicion i,j
                 * en la matriz sea 1 y hay dos posibles casos:
                 * 
                 * caso 1: se encuentran 3 posiciones con valor == 1 y la siguiente pos con
                 * valor == 0, se entrará por el condicional else y se verificará si el
                 * contador es mayor o igual a 3 y guardará el mensaje con la pinta y la escalera
                 * encontrada
                 * 
                 * caso 2: se encuentran 2 pos con valor == 1 y la siguiente pos con
                 * valor == 0, se entrará por el condicional else y se romperá la secuencia
                 * ya que el contador no tuvo un valor mayor o igual que 3
                 * ya que no se cumple el condicional if, entonces se debe reiniciar el contador
                 * y los nombres de las cartas almacenadas en la variable escalera
                 */
                }else{
                    if(contador >= 3){ // Si hay 3 o más cartas seguidas, es una escalera
                        msg += "ESCALERA DE " + Pinta.values()[i] + ": " + escalera + "\n";
                        hayEscaleras = true;
                    }
                    contador = 0;
                    escalera = "";
                }
            }
            /* Si el ciclo for que recorré la posicion de las columnas j, llega hasta el final
             * de la fila entonces también se debe verificar si el contador alcanzó un valor mayor o
             * igual que 3 (este caso aplica cuando por ejemplo se está en la pos
             * i = 0 -> primera fila
             * j = 12 -> último valor de la fila)
             * si los valores de las posiciones 10, 11 y 12 están en 1 en la matriz
             * entonces se utiliza el condicional para agregar la escalera
             */
            if(contador >= 3){
                msg += "ESCALERA DE " + Pinta.values()[i] + ": " + escalera + "\n";
                hayEscaleras = true;
            }

        }
        return hayEscaleras ? msg : "NO SE ENCONTRARON ESCALERAS";
    }
}
