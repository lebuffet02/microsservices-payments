package api.pagamentos.exception.handler;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ErrorDetalhes(
        @JsonProperty("error_momento") String errorMomento,
        @JsonProperty("error_descricao") String errorDescricao,
        @JsonProperty("error_tipo") String errorTipo,
        @JsonProperty("error_code") String errorCode,
        @JsonProperty("error_endereco") String errorIp){}
