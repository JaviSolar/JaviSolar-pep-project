package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    AccountDAO accountDAO;

    // No-args constructor
    public AccountService() {
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    // method that returns the new account that was created
    public Account createAccount(Account account) {
        if (!(account.getUsername().isEmpty()) && account.getPassword().length() >= 4) {
            return accountDAO.createAccount(account);
        }
        return null;
    }

    // method that returns the verified account
    public Account verifyLoginAccount(String username, String password) {
        return accountDAO.verifyLoginAccount(username, password);
    }

}
