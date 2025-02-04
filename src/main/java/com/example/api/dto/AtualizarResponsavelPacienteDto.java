package com.example.api.dto;

import com.example.api.utils.cpf.CpfValido;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AtualizarResponsavelPacienteDto(

        @Size(min = 3, max = 60, message = "informe seu nome completo, por gentileza")
        String nome,

        @Pattern(regexp = "^\\(?[1-9]{2}\\)? ?(?:[2-8]|9[0-9])[0-9]{3}\\-?[0-9]{4}$", message = "numero invalido, informe o ddd junto com o o número, por gentileza")
        String contato,

        @Size(min = 8, max = 255, message = "Endereço precisa ter pelo menso 8 caracteres")
        String endereco
) {
}
