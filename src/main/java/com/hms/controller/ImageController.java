package com.hms.controller;


import com.hms.entity.AppUser;
import com.hms.entity.Images;

import com.hms.implementations.ImagesServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/image")
public class ImageController {



    private ImagesServiceImpl imagesServiceImpl;

    public ImageController( ImagesServiceImpl imagesServiceImpl) {


        this.imagesServiceImpl = imagesServiceImpl;
    }

    @PostMapping(path = "/upload/file/{bucketName}/property/{propertyId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    //localhost:8080/api/v1/image/upload/file/image/property/1
    public ResponseEntity<?> uploadPropertyPhotos(@RequestParam MultipartFile file,
                                        @PathVariable String bucketName,
                                        @PathVariable long propertyId,
                                        @AuthenticationPrincipal AppUser user
    ) {

//        Property property = propertyRepository.findById(propertyId).get();
//        String imageUrl = bucketService.uploadFile(file, bucketName);
//        Images images=new Images();
//        images.setUrl(imageUrl);
//        images.setProperty(property);
        Images save = imagesServiceImpl.save(file, bucketName, propertyId);
        return new ResponseEntity<>(save, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> listAllPropertyPhotos(@RequestParam long propertyId) {
        List<Images> allImages= imagesServiceImpl.getAllImages(propertyId);
        if(allImages.isEmpty()){
            return new ResponseEntity<>("No images found", HttpStatus.OK);
        }
        return new ResponseEntity<>(allImages, HttpStatus.OK);
    }
}
