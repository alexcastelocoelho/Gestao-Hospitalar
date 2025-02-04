package com.example.api.service;

import com.example.api.dto.AtualizarResponsavelPacienteDto;
import com.example.api.dto.CriarResponsavelPacienteDto;
import com.example.api.exception.CpfConflictException;
import com.example.api.exception.EmptyList;
import com.example.api.exception.ResourceNotFound;
import com.example.api.model.ResponsavelPacienteModel;
import com.example.api.repository.ResponsavelPacienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ResponsavelPacienteService {

    final ResponsavelPacienteRepository responsavelPacienteRepository;

    public ResponsavelPacienteService(ResponsavelPacienteRepository responsavelPacienteRepository) {
        this.responsavelPacienteRepository = responsavelPacienteRepository;
    }

    public ResponsavelPacienteModel criarResponsavel(CriarResponsavelPacienteDto responsavelPacienteDto) {
        ResponsavelPacienteModel responsavelModel = new ResponsavelPacienteModel();

        if (responsavelPacienteRepository.existsByCpf(responsavelPacienteDto.cpf())) {
            throw new CpfConflictException("Cpf já registrado na base de dados");
        }

        BeanUtils.copyProperties(responsavelPacienteDto, responsavelModel);
        return responsavelPacienteRepository.save(responsavelModel);
    }

    public List<ResponsavelPacienteModel> listarResponsaveis() {
        var responsaveis = responsavelPacienteRepository.findAll();
        if (responsaveis.isEmpty()) {
            throw new EmptyList();
        }
        return responsaveis;
    }

    public ResponsavelPacienteModel listarUmResponsavel(UUID id) {
        return responsavelPacienteRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Responsavel não localizado"));
    }

    public void atualizarResponsavel(UUID id, AtualizarResponsavelPacienteDto responsavelPacienteDto) {
        var responsavel = responsavelPacienteRepository.findById(id);
        if (responsavel.isPresent()) {
            ResponsavelPacienteModel responsavelAtualizar = responsavel.get();

            if (responsavelPacienteDto.nome() != null) {
                responsavelAtualizar.setNome(responsavelPacienteDto.nome());
            }

            if (responsavelPacienteDto.contato() != null) {
                responsavelAtualizar.setContato(responsavelPacienteDto.contato());
            }

            if (responsavelPacienteDto.endereco() != null) {
                responsavelAtualizar.setEndereco(responsavelPacienteDto.endereco());
            }

            responsavelPacienteRepository.save(responsavelAtualizar);
        } else {
            throw new ResourceNotFound("Responsavel não localizado");
        }
    }

    public void deletarResponsavel(UUID id) {
        var responsavel = responsavelPacienteRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Responsavel não localizado"));
        responsavelPacienteRepository.delete(responsavel);
    }
}
