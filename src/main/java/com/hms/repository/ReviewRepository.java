package com.hms.repository;

import com.hms.entity.AppUser;
import com.hms.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
   //findByAppUser(AppUser appUser);
   List<Review> findByAppUser(AppUser appUser);
   //existsByAppUserAndPropertyId(AppUser appUser, Long propertyId);
   boolean existsByAppUserAndPropertyId(AppUser appUser, Long propertyId);
}