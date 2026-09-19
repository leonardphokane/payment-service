@Service
public class PaymentService {
    private final TransactionRepository transactionRepo;
    private final AccountRepository accountRepo;

    @Transactional
    public PaymentResponse processTransfer(String idempotencyKey, PaymentRequest request) {
        return transactionRepo.findByIdempotencyKey(idempotencyKey)
                .map(Transaction::toResponse)
                .orElseGet(() -> {
                    Account source = accountRepo.findById(request.getSourceAccountId())
                            .orElseThrow(AccountNotFoundException::new);
                    Account target = accountRepo.findById(request.getTargetAccountId())
                            .orElseThrow(AccountNotFoundException::new);

                    if (source.getBalance().compareTo(request.getAmount()) < 0) {
                        throw new InsufficientFundsException();
                    }

                    source.setBalance(source.getBalance().subtract(request.getAmount()));
                    target.setBalance(target.getBalance().add(request.getAmount()));
                    accountRepo.save(source);
                    accountRepo.save(target);

                    Transaction txn = new Transaction();
                    txn.setIdempotencyKey(idempotencyKey);
                    txn.setSourceAccountId(source.getId());
                    txn.setTargetAccountId(target.getId());
                    txn.setAmount(request.getAmount());
                    txn.setCurrency(request.getCurrency());
                    txn.setStatus("SUCCESS");
                    transactionRepo.save(txn);

                    return txn.toResponse();
                });
    }
}
