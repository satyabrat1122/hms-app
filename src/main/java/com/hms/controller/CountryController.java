package com.hms.controller;

import com.hms.entity.Country;
import com.hms.implementations.CountryServiceImpl;
import com.hms.payload.CountryDto;
import com.hms.repository.CountryRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {
     private CountryServiceImpl countryServiceImpl;
    private  CountryRepository countryRepository;


    public CountryController(CountryServiceImpl countryServiceImpl,
                             CountryRepository countryRepository) {
        this.countryServiceImpl = countryServiceImpl;
        this.countryRepository = countryRepository;
    }

    @PostMapping("/addCountry")
    public ResponseEntity<?> createCountry(
            @RequestBody CountryDto countryDto
            ) {
        Optional<Country> byName = countryRepository.findByName(countryDto.getName());
        if(byName.isPresent()){
            return new ResponseEntity<>("Already present ",HttpStatus.OK);
        }
        countryServiceImpl.addCountry(countryDto);
        return new ResponseEntity<>("Added successfully",HttpStatus.CREATED);

  }
  @GetMapping
  public ResponseEntity<?> findAll(){
      List<Country> all = countryRepository.findAll();
      return new ResponseEntity<>(all,HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public String deleteCountry(@PathVariable long id){
        countryServiceImpl.deleteCountryById(id);
        return "Deleted";
  }

  @PutMapping("/{id}")
  public String updateCountry(@PathVariable("id") long countryId,
                              @RequestBody CountryDto countryDto){
      boolean b = countryServiceImpl.updateCountryById(countryId, countryDto);
      if(b==true){
          return "Updated";
      }
      return "Id not found";
  }
}
