import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

public class FrmJuego extends JFrame {

    private JButton btnRepartir;
    private JButton btnVerificar;
    private JPanel pnlJugador1;
    private JPanel pnlJugador2;
    private JTabbedPane tpJugadores;

    public FrmJuego() {
        // Creación de botones, paneles y tabla de jugadores
        btnRepartir = new JButton();
        btnVerificar = new JButton();
        tpJugadores = new JTabbedPane();
        pnlJugador1 = new JPanel();
        pnlJugador2 = new JPanel();

        // Asignación de tamaño de ventana
        setSize(600, 300);
        setTitle("Juego de Cartas");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Asignación de valores a paneles de jugadores donde saldran las cartas
        pnlJugador1.setBackground(new Color(153, 255, 51));
        pnlJugador1.setLayout(null);
        pnlJugador2.setBackground(new Color(0, 255, 255));
        pnlJugador2.setLayout(null);

        // Agregar los nombres en las pestañas
        tpJugadores.setBounds(10, 40, 550, 170);
        tpJugadores.addTab("Martín Estrada Contreras", pnlJugador1);
        tpJugadores.addTab("Raul Vidal", pnlJugador2);

        // Agregar el botón
        btnRepartir.setBounds(10, 10, 100, 25);
        btnRepartir.setText("Repartir");
        /* Añadir la función al botón (agregar un evento)
        para repartir las cartas */
        btnRepartir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnRepartirClick(evt);
            }
        });

        /* El botón de verificar
         * verifica los casos posibles de cartas (pares, ternas y cuartas por ahora) 
         */
        btnVerificar.setBounds(120, 10, 100, 25);
        btnVerificar.setText("Verificar");
        btnVerificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnVerificarClick(evt);
            }
        });

        // Agregar contenido en el programa
        getContentPane().setLayout(null);
        getContentPane().add(tpJugadores);
        getContentPane().add(btnRepartir);
        getContentPane().add(btnVerificar);
    }

    // Instancia de los jugadores
    Jugador jugador1 = new Jugador();
    Jugador jugador2 = new Jugador();

    // Método para repartir cartas a ambos jugadores
    private void btnRepartirClick(ActionEvent evt) {

        jugador1.repartir();
        jugador1.mostrar(pnlJugador1);
        
        jugador2.repartir();
        jugador2.mostrar(pnlJugador2);
        
    }

    // Método para mostrar el ganador del juego
    private void mostrarGanador(){
        int puntaje1 = jugador1.getPuntaje(); // Llamamos al método getPuntaje para obtener el puntaje de ambos jugadores
        int puntaje2 = jugador2.getPuntaje();

        // Mensaje inicial que mostrará el puntaje de cada jugador
        String mensaje = "Puntaje Jugador 1: " + puntaje1 + "\n" +
                         "Puntaje Jugador 2: " + puntaje2 + "\n";

        // Condicional para saber que jugador obtuvo menor puntaje y concatenar el texto del ganador en el string mensaje
        if(puntaje1 < puntaje2){
            mensaje += "Ganador: Jugador 1";
        }else if(puntaje2 < puntaje1){
            mensaje += "Ganador: Jugador 2";
        }else{
            mensaje += "Empate";
        }
        
        // Muestra el resultado con showMessageDialog (y este método es llamado desde btnVerificarClick)
        JOptionPane.showMessageDialog(null, mensaje, "Resultado final", JOptionPane.INFORMATION_MESSAGE);
    }
    // Método para verificar
    private void btnVerificarClick(ActionEvent evt) {
        switch(tpJugadores.getSelectedIndex()){
            case 0:
                JOptionPane.showMessageDialog(null, jugador1.getGrupos());
                JOptionPane.showMessageDialog(null, jugador1.getEscalera());
                mostrarGanador();
                break;
            case 1:
                JOptionPane.showMessageDialog(null, jugador2.getGrupos());
                JOptionPane.showMessageDialog(null, jugador2.getEscalera());
                mostrarGanador();
                break;
        }
    }

}