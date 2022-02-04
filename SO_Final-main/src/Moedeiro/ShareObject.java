package Moedeiro;

public class ShareObject {
    private double valor, troco;
    private boolean estado;

    /**
     * Construtor da classe shared Teclado
     *
     * @param estado
     */
    public ShareObject(boolean estado) {
        this.estado = estado;
    }

    /**
     * Função que retorna o troco
     *
     * @return
     */
    public double getTroco() {
        return troco;
    }

    /**
     * Função que define o estado
     *
     * @param troco
     */
    public void setTroco(double troco) {
        this.troco = troco;
    }

    /**
     * Função que retorna o valor
     *
     * @return
     */
    public double getValor() {
        return valor;
    }

    /**
     * Função que define o valor
     *
     * @param valor
     */
    public void setValor(double valor) {
        if (valor == 0) {
            this.valor = 0;
        } else {
            this.valor += valor;
        }
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
     * Função que define estado da porta
     *
     * @param estado
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}