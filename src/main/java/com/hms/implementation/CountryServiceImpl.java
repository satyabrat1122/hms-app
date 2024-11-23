package com.hms.implementation;

import com.hms.entity.Country;
import com.hms.entity.Property;
import com.hms.payloads.CountryDto;
import com.hms.repository.CountryRepository;
import com.hms.repository.PropertyRepository;
import com.hms.services.CountryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private CountryRepository countryRepository;
    private PropertyRepository propertyRepository;

    public CountryServiceImpl(CountryRepository countryRepository, PropertyRepository propertyRepository) {
        this.countryRepository = countryRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public void addCountry(CountryDto countryDto) {
        Country country=new Country();
        country.setName(countryDto.getName());
        countryRepository.save(country);

    }

    @Override
    public List<Country> getAllCountry() {
        List<Country> all = countryRepository.findAll();
        return all;
    }
    @Transactional
    @Override
    public void deleteCountryById(long countryId) {
        Country country = countryRepository.findById(countryId).orElseThrow(() -> new EntityNotFoundException("Could not find"));
        List<Property> property = propertyRepository.findByCountry(country);
        for(Property property1:property){
            property1.setCountry(null);
            propertyRepository.save(property1);
        }

        countryRepository.deleteById(countryId);
    }

    @Override
    public boolean updateCountryById(long country_id, CountryDto countryDto) {
        Optional<Country> byId = countryRepository.findById(country_id);
        if(byId.isPresent()){
            Country country = byId.get();
            country.setName(countryDto.getName());
            countryRepository.save(country);
            return true;
        }
        return false;
    }

    public Country findById(long countryId) {
        Optional<Country> byId = countryRepository.findById(countryId);
        Country country = byId.get();
        return country;
    }
}
