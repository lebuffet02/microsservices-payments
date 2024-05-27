package api.pedidos.exception.handler;

import api.pedidos.exception.*;
import api.pedidos.utils.IpUtils;
import api.pedidos.utils.RandomUtils;
import api.pedidos.utils.TimeUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorDetalhes> errorValidatingToken(AuthException ex) {
        ErrorDetalhes errorDetails = new ErrorDetalhes(TimeUtils.getZoneTimeWithClock(), ex.getMessage(), ex.getTipo(), RandomUtils.generateCode(), IpUtils.getAddress());
        return new ResponseEntity<>(errorDetails, HttpStatusCode.valueOf(500));
    }

    @ExceptionHandler(RefreshException.class)
    public ResponseEntity<ErrorDetalhes> errorValidatingRefreshToken(RefreshException ex) {
        ErrorDetalhes errorDetails = new ErrorDetalhes(TimeUtils.getZoneTimeWithClock(), ex.getMessage(), ex.getTipo(), RandomUtils.generateCode(), IpUtils.getAddress());
        return new ResponseEntity<>(errorDetails, HttpStatusCode.valueOf(500));
    }

    @ExceptionHandler(PedidosException.class)
    public ResponseEntity<ErrorDetalhes> errorPedidosException(PedidosException ex) {
        ErrorDetalhes error = new ErrorDetalhes(TimeUtils.getZoneTimeWithClock(), ex.getMessage(), ex.getTipo(), RandomUtils.generateCode(), IpUtils.getAddress());
        return new ResponseEntity<>(error, HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<ErrorDetalhes> errorEmailException(EmailException ex) {
        ErrorDetalhes error = new ErrorDetalhes(TimeUtils.getZoneTimeWithClock(), ex.getMessage(), ex.getTipo(), RandomUtils.generateCode(), IpUtils.getAddress());
        return new ResponseEntity<>(error, HttpStatusCode.valueOf(400));
    }
}