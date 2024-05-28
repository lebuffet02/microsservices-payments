package api.pagamentos.exception;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {

    private final String tipo;

    public AuthException(ResponseEnum response, String mensagem) {
        super(String.format("%s", mensagem));
        this.tipo = response.tipo;
    }
}


