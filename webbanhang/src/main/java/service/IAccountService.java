package service;

import entity.Account;

public interface IAccountService <T>{
    Account login(String user, String pass);
}
