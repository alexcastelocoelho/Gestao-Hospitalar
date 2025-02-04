package com.example.api.repository;

import com.example.api.model.ResponsavelPacienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResponsavelPacienteRepository extends JpaRepository<ResponsavelPacienteModel, UUID> {
    boolean existsByCpf(String cpf);

}
