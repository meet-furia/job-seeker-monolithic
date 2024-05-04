package com.meet.first_job_app.job.service;

import com.meet.first_job_app.company.entities.Company;
import com.meet.first_job_app.job.entities.Job;

import java.util.List;

public interface JobService {
    List<Job> findAll();

    void createJob(Job job);

    Job findJobById(Long id);

    boolean deleteJobById(Long id);

    void createAllJobs(List<Job> jobs);

    Job updateJobById(Long id, Job job);

    boolean deleteAll();

    Company findCompanyById(Long id);
}
