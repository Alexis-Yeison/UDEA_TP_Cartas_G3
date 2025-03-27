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
}
