package api.pagamentos.exception;

import lombok.Getter;

@Getter
public class PagamentosException extends RuntimeException {

    private final String tipo;

    public PagamentosException(ResponseEnum response, String mensagem) {
        super(String.format("%s", mensagem));
        this.tipo = response.tipo;
    }
}