package med.clinica.spring.api.domain.patient.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.clinica.spring.api.model.address.AddressDTO;

public record PatientDTO(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String mail,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,
        @NotBlank
        @Pattern(regexp = "\\d{11,15}")
        String fone,
        @NotNull
        @Valid
        AddressDTO address
) {

}

