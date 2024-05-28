package api.pagamentos.dto;

public record EnderecoDTO(
        String estado,
        String cidade,
        String logradouro,
        String cep,
        String numero) {}
