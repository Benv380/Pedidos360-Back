package cl.duoc.pedidos360.pedidosservice.pedido;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {

    private final PedidoRepository pedidoRepository;

    public PedidosController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping("/ping")
    public Map<String, Object> ping(@AuthenticationPrincipal Jwt jwt) {
        String usuario = jwt.getClaimAsString("name") != null
                ? jwt.getClaimAsString("name")
                : jwt.getSubject();

        Map<String, Object> claims = new LinkedHashMap<>();
        claims.put("subject", jwt.getSubject());
        claims.put("issuer", jwt.getIssuer());
        claims.put("audience", jwt.getAudience());
        claims.put("issuedAt", jwt.getIssuedAt());
        claims.put("expiresAt", jwt.getExpiresAt());
        claims.put("nombre", jwt.getClaimAsString("name"));
        claims.put("correo", jwt.getClaimAsString("email"));

        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("mensaje", "Hola " + usuario + ", el backend validó tu token correctamente.");
        respuesta.put("jwtClaims", claims);
        return respuesta;
    }

    @GetMapping
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }
}
