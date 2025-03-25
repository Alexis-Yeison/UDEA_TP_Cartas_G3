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


        int pos = MARGEN;

        for(Carta carta : cartas){
            carta.mostrar(pnl, pos, MARGEN);
            pos += DISTANCIA;
        }

        pnl.repaint();
    }
}
