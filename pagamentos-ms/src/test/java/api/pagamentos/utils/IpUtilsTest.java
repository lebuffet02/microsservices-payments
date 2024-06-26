package api.pagamentos.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

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
}