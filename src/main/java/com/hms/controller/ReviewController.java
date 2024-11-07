package com.hms.controller;

import com.hms.entity.AppUser;
import com.hms.entity.Property;
import com.hms.entity.Review;
import com.hms.implementations.ReviewServiceImpl;
import com.hms.repository.PropertyRepository;
import com.hms.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/api/v1/review")

public class ReviewController {
    private PropertyRepository propertyRepository;
    private ReviewRepository reviewRepository;
    private  ReviewServiceImpl reviewServiceImpl;

    public ReviewController(PropertyRepository propertyRepository, ReviewRepository reviewRepository, ReviewServiceImpl reviewServiceImpl) {
        this.propertyRepository = propertyRepository;
        this.reviewRepository = reviewRepository;
        this.reviewServiceImpl = reviewServiceImpl;
    }
    @PostMapping
    public ResponseEntity<?> write(
            @RequestBody Review review,
            @RequestParam long propertyId,
            @AuthenticationPrincipal AppUser user
            ){

        Review save = reviewServiceImpl.addReview(review, propertyId, user);
        if (save==null){
            return new ResponseEntity<>("Already review exists by the user",HttpStatus.OK);
        }
        return new ResponseEntity<>(save, HttpStatus.OK);

    }

    @GetMapping("/user/review")
    public ResponseEntity<List<Review>> getUserReview(
            @AuthenticationPrincipal AppUser appUser

    ){
        List<Review> reviews=reviewRepository.findByAppUser(appUser);
        return new ResponseEntity<>(reviews,HttpStatus.OK);
    }

}
