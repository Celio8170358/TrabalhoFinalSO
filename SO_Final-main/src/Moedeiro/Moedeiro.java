package Moedeiro;

import javax.swing.*;
import java.util.concurrent.Semaphore;

public class Moedeiro extends Thread {
    private final ShareObject shareObject;
    private final Semaphore sem;
    private final Semaphore semMain;
    private JLabel valorIntroduzido, valorTroco;

    /**
     * Construtor da classe Moedeiro
     *
     * @param sem
     * @param semMain
     * @param shareObject
     */
    public Moedeiro(Semaphore sem, Semaphore semMain, ShareObject shareObject) {
        this.sem = sem;
        this.semMain = semMain;
        this.shareObject = shareObject;
    }

    /**
     * Método com o código a executar quando o main Thread é inicializado
     */
    @Override
    public void run() {

        JFrame ecra = new JFrame("Moedeiro");
        ecra.setSize(300, 400);
        ecra.setVisible(true);
        ecra.setLocation(900, 100);

        JButton button50Cent = new JButton("0,50 €");
        JButton button1Eur = new JButton("1 €");
        JButton button2Eur = new JButton("2 €");
        JButton button5Eur = new JButton("5 €");
        JButton button10Eur = new JButton("10 €");
        JButton button20Eur = new JButton("20 €");
        JButton buttonTroco = new JButton("Retirar Troco");

        try {

            sem.acquire();

        } catch (InterruptedException e) {

        }

        button50Cent.addActionListener(acao -> {
            if (shareObject.getEstado() == true) {
                shareObject.setValor(0.50);
                valorIntroduzido.setText("Valor introduzido: " + shareObject.getValor());
            } else {
                JOptionPane.showMessageDialog(null, "O Moedeiro de momento está bloqueado!!");
            }
        });

        button1Eur.addActionListener(acao -> {
            if (shareObject.getEstado() == true) {
                shareObject.setValor(1);
                valorIntroduzido.setText("Valor introduzido: " + shareObject.getValor());
            } else {
                JOptionPane.showMessageDialog(null, "O Moedeiro de momento está bloqueado!!");
            }
        });

        button2Eur.addActionListener(acao -> {
            if (shareObject.getEstado() == true) {
                shareObject.setValor(2);
                valorIntroduzido.setText("Valor introduzido: " + shareObject.getValor());
            } else {
                JOptionPane.showMessageDialog(null, "O Moedeiro de momento está bloqueado!!");
            }
        });

        button5Eur.addActionListener(acao -> {
            if (shareObject.getEstado() == true) {
                shareObject.setValor(5);
                valorIntroduzido.setText("Valor introduzido: " + shareObject.getValor());
            } else {
                JOptionPane.showMessageDialog(null, "O Moedeiro de momento está bloqueado!!");
            }
        });

        button10Eur.addActionListener(acao -> {
            if (shareObject.getEstado() == true) {
                shareObject.setValor(10);
                valorIntroduzido.setText("Valor introduzido: " + shareObject.getValor());
            } else {
                JOptionPane.showMessageDialog(null, "O Moedeiro de momento está bloqueado!!");
            }
        });

        button20Eur.addActionListener(acao -> {
            if (shareObject.getEstado() == true) {
                shareObject.setValor(20);
                valorIntroduzido.setText("Valor introduzido: " + shareObject.getValor());
            } else {
                JOptionPane.showMessageDialog(null, "O Moedeiro de momento está bloqueado!!");
            }
        });

        buttonTroco.addActionListener(acao -> {
            JOptionPane.showMessageDialog(null, "Valor Troco: " + shareObject.getTroco());
            shareObject.setTroco(0);
            valorTroco.setText("Valor Troco: " + shareObject.getTroco());
        });


        JPanel moedeiro = new JPanel();
        valorIntroduzido = new JLabel();
        valorTroco = new JLabel();
        valorIntroduzido.setText("Valor introduzido: " + shareObject.getValor());
        valorTroco.setText("Valor Troco: " + shareObject.getTroco());


        moedeiro.add(valorIntroduzido);
        moedeiro.add(valorTroco);


        moedeiro.add(button50Cent);
        moedeiro.add(button1Eur);
        moedeiro.add(button2Eur);
        moedeiro.add(button5Eur);
        moedeiro.add(button10Eur);
        moedeiro.add(button20Eur);
        moedeiro.add(button20Eur);
        moedeiro.add(buttonTroco);


        moedeiro.setBorder(BorderFactory.createEmptyBorder(20, 100, 50, 100));

        ecra.add(moedeiro);

        try {
            while (sem.availablePermits() >= 0) {
                sem.acquire();
                valorIntroduzido.setText("Valor introduzido: " + shareObject.getValor());
                valorTroco.setText("Valor Troco: " + shareObject.getTroco());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}