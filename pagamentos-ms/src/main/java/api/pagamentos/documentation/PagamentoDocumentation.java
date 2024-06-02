package api.pagamentos.documentation;


import api.pagamentos.dto.PagamentoDTO;
import api.pagamentos.dto.PagamentoStatusDTO;
import api.pagamentos.exception.ErrorDetalhes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Pagamento", description = "Controller gerenciada através do usuário")
public interface PagamentoDocumentation {

    @Operation(operationId = "Pagamentos", description = "Todos os pagamentos",
            requestBody = @RequestBody(required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista paginada com os pagamentos",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = PagamentoStatusDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDetalhes.class))),
                    @ApiResponse(responseCode = "401", description = "Invalid User or Login",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDetalhes.class))),
                    @ApiResponse(responseCode = "403", description = "Forbidden",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDetalhes.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Error",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDetalhes.class))),
            })
    ResponseEntity<?> getAllController(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size);


    @Operation(operationId = "Salva pagamento", description = "Salva pagamento",
            requestBody = @RequestBody(required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Adiciona um novo pagamento",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = PagamentoStatusDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDetalhes.class))),
                    @ApiResponse(responseCode = "401", description = "Invalid User or Login",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDetalhes.class))),
                    @ApiResponse(responseCode = "403", description = "Forbidden",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDetalhes.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Error",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDetalhes.class))),
            })
    ResponseEntity<?> saveController(
            @org.springframework.web.bind.annotation.RequestBody() PagamentoDTO pagamentoDTO);
}
