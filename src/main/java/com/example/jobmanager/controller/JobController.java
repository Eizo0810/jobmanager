package com.example.jobmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.jobmanager.entity.Job;
import com.example.jobmanager.repository.JobRepository;
@Controller
public class JobController {

    private final JobRepository jobRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @GetMapping("/jobs")
    public String index(Model model) {
        model.addAttribute("jobs", jobRepository.findAll());
        return "jobs/index";
        
    }@GetMapping("/jobs/new")
    public String createForm(Model model) {
        model.addAttribute("job", new Job());
        return "jobs/new";
    }
    
    @PostMapping("/jobs")
    public String create(@ModelAttribute Job job) {

        jobRepository.save(job);

        return "redirect:/jobs";
    }
    
    @GetMapping("/jobs/{id}")
    public String show(@PathVariable Long id, Model model) {

        Job job = jobRepository.findById(id)
                .orElseThrow();

        model.addAttribute("job", job);

        return "jobs/show";
    }
    
    @GetMapping("/jobs/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {

        Job job = jobRepository.findById(id)
                .orElseThrow();

        model.addAttribute("job", job);

        return "jobs/edit";
    }
    
    @PostMapping("/jobs/{id}")
    public String update(
            @PathVariable Long id,
            @ModelAttribute Job job) {

        job.setId(id);

        jobRepository.save(job);

        return "redirect:/jobs/" + id;
    }
    
    @PostMapping("/jobs/{id}/delete")
    public String delete(@PathVariable Long id) {

        jobRepository.deleteById(id);

        return "redirect:/jobs";
    }
}