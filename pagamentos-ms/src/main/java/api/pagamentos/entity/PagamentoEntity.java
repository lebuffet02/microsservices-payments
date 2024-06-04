package api.pagamentos.entity;

import api.pagamentos.constantes.StatusPagamento;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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

    @Column(name = "peso", nullable = false)
    @Positive(message = "Não pode ser negativo.")
    private double pesoKg;

    @Column(name = "valor", nullable = false)
    @PositiveOrZero(message = "Não pode ser negativo.")
    private double valor;

    @Column(name = "cartao", nullable = false)
    private String cartao;

    @Column(name = "parcelas", nullable = false)
    @Positive(message = "Não pode ser negativo.")
    private int parcelas;

    @Column(name = "quantidade", nullable = false)
    @Positive(message = "Não pode ser negativo.")
    private int quantidade;

    @Column(name = "horario", nullable = false)
    private String horario;

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private StatusPagamento status;
}
