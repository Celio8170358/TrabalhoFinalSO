import Aspersores.Aspersor;
import Aspersores.SharedAspersores;
import Moedeiro.Moedeiro;
import Moedeiro.ShareObject;
import Rolos.Rolos;
import Rolos.SharedRolos;
import Tapete.SharedTapete;
import Tapete.Tapete;
import Teclado.SharedTeclado;
import Teclado.Teclado;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main implements Runnable {

    private final Semaphore semMain;
    private final Semaphore semMoedeiro;
    private final Semaphore semTeclado;
    private final Semaphore semTapete;
    private final Semaphore semRolos;
    private final Semaphore semAspersor;
    private final ShareObject shareObject;
    private final SharedTapete sharedTapete;
    private final SharedRolos sharedRolos;
    private final SharedTeclado sharedTeclado;
    private final SharedAspersores sharedAspersores;
    private final Rolos rolos;
    private final Aspersor aspersor;
    private final Tapete tapete;
    private final Moedeiro moedeiro;
    private final Teclado teclado;
    private JLabel stateLabel;
    private Sistemas_Estados estado;
    private int custo;

    /**
     * Construtor da classe main
     *
     * @param semMain
     * @param semMoedeiro
     * @param semTeclado
     * @param semTapete
     * @param semRolos
     * @param semAspersor
     * @param sharedTeclado
     * @param sharedTapete
     * @param sharedRolos
     * @param sharedTeclado
     * @param sharedAspersores
     * @param estado
     * @param custo
     * @param rolos
     * @param aspersor
     * @param tapete
     * @param moedeiro
     * @param teclado
     */
    public Main(Semaphore semMain, Semaphore semMoedeiro, Semaphore semTeclado, Semaphore semTapete, Semaphore semRolos, Semaphore semAspersor, ShareObject shareObject, SharedTapete sharedTapete, SharedRolos sharedRolos, SharedTeclado sharedTeclado, SharedAspersores sharedAspersores, Sistemas_Estados estado, int custo, Rolos rolos, Aspersor aspersor, Tapete tapete, Moedeiro moedeiro, Teclado teclado) {
        this.semMain = semMain;
        this.semMoedeiro = semMoedeiro;
        this.semTeclado = semTeclado;
        this.semTapete = semTapete;
        this.semRolos = semRolos;
        this.semAspersor = semAspersor;
        this.shareObject = shareObject;
        this.sharedTapete = sharedTapete;
        this.sharedRolos = sharedRolos;
        this.sharedTeclado = sharedTeclado;
        this.sharedAspersores = sharedAspersores;
        this.estado = estado;
        this.custo = custo;
        this.rolos = rolos;
        this.aspersor = aspersor;
        this.tapete = tapete;
        this.moedeiro = moedeiro;
        this.teclado = teclado;
    }

    public static void main(String[] args) {
        int custo_sistema = 0;
        Sistemas_Estados estado_sistema = Sistemas_Estados.LIVRE;

        //-----------------------------------------------
        //------------------------Semaforos--------------

        Semaphore semMain = new Semaphore(0);
        Semaphore semMoedeiro = new Semaphore(0);
        Semaphore semTeclado = new Semaphore(0);
        Semaphore semAspersor = new Semaphore(0);
        Semaphore semTapete = new Semaphore(0);
        Semaphore semRolos = new Semaphore(0);

        //-----------------------------------------------
        //------------------------Shared-----------------

        ShareObject share = new ShareObject(true);
        SharedTeclado sharedTeclado = new SharedTeclado();
        SharedTapete sharedTapete = new SharedTapete();
        SharedRolos sharedRolos = new SharedRolos(false);
        SharedAspersores sharedAspersores = new SharedAspersores(false, false);

        //-----------------------------------------------
        //------------------------Classes-----------------
        Moedeiro moedeiro = new Moedeiro(semMoedeiro, semMain, share);
        Teclado teclado = new Teclado(sharedTeclado, semTeclado);
        Aspersor aspersor = new Aspersor(semAspersor, semMain, sharedAspersores);
        Rolos rolos = new Rolos(semRolos, semMain, sharedRolos);
        Tapete tapete = new Tapete(semTapete, semMain, sharedTapete);

        Main main = new Main(semMain, semMoedeiro, semTeclado, semTapete, semRolos, semAspersor, share, sharedTapete, sharedRolos, sharedTeclado, sharedAspersores, estado_sistema, custo_sistema, rolos, aspersor, tapete, moedeiro, teclado);

        //-----------------------------------------------
        //------------------------Thread-----------------
        Thread principal = new Thread(main);
        principal.start();

        try {
            main.config_File();
        } catch (IOException ex) {
            System.err.println("Erro na leitura do ficheiro de configurações!!");
        }


        //-----------------------------------------------
        //------------------------Semaphore Releases-----------------


        semMoedeiro.release();
    }

    /**
     * Função que retorna o estado do sistema
     *
     * @return
     */
    public Sistemas_Estados getEstado() {
        return estado;
    }

    /**
     * Função para definir estado do sistema
     *
     * @param estado
     */
    public void setEstado(Sistemas_Estados estado) {
        this.estado = estado;

        stateLabel.setText(this.estado.toString());
    }

    /**
     * Função que retorna o custo
     *
     * @return
     */
    public int getCusto() {
        return custo;
    }

    /**
     * Função para definir o valor do custo
     *
     * @param custo
     */
    public void setCusto(int custo) {
        this.custo = custo;
    }

    /**
     * Método com o código a executar quando o main Thread é inicializado
     */
    public void run() {
        while (semMain.availablePermits() >= 0) {
            moedeiro.start();
            teclado.start();
            tapete.start();
            rolos.start();
            aspersor.start();

            JFrame frame = new JFrame("Estados");
            frame.setSize(280, 300);
            frame.setVisible(true);
            frame.setLocation(1200, 600);

            stateLabel = new JLabel();
            frame.add(stateLabel);
            stateLabel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 10));

            this.setEstado(Sistemas_Estados.LIVRE);
            try {
                writeLog("Aplicação Iniciada");
                while (true) {
                    semTeclado.acquire();
                    switch (sharedTeclado.getOperacao()) {
                        case INICIAR:
                            start();
                            break;
                        case CANCELAR:
                            cancel();
                            break;
                        case PARAR:
                            stop();
                            break;
                        case REINICIAR:
                            reset();
                            break;
                        case FECHAR:
                            chaveAF();
                            break;
                        default:
                            break;
                    }
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Função que calcula o troco
     */
    public void calcularTroco() {
        shareObject.setTroco(shareObject.getTroco() + (shareObject.getValor() - getCusto()));
        shareObject.setValor(0);
    }

    /**
     * Função que inicia o processo de lavagem, verifica o estado do sistema, se o valor introduzido no
     * moedeiro é sufeciente assim como se os modulos estão a funcionar corretamente.
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public void start() throws IOException, InterruptedException {
        if (this.getEstado() == Sistemas_Estados.LIVRE && semMain.availablePermits() >= 0) {
            if (shareObject.getValor() >= getCusto() && semMain.availablePermits() >= 0) {
                calcularTroco();
                semMoedeiro.release();
                this.setEstado(Sistemas_Estados.OCUPADO);
                writeLog("Ciclo Iniciado");
                sharedTapete.setEstadoMovimentoFrente();
                semTapete.release();
                Thread.sleep(1000 * (sharedTapete.getTempo() / 5));
                semMain.acquire();
                sharedAspersores.setEstadoAspersor(true);
                semAspersor.release();
                Thread.sleep(1000 * sharedAspersores.getAspersorTempo());
                sharedAspersores.setEstadoAspersor(false);
                semAspersor.release();
                if (sharedAspersores.getEstadoAspersor() == false) {
                    sharedRolos.setEstado(true);
                    semRolos.release();
                    Thread.sleep(1000 * sharedRolos.getTempo());
                    sharedRolos.setEstado(false);
                    semRolos.release();
                    semMain.acquire();
                    if (sharedRolos.getEstado() == false) {
                        sharedAspersores.setEstadoSecador(true);
                        semAspersor.release();
                        Thread.sleep(1000 * sharedAspersores.getSecadorTempo());
                        sharedAspersores.setEstadoSecador(false);
                        semAspersor.release();
                    }
                }
                Thread.sleep(3000);
                sharedTapete.setEstadoParado();
                semTapete.release();
                semMain.acquire();
                this.setEstado(Sistemas_Estados.LIVRE);
                writeLog("Ciclo Terminado");
            } else {
                messageBox("VALOR INSUFICIENTE");
            }
        } else {
            shareObject.setTroco(shareObject.getTroco() + shareObject.getValor());
            shareObject.setValor(0);
            semMoedeiro.release();
            messageBox("O SISTEMA ENCONTRA-SE FECHADO");
        }
    }

    /**
     * Função que cancela o valor introduzido no moedeiro
     *
     * @throws IOException
     */
    public void cancel() throws IOException {
        if (this.getEstado() == Sistemas_Estados.LIVRE) {
            try {
                shareObject.setTroco(shareObject.getTroco() + shareObject.getValor());
                shareObject.setValor(0);
                semMoedeiro.release();
                writeLog("Ciclo Cancelado");
            } catch (Exception ex) {
                writeLog(ex.getMessage());
            }
        }
    }

    /**
     * Função que para todo o sistema
     *
     * @throws IOException
     */
    public void stop() throws IOException {
        if (this.getEstado() != Sistemas_Estados.FECHADO) {
            try {

                writeLog("Ciclo Parado");
            } catch (Exception ex) {
                writeLog(ex.getMessage());
            }
        }
    }

    /**
     * Função que faz reset total ao sistema
     *
     * @throws IOException
     */
    public void reset() throws IOException {
        try {
            if (this.getEstado() != Sistemas_Estados.FECHADO) {
                this.setEstado(Sistemas_Estados.LIVRE);
                sharedAspersores.setEstadoSecador(false);
                sharedRolos.setEstado(false);
                sharedTapete.setEstadoParado();
                semAspersor.release();
                semRolos.release();
                semTapete.release();
                shareObject.setTroco(0);
                shareObject.setValor(0);
                semMoedeiro.release();
                semMain.acquire();
                writeLog("Ciclo Reiniciado");
            }
        } catch (Exception ex) {
            writeLog(ex.getMessage());
        }
    }

    /**
     * Função para a utilização da chave
     *
     * @throws IOException
     */
    public void chaveAF() throws IOException {
        try {

            if (this.getEstado() == Sistemas_Estados.FECHADO) {
                this.setEstado(Sistemas_Estados.LIVRE);
                shareObject.setEstado(true);
                writeLog("Sistema Libertado");
            } else {
                this.setEstado(Sistemas_Estados.FECHADO);
                shareObject.setTroco(shareObject.getTroco() + shareObject.getValor());
                shareObject.setValor(0);
                semMoedeiro.release();
                shareObject.setEstado(false);
                writeLog("Sistema Fechado");
            }

        } catch (Exception ex) {
            writeLog(ex.getMessage());
        }
    }

    /**
     * Função que carrega as configurações
     *
     * @throws IOException
     */
    public void config_File() throws IOException {
        try {
            File myObj = new File("Config_File/dados.txt");
            Scanner myReader = new Scanner(myObj);
            int line = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (line == 0) {
                    setCusto(Integer.parseInt(data));
                } else if (line == 1) {
                    sharedTapete.setTempo(Integer.parseInt(data));
                } else if (line == 2) {
                    sharedRolos.setTempo(Integer.parseInt(data));
                } else if (line == 3) {
                    sharedAspersores.setAspersorTempo(Integer.parseInt(data));
                } else {
                    sharedAspersores.setSecadorTempo(Integer.parseInt(data));
                }
                line++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            writeLog(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Função que mostra a Message Dialog
     *
     * @param m
     */
    private void messageBox(String m) {
        JOptionPane.showMessageDialog(null, m);
    }

    /**
     * Função que regista as operações realizadas num ficheiro
     *
     * @param inputString
     * @throws IOException
     */
    public void writeLog(String inputString) throws IOException {
        Path path = Paths.get("Config_File/log.txt");

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
        String text = dateFormatter.format(new Date()) + "; " + inputString + "\n";

        byte[] strByteArray = text.getBytes();
        Files.write(path, strByteArray, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
    }

    /**
     * ENUMERAÇÕES PARA OS ESTADOS DO SISTEMA
     */
    public enum Sistemas_Estados {
        LIVRE, OCUPADO, FECHADO
    }
}
