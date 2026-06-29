public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.createAccount("a", "원태우", 2100);
        bank.createAccount("b", "원태순", 3500);
        bank.transfer("a", "b", 200, "dup-key");
        bank.transfer("a", "b", 200, "dup-key");
        bank.transfer("a", "b", 1000, "key-1");
        bank.deposit("a", 500);
        bank.withdraw("b", 300);
        System.out.println(bank.getAccount("a").getBalance());
        System.out.println(bank.getAccount("b").getBalance());
        System.out.println("--- 이체 전 a 잔액: " + bank.getAccount("a").getBalance());
        try {
            bank.transfer("a", "z", 500, "key-2");
        } catch (AccountNotFoundException e) {
            System.out.println("잡았다: " + e.getMessage());
        }
        System.out.println("--- 이체 후 a 잔액: " + bank.getAccount("a").getBalance());
        try {
            bank.transfer("a", "b", 999999, "key-3");
        } catch (InsufficientBalanceException e) {
            System.out.println("잡았다: " + e.getMessage());
        }
        System.out.println("===== 거래 내역 =====");
        for (Transaction t : bank.getHistory()) {
            System.out.println(t.getType() + " " + t.getFrom() + " -> " + t.getTo() + " : " + t.getAmount());
        }
    }
}
