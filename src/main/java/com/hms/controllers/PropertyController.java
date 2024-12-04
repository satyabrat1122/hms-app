package com.hms.controllers;

import com.hms.entity.Property;
import com.hms.service.PropertyServiceImpl;
import com.hms.payloads.PropertyDto;
import com.hms.repository.PropertyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {
    private PropertyServiceImpl propertyServiceImpl ;
    private PropertyRepository propertyRepository;

    public PropertyController(PropertyServiceImpl propertyServiceImpl, PropertyRepository propertyRepository) {
        this.propertyServiceImpl = propertyServiceImpl;

        this.propertyRepository = propertyRepository;
    }

    @PostMapping("/addProperty")
    //localhost:8080/api/v1/property/addProperty?country_id=1&city_id=1
    public ResponseEntity<?> addPropertyDetails(
            @RequestBody PropertyDto propertyDto,
            @RequestParam long country_id,
            @RequestParam long city_id,
            @RequestParam long state_id

    ) {
        Optional<Property> byName = propertyRepository.findByName(propertyDto.getName());
        if(byName.isPresent()){
            return new ResponseEntity<>("Property already exists", HttpStatus.NOT_ACCEPTABLE);
        }else{
            propertyServiceImpl.addProperty(propertyDto, country_id,city_id,state_id);
            return new ResponseEntity<>("Property successfully added", HttpStatus.CREATED);
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> listAll(){
        List<Property> all = propertyRepository.findAll();
        return new ResponseEntity<>(all,HttpStatus.OK);

    }

    @DeleteMapping
    public ResponseEntity<?> deleteProperty(
            @RequestParam long id
    ){
        propertyRepository.deleteById(id);
        return new ResponseEntity<>("deleted",HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProperty(@PathVariable("id") long property_id,
                                            @RequestBody PropertyDto propertyDto){
        Optional<Property> byId = propertyRepository.findById(property_id);
        if(byId.isPresent()){
            boolean b = propertyServiceImpl.updatePropertyById(property_id, propertyDto);
            if(b==true){
                return new ResponseEntity<>("Update Successfully",HttpStatus.OK);
            }
            }return new ResponseEntity<>("Id not found",HttpStatus.NOT_FOUND);
        }
       //localhost:8080/api/v1/property/search-hotels?name=
        @GetMapping("/search-hotels")
        public ResponseEntity<?> searchHotels(
                @RequestParam String name)
        {
        List<Property> properties = propertyRepository.searchHotel(name);
        if(properties.isEmpty()){
            return new ResponseEntity<>("No hotels found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(properties,HttpStatus.OK);
    }

}
