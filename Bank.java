import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Bank {
    private Map<String, Account> accounts = new HashMap<>();
    private List<Transaction> history = new ArrayList<>();
    private final Set<String> usedKeys = new HashSet<>();

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

    public List<Transaction> getHistory() {
        return history;
    }

    public List<Transaction> getHistoryByAccount(String id) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : history) {
            if (id.equals(t.getFrom()) || id.equals(t.getTo())) {
                result.add(t);
            }
        }
        return result;
    }

    public void deposit(String id, long amount) {
        Account toAccount = getAccount(id);
        toAccount.deposit(amount);
        history.add(new Transaction(TransactionType.DEPOSIT, null, id, amount));
    }

    public void withdraw(String id, long amount) {
        Account fromAccount = getAccount(id);
        fromAccount.withdraw(amount);
        history.add(new Transaction(TransactionType.WITHDRAW, id, null, amount));
    }

    public void transfer(String fromId, String toId, long amount, String idempotencyKey) {
        if (usedKeys.contains(idempotencyKey)) {
            return;
        }
        Account fromAccount = getAccount(fromId);
        Account toAccount = getAccount(toId);
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
        history.add(new Transaction(TransactionType.TRANSFER, fromId, toId, amount));
        usedKeys.add(idempotencyKey);
    }
}
