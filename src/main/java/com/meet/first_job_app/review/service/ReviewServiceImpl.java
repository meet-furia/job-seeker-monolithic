package com.meet.first_job_app.review.service;

import com.meet.first_job_app.company.entities.Company;
import com.meet.first_job_app.company.service.CompanyService;
import com.meet.first_job_app.review.entities.Review;
import com.meet.first_job_app.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    ReviewRepository reviewRepository;
    CompanyService companyService;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public Review addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return review;
        }
        else {
            return null;
        }
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    public Company getCompanyById(Long companyId) {
        return companyService.getCompanyById(companyId);
    }

    public Review updateReviewById(Long companyId, Long reviewId, Review updatedReview) {
        Company existingcompany = companyService.getCompanyById(companyId);
        if (existingcompany != null) {
            Review existingReview = reviewRepository.findById(reviewId).get();
            existingReview.setCompany(companyService.getCompanyById(companyId));
            existingReview.setId(reviewId);
            existingReview.setTitle(updatedReview.getTitle());
            existingReview.setDescription(updatedReview.getDescription());
            existingReview.setRating(updatedReview.getRating());
            reviewRepository.save(existingReview);
            return existingReview;
        }
        else {
            return null;
        }
    }

    @Override
    public Boolean deleteReviewById(Long companyId, Long reviewId) {
        Company existingcompany = companyService.getCompanyById(companyId);
        if (existingcompany != null) {
            if (reviewRepository.findById(reviewId) != null) {
                Review review = reviewRepository.findById(reviewId).orElse(null);
                if (review != null) {
                    Company company = review.getCompany();
                    company.getReviews().remove(review);
                    companyService.updateCompany(company, companyId);
                    reviewRepository.deleteById(reviewId);
                    return true;
                }
            }
        }
        return false;
    }
}
