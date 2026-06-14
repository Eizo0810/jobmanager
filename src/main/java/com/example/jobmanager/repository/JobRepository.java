package com.example.jobmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jobmanager.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

	 List<Job> findByCompanyNameContaining(String keyword);

}