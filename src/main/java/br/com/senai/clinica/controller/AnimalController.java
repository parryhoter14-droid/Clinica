package br.com.senai.clinica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.clinica.entity.Animal;
import br.com.senai.clinica.exception.Response;
import br.com.senai.clinica.repository.AnimalRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/animais")
public class AnimalController {

  @Autowired
  private AnimalRepository repository;

  @PostMapping
  public Animal criaAnimal(@RequestBody Animal entity) {
    Animal saved = repository.save(entity);
    return saved;
  }

  @GetMapping
  public List<Animal> returnTodos() {
    return repository.findAll();
  }

  @PutMapping("/{id}")
  public Response Atualizar(@PathVariable Long id, @RequestBody Animal entity) {

    if (!repository.existsById(id)) {
      return new Response(404, "Animal não encontrado");
    }

    Animal animalAntigo = repository.findById(id).get();

    if (entity.getNome() != null) {
      animalAntigo.setNome(entity.getNome());
    }

    if (entity.getEspecie() != null) {
      animalAntigo.setEspecie(entity.getEspecie());
    }

    if (entity.getRaca() != null) {
      animalAntigo.setRaca(entity.getRaca());
    }

    if (entity.getIdade() != null) {
      animalAntigo.setIdade(entity.getIdade());
    }

    if (entity.getInfosMedicas() != null) {
      animalAntigo.setInfosMedicas(entity.getInfosMedicas());
    }

    if (entity.getStatus() != null) {
      animalAntigo.setStatus(entity.getStatus());
    }

    repository.save(animalAntigo);

    return new Response(200, "Animal atualizado!");
  }

  @DeleteMapping("/{id}")
  public Response deleteAnimal(@PathVariable Long id) {

    if (!repository.existsById(id)) {
      return new Response(404, "Animal não encontrado");
    }

    repository.deleteById(id);

    return new Response(204, "Animal deletado com sucesso");
  }
}