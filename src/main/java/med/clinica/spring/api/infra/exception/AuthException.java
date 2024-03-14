package med.clinica.spring.api.infra.exception;

public class AuthException extends RuntimeException{
    public AuthException(String msg){
        super(msg);
    }
}
