package api.pagamentos.mapper;

import api.pagamentos.constantes.StatusPedido;
import api.pagamentos.dto.EnderecoDTO;
import api.pagamentos.dto.PedidoStatusDTO;
import api.pagamentos.dto.UsuarioDTO;
import api.pagamentos.dto.form.PagamentoForm;
import api.pagamentos.entity.EnderecoEntity;
import api.pagamentos.entity.PagamentoEntity;
import api.pagamentos.entity.UsuarioEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MapperPagamentoTest {

    @InjectMocks
    MapperPagamento mapper;

    @DisplayName("mapper entity para dto.")
    @Test
    void testaPedidoEntityToStatusDTO() {
        Assertions.assertNotNull(mapper.pedidoEntityToStatusDTO(new PagamentoEntity(1L, new UsuarioEntity(1L, "", "tes@gmail.com",
                "00000000000", "26/02/2002", new EnderecoEntity(1L, "RS", "poa", "f", "00000000", "1")),
                "nome", "", 1.0, 2.0, "visa", 1, 1, "20", StatusPedido.PROCESSANDO, true)));
    }

    @DisplayName("mapper dto para entity.")
    @Test
    void testaPagamentoDTOToPagamentoEntity() {
        Assertions.assertNotNull(mapper.pagamentoDTOToPagamentoEntity(new PagamentoForm(new UsuarioDTO("tes@gmail.com", "", "00000000000", "26/02/2002", new EnderecoDTO(
                "rs", "poa", "f", "00000000", "1")), 1, "visa", 1), new PedidoStatusDTO("1", "",
        "", 2.0, 2.0, StatusPedido.PAGO), true));
    }
}