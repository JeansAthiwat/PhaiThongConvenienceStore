package logic.members;

import logic.store.Store;

public class StarvingStudentMember extends FundamentalMintMember { //todo STUDENT: create this class from scratch
    public static final int MAX_LOAN = 1920;
    int loan;

    public StarvingStudentMember(String name, int memberID, int point, int digitalMoney) {
        super(name, memberID, point, digitalMoney);
        this.setLoan(0);
        this.discountPercent = 0.20;
    }

    @Override
    public void convertPoint() {
        int totalPoint = this.getPoint();
        int totalMoney = totalPoint / 75;
        this.setPoint(this.getPoint() - totalMoney * 75);
        this.setDigitalMoney(this.getDigitalMoney() + totalMoney);
    }

    @Override
    public String toString() {
        return "(" + getTierName() + ")" + " " + getMemberID() + "-" + getName() + " DMoney: "
                + getDigitalMoney() + " Pts: " + getPoint() + " Loans: " + getLoan();

    }

    @Override
    public String getTierName() {
        return "StarvingStudent";
    }


    public boolean loanMoney(int amount) {
        if ((this.getLoan() + amount <= MAX_LOAN) && (Store.getInstance().getStoreMoney() >= amount)) {
            Store store = Store.getInstance();
            store.setStoreMoney(store.getStoreMoney() - amount);
            this.setDigitalMoney(this.getDigitalMoney() + amount);
            this.setLoan(this.getLoan() + amount);
            return true;
        }
        return false;
    }

    public boolean returnLoan(int amount) {
        if(amount >= this.getLoan()){ // if input amount is more than
            amount = this.getLoan();
        }
        if (this.getDigitalMoney() >= amount) {
            Store store = Store.getInstance();
            store.setStoreMoney(store.getStoreMoney() + amount);
            this.setDigitalMoney(this.getDigitalMoney() - amount);
            this.setLoan(this.getLoan() - amount);
            return true;
        }
        return false;
    }

    public int getLoan() {
        return loan;
    }

    public void setLoan(int loan) {
        if (loan < 0) {
            loan = 0;
        }
        if(loan > MAX_LOAN){
            loan = MAX_LOAN;
        }
        this.loan = loan;
    }


}
