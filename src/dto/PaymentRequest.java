public class PaymentRequest {
    @NotNull private Long sourceAccountId;
    @NotNull private Long targetAccountId;
    @NotNull @Positive private BigDecimal amount;
    @NotBlank private String currency;
    // getters/setters
}