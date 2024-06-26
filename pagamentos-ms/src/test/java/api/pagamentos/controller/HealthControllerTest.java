package api.pagamentos.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HealthControllerTest {

    @InjectMocks
    HealthController controller;

    @Test
    void testaHealthController() {
        controller.status();
    }
}