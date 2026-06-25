import java.util.Map;
import java.util.HashMap;

public class Bank {
    private Map<String, Account> accounts = new HashMap<>();

    public void createAccount(String id, String owner, long initialBalance) {
        Account account = new Account(id, owner, initialBalance);
        accounts.put(id, account);
    }

    public Account getAccount(String id) {
        Account account = accounts.get(id);
        if (account == null) {
            throw new AccountNotFoundException("계좌를 찾을 수 없습니다");
        }
        return account;
    }

    public void transfer(String fromId, String toId, long amount) {
        Account fromAccount = getAccount(fromId);
        Account toAccount = getAccount(toId);
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
    }
}
