package com.meet.first_job_app.review.service;

import com.meet.first_job_app.review.entities.Review;

import java.util.List;


public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    Review addReview(Long companyId, Review review);
    Review getReviewById(Long companyId, Long reviewId);
    Review updateReviewById(Long companyId, Long reviewId, Review updatedReview);
    Boolean deleteReviewById(Long companyId, Long reviewId);
}
