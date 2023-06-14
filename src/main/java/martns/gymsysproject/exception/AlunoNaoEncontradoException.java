package martns.gymsysproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Membro nao encontrado")
public class AlunoNaoEncontradoException extends RuntimeException{
    
    public AlunoNaoEncontradoException(String message) {
        super(message);
    }

}
