package com.devsuperior.cursospring.services.validation;

import com.devsuperior.cursospring.domain.Cliente;
import com.devsuperior.cursospring.domain.enuns.TipoCliente;
import com.devsuperior.cursospring.dto.ClienteNewDTO;
import com.devsuperior.cursospring.exceptions.FieldMessage;
import com.devsuperior.cursospring.repositories.ClienteRepository;
import com.devsuperior.cursospring.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
    @Autowired
    private ClienteRepository repository;
    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        if(objDto.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfcnpj())){
            list.add(new FieldMessage("cpfcnpj","CPF inválido!"));
        }
        if(objDto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfcnpj())){
            list.add(new FieldMessage("cpfcnpj","CNPJ inválido!"));
        }
        Cliente aux = repository.findByEmail(objDto.getEmail());
        if(aux!=null){
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
