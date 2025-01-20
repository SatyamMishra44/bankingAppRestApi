package com.example.bankingApp.service.impl;

import com.example.bankingApp.dto.AccountDto;
import com.example.bankingApp.entity.Account;
import com.example.bankingApp.mapper.AccountMapper;
import com.example.bankingApp.repository.AccountRepository;
import com.example.bankingApp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceimpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceimpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Account doesn't exist with given account id")
        );
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deleteById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Account doesn't exist with account id")
        );
        accountRepository.deleteById(id); // performing delete here
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto depositAmount(Long id, double balance) {
        Account account  = accountRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Account doesn't exist with given account id")
        );
        double totalAmount = account.getBalance()+balance;
        account.setBalance(totalAmount);
        System.out.println("Successfully deposited: "+balance);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double balance) {
        Account account = accountRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Account doesn't exist with given account id")
        );
        if(balance < account.getBalance()){
            double updatedAmount = account.getBalance()-balance;
            account.setBalance(updatedAmount);
            System.out.println("Successfully withdraw: "+balance);
            Account updatedAccount = accountRepository.save(account);
            return AccountMapper.mapToAccountDto(updatedAccount);
        }else throw new RuntimeException("insufficient balance!!");
         


    }

    @Override
    public List<AccountDto> getAllAccount() {
       List<Account> accounts = accountRepository.findAll();
       return accounts.stream().map(account -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
    }


}
