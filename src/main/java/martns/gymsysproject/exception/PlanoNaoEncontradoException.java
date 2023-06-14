package martns.gymsysproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Ja existe no banco")
public class PlanoNaoEncontradoException extends RuntimeException{
    
    public PlanoNaoEncontradoException(String message) {
        super(message);
    }
}
