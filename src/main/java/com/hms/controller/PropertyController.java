package com.hms.controller;

import com.hms.entity.Property;
import com.hms.implementations.PropertyServiceImpl;
import com.hms.payload.PropertyDto;
import com.hms.repository.PropertyRepository;
import jakarta.transaction.Transactional;
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
    public ResponseEntity<?> addPropertyDetails(
            @RequestBody PropertyDto propertyDto,
            @RequestParam long country_id,
            @RequestParam long city_id
    ){
        Optional<Property> byName = propertyRepository.findByName(propertyDto.getName());
        if(byName.isPresent()){
            return new ResponseEntity<>("Property already exists", HttpStatus.NOT_ACCEPTABLE);
        }else{
            propertyServiceImpl.addProperty(propertyDto, country_id,city_id);
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

        @GetMapping("/search-hotels")
        public List<Property> searchHotels(
                @RequestParam String name

        ){
            List<Property> properties = propertyRepository.searchHotel(name);
            return properties;
        }

}
