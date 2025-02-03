package com.example.api.controller;

import com.example.api.dto.AtualizarPacienteDto;
import com.example.api.dto.CriarPacienteDto;
import com.example.api.model.PacienteModel;
import com.example.api.service.PacienteService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<PacienteModel> criarPaciente(@RequestBody @Valid CriarPacienteDto pacienteDto) {
        var paciente = pacienteService.criarPaciente(pacienteDto);
        return new ResponseEntity<>(paciente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PacienteModel>> listarPacientes() {
        var pacientes = pacienteService.listarPacientes();
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteModel> listarUmPaciente(@PathVariable("id") UUID id){
        var paciente = pacienteService.listarUmPaciente(id);
        return new ResponseEntity<>(paciente, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizarPaciente(@PathVariable("id") UUID id, @RequestBody @Valid AtualizarPacienteDto pacienteDto) {
        pacienteService.atualizarPaciente(id, pacienteDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPaciente(@PathVariable("id") UUID id){
        pacienteService.deletarPaciente(id);
        return ResponseEntity.ok("Paciente deletado com sucesso");
    }

}
