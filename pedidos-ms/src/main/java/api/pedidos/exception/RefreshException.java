package api.pedidos.exception;

import lombok.Getter;

@Getter
public class RefreshException extends RuntimeException {

    private final String tipo;

    public RefreshException(ResponseEnum response, String mensagem) {
        super(String.format("%s", mensagem));
        this.tipo = response.tipo;
    }
}
