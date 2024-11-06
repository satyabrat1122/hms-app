package com.hms.controller;

import com.hms.entity.City;
import com.hms.implementations.CityServiceImpl;
import com.hms.payload.CityDto;
import com.hms.repository.CityRepository;
import com.hms.service.CityService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/city")
public class CityController {
    private CityServiceImpl cityServiceImpl;
    private CityRepository cityRepository;

    public CityController(CityServiceImpl cityServiceImpl, CityRepository cityRepository) {
        this.cityServiceImpl = cityServiceImpl;
        this.cityRepository = cityRepository;
    }

    @PostMapping("/addCity")
    public ResponseEntity<?> addCityDetails(
            @RequestBody CityDto cityDto
            ){

        Optional<City> byName = cityRepository.findByName(cityDto.getName());
        if(byName.isPresent()){
            return new ResponseEntity<>("City already exits", HttpStatus.NOT_ACCEPTABLE);
        }else{
            cityServiceImpl.addCity(cityDto);
            return new ResponseEntity<>("City added successfully", HttpStatus.CREATED);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCity(){
        List<City> all = cityRepository.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);

     }

    @DeleteMapping("/deleteCityById/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable long id){
         Optional<City> byId = cityRepository.findById(id);
        City city = byId.get();
        String name = city.getName();
        if(byId.isPresent()){
            cityServiceImpl.deleteCityById(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Not Found",HttpStatus.OK);
        }
    }
      @GetMapping("/findAll")
      public ResponseEntity<?> findAll(){
          List<City> all = cityServiceImpl.getAllCity();
          return new ResponseEntity<>(all,HttpStatus.OK);

      }
      @PutMapping("/{id}")
      public ResponseEntity<?> updateCity(@PathVariable("id") long cityId,
                                          @RequestBody CityDto cityDto){
          boolean b = cityServiceImpl.updateCityById(cityId, cityDto);
          if(b==true){

              return new ResponseEntity<>("Updated successfully",HttpStatus.OK);
          }
       return new ResponseEntity<>("Id not found",HttpStatus.OK);
      }

}
