package br.com.projeto.politicalmanagement.api.request;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class AppointmentRequest {

    private UUID id;

    private String title;

    private OffsetDateTime dateAppointment;

    private String locationService;

    private String comments;

    private UUID userId;
}
