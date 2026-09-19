@Entity
public class Transaction {
    @Id @GeneratedValue private Long id;
    @Column(unique = true) private String idempotencyKey;
    private Long sourceAccountId;
    private Long targetAccountId;
    private BigDecimal amount;
    private String currency;
    private String status;
    // toResponse() helper
}