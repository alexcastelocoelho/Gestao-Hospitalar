package com.example.api.utils.cpf;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidacao implements ConstraintValidator<CpfValido, String> {
    @Override
    public void initialize(CpfValido constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (MetodosParaCpf.isCPF(cpf)) {
            return true;
        }
        return false;
    }
}
