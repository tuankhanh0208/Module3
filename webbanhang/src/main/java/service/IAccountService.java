package service;

import entity.Account;

public interface IAccountService <T>{
    Account login(String user, String pass);

    Account checkAccountExist(String user);

    void signup(String user, String pass);
    boolean resetPassword(String user, String newPass);
}
