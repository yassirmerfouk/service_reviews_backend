package com.app.service;

import com.app.dto.PersonnelAccountRequestDTO;
import com.app.dto.PersonnelAccountResponseDTO;
import com.app.mapper.AccountMapper;
import com.app.model.PersonnelAccount;
import com.app.model.Role;
import com.app.repository.PersonnelAccountRepository;
import com.app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonnelAccountService {

    private UserRepository userRepository;
    private PersonnelAccountRepository personnelAccountRepository;
    private PasswordEncoder passwordEncoder;
    private AccountMapper accountMapper;

    public PersonnelAccountResponseDTO addPersonnelAccount(PersonnelAccountRequestDTO personnelAccountRequestDTO){
        if(userRepository.existsByEmail(personnelAccountRequestDTO.getEmail()))
            throw new RuntimeException("Email already exist");
        PersonnelAccount personnelAccount = accountMapper.toPersonnelAccount(personnelAccountRequestDTO);
        personnelAccount.setPassword(passwordEncoder.encode(personnelAccount.getPassword()));
        personnelAccount.setRole(Role.PERSONNEL);
        personnelAccountRepository.save(personnelAccount);
        return accountMapper.toPersonnelAccountResponseDTO(personnelAccount);
    }

    public List<PersonnelAccountResponseDTO> getPersonnelAccounts(){
        return personnelAccountRepository.findAll().stream()
                .map(account -> accountMapper.toPersonnelAccountResponseDTO(account))
                .collect(Collectors.toList());
    }

    public PersonnelAccountResponseDTO getPersonnelAccount(Long id){
        PersonnelAccount personnelAccount = personnelAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("APersonnel account " +id+ " not found"));
        return accountMapper.toPersonnelAccountResponseDTO(personnelAccount);
    }
}
