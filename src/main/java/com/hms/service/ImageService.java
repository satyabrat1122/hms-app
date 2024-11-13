package com.hms.service;

import com.hms.entity.Images;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    public List<Images> getAllImages(long propertyId);
    public Images save(MultipartFile file, String bucketName, long propertyId);
}
