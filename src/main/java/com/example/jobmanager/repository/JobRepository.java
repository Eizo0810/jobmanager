package com.example.jobmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jobmanager.entity.AppUser;
import com.example.jobmanager.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

    Page<Job> findByCompanyNameContainingAndJobTitleContainingAndLocationContaining(
            String companyName,
            String jobTitle,
            String location,
            Pageable pageable
    );
    
    Page<Job> findByUserAndCompanyNameContainingAndJobTitleContainingAndLocationContaining(
            AppUser user,
            String companyName,
            String jobTitle,
            String location,
            Pageable pageable
    );
}