package med.clinica.patient.services;

import med.clinica.address.AddressEmbeddable;
import med.clinica.doctor.repository.DoctorEntity;
import med.clinica.patient.dto.PatientDTO;
import med.clinica.patient.dto.ShortPatientDTO;
import med.clinica.patient.dto.UpdatePatientDTO;
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

    public ShortPatientDTO insert(PatientDTO dto){
        var patient = new PatientEntity(dto);
        this.repository.save(patient);
        return new ShortPatientDTO(patient);
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

    public void updateWhereID(UpdatePatientDTO dto) throws Exception{
        try {
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

        }catch (Exception e){
            throw new Exception("Error :" + e.getMessage());
        }
    }

    public void logicalDeleteWhereID(Long id) throws Exception{
        try {
            PatientEntity patientManager = this.repository.getReferenceById(id);
            patientManager.setActive(false);
        }catch(Exception e) {
            throw new Exception("Error :" + e.getMessage());
        }

    }
}
