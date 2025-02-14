package api.pagamentos.mapper;

import api.pagamentos.constantes.StatusPedido;
import api.pagamentos.dto.request.PagamentoRequest;
import api.pagamentos.dto.response.EnderecoDTO;
import api.pagamentos.dto.response.PagamentoStatusDTO;
import api.pagamentos.dto.response.PedidoStatusDTO;
import api.pagamentos.dto.response.UsuarioDTO;
import api.pagamentos.entity.EnderecoEntity;
import api.pagamentos.entity.PagamentoEntity;
import api.pagamentos.entity.UsuarioEntity;
import api.pagamentos.utils.MaskUtils;
import org.springframework.stereotype.Component;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class MapperPagamento {

    private static final String PATTERN_TIME = "(dd-MM-yyyy)'T'(HH:mm:ss.SSSSSSXXX)";

    public PagamentoStatusDTO pedidoEntityToStatusDTO(PagamentoEntity pagamentoEntity) {
        return PagamentoStatusDTO.builder()
                .id(pagamentoEntity.getId().toString())
                .usuarioDTO(toUsuarioDTO(pagamentoEntity.getUsuario()))
                .cartao(pagamentoEntity.getCartao())
                .parcelas(pagamentoEntity.getParcelas())
                .nomeProduto(pagamentoEntity.getNomeProduto())
                .tipo(pagamentoEntity.getTipo())
                .pesoKg(pagamentoEntity.getPesoKg())
                .valor(pagamentoEntity.getValor())
                .quantidade(pagamentoEntity.getQuantidade())
                .horario(pagamentoEntity.getHorario())
                .status(pagamentoEntity.getStatus())
                .isPagamentoAceito(pagamentoEntity.isPagamentoAceito())
                .build();
    }

    public PagamentoEntity pagamentoDTOToPagamentoEntity(PagamentoRequest pagamentoRequest, PedidoStatusDTO pedidoStatusDTO, boolean isAceito) {
        return PagamentoEntity.builder()
                .usuario(toUsuarioEntity(pagamentoRequest.usuarioDTO()))
                .cartao(pagamentoRequest.cartao())
                .parcelas(pagamentoRequest.parcelas())
                .quantidade(pagamentoRequest.quantidade())
                .nomeProduto(pedidoStatusDTO.nomeProduto())
                .tipo(pedidoStatusDTO.tipo())
                .pesoKg(pedidoStatusDTO.pesoKg())
                .valor(pedidoStatusDTO.valor())
                .horario(OffsetDateTime.now().format(DateTimeFormatter.ofPattern(PATTERN_TIME)))
                .status(StatusPedido.PAGO)
                .isPagamentoAceito(isAceito)
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