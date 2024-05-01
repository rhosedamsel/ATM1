class WithdrawalTransaction extends Transaction {
    public WithdrawalTransaction(double amount) {
        super(amount);
    }

    @Override
    public boolean execute(Account account) {
        if (account.withdraw(amount)) {
            return true;
        } else {
            return false;
        }
    }
}