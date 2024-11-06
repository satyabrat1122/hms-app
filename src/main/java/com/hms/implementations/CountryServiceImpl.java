package com.hms.implementations;

import com.hms.entity.Country;
import com.hms.payload.CountryDto;
import com.hms.payload.PropertyDto;
import com.hms.repository.CountryRepository;
import com.hms.service.CountryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
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
    public void deleteCountryById(long id) {
      countryRepository.deleteById(id);
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
