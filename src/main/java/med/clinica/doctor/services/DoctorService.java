package med.clinica.doctor.services;

import med.clinica.address.AddressEmbeddable;
import med.clinica.doctor.dto.DoctorDTO;
import med.clinica.doctor.dto.DoctorUpdateDTO;
import med.clinica.doctor.dto.ShortDoctorDTO;
import med.clinica.doctor.repository.DoctorEntity;
import med.clinica.doctor.repository.DoctorRepository;
import med.clinica.util.PageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.print.Doc;
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

    public ShortDoctorDTO selectWhereID(Long id) throws Exception{
        try {
        return new ShortDoctorDTO(this.repository.getReferenceById(id));
        }catch (Exception e){
            throw new Exception("error: " + e.getMessage());
        }
    }

    public DoctorDTO updateWhereID(DoctorUpdateDTO dto) throws Exception{
        try {
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
        }catch (Exception e){
            throw new Exception("Error :" + e.getMessage());
        }
    }

    public void logicalDeleteWhereID(Long id) throws Exception{
        try {
            DoctorEntity docManager = this.repository.getReferenceById(id);
            docManager.setActive(false);
        }catch (Exception e){
            throw new Exception("error: " + e.getMessage());
        };
    }

    public void setActiveWhereID(Long id, DoctorUpdateDTO data){
        try {
            if(this.repository.getReferenceById(id).isActive()){
                    DoctorEntity notActiveDoctor = this.repository.getReferenceById(data.id());
                    notActiveDoctor.setActive(true);

            }
        }catch (Exception e){
            System.out.println("error: " + e.getMessage());
        };
    }


}
