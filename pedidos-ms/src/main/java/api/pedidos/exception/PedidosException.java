package api.pedidos.exception;

import lombok.Getter;

@Getter
public class PedidosException extends RuntimeException {

    private final String tipo;

    public PedidosException(ResponseEnum response, String mensagem) {
        super(String.format("%s", mensagem));
        this.tipo = response.tipo;
    }
}
