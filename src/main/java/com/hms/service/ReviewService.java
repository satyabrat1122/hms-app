package com.hms.service;

import com.hms.entity.AppUser;
import com.hms.entity.Review;

public interface ReviewService {

    public Review addReview(Review review, long propertyId, AppUser user);
}
