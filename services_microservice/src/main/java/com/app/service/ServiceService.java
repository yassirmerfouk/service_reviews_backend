package com.app.service;

import com.app.dto.*;
import com.app.http.AccountClient;
import com.app.http.ReviewClient;
import com.app.mapper.ServiceMapper;
import com.app.model.Category;
import com.app.model.Service;
import com.app.model.ServiceImage;
import com.app.rabbit.QueueProducer;
import com.app.repository.CategoryRepository;
import com.app.repository.ServiceImageRepository;
import com.app.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@org.springframework.stereotype.Service
public class ServiceService {

    private ServiceRepository serviceRepository;
    private ServiceImageRepository serviceImageRepository;
    private AccountClient accountClient;
    private ReviewClient reviewClient;
    private CategoryRepository categoryRepository;
    private ServiceMapper serviceMapper;
    private FileStorageService fileStorageService;
    private QueueProducer queueProducer;

    public ServiceResponseDTO addService(
            ServiceRequestDto serviceRequestDto,
            MultipartFile image
    ){
        Service service = serviceMapper.toService(serviceRequestDto);
        Category category = categoryRepository.findById(serviceRequestDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category " +serviceRequestDto.getCategoryId()+ " not found"));
        BusinessAccountResponseDTO businessAccountResponseDTO
                = accountClient.getBusinessAccount(service.getBusinessAccountId());
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

    public void addImageToService(Long id, MultipartFile image){
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service "+id+ " not found"));
        if(image != null && !image.isEmpty()){
            String storageName = fileStorageService.saveFile(image);
            ServiceImage serviceImage = ServiceImage.builder()
                    .storageName(storageName)
                    .service(service)
                    .build();
            serviceImageRepository.save(serviceImage);
        }
    }

    public ServiceResponseDTO getService(Long id){
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service "+id+ " not found"));
        BusinessAccountResponseDTO businessAccountResponseDTO =
                accountClient.getBusinessAccount(service.getBusinessAccountId());
        return serviceMapper.toServiceResponseDTO(service, businessAccountResponseDTO);
    }

    public List<ServiceResponseDTO> getServices(){
        return serviceRepository.findAll().stream()
                .map(service -> {
                    BusinessAccountResponseDTO businessAccountResponseDTO =
                            accountClient.getBusinessAccount(service.getBusinessAccountId());
                    return serviceMapper.toServiceResponseDTO(service, businessAccountResponseDTO);
                })
                .collect(Collectors.toList());
    }

    public List<ServiceResponseDTO> getServicesByCategoryId(Long categoryId){
        return serviceRepository.findByCategoryId(categoryId).stream()
                .map(service -> {
                    BusinessAccountResponseDTO businessAccountResponseDTO =
                            accountClient.getBusinessAccount(service.getBusinessAccountId());
                    return serviceMapper.toServiceResponseDTO(service, businessAccountResponseDTO);
                })
                .collect(Collectors.toList());
    }

    public List<ServiceResponseDTO> getServicesByBusinessAccountId(Long businessAccountId){
        return serviceRepository.findByBusinessAccountId(businessAccountId).stream()
                .map(service -> {
                    BusinessAccountResponseDTO businessAccountResponseDTO =
                            accountClient.getBusinessAccount(service.getBusinessAccountId());
                    return serviceMapper.toServiceResponseDTO(service, businessAccountResponseDTO);
                })
                .collect(Collectors.toList());
    }

    public void addReviewToService(ReviewRequestDTO reviewRequestDTO){
        String message = reviewRequestDTO.getServiceId() + ";" + reviewRequestDTO.getPersonnelAccountId()
                + ";" + reviewRequestDTO.getGrade() + ";" + reviewRequestDTO.getComment();
        queueProducer.send(message);
    }

    public void updateServiceAverage(Long id, UpdateAverageRequestDTO updateAverageRequestDTO){
        Service service = serviceRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Service " +id+ " not found")
        );
        service.setReviewsAverage(updateAverageRequestDTO.getAverage());
        service.setReviewsNumbers(updateAverageRequestDTO.getTotal());
        serviceRepository.save(service);
    }

    public List<ReviewResponseDTO> getServiceReviews(Long id){
        Service service = serviceRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Service " +id+ " not found")
        );
        List<ReviewResponseDTO> reviews = reviewClient.getServiceReviews(service.getId());
        reviews.stream().forEach(review -> {
            PersonnelAccountResponseDTO personnelAccountResponseDTO =
                    accountClient.getPersonnelAccount(review.getPersonnelAccountId());
            review.setPersonnelAccountResponseDTO(personnelAccountResponseDTO);
        });
        return reviews;
    }

    public Resource getServiceImage(String imageName){
        try{
            Path image = fileStorageService.uploadPath.resolve(imageName);
            Resource resource = new UrlResource(image.toUri());
            if(resource.exists() && resource.isReadable())
                return resource;
            else
                throw new RuntimeException("could not read the file");
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
