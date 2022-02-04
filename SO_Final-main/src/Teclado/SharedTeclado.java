package Teclado;

public class SharedTeclado {
    private Operacoes operacao = Operacoes.Ola;

    /**
     * Construtor da classe shared Teclado
     */
    public SharedTeclado() {
    }

    /**
     * Função que retorna a operação
     *
     * @return
     */
    public Operacoes getOperacao() {
        return operacao;
    }

    /**
     * Função que define operação
     *
     * @param operacao
     */
    public void setOperacao(Operacoes operacao) {
        this.operacao = operacao;
    }

    /**
     * ENUMERAÇÕES PARA AS OPERAÇÕES A REALIZAR
     */
    public enum Operacoes {
        INICIAR, CANCELAR, PARAR, REINICIAR, FECHAR, Ola
    }
}
