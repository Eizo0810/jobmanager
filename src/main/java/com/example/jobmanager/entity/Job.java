package com.example.jobmanager.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String jobTitle;
    private String location;
    private String employmentType;
    private String salary;
    private String description;
    private String requiredSkills;
    private LocalDate postedDate;

    // getter / setter は後で追加
}