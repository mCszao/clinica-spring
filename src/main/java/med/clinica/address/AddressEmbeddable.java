package med.clinica.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressEmbeddable {
    private String street;
    private String number;
    private String complement;
    private String district;
    private String city;
    private String uf;
    private String zipcode;

    public AddressEmbeddable(AddressDTO address){
        this.street = address.street();
        this.number = address.number();
        this.complement = address.complement();
        this.district = address.district();
        this.city = address.city();
        this.uf = address.uf();
        this.zipcode = address.zipcode();
    }
}
