package com.hms.services;

import com.hms.entity.City;
import com.hms.payloads.CityDto;
import java.util.List;

public interface CityService {
     void addCity(CityDto cityDto);

     List<City> getAllCity();

      void deleteCityById(long cityId);

    boolean updateCityById(long cityId, CityDto cityDto);

    void addNewCity(CityDto cityDto);
}