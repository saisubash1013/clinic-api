package com.subash.clinic.service;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentStatsService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Document> countByStatus(){
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.group("status").count().as("total"),
                Aggregation.project("total").and("_id").as("status")
        );
        return mongoTemplate.aggregate(agg, "appointments", Document.class).getMappedResults();
    }

    public List<Document> countPerDoctor(){
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.group("doctorName").count().as("total"),
                Aggregation.project("total").and("_id").as("doctor")
        );
        return mongoTemplate.aggregate(agg, "appointments", Document.class).getMappedResults();
    }

    public List<Document> countBookedPerDoctor(){
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("status").is("BOOKED")),
                Aggregation.group("doctorName").count().as("total"),
                Aggregation.project("total").and("_id").as("doctor")
        );
        return mongoTemplate.aggregate(agg, "appointments", Document.class).getMappedResults();
    }
}
