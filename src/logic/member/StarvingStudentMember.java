package logic.member;

import logic.store.Store;

public class StarvingStudentMember extends FundamentalMintMember {
    static final int MAX_LOAN = 1920;
    int loan;

    public StarvingStudentMember(String name, int memberID, int point, int digitalMoney) {
        super(name, memberID, point, digitalMoney);
        this.setLoan(0);
        this.discountPercent = 0.20;
    }

    public boolean loanMoney(int amount) {
        if (this.getLoan() + amount <= MAX_LOAN) {
            Store store = Store.getInstance();
            store.setStoreMoney(store.getStoreMoney() - amount);
            this.setDigitalMoney(this.getDigitalMoney() + amount);
            this.setLoan(this.getLoan() + amount);
            return true;
        }
        return false;
    }

    public void returnLoan(int amount) {
        Store store = Store.getInstance();
        store.setStoreMoney(store.getStoreMoney() + amount);
        this.setDigitalMoney(this.getDigitalMoney() - amount);
        this.setLoan(this.getLoan() - amount);
    }

    public int getLoan() {
        return loan;
    }

    public void setLoan(int loan) {
        if (loan < 0) {
            loan = 0;
        }
        this.loan = loan;
    }
}
