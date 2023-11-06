package med.clinica.spring.api.domain.patient.controller;

import med.clinica.spring.api.domain.patient.dto.PatientDTO;
import med.clinica.spring.api.domain.patient.dto.ShortPatientDTO;
import med.clinica.spring.api.domain.patient.dto.UpdatePatientDTO;
import med.clinica.spring.api.util.PageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private med.clinica.spring.api.domain.patient.services.PatientService PatientService;

    @PostMapping
    @Transactional
    @RequestMapping(value = "/signup")
    public ResponseEntity<ShortPatientDTO> add(@RequestBody PatientDTO patient, UriComponentsBuilder uriBuilder){
        System.out.println(patient);
        try {
            var dto = PatientService.insert(patient);
            var uri = uriBuilder.path("/patient/{id}").buildAndExpand(dto.getId()).toUri();
            return ResponseEntity.created(uri).body(dto);
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<PageableResponse<ShortPatientDTO>> getAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pageOptions){
        var response = PatientService.selectAll(pageOptions);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<ShortPatientDTO> getById(@PathVariable Long patientId) throws Exception {
        var response = PatientService.selectWhereID(patientId);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ShortPatientDTO> update(@RequestBody UpdatePatientDTO data) throws Exception{
        var patient = PatientService.updateWhereID(data);
        return ResponseEntity.ok(patient);
    }

    @DeleteMapping("/{patientId}")
    @Transactional
    public ResponseEntity setOff(@PathVariable Long patientId) throws Exception{
        PatientService.logicalDeleteWhereID(patientId);
        return ResponseEntity.noContent().build();
    }




}
