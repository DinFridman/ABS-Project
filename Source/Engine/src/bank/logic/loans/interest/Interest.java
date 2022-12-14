package bank.logic.loans.interest;

import java.io.Serializable;

public interface Interest extends Serializable {
    int getFinalAmount();
    int getInterest(); // returns calculated amount of interest to all payments

    int getCyclesPerPayment();

    int getDuration();

    int getPercent(); // returns interest's percent per payment
    int getBaseAmount();

}
