package api.pedidos.documentation;


import api.pedidos.dto.AuthKeycloakDTO;
import api.pedidos.dto.form.AuthKeycloak;
import api.pedidos.dto.form.RefreshKeycloak;
import api.pedidos.exception.ErrorDetalhes;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.links.LinkParameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "AccessToken", description = "Generate token and refresh token by keycloak", externalDocs = @ExternalDocumentation(
        description = "documentation keycloak", url = "https://www.keycloak.org/documentation"
))
public interface AuthDocumentation {

    @Operation(operationId = "Auth", description = "Authenticate The User",
            requestBody = @RequestBody(required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Generates Bearer token for specified login",
                    links = @Link(operationRef = "Curl Token Postman ", ref = "", parameters = @LinkParameter(name = "Example Token Curl", expression = "curl --location 'http://localhost:8081/token' \\\n" +
                            "--header 'Content-Type: application/json' \\\n" +
                            "--data '{\n" +
                            "    \"clientId\": \"client\",\n" +
                            "    \"username\": \"user\",\n" +
                            "    \"password\": \"user\",\n" +
                            "    \"grantType\": \"password\"\n" +
                            "}'")),
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AuthKeycloak.class))),
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
    ResponseEntity<AuthKeycloakDTO> token(AuthKeycloak authKeycloak);


    @Operation(operationId = "Refresh auth", description = "Refresh User",
            requestBody = @RequestBody(required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Generates Bearer token for specified login",
                            links = @Link(operationRef = "Curl Refresh Token Postman ", ref = "", parameters = @LinkParameter(name = "Example Refresh Token Curl", expression = "curl --location 'http://localhost:8081/refresh-token' \\\n" +
                                    "--header 'Content-Type: application/json' \\\n" +
                                    "--data '{\n" +
                                    "    \"refresh_token\": \"eyJhbGciOiJIUzUxMiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJhMmY0ZjBkYS1hNDFmLTRhODQtODM0YS00ZjIwOTY5M2M2ZGIifQ.eyJleHAiOjE3MTI1NDk5NDEsImlhdCI6MTcxMjU0ODE0MSwianRpIjoiODk0YTg0N2QtMWU2MC00ZjQ3LThkMDAtYTQxYjkxZDk3ZWU1IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy9yZWFsbSIsImF1ZCI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9yZWFsbXMvcmVhbG0iLCJzdWIiOiJjMGZjZGVjZC1kMjE0LTRhNzgtOWZiNi04NzI3ODAwNTg1NTAiLCJ0eXAiOiJSZWZyZXNoIiwiYXpwIjoiY2xpZW50Iiwic2Vzc2lvbl9zdGF0ZSI6IjY0ZjUyYWNlLWJjZGMtNDBiMy1iYTc5LTAwOTQzNzkzNWYyYyIsInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsInNpZCI6IjY0ZjUyYWNlLWJjZGMtNDBiMy1iYTc5LTAwOTQzNzkzNWYyYyJ9.QZEMTYYCd9Ohfn1k87tdOrjfpZkrN0cv7qDa3V6ylb8l1o5rRva7vjdb5j0RvmVKpuLSippQAW3W9cwyQeqNQA\",\n" +
                                    "    \"clientId\": \"client\"\n" +
                                    "}'")),
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AuthKeycloak.class))),
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
    ResponseEntity<AuthKeycloakDTO> refreshToken(RefreshKeycloak refresh);
}