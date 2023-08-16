package med.clinica.patient.services;

import med.clinica.patient.dto.PatientDTO;
import med.clinica.patient.dto.ShortPatientDTO;
import med.clinica.patient.repository.PatientEntity;
import med.clinica.patient.repository.PatientRepository;
import med.clinica.util.PageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;

    public void insert(PatientDTO dto){
        this.repository.save(new PatientEntity(dto));
    }


    public PageableResponse<ShortPatientDTO> selectAll(Pageable option) {
        return new PageableResponse<ShortPatientDTO>(this.repository.findAll(option).map(ShortPatientDTO::new));
    }

    public ShortPatientDTO selectWhereID(Long id) throws Exception{
        try {
            return new ShortPatientDTO(this.repository.getReferenceById(id));
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
