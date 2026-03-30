package italo.santana.itau_desafio_cartoes.enums;

public enum TiposDeCartoes {
    CARTAO_SEM_ANUIDADE("Cartão sem Anuidade", 1000, 3500),
    CARTAO_COM_CASHBACK("Cartão com Cashback", 5000, 7500),
    CARTAO_DE_PARCEIROS("Cartão de Parceiros", 3000, 5500);

    private String descricao;
    private final double limiteDisponivel;
    private final double rendaMensalMinima;

    TiposDeCartoes(String descricao, double limiteDisponivel, double rendaMensalMinima) {
        this.descricao = descricao;
        this.limiteDisponivel = limiteDisponivel;
        this.rendaMensalMinima = rendaMensalMinima;
    }

    public static TiposDeCartoes fromApi(String input) {
        switch (input.toUpperCase()) {
            case "CARTÃO SEM ANUIDADE":
                return CARTAO_SEM_ANUIDADE;
            case "CARTÃO COM CASHBACK":
                return CARTAO_COM_CASHBACK;
            case "CARTÃO DE PARCEIROS":
                return CARTAO_DE_PARCEIROS;
            default:
                throw new RuntimeException("Enum inválido");
        }
    }

    public String toApi() {
        return this.name();
    }

    public String getDescricao() {
        return descricao;
    }

    public double getLimiteDisponivel() {
        return limiteDisponivel;
    }

    public double getRendaMensalMinima() {
        return rendaMensalMinima;
    }
}
