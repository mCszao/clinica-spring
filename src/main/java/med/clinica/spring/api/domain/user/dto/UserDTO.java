package med.clinica.spring.api.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        @NotBlank
        @Email
        String username,
        @NotBlank
        String password) {}
