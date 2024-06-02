package api.pagamentos.mapper;

import api.pagamentos.constantes.StatusPagamento;
import api.pagamentos.dto.EnderecoDTO;
import api.pagamentos.dto.PagamentoDTO;
import api.pagamentos.dto.PagamentoStatusDTO;
import api.pagamentos.dto.UsuarioDTO;
import api.pagamentos.entity.EnderecoEntity;
import api.pagamentos.entity.PagamentoEntity;
import api.pagamentos.entity.UsuarioEntity;
import api.pagamentos.utils.MaskUtils;
import org.springframework.stereotype.Component;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class MapperPagamento {

    private static final String PATTERN_TIME = "dd-MM-yyyy'T'HH:mm:ss.SSSSSSXXX";

    public PagamentoStatusDTO pedidoEntityToStatusDTO(PagamentoEntity pagamentoEntity) {
        return PagamentoStatusDTO.builder()
                .id(pagamentoEntity.getId().toString())
                .usuarioDTO(toUsuarioDTO(pagamentoEntity.getUsuario()))
                .nomeProduto(pagamentoEntity.getNomeProduto())
                .tipo(pagamentoEntity.getTipo())
                .pesoKg(pagamentoEntity.getPesoKg())
                .quantidade(pagamentoEntity.getQuantidade())
                .horario(OffsetDateTime.now().format(DateTimeFormatter.ofPattern(PATTERN_TIME)))
                .status(pagamentoEntity.getStatus())
                .build();
    }

    public PagamentoEntity pagamentoDTOToPagamentoEntity(PagamentoDTO pagamentoDTO) {
        return PagamentoEntity.builder()
                .usuario(toUsuarioEntity(pagamentoDTO.usuarioDTO()))
                .nomeProduto(pagamentoDTO.nomeProduto())
                .tipo(pagamentoDTO.tipo())
                .pesoKg(pagamentoDTO.pesoKg())
                .valor(pagamentoDTO.valor())
                .quantidade(pagamentoDTO.quantidade())
                .status(StatusPagamento.PAGO)
                .build();
    }

    private UsuarioEntity toUsuarioEntity(UsuarioDTO usuarioDTO) {
        return UsuarioEntity.builder()
                .nomeCompleto(usuarioDTO.nomeCompleto())
                .email(usuarioDTO.email())
                .cpf(MaskUtils.maskCpf(usuarioDTO.cpf()))
                .dataNascimento(usuarioDTO.dataNascimento())
                .enderecos(toEnderecoDTO(usuarioDTO.enderecoDTO()))
                .build();
    }

    private EnderecoEntity toEnderecoDTO(EnderecoDTO enderecoDTO) {
        return EnderecoEntity.builder()
                .estado(enderecoDTO.estado() != null ? enderecoDTO.estado().toUpperCase() : null)
                .cidade(enderecoDTO.cidade() != null ?
                        enderecoDTO.cidade().substring(0, 1).toUpperCase().concat(enderecoDTO.cidade().substring(1)) : null)
                .logradouro(enderecoDTO.logradouro() != null ?
                        enderecoDTO.logradouro().substring(0, 1).toUpperCase().concat(enderecoDTO.logradouro().substring(1)) : null)
                .cep(MaskUtils.maskCep(enderecoDTO.cep()))
                .numero(enderecoDTO.numero())
                .build();
    }

    private UsuarioDTO toUsuarioDTO(UsuarioEntity usuarioEntity) {
        return UsuarioDTO.builder()
                .nomeCompleto(usuarioEntity.getNomeCompleto())
                .email(usuarioEntity.getEmail())
                .cpf(usuarioEntity.getCpf())
                .dataNascimento(usuarioEntity.getDataNascimento())
                .enderecoDTO(getEnderecoDTO(usuarioEntity.getEnderecos()))
                .build();
    }

    private EnderecoDTO getEnderecoDTO(EnderecoEntity enderecoEntity) {
        return EnderecoDTO.builder()
                .estado(enderecoEntity.getEstado() != null ? enderecoEntity.getEstado().toUpperCase() : null)
                .cidade(enderecoEntity.getCidade())
                .logradouro(enderecoEntity.getLogradouro())
                .cep(enderecoEntity.getCep())
                .numero(enderecoEntity.getNumero())
                .build();
    }
}