package service;

import entity.Account;
import repository.AccountRepository;
import repository.IAccountRepository;

import java.sql.SQLException;

public class AccountService implements IAccountService<Account>{
    private final IAccountRepository<Account> iAccountRepository = new AccountRepository();

    public AccountService() throws SQLException {
    }

    @Override
    public Account login(String user, String pass) {
        return this.iAccountRepository.login(user,pass);
    }

    @Override
    public Account checkAccountExist(String user) {
        return this.iAccountRepository.checkAccountExist(user);
    }

    @Override
    public void signup(String user, String pass) {
        this.iAccountRepository.signup(user,pass);
    }

    @Override
    public boolean resetPassword(String user, String newPass) {
        return iAccountRepository.resetPassword(user, newPass);
    }
}
