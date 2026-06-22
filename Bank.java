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
            throw new IllegalArgumentException("계좌를 찾을 수 없습니다");
        }
        return account;
    }
}
