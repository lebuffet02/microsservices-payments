package api.pedidos.exception;

public enum ResponseEnum {

    ERRO_EXTERNO("Externo"),
    ERRO_INTERNO("Interno");

    public final String tipo;

    ResponseEnum(String tipo) {
        this.tipo = tipo;
    }
}
