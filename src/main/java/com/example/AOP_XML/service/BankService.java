package com.example.AOP_XML.service;

public class BankService {

    public Hello hello;

    public BankService(Hello hello){
        this.hello = hello;
    }

    public void deposit(String account, double amount){
        System.out.println("depositing : " + amount + " to account : " + account );
    }

    public void withdraw(String account , double amount){
        System.out.println("withdrawing amount : " + amount + " from account");
    }

    public void newOnboard(String acc){
         this.hello.inviteAccount(acc);
    }
}
