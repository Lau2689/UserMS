package usermicroservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<Object> handlerResourceNotFoundException(Exception ex, WebRequest request){
    ExceptionModel exception = new ExceptionModel(ex.getMessage(), request.getDescription(false));

    return new ResponseEntity <>(exception, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BadArgumentsException.class)
    public final ResponseEntity<Object> handlerBadArgumentsException(Exception ex, WebRequest request){
        ExceptionModel exception = new ExceptionModel(ex.getMessage(), request.getDescription(false));

        return new ResponseEntity <>(exception, HttpStatus.BAD_REQUEST);
    }


}
