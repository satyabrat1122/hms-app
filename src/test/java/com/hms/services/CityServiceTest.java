package com.hms.services;

import com.hms.entity.City;
import com.hms.service.CityServiceImpl;
import com.hms.repository.CityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CityServiceTest {
    @Mock
    CityRepository cityRepository;
    @InjectMocks
    CityServiceImpl cityService;

    @Test
    void addCityShouldAddCitySuccessfully(){
        City city=new City();
        city.setName("Angul");
        city.setId(1l);
         Mockito.when(cityRepository.save(city)).thenReturn(city);
        City city1 = cityService.addCity(city);
        Assertions.assertEquals(1,city1.getId());
    }

}