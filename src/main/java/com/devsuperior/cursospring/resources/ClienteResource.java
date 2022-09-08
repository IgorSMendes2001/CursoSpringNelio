package com.devsuperior.cursospring.resources;

import com.devsuperior.cursospring.domain.Cliente;
import com.devsuperior.cursospring.dto.ClienteDTO;
import com.devsuperior.cursospring.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
    @Autowired
    private ClienteService service;
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findALl(){
        List<Cliente>lista=service.findAll();
        List<ClienteDTO>listDto= lista.stream().map(obj-> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
    @GetMapping("/page")
    public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page",defaultValue = "0") Integer page, @RequestParam(value = "linesPerPage",defaultValue = "24") Integer linesPerPage,
                                                       @RequestParam (value = "orderBy",defaultValue = "nome") String orderBy, @RequestParam (value = "direction",defaultValue = "ASC") String direction){
        Page<Cliente>lista=service.findPage(page,linesPerPage,orderBy,direction);
        Page<ClienteDTO>listDto= lista.map(obj-> new ClienteDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Integer id){
        Cliente response=service.findById(id);
        return ResponseEntity.ok().body(response);
    }
    @PostMapping()
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO objDTO){
        Cliente obj = service.fromDTO(objDTO);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO,@PathVariable Integer id){
        Cliente obj = service.fromDTO(objDTO);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
