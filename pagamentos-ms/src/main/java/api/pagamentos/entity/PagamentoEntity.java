package api.pagamentos.entity;

import api.pagamentos.constantes.StatusPagamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name = "PagamentoEntity")
@Table(name = "PAGAMENTOS")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_Id", referencedColumnName = "id")
    private UsuarioEntity usuario;

    @Column(name = "nomeProduto", nullable = false)
    private String nomeProduto;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "peso")
    @Positive(message = "Não pode ser negativo.")
    private int peso;

    @Column(name = "valor")
    @Positive(message = "Não pode ser negativo.")
    private int valor;

    @Column(name = "horario")
    private LocalDateTime horario = LocalDateTime.now();

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private StatusPagamento status;
}
