package com.devsuperior.cursospring.exceptions;

import com.devsuperior.cursospring.resources.ErroDTO;
import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDTO extends ErroDTO {
    private static final long serialVersionUID = 1L;
    private List<FieldMessage>errors = new ArrayList<>();
    public ValidationErrorDTO(Integer status,String msg,Long timeStamp){
        super(status,msg,timeStamp);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName,String mensagem){
        errors.add(new FieldMessage(fieldName,mensagem));
    }
}
