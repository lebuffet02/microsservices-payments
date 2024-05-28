package api.pedidos.dto.form;

import api.pedidos.exception.EmailException;
import api.pedidos.exception.PedidosException;
import api.pedidos.exception.ResponseEnum;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import java.util.regex.Matcher;

@Builder
public record PedidoDTO(
        @Pattern(regexp = EMAIL_PATTERN)
        String email,
        String nome,
        String tipo,
        int peso) {


    private static final String EMAIL_PATTERN =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";


    private static final java.util.regex.Pattern PATTERN = java.util.regex.Pattern.compile(EMAIL_PATTERN);

    public boolean isEmailValid(final String email) {
        try {
            Matcher matcher = PATTERN.matcher(email);
            if(matcher.matches()) {
                return true;
            }
            throw new EmailException(ResponseEnum.ERRO_INTERNO, "Email já existe.");
        } catch (RuntimeException ex) {
            throw new PedidosException(ResponseEnum.ERRO_INTERNO, ex.getMessage());
        }
    }
}