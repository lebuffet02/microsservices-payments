package api.pedidos.dto.form;

import lombok.Builder;

@Builder
public record RefreshKeycloak(String clientId, String refresh_token){}
