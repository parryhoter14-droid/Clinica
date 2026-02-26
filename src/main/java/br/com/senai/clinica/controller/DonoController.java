package br.com.senai.clinica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.clinica.entity.Dono;
import br.com.senai.clinica.exception.Response;
import br.com.senai.clinica.repository.ClinicaRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping
public class DonoController {

    @Autowired
    private ClinicaRepository repository;

    @PostMapping
    public Dono criaClinica(@RequestBody Dono entity){
        Dono saved = repository.save(entity);
        return saved;
    }
    
    @GetMapping
    public List<Dono> returnTodos(){
        return repository.findAll();
    }

     @PutMapping("/{id}")
  public  Response Atualizar(@PathVariable Long id, @RequestBody Dono entity) {

    if (!repository.existsById(id)) {
      return new Response(404, "Produto não encontrado");
    }

    Dono clinicaAntigo = repository.findById(id).get();

    if (entity.getNome() != null) {
      clinicaAntigo.setNome(entity.getNome());
    }

    if (entity.getTelefone() != null) {
      clinicaAntigo.setTelefone(entity.getTelefone());
    }

    repository.save(clinicaAntigo);

    return new Response(200, "produto atualizado!");

  }

}
