package Tapete;

import javax.swing.*;
import java.util.concurrent.Semaphore;

public class Tapete extends Thread {
    private final SharedTapete sharedTapete;
    private final Semaphore sem;
    private final Semaphore semMain;

    /**
     * Construtor da classe Teclado
     *
     * @param sem
     * @param semMain
     * @param sharedTapete
     */
    public Tapete(Semaphore sem, Semaphore semMain, SharedTapete sharedTapete) {
        this.sem = sem;
        this.sharedTapete = sharedTapete;
        this.semMain = semMain;
    }

    /**
     * Método com o código a executar quando o main Thread é inicializado
     */
    @Override
    public void run() {
        JFrame frame = new JFrame("Tapete");
        frame.setSize(280, 300);
        frame.setVisible(true);
        frame.setLocation(500, 600);

        JLabel label = new JLabel();
        frame.add(label);
        label.setBorder(BorderFactory.createEmptyBorder(10, 85, 10, 10));

        label.setText(sharedTapete.getEstado());

        try {
            while (sem.availablePermits() >= 0) {
                sem.acquire();
                label.setText(sharedTapete.getEstado());
                Thread.sleep(1000);
                semMain.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}