import java.time.LocalDateTime;

public class Transaction {
    private final TransactionType type;
    private final String from;
    private final String to;
    private final long amount;
    private final LocalDateTime timestamp;

    public Transaction(TransactionType type, String from, String to, long amount) {
        this.type = type;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }
    public TransactionType getType() { return this.type; }
    public String getFrom() { return this.from; }
    public String getTo() { return this.to; }
    public long getAmount() { return this.amount; }
    public LocalDateTime getTimestamp() { return this.timestamp; }
}
