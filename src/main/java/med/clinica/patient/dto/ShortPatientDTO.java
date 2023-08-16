package med.clinica.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.clinica.patient.repository.PatientEntity;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShortPatientDTO {
    private Long id;
    private String name;
    private String mail;
    private String cpf;

    public ShortPatientDTO(PatientEntity patient){
        this.id = patient.getId();
        this.cpf = patient.getCpf();
        this.name = patient.getName();
        this.mail = patient.getMail();
    }
}