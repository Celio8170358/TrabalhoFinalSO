package Rolos;

public class SharedRolos {
    public int tempo;
    private boolean estado;

    /**
     * Construtor da classe shared Teclado
     *
     * @param estado
     */
    public SharedRolos(boolean estado) {
        this.estado = estado;
    }

    /**
     * Função que retorna o estado
     *
     * @return
     */
    public boolean getEstado() {
        return estado;
    }

    /**
     * Função que define o estado
     *
     * @param estado
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * Função que retorna o tempo
     *
     * @return
     */
    public int getTempo() {
        return this.tempo;
    }

    /**
     * Função que define o tempo
     *
     * @param tempo
     */
    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
}
