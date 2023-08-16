package med.clinica.doctor.repository;

import med.clinica.doctor.repository.DoctorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
    Page<DoctorEntity> findAllByActiveTrue(Pageable pageOptions);


}
