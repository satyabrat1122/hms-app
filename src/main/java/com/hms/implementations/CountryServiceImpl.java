package com.hms.implementations;

import com.hms.entity.Country;
import com.hms.entity.Property;
import com.hms.payload.CountryDto;
import com.hms.payload.PropertyDto;
import com.hms.repository.CountryRepository;
import com.hms.repository.PropertyRepository;
import com.hms.service.CountryService;
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
        // Get the Country by ID
//        Country country = countryRepository.findById(countryId).orElseThrow(() -> new RuntimeException("Country not found"));
//
//        // Disassociate the properties from this country by setting the country reference to null
//        for (Property property : country.getProperties()) {
//            property.setCountry(null);
//        }
//
//        // Optionally, save the updated properties (in case changes need to be persisted)
//        propertyRepository.saveAll(country.getProperties());
//
//        // Now, delete the country
//        countryRepository.deleteById(countryId);
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
}
