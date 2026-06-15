package com.example.jobmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jobmanager.entity.Job;
import com.example.jobmanager.repository.JobRepository;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public List<Job> search(String companyName, String jobTitle, String location) {
        return jobRepository
                .findByCompanyNameContainingAndJobTitleContainingAndLocationContaining(
                        companyName,
                        jobTitle,
                        location
                );
    }

    public Job findById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow();
    }

    public Job save(Job job) {
        return jobRepository.save(job);
    }

    public void deleteById(Long id) {
        jobRepository.deleteById(id);
    }
}