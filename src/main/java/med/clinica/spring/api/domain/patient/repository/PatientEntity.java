package med.clinica.spring.api.domain.patient.repository;


import jakarta.persistence.*;
import lombok.*;
import med.clinica.spring.api.model.address.AddressEmbeddable;
import med.clinica.spring.api.domain.patient.dto.PatientDTO;

@Entity(name = "patients")
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean active = true;
    private String name;
    private String mail;
    private String fone;
    private String cpf;
    @Embedded
    private AddressEmbeddable address;

    public PatientEntity(PatientDTO patientDTO){
        this.name = patientDTO.name();
        this.mail = patientDTO.mail();
        this.cpf = patientDTO.cpf();
        this.fone = patientDTO.fone();
        this.address = new AddressEmbeddable(patientDTO.address());
    }

}
