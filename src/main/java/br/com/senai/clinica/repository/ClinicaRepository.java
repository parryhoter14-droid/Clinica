package br.com.senai.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.clinica.entity.Dono;

public interface ClinicaRepository extends JpaRepository <Dono, Long>{
    
}
