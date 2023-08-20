package med.clinica.patient.controller;

import med.clinica.doctor.dto.ShortDoctorDTO;
import med.clinica.patient.dto.PatientDTO;
import med.clinica.patient.dto.ShortPatientDTO;
import med.clinica.patient.dto.UpdatePatientDTO;
import med.clinica.patient.services.PatientService;
import med.clinica.util.PageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService PatientService;

    @PostMapping
    @Transactional
    @RequestMapping(value = "/signup")
    public void add(@RequestBody PatientDTO patient){
        System.out.println(patient);
        try {
            PatientService.insert(patient);
        }catch (Exception e) {
            System.out.println("Error: "+ e.getMessage());
        }
    }

    @GetMapping
    public PageableResponse<ShortPatientDTO> getAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pageOptions){
        return PatientService.selectAll(pageOptions);
    }

    @GetMapping("/{patientId}")
    public ShortPatientDTO getById(@PathVariable Long patientId) throws Exception {
        return PatientService.selectWhereID(patientId);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody UpdatePatientDTO data) throws Exception{
        PatientService.updateWhereID(data);
    }

    @DeleteMapping("/{patientId}")
    @Transactional
    public void setOff(@PathVariable Long patientId) throws Exception{
        PatientService.logicalDeleteWhereID(patientId);
    }




}
