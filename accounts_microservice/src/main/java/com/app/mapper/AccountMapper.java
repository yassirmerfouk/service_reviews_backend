package com.app.mapper;

import com.app.dto.BusinessAccountRequestDTO;
import com.app.dto.BusinessAccountResponseDTO;
import com.app.dto.PersonnelAccountRequestDTO;
import com.app.dto.PersonnelAccountResponseDTO;
import com.app.model.BusinessAccount;
import com.app.model.PersonnelAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public BusinessAccount toBusinessAccount(BusinessAccountRequestDTO businessAccountRequestDTO){
        BusinessAccount businessAccount = new BusinessAccount();
        BeanUtils.copyProperties(businessAccountRequestDTO, businessAccount);
        return businessAccount;
    }

    public BusinessAccountResponseDTO toBusinessAccountResponseDTO(BusinessAccount businessAccount){
        BusinessAccountResponseDTO businessAccountResponseDTO = new BusinessAccountResponseDTO();
        BeanUtils.copyProperties(businessAccount, businessAccountResponseDTO);
        return businessAccountResponseDTO;
    }

    public PersonnelAccount toPersonnelAccount(PersonnelAccountRequestDTO personnelAccountRequestDTO){
        PersonnelAccount personnelAccount = new PersonnelAccount();
        BeanUtils.copyProperties(personnelAccountRequestDTO, personnelAccount);
        return personnelAccount;
    }

    public PersonnelAccountResponseDTO toPersonnelAccountResponseDTO(PersonnelAccount personnelAccount){
        PersonnelAccountResponseDTO personnelAccountResponseDTO = new PersonnelAccountResponseDTO();
        BeanUtils.copyProperties(personnelAccount, personnelAccountResponseDTO);
        return personnelAccountResponseDTO;
    }
}
