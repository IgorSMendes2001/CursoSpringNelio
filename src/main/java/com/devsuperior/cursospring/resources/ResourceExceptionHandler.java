package com.devsuperior.cursospring.resources;

import com.devsuperior.cursospring.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
@ExceptionHandler(ObjectNotFoundException.class)
public ResponseEntity<ErroDTO>objectNotFound(ObjectNotFoundException exception, HttpServletRequest httpServletRequest){
    ErroDTO erroDTO=new ErroDTO(HttpStatus.NOT_FOUND.value(), exception.getMessage(),System.currentTimeMillis());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroDTO);
}
@ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<ErroDTO>dataIntegrity(DataIntegrityException exception, HttpServletRequest httpServletRequest){
        ErroDTO erroDTO=new ErroDTO(HttpStatus.BAD_REQUEST.value(), exception.getMessage(),System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroDTO);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO>mathodArgument(MethodArgumentNotValidException exception, HttpServletRequest httpServletRequest) {
        ValidationErrorDTO erroDTO = new ValidationErrorDTO(HttpStatus.BAD_REQUEST.value(), "Erro de Validação", System.currentTimeMillis());
       for (FieldError x: exception.getBindingResult().getFieldErrors()){
           erroDTO.addError(x.getField(),x.getDefaultMessage());
       }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroDTO);
    }
    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ErroDTO> authorization(AuthorizationException e, HttpServletRequest request) {

        ErroDTO err = new ErroDTO(HttpStatus.FORBIDDEN.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
    }
}
