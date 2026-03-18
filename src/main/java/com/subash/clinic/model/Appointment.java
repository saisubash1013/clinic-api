package com.subash.clinic.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "appointments")
public class Appointment {

    @Id
    private String id;

    @NotBlank(message = "Patient name is required")
    private String patientName;

    @NotBlank(message = "Doctor name is required")
    private String doctorName;

    @NotBlank(message = "Appointment date is required")
    private String appointmentDate;
    private String status;
}
