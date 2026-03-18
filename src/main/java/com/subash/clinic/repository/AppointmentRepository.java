package com.subash.clinic.repository;

import com.subash.clinic.model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    List<Appointment> findByDoctorName(String doctorName);
    List<Appointment> findByStatus(String status);

    @Query("{ 'status': ?0, 'doctorName': ?1 }")
    List<Appointment> findByStatusAndDoctor(String status, String doctorName);

}
