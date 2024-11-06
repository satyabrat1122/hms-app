package com.hms.implementations;

import com.hms.entity.City;
import com.hms.entity.Country;
import com.hms.entity.Property;
import com.hms.payload.PropertyDto;
import com.hms.repository.CityRepository;
import com.hms.repository.CountryRepository;
import com.hms.repository.PropertyRepository;
import com.hms.service.PropertyService;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PropertyServiceImpl implements PropertyService {
    private PropertyRepository propertyRepository;
    private CityRepository cityRepository;
    private CountryRepository countryRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository, CityRepository cityRepository, CountryRepository countryRepository) {
        this.propertyRepository = propertyRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public void addProperty(PropertyDto propertyDto, long country_id, long city_id) {
        City city = cityRepository.findById(city_id).orElseThrow(() -> new RuntimeException("City Not Found"));
        Country country = countryRepository.findById(country_id).orElseThrow(() -> new RuntimeException("Country not found"));

        Property property = mapToEntity(propertyDto);
        property.setCity(city);
        property.setCountry(country);
        propertyRepository.save(property);

    }

    @Override
    public List getAllProperty() {
        List<Property> all = propertyRepository.findAll();
        return all;

    }

    @Override
    public void deletePropertyById(long property_id) {

    }

    @Override
    public boolean updatePropertyById(long property_id, PropertyDto propertyDto) {
        Property property = propertyRepository.findById(property_id).orElseThrow(() -> new RuntimeException("Id Not Found"));
        City city = cityRepository.
                findById(propertyDto.getCity_id())
                .orElseThrow(() -> new RuntimeException("Not found city"));
        Country country = countryRepository.findById(propertyDto.getCountry_id()).orElseThrow(() -> new RuntimeException("Not found Country"));
        property.setName(propertyDto.getName());
        property.setNo_of_beds(propertyDto.getNo_of_beds());
        property.setNo_of_guest(propertyDto.getNo_of_guest());
        property.setNo_of_bathroom(propertyDto.getNo_of_bathroom());
        property.setNo_of_bedrooms(propertyDto.getNo_of_bedrooms());
        property.setCity(city);
        property.setCountry(country);
        propertyRepository.save(property);

        return true;
    }


    Property mapToEntity(PropertyDto propertyDto){
        Property property=new Property();
        property.setName(propertyDto.getName());
        property.setNo_of_beds(propertyDto.getNo_of_beds());
        property.setNo_of_guest(propertyDto.getNo_of_guest());
        property.setNo_of_bathroom(propertyDto.getNo_of_bathroom());
        property.setNo_of_bedrooms(propertyDto.getNo_of_bedrooms());
        return property;
    }
    PropertyDto mapToDto(Property property){
        PropertyDto propertyDto=new PropertyDto();
        propertyDto.setName(property.getName());
        propertyDto.setNo_of_bedrooms(property.getNo_of_bedrooms());
        propertyDto.setNo_of_bathroom(property.getNo_of_bathroom());
        propertyDto.setNo_of_beds(property.getNo_of_beds());
        return propertyDto;

    }
}
