package med.clinica.spring.api.domain.doctor.repository;

import jakarta.persistence.*;
import lombok.*;
import med.clinica.spring.api.domain.address.AddressEmbeddable;
import med.clinica.spring.api.domain.doctor.dto.DoctorDTO;
import med.clinica.spring.api.domain.specialty.Specialty;
import med.clinica.spring.api.domain.user.repository.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name = "doctors")
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class DoctorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean active = true;
    private String name;
    private String cpf;
    private String mail;
    private String fone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private AddressEmbeddable address;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    public DoctorEntity(DoctorDTO doctor){
        this.name = doctor.name();
        this.mail = doctor.mail();
        this.cpf = doctor.cpf();
        this.fone = doctor.fone();
        this.crm = doctor.crm();
        this.specialty = doctor.specialty();
        this.address = new AddressEmbeddable(doctor.address());
    }


}
