package Teclado;

import javax.swing.*;
import java.util.concurrent.Semaphore;

public class Teclado extends Thread {
    private final SharedTeclado sharedTeclado;
    private final Semaphore semTeclado;

    public Teclado(SharedTeclado sharedTeclado, Semaphore semTeclado) {
        this.sharedTeclado = sharedTeclado;
        this.semTeclado = semTeclado;
    }


    @Override
    public void run() {
        JFrame frame = new JFrame("Teclado");
        frame.setSize(280, 300);
        frame.setVisible(true);
        frame.setLocation(500, 100);
        while (semTeclado.availablePermits() >= 0) {
            JButton btnI = new JButton("I"); //Inicio de Lavagem
            JButton btnC = new JButton("C"); //Cancelar operação de utilização do sistema
            JButton btnE = new JButton("E"); //Parar imediatamente o sistema / Pressionado novamente = voltar a onde estava
            JButton btnR = new JButton("R"); //Reiniciar o sistema
            JButton btnChave = new JButton("Chave A/F"); // Abrir/Fechar o sistema


            btnI.addActionListener(action -> {
                sharedTeclado.setOperacao(SharedTeclado.Operacoes.INICIAR);
                semTeclado.release();
            });

            btnC.addActionListener(action -> {
                sharedTeclado.setOperacao(SharedTeclado.Operacoes.CANCELAR);
                semTeclado.release();
            });

            btnE.addActionListener(action -> {
                sharedTeclado.setOperacao(SharedTeclado.Operacoes.PARAR);
                semTeclado.release();
            });

            btnR.addActionListener(action -> {
                sharedTeclado.setOperacao(SharedTeclado.Operacoes.REINICIAR);
                semTeclado.release();
            });

            btnChave.addActionListener(action -> {
                sharedTeclado.setOperacao(SharedTeclado.Operacoes.FECHAR);
                semTeclado.release();
            });

            JPanel panel = new JPanel();
            panel.add(btnI);
            panel.add(btnC);
            panel.add(btnE);
            panel.add(btnR);
            panel.add(btnChave);

            panel.setBorder(BorderFactory.createEmptyBorder(20, 100, 50, 100));
            frame.add(panel);
        }
    }
}
