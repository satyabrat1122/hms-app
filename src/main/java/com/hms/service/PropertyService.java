package com.hms.service;

import com.hms.entity.Property;
import com.hms.payload.PropertyDto;

import java.util.List;

public interface PropertyService {
    public void addProperty(PropertyDto propertyDto , long country_id, long city_id,long state_id);



    public List<Property> getAllProperty();

    public void deletePropertyById(long property_id);

    public boolean updatePropertyById(long propertyId, PropertyDto propertyDto);


}
