package com.hms.service;

import com.hms.entity.City;
import com.hms.payload.CityDto;

import java.util.List;

public interface CityService {
    public void addCity(CityDto cityDto);

    public List<City> getAllCity();

    public void deleteCityById(long cityId);

    boolean updateCityById(long cityId, CityDto cityDto);
}
