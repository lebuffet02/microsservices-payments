package api.pedidos.dto.request;

import lombok.Builder;

@Builder
public record RefreshKeycloak(String clientId, String refresh_token){}
