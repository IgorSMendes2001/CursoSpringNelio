package com.devsuperior.cursospring.services.validation;

import com.devsuperior.cursospring.domain.Cliente;
import com.devsuperior.cursospring.domain.enuns.TipoCliente;
import com.devsuperior.cursospring.dto.ClienteDTO;
import com.devsuperior.cursospring.dto.ClienteNewDTO;
import com.devsuperior.cursospring.exceptions.FieldMessage;
import com.devsuperior.cursospring.repositories.ClienteRepository;
import com.devsuperior.cursospring.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private HttpServletRequest request;
    @Override
    public void initialize(ClienteUpdate ann) {
    }

    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
        @SuppressWarnings("unchecked")
        Map<String,String > map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uri=Integer.parseInt(map.get("id"));
        List<FieldMessage> list = new ArrayList<>();
        Cliente aux = repository.findByEmail(objDto.getEmail());
        if(aux!=null && !aux.getId().equals(uri)){
            list.add(new FieldMessage("email","Email já existente!"));
        }
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
