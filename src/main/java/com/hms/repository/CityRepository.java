package com.hms.repository;

import com.hms.entity.AppUser;
import com.hms.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

  Optional<City> findByName(String name);


}