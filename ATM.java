class ATM {
    private Account account;

    public ATM(Account account) {
        this.account = account;
    }

    public double checkBalance() {
        return account.getBalance();
    }

    public boolean executeTransaction(Transaction transaction) {
        return transaction.execute(account);
    }
}