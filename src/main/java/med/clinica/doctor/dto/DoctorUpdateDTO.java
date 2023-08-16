package med.clinica.doctor.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.clinica.address.AddressDTO;

public record DoctorUpdateDTO(
   @NotNull
   Long id,

   @Pattern(regexp = "\\d{11,15}")
   String fone,

   String name,

   Boolean active,
   @Valid
   AddressDTO addressDTO
) {}
