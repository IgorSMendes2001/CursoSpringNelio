package com.devsuperior.cursospring.resources;

import com.devsuperior.cursospring.exceptions.DataIntegrityException;
import com.devsuperior.cursospring.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
