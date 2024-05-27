package api.pedidos.exception;

import lombok.Getter;

@Getter
public class EmailException extends RuntimeException {

    private final String tipo;

    public EmailException(ResponseEnum response, String mensagem) {
        super(String.format("%s", mensagem));
        this.tipo = response.tipo;
    }
}
