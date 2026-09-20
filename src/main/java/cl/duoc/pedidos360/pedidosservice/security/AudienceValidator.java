package cl.duoc.pedidos360.pedidosservice.security;

import java.util.List;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

/**
 * Verifica que el token JWT haya sido emitido para esta API (audience),
 * y no para otra aplicación registrada en el mismo tenant.
 */
class AudienceValidator implements OAuth2TokenValidator<Jwt> {

    private final List<String> audienciasValidas;

    AudienceValidator(List<String> audienciasValidas) {
        this.audienciasValidas = audienciasValidas;
    }

    @Override
    public OAuth2TokenValidatorResult validate(Jwt jwt) {
        boolean coincide = jwt.getAudience().stream().anyMatch(audienciasValidas::contains);

        if (coincide) {
            return OAuth2TokenValidatorResult.success();
        }

        OAuth2Error error = new OAuth2Error(
                "invalid_token",
                "El token no fue emitido para la API de Pedidos360 (audience inválida)",
                null);
        return OAuth2TokenValidatorResult.failure(error);
    }
}
