package api.pagamentos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name = "UsuarioEntity")
@Table(name = "USUARIOS")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "nomeCompleto")
    @NotBlank(message = "O nome completo não pode estar vazio")
    private String nomeCompleto;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "dataNascimento")
    private String dataNascimento;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_Id", referencedColumnName = "id")
    private EnderecoEntity enderecos;
}
