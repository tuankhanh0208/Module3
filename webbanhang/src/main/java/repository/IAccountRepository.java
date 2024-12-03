package repository;

import entity.Account;

public interface IAccountRepository<E>{
    Account login(String user, String pass);

    Account checkAccountExist(String user);

    void signup(String user, String pass);

    boolean resetPassword(String user, String newPass);
}
