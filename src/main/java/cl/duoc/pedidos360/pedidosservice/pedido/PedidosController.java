package cl.duoc.pedidos360.pedidosservice.pedido;

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
    public Map<String, String> ping(@AuthenticationPrincipal Jwt jwt) {
        String usuario = jwt.getClaimAsString("name") != null
                ? jwt.getClaimAsString("name")
                : jwt.getSubject();

        return Map.of("mensaje", "Hola " + usuario + ", el backend validó tu token correctamente.");
    }

    @GetMapping
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }
}
