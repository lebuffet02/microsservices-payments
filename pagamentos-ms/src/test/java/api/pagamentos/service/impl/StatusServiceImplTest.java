package api.pagamentos.service.impl;

import api.pagamentos.constantes.StatusPedido;
import api.pagamentos.entity.PagamentoEntity;
import api.pagamentos.entity.UsuarioEntity;
import api.pagamentos.exception.EmailException;
import api.pagamentos.exception.PagamentosException;
import api.pagamentos.mapper.MapperPagamento;
import api.pagamentos.repository.PagamentoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StatusServiceImplTest {

    @Mock
    JavaMailSender mailSender;
    @Mock
    MapperPagamento mapper;
    @Mock
    PagamentoRepository repository;
    @InjectMocks
    StatusServiceImpl service;


    @DisplayName("Testa método buscarStatusService.")
    @Test
    void testaBuscarStatusService() {
       Mockito.when(repository.buscaStatusAtivo(StatusPedido.APROVADO)).thenReturn(List.of(new PagamentoEntity()));
       Mockito.when(mapper.pedidoEntityToStatusDTO(any())).thenReturn(any());
       Assertions.assertNotNull(service.buscarStatusService(StatusPedido.APROVADO));
    }

    @DisplayName("Lança exceção no método buscarStatusService.")
    @Test
    void lancaExcecaoNoMetodoBuscarStatusService() {
        Mockito.when(repository.buscaStatusAtivo(StatusPedido.APROVADO)).thenThrow(PagamentosException.class);
        Assertions.assertThrows(PagamentosException.class, () -> service.buscarStatusService(StatusPedido.RECUSADO));
    }

    @DisplayName("Testa método atualizaStatusService.")
    @Test
    void testaAtualizaStatusService() {
        PagamentoEntity pagamento = getPagamentoEntity();
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(pagamento));
        service.atualizaStatusService(1L, StatusPedido.RECUSADO);
    }

    @DisplayName("Lança exceção no método atualizaStatusService.")
    @Test
    void lancaExcecaoNoMetodoAtualizaStatusService() {
        PagamentoEntity pagamento = getPagamentoEntity();
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(pagamento));
        doThrow(new RuntimeException()).when(repository).atualizaStatusPedido(StatusPedido.RECUSADO, false, 1L);
        Assertions.assertThrows(Exception.class, () -> service.atualizaStatusService(1L, StatusPedido.RECUSADO));
    }

    @DisplayName("Lança exceção EmailException no método atualizaStatusService.")
    @Test
    void lancaExcecaoEmailExceptionNoMetodoAtualizaStatusService() {
        PagamentoEntity pagamentoEntity = getPagamentoEntity();
        pagamentoEntity.setNomeProduto(null);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(pagamentoEntity));
        Assertions.assertThrows(EmailException.class, () -> service.atualizaStatusService(1L, StatusPedido.RECUSADO));
    }

    private PagamentoEntity getPagamentoEntity() {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setEmail("teste@gmail.com");
        PagamentoEntity pagamentoEntity = new PagamentoEntity();
        pagamentoEntity.setPagamentoAceito(true);
        pagamentoEntity.setUsuario(usuarioEntity);
        pagamentoEntity.setNomeProduto("produto");
        pagamentoEntity.setStatus(StatusPedido.PAGO);
        return pagamentoEntity;
    }
}