package com.hms.implementations;

import com.hms.entity.City;
import com.hms.entity.Property;
import com.hms.payload.CityDto;
import com.hms.repository.CityRepository;
import com.hms.repository.CountryRepository;
import com.hms.repository.PropertyRepository;
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
    @Autowired
    private PropertyRepository propertyRepository;

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
    public void deleteCityById(long cityId) {
//        City city = cityRepository.findById(cityId).orElseThrow(() -> new RuntimeException("City not found"));
//        for (Property property : city.getProperties()) {
//            property.setCity(null);
//        }
//
//        propertyRepository.saveAll(city.getProperties());
//        cityRepository.deleteById(cityId);

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
