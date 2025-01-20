package com.example.bankingApp.controller;

import com.example.bankingApp.dto.AccountDto;
import com.example.bankingApp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }


    //Add account rest api
@PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }


    // get account rest api
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable("id") Long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);

    }

    // delete account rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") Long id){
        accountService.deleteById(id);
        return ResponseEntity.ok("Account is Successfully deleted!!");
    }

    //rest api for deposit desired amount to the account

    @PutMapping("/{id}/deposit")
    public  ResponseEntity<AccountDto> depositAmount(@PathVariable Long id, @RequestBody Map<String,Double> request){

        double amount = request.get("balance");
        AccountDto accountDto = accountService.depositAmount(id,amount);
        return  ResponseEntity.ok(accountDto);
    }


    // rest api for withdraw amount from given account id
    @PutMapping("/{id}/withdraw")
    public  ResponseEntity<AccountDto> withdrawAmount(@PathVariable Long id, @RequestBody Map<String ,Double> request){
        double amount = request.get("balance");
        AccountDto accountDto = accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    // get all account rest api
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccount(){
        List<AccountDto> accounts = accountService.getAllAccount();
        return ResponseEntity.ok(accounts);
    }


}
