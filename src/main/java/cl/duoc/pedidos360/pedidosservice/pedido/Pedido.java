package cl.duoc.pedidos360.pedidosservice.pedido;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cliente;
    private String estado;
    private Double total;

    protected Pedido() {
    }

    public Pedido(String cliente, String estado, Double total) {
        this.cliente = cliente;
        this.estado = estado;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public String getEstado() {
        return estado;
    }

    public Double getTotal() {
        return total;
    }
}
