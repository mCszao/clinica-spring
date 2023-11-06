package med.clinica.spring.api.domain.doctor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.clinica.spring.api.domain.doctor.repository.DoctorEntity;
import med.clinica.spring.api.model.Specialty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShortDoctorDTO {
    private Long id;
    private String name;
    private String mail;
    private boolean active;
    private String crm;
    private Specialty specialty;

    public ShortDoctorDTO(DoctorEntity doctor){
        this.id = doctor.getId();
        this.active = doctor.isActive();
        this.name = doctor.getName();
        this.mail = doctor.getMail();
        this.crm = doctor.getCrm();
        this.specialty = doctor.getSpecialty();
    }
}
