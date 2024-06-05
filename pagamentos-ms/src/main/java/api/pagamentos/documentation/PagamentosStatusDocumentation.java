package api.pagamentos.documentation;

import api.pagamentos.constantes.StatusPedido;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Status Pagamento", description = "Controller gerenciada através do admin")
public interface PagamentosStatusDocumentation {

    @Operation(operationId = "Status Pagamentos", description = "Todos os status dos pagamentos",
            requestBody = @RequestBody(required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retorna os objetos com os respectivos status do pagamento",
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
    ResponseEntity<List<PagamentoStatusDTO>> statusController(@RequestParam("statusPedido") StatusPedido statusPedido);


    @Operation(operationId = "Atualiza Status do Pagamento", description = "Status atualizado de um usuário",
            requestBody = @RequestBody(required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retorna os dados do usuário junto ao seu pagamento atualizado",
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
    ResponseEntity<?> atualizaStatusController(@PathVariable("id") Long id, @RequestParam("statusPedido") StatusPedido statusPedido);
}
