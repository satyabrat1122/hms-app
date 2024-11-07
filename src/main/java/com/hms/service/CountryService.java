package com.hms.service;

import com.hms.entity.Country;
import com.hms.payload.CountryDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CountryService {
    public void addCountry(CountryDto countryDto);
    public List<Country> getAllCountry();

    public void deleteCountryById(long countryId);

    public boolean updateCountryById(long country_id, CountryDto countryDto);
}
