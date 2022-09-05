package com.devsuperior.cursospring.exceptions;

public class ObjectNotFoundException extends RuntimeException{
    private static final long serialLVersionUID=1L;
    public ObjectNotFoundException(String msg){
        super(msg);
    }
    public ObjectNotFoundException(String msg,Throwable cause){
        super(msg,cause);
    }

}
