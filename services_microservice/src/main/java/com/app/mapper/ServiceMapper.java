package com.app.mapper;

import com.app.dto.BusinessAccountResponseDTO;
import com.app.dto.ServiceRequestDto;
import com.app.dto.ServiceResponseDTO;
import com.app.model.Category;
import com.app.model.Service;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ServiceMapper {

    @Autowired
    private CategoryMapper categoryMapper;
    private String url = "http://localhost:8080/SERVICES-MICROSERVICE/services/";

    public Service toService(ServiceRequestDto serviceRequestDto){
        Service service = new Service();
        BeanUtils.copyProperties(serviceRequestDto, service);
        return service;
    }

    public ServiceResponseDTO toServiceResponseDTO(Service service,BusinessAccountResponseDTO businessAccountResponseDTO){
        ServiceResponseDTO serviceResponseDTO =
                new ServiceResponseDTO();
        BeanUtils.copyProperties(service, serviceResponseDTO);
        serviceResponseDTO.setCategoryResponseDTO(categoryMapper.toCategoryResponseDTO(service.getCategory()));
        serviceResponseDTO.setImagesUrls(
                service.getServiceImages().stream()
                        .map(x -> url + "images/" + x.getStorageName() )
                        .collect(Collectors.toList())
        );
        serviceResponseDTO.setBusinessAccountResponseDTO(businessAccountResponseDTO);
        return serviceResponseDTO;
    }
}
