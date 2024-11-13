package com.hms.service;

import com.hms.entity.AppUser;
import com.hms.entity.Review;
import com.hms.payload.ReviewDto;

public interface ReviewService {

    public Review addReview(Review review, long propertyId, AppUser user);

    String deleteReview(long reviewId,AppUser user);
}
