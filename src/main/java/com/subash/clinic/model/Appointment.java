package com.subash.clinic.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "appointments")
public class Appointment {

    @Id
    private String id;
    private String patientName;
    private String doctorName;
    private String appointmentDate;
    private String status;
}
