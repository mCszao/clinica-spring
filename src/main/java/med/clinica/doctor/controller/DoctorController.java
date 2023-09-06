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
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorService DocService;

    @PostMapping
    @Transactional
    @RequestMapping(value = "/signup")
    public ResponseEntity<ShortDoctorDTO> add(@RequestBody @Valid DoctorDTO doctor, UriComponentsBuilder uriBuilder){
        try {
            var dto = DocService.insert(doctor);
            var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(dto.getId()).toUri();
            return ResponseEntity.created(uri).body(dto);
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

    }


    @GetMapping
    public ResponseEntity<PageableResponse<ShortDoctorDTO>> getAll(@PageableDefault(size = 5, sort = {"name"}) Pageable pageOptions) throws Exception{
        try {
            var response = DocService.selectAll(pageOptions);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }



    @GetMapping("/{doctorId}")
    public ResponseEntity<ShortDoctorDTO> getById(@PathVariable Long doctorId) throws Exception{
            var response = DocService.selectWhereID(doctorId);
            return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DoctorDTO> update(@RequestBody DoctorUpdateDTO data) throws Exception{
            var doctor = DocService.updateWhereID(data);
            return ResponseEntity.ok(doctor);
    }

    @DeleteMapping("/{doctorId}")
    @Transactional
    public ResponseEntity setOff(@PathVariable Long doctorId) throws Exception{
       DocService.logicalDeleteWhereID(doctorId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{doctorId}")
    @Transactional
    public ResponseEntity setOn(@PathVariable Long doctorId , @RequestBody DoctorUpdateDTO data) throws Exception{;
        DocService.setActiveWhereID(doctorId, data);
        return ResponseEntity.noContent().build();
    }


}
