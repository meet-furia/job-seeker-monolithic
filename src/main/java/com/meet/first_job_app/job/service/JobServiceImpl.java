package com.meet.first_job_app.job.service;

import com.meet.first_job_app.company.entities.Company;
import com.meet.first_job_app.job.entities.Job;
import com.meet.first_job_app.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

//    private List<Job> jobs = new ArrayList<>();

    JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job findJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void createAllJobs(List<Job> jobs) {
        jobRepository.saveAll(jobs);
    }

    @Override
    public Job updateJobById(Long id, Job updatedJob) {
        Job existingJob = jobRepository.findById(id).orElse(null);

        if (existingJob != null) {
            // Update existingJob properties with values from updatedJob
            existingJob.setTitle(updatedJob.getTitle());
            existingJob.setDescription(updatedJob.getDescription());
            existingJob.setMinSalary(updatedJob.getMinSalary());
            existingJob.setMaxSalary(updatedJob.getMaxSalary());
            existingJob.setLocation(updatedJob.getLocation());

            jobRepository.save(existingJob);
            // Optionally, update other properties as needed

            // Return the updated existingJob
            return existingJob;
        } else {
            // Handle the case where the job with the given id is not found
            return null;
        }
    }

    @Override
    public boolean deleteAll() {
        if (jobRepository.findAll().isEmpty()) {
            return false;
        }
        jobRepository.deleteAll();
        return true;
    }

    @Override
    public Company findCompanyById(Long id) {
        return jobRepository.findByCompanyId(id);
    }


}
