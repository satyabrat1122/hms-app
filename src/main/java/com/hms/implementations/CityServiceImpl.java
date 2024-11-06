package com.hms.implementations;

import com.hms.entity.City;
import com.hms.payload.CityDto;
import com.hms.repository.CityRepository;
import com.hms.repository.CountryRepository;
import com.hms.service.CityService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {


    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;
    @Override
    public void addCity(CityDto cityDto) {
      City city=new City();
      city.setName(city.getName());
      cityRepository.save(city);
    }

    @Override
    public List<City> getAllCity() {
        List<City> all = cityRepository.findAll();
        return all;
    }
    @Transactional
    @Override
    public void deleteCityById(long id) {
     cityRepository.deleteById(id);
    }

    @Override
    public boolean updateCityById(long cityId, CityDto cityDto) {
        Optional<City> byId = cityRepository.findById(cityId);
        if (byId.isPresent()) {
            City city = byId.get();
            city.setName(cityDto.getName());
            cityRepository.save(city);
            return true;
        }
        return false;
    }


}
