package br.com.traumfabrik.compraoq.exception;

import br.com.traumfabrik.compraoq.infra.Retorno;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Retorno<String> retorno = new Retorno<>();
        retorno.setTitulo("Erro de validação");
        retorno.setStatus(400);
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            retorno.getDados().add(fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(retorno);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest wr) {
        if(ex instanceof MethodArgumentNotValidException) {
            return handleMethodArgumentNotValid((MethodArgumentNotValidException) ex, null, HttpStatus.BAD_REQUEST, null);
        }else if(ex instanceof ExceptionOfNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(new Date(),ex.getMessage(),wr.getDescription(false)));
        }else if(ex instanceof ExceptionOfValidate) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(new Date(),ex.getMessage(),wr.getDescription(false)));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionResponse(new Date(),ex.getMessage(),wr.getDescription(false)));
        }
    }

}
