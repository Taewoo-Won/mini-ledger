public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.createAccount("a", "원태우", 2100);
        bank.createAccount("b", "원태순", 3500);
        bank.transfer("a", "b", 1000);
        System.out.println(bank.getAccount("a").getBalance());
        System.out.println(bank.getAccount("b").getBalance());
    }
}
