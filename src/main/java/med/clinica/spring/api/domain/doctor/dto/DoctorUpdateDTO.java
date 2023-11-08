package med.clinica.spring.api.domain.doctor.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.clinica.spring.api.model.address.AddressDTO;

public record DoctorUpdateDTO(
   @NotNull
   Long id,

   @NotBlank
   @Pattern(regexp = "\\d{11,15}")
   String fone,

   String name,

   Boolean active,
   @Valid
   AddressDTO addressDTO
) {}
