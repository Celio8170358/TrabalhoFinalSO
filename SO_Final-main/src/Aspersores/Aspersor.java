package Aspersores;

import javax.swing.*;
import java.util.concurrent.Semaphore;

public class Aspersor extends Thread {
    private final Semaphore sem;
    private final Semaphore semMain;
    private final SharedAspersores sharedAspersores;

    /**
     * Construtor da classe Moedeiro
     *
     * @param sem
     * @param semMain
     * @param sharedAspersores
     */
    public Aspersor(Semaphore sem, Semaphore semMain, SharedAspersores sharedAspersores) {
        this.sem = sem;
        this.semMain = semMain;
        this.sharedAspersores = sharedAspersores;
    }

    /**
     * Método com o código a executar quando o main Thread é inicializado
     */
    @Override
    public void run() {
        JFrame frame = new JFrame("Aspersores");
        frame.setSize(280, 300);
        frame.setVisible(true);
        frame.setLocation(1200, 600);

        JLabel label = new JLabel();
        frame.add(label);
        label.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 10));

        label.setText(sharedAspersores.getEstadoAspersor() ? "Aspersores  em movimentos" : "Aspersores e secador parados");

        try {
            while (sem.availablePermits() >= 0) {
                sem.acquire();
                label.setText(sharedAspersores.getEstadoAspersor() ? "Aspersores  em movimentos" : "Aspersores e secador parados");
                if (sharedAspersores.getEstadoSecador() == true) {
                    label.setText(sharedAspersores.getEstadoSecador() ? "Secador  em movimento" : "Secador parado");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}