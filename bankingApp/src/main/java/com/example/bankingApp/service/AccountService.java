package com.example.bankingApp.service;

import com.example.bankingApp.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);

    AccountDto  getAccountById(Long id);

    AccountDto deleteById(Long id);

    AccountDto depositAmount(Long id,double balance);

    AccountDto withdraw(Long id,double balance);


    List<AccountDto> getAllAccount();


}
