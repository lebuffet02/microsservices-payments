package api.pedidos.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import java.net.InetAddress;
import java.net.UnknownHostException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class IpUtilsTest {

    @DisplayName("Não deve ser nulo ao chamar o método getAddress.")
    @Test
    void testaGetAddressNaoNulo() {
        Assertions.assertNotNull(IpUtils.getAddress());
    }

    @DisplayName("O resultado deve ser diferente do esperado.")
    @Test
    void testaValoresDiferentes() {
        Assertions.assertNotEquals("10.10.10.2", IpUtils.getAddress());
    }

    @DisplayName("A instância da classe deve ser igual a da esperada.")
    @Test
    void testaInstanciaDoObjeto() {
        Assertions.assertInstanceOf(String.class, IpUtils.getAddress());
    }

    @DisplayName("Lança exceção ao ocorrer um erro.")
    @Test
    void testaGetAddressComExcecao() {
        try (MockedStatic<InetAddress> mockedInetAddress = mockStatic(InetAddress.class)) {
            mockedInetAddress.when(InetAddress::getLocalHost).thenThrow(new UnknownHostException());
            assertThat(IpUtils.getAddress()).isEqualTo("Can't possible generate ip address.");
        }
    }
}