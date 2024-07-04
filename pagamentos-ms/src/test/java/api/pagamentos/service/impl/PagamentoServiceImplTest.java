package api.pagamentos.service.impl;


import api.pagamentos.client.PedidosClient;
import api.pagamentos.constantes.StatusPedido;
import api.pagamentos.dto.EnderecoDTO;
import api.pagamentos.dto.PagamentoStatusDTO;
import api.pagamentos.dto.PedidoStatusDTO;
import api.pagamentos.dto.UsuarioDTO;
import api.pagamentos.dto.form.PagamentoForm;
import api.pagamentos.entity.EnderecoEntity;
import api.pagamentos.entity.PagamentoEntity;
import api.pagamentos.entity.UsuarioEntity;
import api.pagamentos.exception.PagamentosException;
import api.pagamentos.mapper.MapperPagamento;
import api.pagamentos.repository.PagamentoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PagamentoServiceImplTest {

    @Mock
    PagamentoRepository repository;
    @Mock
    MapperPagamento mapper;
    @Mock
    PedidosClient client;
    @InjectMocks
    PagamentoServiceImpl service;

    @DisplayName("Testa método getAllService.")
    @Test
    void testaGetAllService() {
        PagamentoEntity pagamentoEntity = getPagamentoEntity();
        when(repository.findAll(PageRequest.of(1, 1, Sort.by("valor").ascending()))).thenReturn(new PageImpl<>(List.of(pagamentoEntity)));
        when(mapper.pedidoEntityToStatusDTO(getPagamentoEntity())).thenReturn(new PagamentoStatusDTO("1", new UsuarioDTO("t@gmail.com", "",
                "00000000000", "26/02/2002", new EnderecoDTO("rs", "poa", "f", "00000000", "")), "", 1, "nome",
                "", 1.0, 1.0, 1, "", StatusPedido.PROCESSANDO, true));
        Assertions.assertNotNull(service.getAllService(1, 1));
    }

    @DisplayName("Testa exceção do método getAllService.")
    @Test
    void testaExcecaoPedidosExceptionNoMetodoGetAllService() {
        when(repository.findAll()).thenThrow(PagamentosException.class);
        Assertions.assertThrows(PagamentosException.class, () -> service.getAllService(1, 1));
    }

    @DisplayName("Testa método saveService.")
    @Test
    void testaSaveService() {
        PagamentoForm form = getPagamentoForm();
        PedidoStatusDTO dto = getPedidoStatusDTO();
        when(client.getStatusIdPedido(1L)).thenReturn(Optional.of(dto));
        when(mapper.pagamentoDTOToPagamentoEntity(form, dto, false)).thenReturn(getPagamentoEntity());
        when(mapper.pedidoEntityToStatusDTO(getPagamentoEntity())).thenReturn(getPagamentoStatusDTO());
        Assertions.assertNotNull(service.saveService(form, 1L));
    }

    @DisplayName("Testa empty no método saveService.")
    @Test
    void testaEmptySaveService() {
        when(client.getStatusIdPedido(1L)).thenReturn(Optional.empty());
        Assertions.assertEquals(Optional.empty(), service.saveService(getPagamentoForm(), 1L));
    }

    @DisplayName("Deve lançar exception do tipo PagamentosException no método saveService.")
    @Test
    void lancaExcecaoPagamentosExceptionNoSaveService() {
        when(client.getStatusIdPedido(1L)).thenThrow(PagamentosException.class);
        Assertions.assertThrows(PagamentosException.class, () -> service.saveService(getPagamentoForm(), 1L));
    }

    private PagamentoEntity getPagamentoEntity() {
        PagamentoEntity pagamentoEntity = new PagamentoEntity();
        pagamentoEntity.setId(1L);
        pagamentoEntity.setUsuario(new UsuarioEntity(1L, "nome", "t@gmail.com", "00000000000", "26/02/2002",
                new EnderecoEntity(1L, "rs", "poa", "f", "00000000", "")));
        pagamentoEntity.setNomeProduto("nome");
        pagamentoEntity.setTipo("tipo");
        pagamentoEntity.setPesoKg(2.0);
        pagamentoEntity.setValor(2.0);
        pagamentoEntity.setCartao("visa");
        pagamentoEntity.setParcelas(1);
        pagamentoEntity.setQuantidade(1);
        pagamentoEntity.setHorario("agora");
        pagamentoEntity.setStatus(StatusPedido.PAGO);
        pagamentoEntity.setPagamentoAceito(true);
        return pagamentoEntity;
    }

    private PagamentoStatusDTO getPagamentoStatusDTO() {
        return new PagamentoStatusDTO("1", new UsuarioDTO("t@gmail.com", "nome", "",
                "00000000000", new EnderecoDTO("rs", "poa", "f", "00000000", "")), "visa",
                1, "nome", "tipo", 1.0, 1.0, 1, "agora", StatusPedido.PAGO, true);
    }

    private PedidoStatusDTO getPedidoStatusDTO() {
        return new PedidoStatusDTO("1", "nome", "tipo", 2.0, 2.0, StatusPedido.APROVADO);
    }

    private PagamentoForm getPagamentoForm() {
        return new PagamentoForm(new UsuarioDTO("t@gmail.com", "nome",
                "00000000000", "26/02/2002", new EnderecoDTO("rs", "poa", "f", "00000000", "")), 1, "visa", 1);
    }
}