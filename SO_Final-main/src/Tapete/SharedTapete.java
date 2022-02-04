package Tapete;

public class SharedTapete {
    public static final String MOV_FRENTE = "Mov. Frente";
    public static final String MOV_TRAS = "Mov. Trás";
    public static final String PARADO = "Parado";

    public String estado;
    public int tempo;

    /**
     * Construtor da classe shared Tapete
     */
    public SharedTapete() {
        this.estado = PARADO;
    }

    /**
     * Função que retorna o estado
     *
     * @return
     */
    public String getEstado() {
        return this.estado;
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

    /**
     * Função que define o estado
     */
    public void setEstadoMovimentoFrente() {
        this.estado = MOV_FRENTE;
    }

    /**
     * Função que define o estado
     */
    public void setEstadoMovimentoTras() {
        this.estado = MOV_TRAS;
    }

    /**
     * Função que define o estado
     */
    public void setEstadoParado() {
        this.estado = PARADO;
    }
}
