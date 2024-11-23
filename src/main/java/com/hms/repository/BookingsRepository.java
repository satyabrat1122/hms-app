package com.hms.repository;

import com.hms.entity.AppUser;
import com.hms.entity.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingsRepository extends JpaRepository<Bookings, Long> {
    List<Bookings> findByName(String name);

}