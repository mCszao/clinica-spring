package med.clinica.spring.api.domain.schedule.dto;

import java.time.LocalDateTime;

public record ScheduleDetailsDTO(Long id, Long idPatient, Long idDoctor, LocalDateTime scheduleDateTime) {
}
