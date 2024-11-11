package com.hms.implementations;

import com.hms.entity.AppUser;
import com.hms.entity.Property;
import com.hms.entity.Review;
import com.hms.payload.ReviewDto;
import com.hms.repository.PropertyRepository;
import com.hms.repository.ReviewRepository;
import com.hms.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private PropertyRepository propertyRepository;
    public ReviewServiceImpl(ReviewRepository reviewRepository, PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
    }


    @Override
    public Review addReview(Review review, long propertyId, AppUser user) {
        Property property = propertyRepository.findById(propertyId).get();
        boolean b = reviewRepository.existsByAppUserAndPropertyId(user, propertyId);
        if(b==false){
            review.setProperty(property);
            review.setAppUser(user);
            Review save = reviewRepository.save(review);
            return save;
        }
        return null;
    }

    public String updateUserReview(Review review, AppUser user, long propertyId) {


        Property property = propertyRepository.findById(propertyId).get();
        Review save = reviewRepository.findByAppUserAndProperty(user, property);
        if(save==null){
            return "No review exists";
        }
        if(save.getRating()==review.getRating() && save.getDescription().equals(review.getDescription())){
            return "Review is same as previous review";
        }
        save.setRating(review.getRating());
        save.setDescription(review.getDescription());
        reviewRepository.save(save);
         return "Updated user review";
    }
}
