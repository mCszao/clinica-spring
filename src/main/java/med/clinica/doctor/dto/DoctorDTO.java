package med.clinica.doctor.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.clinica.address.AddressDTO;
import med.clinica.model.Specialty;

public record DoctorDTO(
        @NotBlank String name,
        @NotBlank
        @Email
        String mail,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,
        @NotBlank
        @Pattern(regexp = "\\d{11,15}")
        String fone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Specialty specialty,
        @NotNull
        @Valid
        AddressDTO address
) {

}
