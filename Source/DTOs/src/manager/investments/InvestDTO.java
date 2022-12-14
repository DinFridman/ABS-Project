package manager.investments;

public class InvestDTO {
    final String investorName;
    final String loanName;
    final int amount;

    public InvestDTO(String investorName, String loanName, int amount) {
        this.investorName = investorName;
        this.loanName = loanName;
        this.amount = amount;
    }

    public String getInvestorName() {
        return investorName;
    }

    public String getLoanName() {
        return loanName;
    }

    public int getAmount() {
        return amount;
    }
}
