package com.example.api.dto;

import com.example.api.utils.cpf.CpfValido;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CriarPacienteDto(

        @NotBlank
        @Size(min = 3, max = 60, message = "informe seu nome completo, por gentileza")
        String nome,

        @NotNull(message = "informe sua idade, por favor")
        @Min(value = 3, message = "idade minima do paciente é 3 anos")
        @Max(value = 110, message = "idade máxima permitida é 120 anos")
        Integer idade,

        @NotBlank(message = "Informe o seu CPF, por favor")
        @CpfValido
        String cpf,

        @NotNull(message = "Informe sua data de nascimento, por favor")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataNascimento,

        @NotBlank(message = "Informe o seu contato")
        @Pattern(regexp = "^\\(?[1-9]{2}\\)? ?(?:[2-8]|9[0-9])[0-9]{3}\\-?[0-9]{4}$", message = "numero invalido, informe o ddd junto com o o número, por gentileza")
        String contato,

        @NotBlank(message = "Informe o seu endereço, por favor")
        @Size(min = 8, max = 255, message = "Endereço precisa ter pelo menso 8 caracteres")
        String endereco

) {
}
