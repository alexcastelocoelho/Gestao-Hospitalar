package com.example.api.service;

import com.example.api.dto.AtualizarPacienteDto;
import com.example.api.dto.CriarPacienteDto;
import com.example.api.exception.CpfConflictException;
import com.example.api.exception.EmptyList;
import com.example.api.exception.ResourceNotFound;
import com.example.api.model.PacienteModel;
import com.example.api.repository.PacienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PacienteService {

    final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public PacienteModel criarPaciente(CriarPacienteDto pacienteDto) {
        PacienteModel paciente = new PacienteModel();

        if (pacienteRepository.existsByCpf(pacienteDto.cpf())) {
            throw new CpfConflictException("Cpf já registrado na base de dados");
        }

        BeanUtils.copyProperties(pacienteDto, paciente);
        return pacienteRepository.save(paciente);
    }

    public List<PacienteModel> listarPacientes() {
        var pacientes = pacienteRepository.findAll();
        if (pacientes.isEmpty()) {
            throw new EmptyList();
        }
        return pacientes;
    }


    public PacienteModel listarUmPaciente(UUID id) {
        return pacienteRepository.findById(id).orElseThrow(() -> new ResourceNotFound("paciente não localizado"));
    }

    public void atualizarPaciente(UUID id, AtualizarPacienteDto pacienteDto) {
        var paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()) {
            PacienteModel pacienteAtualizar = paciente.get();

            if (pacienteDto.nome() != null){
                pacienteAtualizar.setNome(pacienteDto.nome());
            }

            if (pacienteDto.idade() != null){
                pacienteAtualizar.setIdade(pacienteDto.idade());
            }

            if (pacienteDto.contato() != null){
                pacienteAtualizar.setContato(pacienteDto.contato());
            }

            if (pacienteDto.endereco() != null){
                pacienteAtualizar.setEndereco(pacienteDto.endereco());
            }

            pacienteRepository.save(pacienteAtualizar);
        } else {
            throw new ResourceNotFound("paciente não localizado");
        }
    }

    public void deletarPaciente(UUID id) {
        var paciente = pacienteRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Paciente não localizado"));
        pacienteRepository.delete(paciente);
    }
}
