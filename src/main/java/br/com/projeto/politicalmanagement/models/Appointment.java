package br.com.projeto.politicalmanagement.models;

import br.com.projeto.politicalmanagement.api.request.AppointmentRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_APPOINTMENT")
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private OffsetDateTime dateAppointment;

    private String locationService;

    private String comments;

    // Criando Integração com FullCalendar

    @Column(name = "START")
    private OffsetDateTime start = dateAppointment;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CLASS_NAME")
    private String className;

    private String groupId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(    name = "TB_APPOINTMENT_USERS",
        joinColumns = @JoinColumn(name = "appointment_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id"))
    @ToString.Exclude
    private Set<User> users;

    public Appointment createAppointmentFromRequest(AppointmentRequest appointmentRequest) {
        Appointment appointment = new Appointment();
        appointment.createAppointmentFromRequest(appointmentRequest);
        appointment.setDateAppointment(appointmentRequest.getDateAppointment());
        appointment.setComments(appointmentRequest.getComments());
        appointment.setStart(appointmentRequest.getDateAppointment());
        appointment.setTitle(appointmentRequest.getTitle());
        appointment.setLocationService(appointmentRequest.getLocationService());
        return appointment;
    }

}
