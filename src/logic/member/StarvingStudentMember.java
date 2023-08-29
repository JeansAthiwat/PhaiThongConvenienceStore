package logic.member;

import logic.store.Store;

public class StarvingStudentMember extends FundamentalMintMember {
    static final int MAX_LOAN = 1920;
    int totalLoan;

    public StarvingStudentMember(String name, int memberID, int point, int digitalMoney) {
        super(name, memberID, point, digitalMoney);
        this.setLoan(0);
        this.discountPercent = 0.20;
    }

    public void loanMoney(int amount) {
       // Store store = Store.getInstance().getStoreMoney();

    }

    public int getTotalLoan() {
        return loan;
    }

    public void setLoan(int loan) {
        if (loan < 0) {
            loan = 0;
        }
        this.loan = loan;
    }
}
