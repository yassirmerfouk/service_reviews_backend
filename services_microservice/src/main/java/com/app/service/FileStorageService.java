package com.app.service;

import com.app.exception.CustomException;
import com.cloudinary.Cloudinary;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FileStorageService {
    public Path uploadPath = Paths.get("services_microservice/images");
    @Resource
    private Cloudinary cloudinary;

    @PostConstruct
    public void init() {
        try {
            if(!Files.exists(uploadPath))
                Files.createDirectories(uploadPath);
        }catch(Exception e) {
            throw new RuntimeException("Could not create uploads folder");
        }
    }

    public String saveFile(MultipartFile file) {
        try {
            String name = UUID.randomUUID().toString() + "." + getExtension(file);
            Files.copy(file.getInputStream(), uploadPath.resolve(name));
            return name;
        }catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not save the file");
        }
    }

    private String getExtension(MultipartFile file) {
        return file.getOriginalFilename().split("\\.")[file.getOriginalFilename().split("\\.").length-1];
    }

    public void deleteFile(String name) {
        try {
            Files.deleteIfExists(uploadPath.resolve(name));
        }catch(Exception e) {
            throw new RuntimeException("Cannot delete the file");
        }
    }

    public String uploadFileToCloud(MultipartFile file) {
        try{
            Map<Object, Object> options = new HashMap<>();
            Map uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);
            String publicId = (String) uploadedFile.get("public_id");
            return cloudinary.url().secure(true).generate(publicId);
        }catch (IOException e){
            throw new CustomException(e.getMessage());
        }
    }
}
