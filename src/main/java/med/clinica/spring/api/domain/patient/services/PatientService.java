package med.clinica.spring.api.domain.patient.services;

import med.clinica.spring.api.model.address.AddressEmbeddable;
import med.clinica.spring.api.domain.patient.dto.PatientDTO;
import med.clinica.spring.api.domain.patient.dto.ShortPatientDTO;
import med.clinica.spring.api.domain.patient.dto.UpdatePatientDTO;
import med.clinica.spring.api.domain.patient.repository.PatientEntity;
import med.clinica.spring.api.domain.patient.repository.PatientRepository;
import med.clinica.spring.api.util.PageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;

    public ShortPatientDTO insert(PatientDTO dto){
        var patient = new PatientEntity(dto);
        this.repository.save(patient);
        return new ShortPatientDTO(patient);
    }


    public PageableResponse<ShortPatientDTO> selectAll(Pageable option) {
        return new PageableResponse<ShortPatientDTO>(this.repository.findAll(option).map(ShortPatientDTO::new));
    }

    public ShortPatientDTO selectWhereID(Long id) {
            return new ShortPatientDTO(this.repository.getReferenceById(id));
    }

    public ShortPatientDTO updateWhereID(UpdatePatientDTO dto) {
            PatientEntity patientManager = this.repository.getReferenceById(dto.id());
            if(dto.fone() != null){
                patientManager.setFone(dto.fone());
            }
            if(dto.name() != null){
                patientManager.setName(dto.name());
            }
            if(dto.address() != null){
                patientManager.setAddress(new AddressEmbeddable(dto.address()));
            }
            if(dto.mail() != null){
                patientManager.setMail(dto.mail());
            }
            return new ShortPatientDTO(patientManager);

    }

    public void logicalDeleteWhereID(Long id){
            PatientEntity patientManager = this.repository.getReferenceById(id);
            patientManager.setActive(false);

    }
}
