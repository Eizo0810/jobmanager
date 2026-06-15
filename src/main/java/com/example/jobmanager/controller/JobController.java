package com.example.jobmanager.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.jobmanager.entity.Job;
import com.example.jobmanager.service.JobService;
@Controller
public class JobController {

	private final JobService jobService;

	public JobController(JobService jobService) {
	    this.jobService = jobService;
	}

    @GetMapping("/jobs")
    public String index(
            @RequestParam(required = false, defaultValue = "") String companyName,
            @RequestParam(required = false, defaultValue = "") String jobTitle,
            @RequestParam(required = false, defaultValue = "") String location,
            Model model) {

    	List<Job> jobs = jobService.search(companyName, jobTitle, location);

        model.addAttribute("jobs", jobs);
        model.addAttribute("companyName", companyName);
        model.addAttribute("jobTitle", jobTitle);
        model.addAttribute("location", location);

        return "jobs/index";
    }
    
    @GetMapping("/jobs/new")
    public String createForm(Model model) {
        model.addAttribute("job", new Job());
        return "jobs/new";
    }
    
    @GetMapping("/jobs/{id}")
    public String show(@PathVariable Long id, Model model) {
        Job job = jobService.findById(id);

        model.addAttribute("job", job);
        return "jobs/show";
    }
    
    @GetMapping("/jobs/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {

        Job job = jobService.findById(id);

        model.addAttribute("job", job);

        return "jobs/edit";
    }
  
    @PostMapping("/jobs")
    public String create(@Valid @ModelAttribute Job job,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "jobs/new";
        }

        jobService.save(job);
        return "redirect:/jobs";
    }
    
    @PostMapping("/jobs/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute Job job,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "jobs/edit";
        }

        job.setId(id);
        jobService.save(job);

        return "redirect:/jobs/" + id;
    }
    

    @PostMapping("/jobs/{id}/delete")
    public String delete(@PathVariable Long id) {

    	jobService.deleteById(id);

        return "redirect:/jobs";
    }
}