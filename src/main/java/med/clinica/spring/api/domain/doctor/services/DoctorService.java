package med.clinica.spring.api.domain.doctor.services;

import med.clinica.spring.api.domain.address.AddressEmbeddable;
import med.clinica.spring.api.domain.doctor.dto.DoctorDTO;
import med.clinica.spring.api.domain.doctor.dto.DoctorUpdateDTO;
import med.clinica.spring.api.domain.doctor.dto.ShortDoctorDTO;
import med.clinica.spring.api.domain.doctor.repository.DoctorEntity;
import med.clinica.spring.api.domain.doctor.repository.DoctorRepository;
import med.clinica.spring.api.util.PageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repository;


    public ShortDoctorDTO insert(DoctorDTO dto) {
        var doctor = new DoctorEntity(dto);
        this.repository.save(doctor);
        return new ShortDoctorDTO(doctor);
    }


    public PageableResponse<ShortDoctorDTO> selectAll(Pageable option){
        return new PageableResponse<ShortDoctorDTO>(this.repository.findAll(option).map(ShortDoctorDTO::new));
    }

    public ShortDoctorDTO selectWhereID(Long id) {
        return new ShortDoctorDTO(this.repository.getReferenceById(id));
    }

    public DoctorDTO updateWhereID(DoctorUpdateDTO dto) {
            DoctorEntity docManager = this.repository.getReferenceById(dto.id());
            if(dto.fone() != null){
                docManager.setFone(dto.fone());
            }
            if(dto.name() != null){
                docManager.setName(dto.name());
            }
            if(dto.addressDTO() != null){
                docManager.setAddress(new AddressEmbeddable(dto.addressDTO()));
            }
            if (dto.active() != null) {
                docManager.setActive(dto.active());
            }
            return new DoctorDTO(docManager);
    }

    public void logicalDeleteWhereID(Long id) {
            DoctorEntity docManager = this.repository.getReferenceById(id);
            docManager.setActive(false);
    }

    public void setActiveWhereID(Long id, DoctorUpdateDTO data){
            if(this.repository.getReferenceById(id).isActive()){
                    DoctorEntity notActiveDoctor = this.repository.getReferenceById(data.id());
                    notActiveDoctor.setActive(true);

            }
    }


}
