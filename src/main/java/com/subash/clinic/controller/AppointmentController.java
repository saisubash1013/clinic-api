package com.subash.clinic.controller;

import com.subash.clinic.exception.AppointmentNotFoundException;
import com.subash.clinic.model.Appointment;
import com.subash.clinic.repository.AppointmentRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private  final AppointmentRepository repository;

    public AppointmentController(AppointmentRepository repository){
        this.repository = repository;
    }

    @PostMapping
    public Appointment createAppointment(@Valid @RequestBody Appointment appointment){
        return repository.save(appointment);
    }

    @GetMapping
    public List<Appointment> getAll(){
        return repository.findAll();
    }

    @GetMapping("/doctor/{doctorName}")
    public  List<Appointment> findByDoctorName(@PathVariable String doctorName){
        return repository.findByDoctorName(doctorName);
    }

    @GetMapping("/status/{status}")
    public List<Appointment> getByStatus(@PathVariable String status){
        return repository.findByStatus(status);
    }

    @GetMapping("/search")
    public  List<Appointment> searchByStatusAndDoctor(@RequestParam String status, @RequestParam String doctor){
        return repository.findByStatusAndDoctor(status, doctor);
    }

    @GetMapping("/{id}")
    public Appointment getById(@PathVariable String id){
        return repository.findById(id).orElseThrow(() -> new AppointmentNotFoundException(id));
    }
}
