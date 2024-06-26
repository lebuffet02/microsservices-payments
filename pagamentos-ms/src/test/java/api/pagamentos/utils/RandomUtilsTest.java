package api.pagamentos.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RandomUtilsTest {

    @DisplayName("Não deve ser nulo ao chamar o método generateCode.")
    @Test
    void testaGenerateCodeNaoNulo() {
        Assertions.assertNotNull(RandomUtils.generateCode());
    }

    @DisplayName("O resultado deve ser diferente do esperado.")
    @Test
    void testaValoresDiferentes() {
        Assertions.assertNotEquals("diferente", RandomUtils.generateCode());
    }

    @DisplayName("A instância da classe deve ser igual da esperada.")
    @Test
    void testaInstanciaDoObjeto() {
        Assertions.assertInstanceOf(String.class, RandomUtils.generateCode());
    }

}