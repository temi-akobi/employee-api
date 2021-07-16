package za.co.CrudApi.exception;


import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(EmployeeNotFoundException e, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage( HttpStatus.NOT_FOUND.value(), new Date(), e.getMessage(), request.getDescription( false ) );
        return new ResponseEntity<ErrorMessage>( errorMessage, HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(ResourceNotFoundException e, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage( HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), e.getMessage(), request.getDescription( false ) );
        return new ResponseEntity<ErrorMessage>( errorMessage, HttpStatus.INTERNAL_SERVER_ERROR );
    }

    @ExceptionHandler(InvalidIDException.class)
    public ResponseEntity<ErrorMessage> idNotFoundException(InvalidIDException e, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage( HttpStatus.BAD_REQUEST.value(), new Date(), e.getMessage(), request.getDescription( false ) );
        return new ResponseEntity<ErrorMessage>( errorMessage, HttpStatus.BAD_REQUEST );
    }

    @Override
    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders httpHeaders,HttpStatus status,WebRequest request){
        Map<String, List<String>> body = new HashMap<>(  );
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map( DefaultMessageSourceResolvable::getDefaultMessage )
                .collect( Collectors.toList());
        body.put( "errors",errors );
        return new ResponseEntity( body,HttpStatus.BAD_REQUEST );

    }

    @ExceptionHandler(EntityAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> handlerDatabaseException (EntityAlreadyExistException e, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage( HttpStatus.BAD_REQUEST.value(), new Date(), e.getMessage(), request.getDescription( false ) );
        return new ResponseEntity<ErrorMessage>( errorMessage, HttpStatus.BAD_REQUEST );
    }



}
