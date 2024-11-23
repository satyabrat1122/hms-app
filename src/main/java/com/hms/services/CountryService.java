package com.hms.services;

import com.hms.entity.Country;
import com.hms.payloads.CountryDto;

import java.util.List;


public interface CountryService {
    public void addCountry(CountryDto countryDto);
    public List<Country> getAllCountry();

    public void deleteCountryById(long countryId);

    public boolean updateCountryById(long country_id, CountryDto countryDto);
}
