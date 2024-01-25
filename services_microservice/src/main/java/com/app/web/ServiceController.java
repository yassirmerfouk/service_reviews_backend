package com.app.web;

import com.app.dto.*;
import com.app.service.ServiceService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/services")
@AllArgsConstructor
public class ServiceController {

    private ServiceService serviceService;

    @PreAuthorize("hasAuthority('SCOPE_BUSINESS')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceResponseDTO addService(
            @RequestPart(name = "service") ServiceRequestDto serviceRequestDto,
            @RequestPart(name = "image", required = false)MultipartFile image
            ){
        return serviceService.addService(serviceRequestDto, image);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ServiceResponseDTO> getServices(){
        return serviceService.getServices();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServiceResponseDTO getService(@PathVariable Long id){
        return serviceService.getService(id);
    }

    @PreAuthorize("hasAuthority('SCOPE_BUSINESS')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteService(@PathVariable Long id){
        serviceService.deleteService(id);
    }

    @PreAuthorize("hasAuthority('SCOPE_BUSINESS')")
    @PostMapping(path = "/{id}/images",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addImageToService(
            @PathVariable Long id,
            @RequestPart(name = "image", required = true) MultipartFile image
    ){
        serviceService.addImageToService(id,image);
    }

    @GetMapping("/categories/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ServiceResponseDTO> getServicesByCategoryId(@PathVariable Long categoryId){
        return serviceService.getServicesByCategoryId(categoryId);
    }

    @PreAuthorize("hasAuthority('SCOPE_BUSINESS')")
    @GetMapping("/accounts/business/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ServiceResponseDTO> getServicesByBusinessAccountId(@PathVariable Long accountId){
        return serviceService.getServicesByBusinessAccountId(accountId);
    }

    @PostMapping("/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public void addReviewToService(@RequestBody ReviewRequestDTO reviewRequestDTO){
        serviceService.addReviewToService(reviewRequestDTO);
    }

    @GetMapping("/{id}/reviews")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewResponseDTO> getServiceReviews(@PathVariable Long id){
        return serviceService.getServiceReviews(id);
    }

    @PostMapping("/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateServiceAverage(@PathVariable Long id, @RequestBody UpdateAverageRequestDTO updateAverageRequestDTO){
        serviceService.updateServiceAverage(id, updateAverageRequestDTO);
    }

    @GetMapping("/images/{imageName}")
    public ResponseEntity<Resource> getServiceImage(@PathVariable String imageName){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentDispositionFormData("attachment", imageName);
        return new ResponseEntity<Resource>(serviceService.getServiceImage(imageName), headers, HttpStatus.OK);
    }

}
