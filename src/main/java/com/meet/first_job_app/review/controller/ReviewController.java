package com.meet.first_job_app.review.controller;

import com.meet.first_job_app.review.entities.Review;
import com.meet.first_job_app.review.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies/{companyId}/review")
public class ReviewController {
    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllReviews(@PathVariable Long companyId) {
        List<Review> reviews = reviewService.getAllReviews(companyId);
        if (reviews != null) {
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("No reviews found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> addReview(@PathVariable Long companyId,
                                         @RequestBody Review review) {

        Review savedReview = reviewService.addReview(companyId, review);
        if (savedReview != null) {
            return new ResponseEntity<>("Review Added Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("No Company exists by Id: " + companyId, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> getReviewById(@PathVariable Long companyId,
                                           @PathVariable Long reviewId) {
       Review review = reviewService.getReviewById(companyId, reviewId);
       if (review == null) {
           return new ResponseEntity<>("Review does not exist", HttpStatus.NOT_FOUND);
       }
       else {
           return new ResponseEntity<>(review, HttpStatus.OK);
       }
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<?> updateReviewById(@PathVariable Long companyId,
                                             @PathVariable Long reviewId,
                                             @RequestBody Review review) {
        Review savedReview = reviewService.updateReviewById(companyId, reviewId, review);
        if (savedReview == null) {
            return new ResponseEntity<>("Incorrect review id or company id", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(savedReview, HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Boolean reviewDeleted = reviewService.deleteReviewById(companyId, reviewId);
        if (reviewDeleted) {
            return new ResponseEntity<>("Review Deleted Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Incorect Company id or Review id", HttpStatus.NOT_FOUND);
    }
}
