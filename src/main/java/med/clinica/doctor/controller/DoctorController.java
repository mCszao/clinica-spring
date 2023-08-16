package med.clinica.doctor.controller;

import jakarta.validation.Valid;

import med.clinica.doctor.dto.DoctorDTO;
import med.clinica.doctor.dto.DoctorUpdateDTO;
import med.clinica.doctor.dto.ShortDoctorDTO;
import med.clinica.doctor.services.DoctorService;
import med.clinica.util.PageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorService DocService;

    @PostMapping
    @Transactional
    @RequestMapping(value = "/signup")
    public void add(@RequestBody @Valid DoctorDTO doctor){
        System.out.println(doctor);
        try {
            DocService.insert(doctor);
        }catch (Exception e) {
            System.out.println("Error: "+ e.getMessage());
        }

    }


    @GetMapping
    public PageableResponse<ShortDoctorDTO> getAll(@PageableDefault(size = 5, sort = {"name"}) Pageable pageOptions) throws Exception{
        try {
            return DocService.selectAll(pageOptions);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }



    @GetMapping("/{doctorId}")
    public ShortDoctorDTO getById(@PathVariable Long doctorId) throws Exception{
            return DocService.selectWhereID(doctorId);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody DoctorUpdateDTO data) throws Exception{
            DocService.updateWhereID(data);
    }

    @DeleteMapping("/{doctorId}")
    @Transactional
    public void setOff(@PathVariable Long doctorId) throws Exception{
       DocService.logicalDeleteWhereID(doctorId);
    }

    @PatchMapping("/{doctorId}")
    @Transactional
    public void setOn(@PathVariable Long doctorId , @RequestBody DoctorUpdateDTO data) throws Exception{;
        DocService.setActiveWhereID(doctorId, data);
    }


}
