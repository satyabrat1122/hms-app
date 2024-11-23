package com.hms.implementation;

import com.hms.entity.AppUser;
import com.hms.entity.Property;
import com.hms.entity.Review;
import com.hms.repository.AppUserRepository;
import com.hms.repository.PropertyRepository;
import com.hms.repository.ReviewRepository;
import com.hms.services.ReviewService;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private PropertyRepository propertyRepository;
    private AppUserRepository appUserRepository;
    public ReviewServiceImpl(ReviewRepository reviewRepository, PropertyRepository propertyRepository, AppUserRepository appUserRepository) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
        this.appUserRepository = appUserRepository;
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

    @Override
    public String deleteReview(long reviewId,AppUser user) {
        AppUser appUser = appUserRepository.findById(user.getId()).get();
        Review review = reviewRepository.findById(reviewId).get();
        if(review==null){
            return "No review exists";
        }
        if (review.getAppUser().getId() != appUser.getId()){
            return "You cannot delete other users review";
        }
        reviewRepository.delete(review);
        return "Review deleted";

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