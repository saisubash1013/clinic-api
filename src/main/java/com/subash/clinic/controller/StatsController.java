package com.subash.clinic.controller;

import com.subash.clinic.service.AppointmentStatsService;
import org.bson.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private AppointmentStatsService appointmentStatsService;

    public StatsController(AppointmentStatsService appointmentStatsService){this.appointmentStatsService =appointmentStatsService; };

    @GetMapping("/byStats")
    public List<Document> countByStatus(){
        return appointmentStatsService.countByStatus();
    }

    @GetMapping("/byDoctor")
    public List<Document> countByDoctor(){
        return appointmentStatsService.countPerDoctor();
    }

    @GetMapping("/countBookedPerDoctor")
    public List<Document> countBookedPerDoctor(){
        return appointmentStatsService.countBookedPerDoctor();
    }
}
