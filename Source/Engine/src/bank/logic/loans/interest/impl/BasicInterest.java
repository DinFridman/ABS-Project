package bank.logic.loans.interest.impl;

import bank.logic.loans.interest.Interest;

public class BasicInterest implements Interest {
    private final int percent;
    private final int cyclesPerPayment, duration, baseAmount;

    public BasicInterest(int percent, int baseAmount, int cyclesPerPayment, int duration) {
        this.percent = percent;
        this.baseAmount = baseAmount;
        this.cyclesPerPayment = cyclesPerPayment;
        this.duration = duration;
    }


    @Override
    public int getCyclesPerPayment() {
        return cyclesPerPayment;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public int getPercent() {
        return percent;
    }

    @Override
    public int getInterest() {
        return (int) (baseAmount * percent / 100 );
    }

    @Override
    public int getFinalAmount() {
        return baseAmount + getInterest();
    }

    @Override
    public int getBaseAmount() {
        return baseAmount;
    }

    @Override
    public String toString() {
        return "BasicInterest{" +
                "percent=" + percent +
                ", baseAmount=" + baseAmount +
                ", totalPayment=" + getFinalAmount() +
                '}';
    }
}
