package med.clinica.spring.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(BaseErrorResponse.of(LocalDateTime.now(), ex.getClass().getName()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleInvalidArgument(MethodArgumentNotValidException ex, WebRequest request){
        return new ResponseEntity<>(ex.getFieldErrors().stream().map(BaseErrorResponse::new).toList(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataViolationIntegrity(){
        return new ResponseEntity<>(BaseErrorResponse.of(LocalDateTime.now(), "sensitive data duplicated, please verify your form again"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<BaseErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        return new ResponseEntity<>(BaseErrorResponse.of(LocalDateTime.now(), "Access Denied"), new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<BaseErrorResponse> handleAuthException(AuthException ex) {
        return new ResponseEntity<>(BaseErrorResponse.of(LocalDateTime.now(), "Erro de autenticação jwt"), new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

}
