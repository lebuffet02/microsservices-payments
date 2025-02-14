package api.pagamentos.dto.response;

import lombok.Builder;

@Builder
public record EnderecoDTO(String estado, String cidade, String logradouro, String cep, String numero){}
