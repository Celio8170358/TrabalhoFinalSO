package Aspersores;

public class SharedAspersores {
    private boolean estadoAspersor, estadoSecador;
    private int aspersor_tempo, secador_tempo;

    /**
     * Construtor da classe shared Teclado
     *
     * @param estadoAspersor
     * @param estadoSecador
     */
    public SharedAspersores(boolean estadoAspersor, boolean estadoSecador) {
        this.estadoAspersor = estadoAspersor;
        this.estadoSecador = estadoSecador;
    }

    /**
     * Função que retorna o estado do aspersor
     *
     * @return
     */
    public boolean getEstadoAspersor() {
        return estadoAspersor;
    }

    /**
     * Função que define o estado do aspersor
     *
     * @param estado
     */
    public void setEstadoAspersor(boolean estado) {
        this.estadoAspersor = estado;
    }

    /**
     * Função que retorna o tempo do aspersor
     *
     * @return
     */
    public int getAspersorTempo() {
        return this.aspersor_tempo;
    }

    /**
     * Função que define o tempo do aspersor
     *
     * @param tempo
     */
    public void setAspersorTempo(int tempo) {
        this.aspersor_tempo = tempo;
    }

    /**
     * Função que retorna o estado do secador
     *
     * @return
     */
    public boolean getEstadoSecador() {
        return estadoSecador;
    }

    /**
     * Função que define o estado do secador
     *
     * @param estado
     */
    public void setEstadoSecador(boolean estado) {
        this.estadoSecador = estado;
    }

    /**
     * Função que retorna o tempo do secador
     *
     * @return
     */
    public int getSecadorTempo() {
        return this.secador_tempo;
    }

    /**
     * Função que define o tempo do secador
     *
     * @param tempo
     */
    public void setSecadorTempo(int tempo) {
        this.secador_tempo = tempo;
    }
}
