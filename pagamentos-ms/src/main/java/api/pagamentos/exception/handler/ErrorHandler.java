package api.pagamentos.exception.handler;

import api.pagamentos.exception.*;
import api.pagamentos.utils.IpUtils;
import api.pagamentos.utils.RandomUtils;
import api.pagamentos.utils.TimeUtils;
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

    @ExceptionHandler(PagamentosException.class)
    public ResponseEntity<ErrorDetalhes> errorPedidosException(PagamentosException ex) {
        ErrorDetalhes error = new ErrorDetalhes(TimeUtils.getZoneTimeWithClock(), ex.getMessage(), ex.getTipo(), RandomUtils.generateCode(), IpUtils.getAddress());
        return new ResponseEntity<>(error, HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<ErrorDetalhes> errorEmailException(EmailException ex) {
        ErrorDetalhes error = new ErrorDetalhes(TimeUtils.getZoneTimeWithClock(), ex.getMessage(), ex.getTipo(), RandomUtils.generateCode(), IpUtils.getAddress());
        return new ResponseEntity<>(error, HttpStatusCode.valueOf(400));
    }
}