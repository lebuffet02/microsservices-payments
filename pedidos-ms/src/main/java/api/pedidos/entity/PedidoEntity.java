package api.pedidos.entity;

import api.pedidos.constantes.StatusPedido;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Entity(name = "PedidoEntity")
@Table(name = "PEDIDOS")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "nomeProduto", nullable = false)
    private String nomeProduto;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "peso")
    @Positive(message = "Não pode ser negativo.")
    private double pesoKg;

    @Column(name = "valor")
    @PositiveOrZero(message = "Não pode ser negativo.")
    private double valor;

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private StatusPedido status;
}