package com.example.api.controller;

import com.example.api.dto.AtualizarPacienteDto;
import com.example.api.dto.AtualizarResponsavelPacienteDto;
import com.example.api.dto.CriarResponsavelPacienteDto;
import com.example.api.model.PacienteModel;
import com.example.api.model.ResponsavelPacienteModel;
import com.example.api.service.ResponsavelPacienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/responsavel")
public class ResponsavelPacienteController {

    final ResponsavelPacienteService responsavelPacienteService;

    public ResponsavelPacienteController(ResponsavelPacienteService responsavelPacienteService) {
        this.responsavelPacienteService = responsavelPacienteService;
    }

    @PostMapping
    public ResponseEntity<ResponsavelPacienteModel> criarResponsavel(@RequestBody @Valid CriarResponsavelPacienteDto responsavelPacienteDto){
        var responsavel = responsavelPacienteService.criarResponsavel(responsavelPacienteDto);
        return new ResponseEntity<>(responsavel, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ResponsavelPacienteModel>> listarResponsaveis() {
        var responsaveis = responsavelPacienteService.listarResponsaveis();
        return new ResponseEntity<>(responsaveis, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsavelPacienteModel> listarUmResponsavel(@PathVariable("id") UUID id){
        var responsavel = responsavelPacienteService.listarUmResponsavel(id);
        return new ResponseEntity<>(responsavel, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizarResponsavel(@PathVariable("id") UUID id, @RequestBody @Valid AtualizarResponsavelPacienteDto responsavelPacienteDto) {
        responsavelPacienteService.atualizarResponsavel(id, responsavelPacienteDto);
        return ResponseEntity.noContent().build();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarResponsavel(@PathVariable("id") UUID id){
        responsavelPacienteService.deletarResponsavel(id);
        return ResponseEntity.ok("Responsavel deletado com sucesso");
    }


}
