package com.hms.implementations;


import com.hms.entity.Images;
import com.hms.entity.Property;
import com.hms.repository.ImagesRepository;
import com.hms.repository.PropertyRepository;
import com.hms.service.BucketService;
import com.hms.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ImagesServiceImpl implements ImageService {
    private ImagesRepository imagesRepository;
    private PropertyRepository propertyRepository;
    private BucketService bucketService;

    public ImagesServiceImpl(ImagesRepository imagesRepository, PropertyRepository propertyRepository, BucketService bucketService) {
        this.imagesRepository = imagesRepository;
        this.propertyRepository = propertyRepository;
        this.bucketService = bucketService;
    }

    @Override
    public List<Images> getAllImages(long propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(()-> new RuntimeException("Property not found"));
        List<Images> byProperty = imagesRepository.findByProperty(property);
        return byProperty;
    }



    public Images save(MultipartFile file, String bucketName, long propertyId) {
        Property property = propertyRepository.findById(propertyId).get();
        String imageUrl = bucketService.uploadFile(file, bucketName);
        Images images=new Images();
        images.setUrl(imageUrl);
        images.setProperty(property);
        Images save = imagesRepository.save(images);
        return save;
    }
}
