package br.com.projeto.politicalmanagement.api.controllers;


import br.com.projeto.politicalmanagement.api.request.AppointmentRequest;
import br.com.projeto.politicalmanagement.filter.AppointmentFilter;
import br.com.projeto.politicalmanagement.models.Appointment;
import br.com.projeto.politicalmanagement.service.AppointmentService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<Page<Appointment>> search(AppointmentFilter filter,
                                                    @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable pageable,
                                                    Authentication authentication) {
        return ResponseEntity.ok().body(appointmentService.search(filter, pageable));
    }

    @GetMapping("list")
    public ResponseEntity<List<Appointment>> list(Authentication authentication) {
        return ResponseEntity.ok().body(appointmentService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(appointmentService.buscarOuFalhar(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> update(@PathVariable UUID id,
        @RequestBody @Valid Appointment appointment) {
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.update(id, appointment));
    }


    @PostMapping
    public ResponseEntity<Object> saveAppointment(@RequestBody @Valid AppointmentRequest appointmentRequest) {
        log.info("POST saveAppointment AppointmentRequest received {} ", appointmentRequest.toString());
        Appointment appointment = new Appointment();
        appointment.createAppointmentFromRequest(appointmentRequest);

        //TODO: Rever essa logica - Atribuindo o compromisso ao usuario pois mapeamento seria para setar varios usuarios
        Appointment savedAppointment = appointmentService.save(appointment);
        appointmentService.saveSubscriptionUserInAppointment(savedAppointment.getId(), appointmentRequest.getUserId());

        log.info("POST saveAppointment appointmentId saved {} ", savedAppointment.getId());
        log.info("Appointment saved successfully appointmentId {} ", savedAppointment.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        appointmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
