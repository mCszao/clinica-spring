package med.clinica.address;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public record AddressDTO(
        @NotBlank
        String street,
        @NotBlank
        String number,
        @NotBlank
        String complement,
        @NotBlank
        String district,
        @NotBlank
        String city,
        @NotBlank
        String uf,
        @NotBlank
        @Pattern(regexp = "\\d{6,8}")
        String zipcode) {
        public AddressDTO(AddressEmbeddable address){
                this(address.getStreet(), address.getNumber(),address.getComplement(),address.getDistrict(),address.getCity(),address.getUf(),address.getZipcode());
        }
}
