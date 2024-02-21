package med.clinica.spring.api.domain.schedule.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ScheduleEntryDTO(
        @NotNull
        Long idPatient,
        Long idDoctor,

        @NotNull
        @Future
        LocalDateTime scheduleDateTime
) {
}
