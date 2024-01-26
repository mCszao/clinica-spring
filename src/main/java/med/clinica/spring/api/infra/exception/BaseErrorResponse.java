package med.clinica.spring.api.infra.exception;

import org.springframework.validation.FieldError;

import java.time.LocalDateTime;

public record BaseErrorResponse(LocalDateTime date, String message) {

  public static BaseErrorResponse of(LocalDateTime date, String message){
      return new BaseErrorResponse(date, message);
  }

  public BaseErrorResponse(FieldError error){
        this(LocalDateTime.now(), error.getField().concat(" \n on field ").concat(error.getDefaultMessage()));
  }
}
