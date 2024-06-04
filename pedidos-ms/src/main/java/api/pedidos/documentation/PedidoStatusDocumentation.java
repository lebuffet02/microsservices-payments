package api.pedidos.documentation;

import api.pedidos.constantes.StatusPedido;
import api.pedidos.dto.PedidoStatusDTO;
import api.pedidos.exception.ErrorDetalhes;
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
import java.util.Optional;

@Tag(name = "Pedido Status", description = "Admin pode atualizar e verificar status")
public interface PedidoStatusDocumentation {

    @Operation(operationId = "Status de um pedido", description = "analisa pedido",
            requestBody = @RequestBody(required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "retorna status do pedido buscado",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = PedidoStatusDTO.class))),
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
    ResponseEntity<List<PedidoStatusDTO>> statusPedidoController(@RequestParam("statusPagamento") StatusPedido statusPedido);

    @Operation(operationId = "Atualiza Status do pedido", description = "Atualiza status pedido",
            requestBody = @RequestBody(required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "retorna status atualizado",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = PedidoStatusDTO.class))),
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
    ResponseEntity<?> atualizaStatusController(@PathVariable("id") Long id, @RequestParam("statusPagamento") StatusPedido statusPedido);

    @Operation(operationId = "Busca pedido pelo id", description = "Busca pedido de um usuário através do seu id",
            requestBody = @RequestBody(required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "retorna 204 caso pedido seja deletado",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = PedidoStatusDTO.class))),
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
    ResponseEntity<Optional<PedidoStatusDTO>> statusByIdController(@RequestParam("pedidoId") Long pedidoId);
}
