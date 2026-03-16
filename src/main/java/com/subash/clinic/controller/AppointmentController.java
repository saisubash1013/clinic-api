package com.subash.clinic.controller;

import com.subash.clinic.model.Appointment;
import com.subash.clinic.repository.AppointmentRepository;
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
    public Appointment create(@RequestBody Appointment appointment){
        return repository.save(appointment);
    }

    @GetMapping
    public List<Appointment> getAll(){
        return repository.findAll();
    }
}
