package com.app.service;

import com.app.dto.BusinessAccountRequestDTO;
import com.app.dto.BusinessAccountResponseDTO;
import com.app.mapper.AccountMapper;
import com.app.model.BusinessAccount;
import com.app.model.Role;
import com.app.repository.BusinessAccountRepository;
import com.app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BusinessAccountService {

    private UserRepository userRepository;
    private BusinessAccountRepository businessAccountRepository;
    private PasswordEncoder passwordEncoder;
    private AccountMapper accountMapper;

    public BusinessAccountResponseDTO addBusinessAccount(BusinessAccountRequestDTO businessAccountRequestDTO){
        if(userRepository.existsByEmail(businessAccountRequestDTO.getEmail()))
            throw new RuntimeException("Email already exist");
        BusinessAccount businessAccount = accountMapper.toBusinessAccount(businessAccountRequestDTO);
        businessAccount.setPassword(passwordEncoder.encode(businessAccount.getPassword()));
        businessAccount.setRole(Role.BUSINESS);
        businessAccountRepository.save(businessAccount);
        return accountMapper.toBusinessAccountResponseDTO(businessAccount);
    }

    public List<BusinessAccountResponseDTO> getBusinessAccounts(){
        return businessAccountRepository.findAll().stream()
                .map(account -> accountMapper.toBusinessAccountResponseDTO(account))
                .collect(Collectors.toList());
    }

    public BusinessAccountResponseDTO getBusinessAccount(Long id){
        BusinessAccount businessAccount = businessAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Businness account "+id+ " not found"));
        return accountMapper.toBusinessAccountResponseDTO(businessAccount);
    }

}
