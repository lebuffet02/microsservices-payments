package api.pagamentos.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TimeUtilsTest {

    @DisplayName("Não deve ser nulo ao chamar o método getZoneTimeWithClock.")
    @Test
    void testaGetZoneTimeWithClockNaoNulo() {
        Assertions.assertNotNull(TimeUtils.getZoneTimeWithClock());
    }

    @DisplayName("O resultado deve ser diferente do esperado.")
    @Test
    void testaValoresDiferentes() {
        Assertions.assertNotEquals("29/04/2024", TimeUtils.getZoneTimeWithClock());
    }

    @DisplayName("A instância da classe deve ser igual da esperada.")
    @Test
    void testaInstanciaDoObjeto() {
        Assertions.assertInstanceOf(String.class, TimeUtils.getZoneTimeWithClock());
    }
}