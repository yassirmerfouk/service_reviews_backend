package com.app.service;

import com.app.dto.BusinessAccountResponseDTO;
import com.app.dto.ServiceRequestDto;
import com.app.dto.ServiceResponseDTO;
import com.app.http.BusinessAccountClient;
import com.app.mapper.ServiceMapper;
import com.app.model.Category;
import com.app.model.Service;
import com.app.model.ServiceImage;
import com.app.repository.CategoryRepository;
import com.app.repository.ServiceImageRepository;
import com.app.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@org.springframework.stereotype.Service
public class ServiceService {

    private ServiceRepository serviceRepository;
    private ServiceImageRepository serviceImageRepository;
    private BusinessAccountClient businessAccountClient;
    private CategoryRepository categoryRepository;
    private ServiceMapper serviceMapper;
    private FileStorageService fileStorageService;

    public ServiceResponseDTO addService(
            ServiceRequestDto serviceRequestDto,
            MultipartFile image
    ){
        Service service = serviceMapper.toService(serviceRequestDto);
        Category category = categoryRepository.findById(serviceRequestDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category " +serviceRequestDto.getCategoryId()+ " not found"));
        BusinessAccountResponseDTO businessAccountResponseDTO
                = businessAccountClient.getBusinessAccount(service.getBusinessAccountId());
        service.setCategory(category);
        service.setReviewsAverage(0);
        service.setReviewsNumbers(0);
        serviceRepository.save(service);
        if(image != null && !image.isEmpty()){
            String storageName = fileStorageService.saveFile(image);
                ServiceImage serviceImage = ServiceImage.builder()
                        .storageName(storageName)
                        .service(service)
                        .build();
                serviceImageRepository.save(serviceImage);
                service.setServiceImages(List.of(serviceImage));
        }
        return serviceMapper.toServiceResponseDTO(service, businessAccountResponseDTO);
    }

    public void deleteService(Long id){
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service "+id+ " not found"));
        serviceRepository.delete(service);
        service.getServiceImages().stream().forEach(
                image -> fileStorageService.deleteFile(image.getStorageName())
        );
    }

    public ServiceResponseDTO getService(Long id){
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service "+id+ " not found"));
        BusinessAccountResponseDTO businessAccountResponseDTO =
                businessAccountClient.getBusinessAccount(service.getBusinessAccountId());
        return serviceMapper.toServiceResponseDTO(service, businessAccountResponseDTO);
    }

    public List<ServiceResponseDTO> getServices(){
        return serviceRepository.findAll().stream()
                .map(service -> {
                    BusinessAccountResponseDTO businessAccountResponseDTO =
                            businessAccountClient.getBusinessAccount(service.getBusinessAccountId());
                    return serviceMapper.toServiceResponseDTO(service, businessAccountResponseDTO);
                })
                .collect(Collectors.toList());
    }

    public List<ServiceResponseDTO> getServicesByCategoryId(Long categoryId){
        return serviceRepository.findByCategoryId(categoryId).stream()
                .map(service -> {
                    BusinessAccountResponseDTO businessAccountResponseDTO =
                            businessAccountClient.getBusinessAccount(service.getBusinessAccountId());
                    return serviceMapper.toServiceResponseDTO(service, businessAccountResponseDTO);
                })
                .collect(Collectors.toList());
    }
}
