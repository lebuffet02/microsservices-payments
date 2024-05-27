package api.pedidos.dto.auth;

import lombok.Builder;

@Builder
public record RefreshKeycloak(String clientId, String refresh_token){}
