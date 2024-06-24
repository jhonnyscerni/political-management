package br.com.projeto.politicalmanagement.service;


import br.com.projeto.politicalmanagement.filter.AppointmentFilter;
import br.com.projeto.politicalmanagement.models.Appointment;
import br.com.projeto.politicalmanagement.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppointmentService {


    public Appointment buscarOuFalhar(UUID id);

    public void delete(UUID id);

    public Page<Appointment> search(AppointmentFilter filter, Pageable pageable);

    public Appointment save(Appointment appointment);

    public Optional<Appointment> findById(UUID uuid);

    public List<Appointment> list();

    public Appointment update(UUID id, Appointment appointment);

    public boolean existsByCourseAndUser(UUID courseId, UUID userId);

    public void saveSubscriptionUserInAppointment(UUID courseId, UUID userId);

}
