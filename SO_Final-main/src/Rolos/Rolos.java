package Rolos;

import javax.swing.*;
import java.util.concurrent.Semaphore;

public class Rolos extends Thread {
    private final Semaphore semRolos;
    private final Semaphore semMain;
    private final SharedRolos sharedRolos;

    /**
     * Construtor da classe Teclado
     *
     * @param sem
     * @param semMain
     * @param sharedRolos
     */
    public Rolos(Semaphore sem, Semaphore semMain, SharedRolos sharedRolos) {
        this.semRolos = sem;
        this.semMain = semMain;
        this.sharedRolos = sharedRolos;
    }

    /**
     * Método com o código a executar quando o main Thread é inicializado
     */
    @Override
    public void run() {
        JFrame frame = new JFrame("Rolos");
        frame.setSize(280, 300);
        frame.setVisible(true);
        frame.setLocation(850, 600);

        JLabel label = new JLabel();
        frame.add(label);
        label.setBorder(BorderFactory.createEmptyBorder(10, 85, 10, 10));

        label.setText(sharedRolos.getEstado() ? "Rolos em andamento" : "Rolos parados");

        try {
            while (semRolos.availablePermits() >= 0) {
                semRolos.acquire();
                label.setText(sharedRolos.getEstado() ? "Rolos em andamento" : "Rolos parados");
                semMain.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}