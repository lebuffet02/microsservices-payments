package api.pagamentos.dto;

import api.pagamentos.exception.EmailException;
import api.pagamentos.exception.PagamentosException;
import api.pagamentos.exception.ResponseEnum;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import java.util.regex.Matcher;

@Builder
public record UsuarioDTO(
        @Pattern(regexp = EMAIL_PATTERN)
        String email,
        String nomeCompleto,
        String cpf,
        String dataNascimento,
        EnderecoDTO enderecoDTO) {


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
            throw new PagamentosException(ResponseEnum.ERRO_INTERNO, ex.getMessage());
        }
    }
}
