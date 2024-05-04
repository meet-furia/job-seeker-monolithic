package com.meet.first_job_app.job.controller;


import com.meet.first_job_app.job.entities.Job;
import com.meet.first_job_app.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    JobService jobService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getJobById(@PathVariable Long id) {
        Job job =  jobService.findJobById(id);
        if (job != null) {

            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>("Id: " + id + " not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Job>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job Added Successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/all")
    public ResponseEntity<String> createJobs(@RequestBody List<Job> jobs) {
        jobService.createAllJobs(jobs);
        return new ResponseEntity<>("Job Added Successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJobById(@PathVariable Long id, @RequestBody Job job) {
        Job updatedJob = jobService.updateJobById(id, job);
        if (updatedJob != null) {
            return ResponseEntity.ok(updatedJob);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        Boolean deleted = jobService.deleteJobById(id);
        if (deleted) {
            return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Cannot find the job", HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAll() {
        Boolean jobsDeleted = jobService.deleteAll();
        if (jobsDeleted) {
            return new ResponseEntity<>("Jobs deleted successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("No jobs available to be deleted", HttpStatus.NOT_FOUND);
        }
    }

}
