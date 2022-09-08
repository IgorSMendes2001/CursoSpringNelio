package com.devsuperior.cursospring.services;

import com.devsuperior.cursospring.domain.Cliente;
import com.devsuperior.cursospring.dto.ClienteDTO;
import com.devsuperior.cursospring.exceptions.DataIntegrityException;
import com.devsuperior.cursospring.exceptions.ObjectNotFoundException;
import com.devsuperior.cursospring.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    public List<Cliente> findAll() {
        List<Cliente> resposta = repository.findAll();
        return resposta;
    }
    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction),
                orderBy);
        return repository.findAll(pageRequest);
    }
    public Cliente findById(Integer id){
        Cliente obj = repository.findById(id).get();
       if(obj==null){
           throw new ObjectNotFoundException("Objeto não encontrado! Id: "+ id + ",Tipo: " + Cliente.class.getName());
       }
       return obj;
    }
    public Cliente insert(Cliente obj){
        obj.setId(null);
        return repository.save(obj);
    }
    public Cliente update(Cliente obj){
        Cliente newObj= findById(obj.getId());
        updateData(newObj,obj);
        return repository.save(newObj);
    }
    public void  delete(Integer id){
        findById(id);
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir um Cliente que possui vínculos com pedidos!");
        }
    }
    public Cliente fromDTO(ClienteDTO objDTO){
        return new Cliente(objDTO.getId(),objDTO.getNome(),objDTO.getEmail(),null,null);
    }
    private void updateData(Cliente newObj,Cliente obj){
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}
