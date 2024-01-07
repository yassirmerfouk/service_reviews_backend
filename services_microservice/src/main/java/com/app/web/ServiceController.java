package com.app.web;

import com.app.dto.ServiceRequestDto;
import com.app.dto.ServiceResponseDTO;
import com.app.service.ServiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/services")
@AllArgsConstructor
public class ServiceController {

    private ServiceService serviceService;

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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteService(@PathVariable Long id){
        serviceService.deleteService(id);
    }

    @GetMapping("/category/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ServiceResponseDTO> getServicesByCategoryId(@PathVariable Long categoryId){
        return serviceService.getServicesByCategoryId(categoryId);
    }

}
