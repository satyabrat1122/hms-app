package com.hms.services;

import com.hms.entity.AppUser;
import com.hms.entity.Review;

public interface ReviewService {

    public Review addReview(Review review, long propertyId, AppUser user);

    String deleteReview(long reviewId,AppUser user);
}
