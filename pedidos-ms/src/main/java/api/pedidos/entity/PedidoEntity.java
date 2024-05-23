package api.pedidos.entity;

import api.pedidos.constantes.StatusPedido;
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

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "peso", nullable = false)
    @Positive(message = "Não pode ser negativo.")
    private int peso;

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private StatusPedido status;
}