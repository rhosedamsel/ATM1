class DepositTransaction extends Transaction {
    public DepositTransaction(double amount) {
        super(amount);
    }

    @Override
    public boolean execute(Account account) {
        account.deposit(amount);
        return true;
    }
}