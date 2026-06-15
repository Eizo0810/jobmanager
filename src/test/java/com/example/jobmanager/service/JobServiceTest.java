package com.example.jobmanager.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.example.jobmanager.entity.AppUser;
import com.example.jobmanager.entity.Job;
import com.example.jobmanager.repository.JobRepository;

class JobServiceTest {

    private final JobRepository jobRepository = Mockito.mock(JobRepository.class);

    private final JobService jobService = new JobService(jobRepository);

    @Test
    void searchByUser_returnsJobs() {
        AppUser user = new AppUser();
        user.setId(1L);
        user.setUsername("admin");

        Job job = new Job();
        job.setId(1L);
        job.setCompanyName("サンプル株式会社");
        job.setJobTitle("Javaエンジニア");
        job.setLocation("東京");
        job.setUser(user);

        PageRequest pageable = PageRequest.of(0, 10);
        Page<Job> page = new PageImpl<>(List.of(job));

        when(jobRepository.findByUserAndCompanyNameContainingAndJobTitleContainingAndLocationContaining(
                user, "サンプル", "Java", "東京", pageable
        )).thenReturn(page);

        Page<Job> result = jobService.searchByUser(
                user, "サンプル", "Java", "東京", pageable
        );

        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getCompanyName()).isEqualTo("サンプル株式会社");
    }
}