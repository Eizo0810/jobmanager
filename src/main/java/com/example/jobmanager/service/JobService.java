package com.example.jobmanager.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.jobmanager.entity.AppUser;
import com.example.jobmanager.entity.Job;
import com.example.jobmanager.exception.JobNotFoundException;
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
    
    public Page<Job> search(String companyName, String jobTitle, String location, Pageable pageable) {
        return jobRepository
                .findByCompanyNameContainingAndJobTitleContainingAndLocationContaining(
                        companyName,
                        jobTitle,
                        location,
                        pageable
                );
    }
    
    public Page<Job> searchByUser(
            AppUser user,
            String companyName,
            String jobTitle,
            String location,
            Pageable pageable) {

        return jobRepository
                .findByUserAndCompanyNameContainingAndJobTitleContainingAndLocationContaining(
                        user,
                        companyName,
                        jobTitle,
                        location,
                        pageable
                );
    }
    
    public Job findByIdAndUser(Long id, AppUser user) {
        return jobRepository.findByIdAndUser(id, user)
                .orElseThrow(JobNotFoundException::new);
    }
}