package api.pedidos.documentation;

import api.pedidos.dto.PedidoDTO;
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

import java.util.Optional;

@Tag(name = "Pedidos", description = "crud para cadastrar um pedido")
public interface PedidoDocumentation {

    @Operation(operationId = "Todos pedidos", description = "pega todos os pedidos cadastrados",
            requestBody = @RequestBody(required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "retorna lista paginada com pedidos",
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
    ResponseEntity<?> getAllController(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size);


    @Operation(operationId = "Salva pedido", description = "Adiciona um pedido novo",
            requestBody = @RequestBody(required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "retorna objeto pedido cadastrado",
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
    ResponseEntity<Optional<PedidoStatusDTO>> saveController(@org.springframework.web.bind.annotation.RequestBody PedidoDTO dto);

    @Operation(operationId = "Atualiza pedido", description = "atualiza pedido cadastrado",
            requestBody = @RequestBody(required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "retorna objeto de pedido atualizado",
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
    ResponseEntity<Optional<PedidoStatusDTO>> updateController(@PathVariable("id") Long id, @org.springframework.web.bind.annotation.RequestBody PedidoDTO dto);

    @Operation(operationId = "Pega um pedido", description = "Retorna um pedido de um usuário",
            requestBody = @RequestBody(required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "retorna objeto de pedido do usuário",
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
    ResponseEntity<Optional<PedidoStatusDTO>> getByIdController(@PathVariable("id") Long id);


    @Operation(operationId = "Deleta um pedido", description = "deleta pedido",
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
    public ResponseEntity<?> deleteByIdController(@PathVariable("id") Long id);
}