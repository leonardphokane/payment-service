@Entity
public class Account {
    @Id @GeneratedValue private Long id;
    private BigDecimal balance;
    private String currency;
    @Version private Long version; // optimistic locking
    // debit/credit methods
}