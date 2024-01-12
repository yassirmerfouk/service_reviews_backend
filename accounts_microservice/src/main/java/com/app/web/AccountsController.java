package com.app.web;

import com.app.dto.BusinessAccountRequestDTO;
import com.app.dto.BusinessAccountResponseDTO;
import com.app.dto.PersonnelAccountRequestDTO;
import com.app.dto.PersonnelAccountResponseDTO;
import com.app.service.BusinessAccountService;
import com.app.service.PersonnelAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@CrossOrigin("*")
@AllArgsConstructor
public class AccountsController {

    private PersonnelAccountService personnelAccountService;
    private BusinessAccountService businessAccountService;

    @PostMapping("/personnel")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonnelAccountResponseDTO addPersonnelAccount(
            @RequestBody PersonnelAccountRequestDTO personnelAccountRequestDTO
            ){
        return personnelAccountService.addPersonnelAccount(personnelAccountRequestDTO);
    }

    @PostMapping("/business")
    @ResponseStatus(HttpStatus.CREATED)
    public BusinessAccountResponseDTO addBusinessAccount(
            @RequestBody BusinessAccountRequestDTO businessAccountRequestDTO
            ){
        return businessAccountService.addBusinessAccount(businessAccountRequestDTO);
    }

    @GetMapping("/personnel")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonnelAccountResponseDTO> getPersonnelAccounts(){
        return personnelAccountService.getPersonnelAccounts();
    }

    @GetMapping("/business")
    @ResponseStatus(HttpStatus.OK)
    public List<BusinessAccountResponseDTO> getBusinessAccounts(){
        return businessAccountService.getBusinessAccounts();
    }

    @GetMapping("/personnel/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonnelAccountResponseDTO getPersonnelAccount(@PathVariable Long id){
        return personnelAccountService.getPersonnelAccount(id);
    }

    @GetMapping("/business/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BusinessAccountResponseDTO getBusinessAccount(@PathVariable Long id){
        return businessAccountService.getBusinessAccount(id);
    }
}
