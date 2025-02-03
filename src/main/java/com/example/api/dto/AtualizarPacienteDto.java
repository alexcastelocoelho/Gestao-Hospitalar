package com.example.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record AtualizarPacienteDto(

        @Size(min = 3, max = 60, message = "informe seu nome completo, por gentileza")
        String nome,

        @Min(value = 3, message = "idade minima do paciente é 3 anos")
        @Max(value = 110, message = "idade máxima permitida é 120 anos")
        Integer idade,

        @Pattern(regexp = "^\\(?[1-9]{2}\\)? ?(?:[2-8]|9[0-9])[0-9]{3}\\-?[0-9]{4}$", message = "numero invalido, informe o ddd junto com o o número, por gentileza")
        String contato,

        @Size(min = 8, max = 255, message = "Endereço precisa ter pelo menso 8 caracteres")
        String endereco

) {
}
