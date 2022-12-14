package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoanModel {
    private final StringProperty id;
    private final StringProperty status;
    private final IntegerProperty amount;
    private final IntegerProperty startYaz;
    private final IntegerProperty endYaz;
    private final IntegerProperty nextPaymentInYaz;
    private final IntegerProperty finalAmount;
    private final IntegerProperty amountToActive;
    private final IntegerProperty investorsAmount;
    private final IntegerProperty deriskAmount;
    private final IntegerProperty missingCycles;
    private final IntegerProperty paymentAmount;
    private final IntegerProperty amountLeftToPay;

    private LoanModel(LoanModelBuilder builder) {
        this.id = builder.id;
        this.amount = builder.amount;
        this.startYaz = builder.startYaz;
        this.endYaz = builder.endYaz;
        this.nextPaymentInYaz = builder.nextPaymentInYaz;
        this.finalAmount = builder.finalAmount;
        this.status = builder.status;
        this.amountToActive = builder.amountToActive;
        this.deriskAmount = builder.deriskAmount;
        this.investorsAmount = builder.investorsAmount;
        this.missingCycles = builder.missingCycles;
        this.paymentAmount = builder.paymentAmount;
        this.amountLeftToPay = builder.amountLeftToPay;
    }

    public int getPaymentAmount() {
        return paymentAmount.get();
    }

    public IntegerProperty paymentAmountProperty() {
        return paymentAmount;
    }

    public int getAmountLeftToPay() {
        return amountLeftToPay.get();
    }

    public IntegerProperty amountLeftToPayProperty() {
        return amountLeftToPay;
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public int getAmount() {
        return amount.get();
    }

    public IntegerProperty amountProperty() {
        return amount;
    }

    public int getStartYaz() {
        return startYaz.get();
    }

    public IntegerProperty startYazProperty() {
        return startYaz;
    }

    public int getEndYaz() {
        return endYaz.get();
    }

    public IntegerProperty endYazProperty() {
        return endYaz;
    }

    public int getNextPaymentInYaz() {
        return nextPaymentInYaz.get();
    }

    public IntegerProperty nextPaymentInYazProperty() {
        return nextPaymentInYaz;
    }

    public int getFinalAmount() {
        return finalAmount.get();
    }

    public IntegerProperty finalAmountProperty() {
        return finalAmount;
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public int getAmountToActive() {
        return amountToActive.get();
    }

    public IntegerProperty amountToActiveProperty() {
        return amountToActive;
    }

    public int getInvestorsAmount() {
        return investorsAmount.get();
    }

    public IntegerProperty investorsAmountProperty() {
        return investorsAmount;
    }

    public int getDeriskAmount() {
        return deriskAmount.get();
    }

    public IntegerProperty deriskAmountProperty() {
        return deriskAmount;
    }

    public int getMissingCycles() {
        return missingCycles.get();
    }

    public IntegerProperty missingCyclesProperty() {
        return missingCycles;
    }

    public static class LoanModelBuilder {
        public final IntegerProperty amountToActive;
        public final IntegerProperty investorsAmount;
        public final IntegerProperty deriskAmount;
        public final IntegerProperty missingCycles;
        private final StringProperty id;
        private final StringProperty status;
        private final IntegerProperty amount;
        private final IntegerProperty startYaz;
        private final IntegerProperty endYaz;
        private final IntegerProperty nextPaymentInYaz;
        private final IntegerProperty finalAmount;
        final IntegerProperty paymentAmount;
        final IntegerProperty amountLeftToPay;

        public LoanModelBuilder() {
            id = new SimpleStringProperty(null);
            amount = new SimpleIntegerProperty(0);
            startYaz = new SimpleIntegerProperty(0);
            endYaz = new SimpleIntegerProperty(0);
            nextPaymentInYaz = new SimpleIntegerProperty(0);
            finalAmount = new SimpleIntegerProperty(0);
            status = new SimpleStringProperty("New");
            amountToActive = new SimpleIntegerProperty(0);
            investorsAmount = new SimpleIntegerProperty(0);
            deriskAmount = new SimpleIntegerProperty(0);
            missingCycles = new SimpleIntegerProperty(0);
            paymentAmount = new SimpleIntegerProperty(0);
            amountLeftToPay = new SimpleIntegerProperty(0);
        }

        public LoanModelBuilder payment(int amount) {
            paymentAmount.set(amount);
            return this;

        }

        public LoanModelBuilder left(int amount) {
            amountLeftToPay.set(amount);
            return this;
        }

        public LoanModelBuilder missingCycles(int amount) {
            missingCycles.set(amount);
            return this;
        }

        public LoanModelBuilder id(String id) {
            this.id.set(id);
            return this;
        }

        public LoanModelBuilder amountToActive(int amount) {
            this.amountToActive.set(amount);
            return this;
        }

        public LoanModelBuilder deriskAmount(int amount) {
            this.deriskAmount.set(amount);
            return this;
        }

        public LoanModelBuilder investorsAmount(int amount) {
            this.investorsAmount.set(amount);
            return this;
        }

        public LoanModelBuilder amount(int amount) {
            this.amount.set(amount);
            return this;
        }

        public LoanModelBuilder status(String status) {
            this.status.set(status);
            return this;
        }

        public LoanModelBuilder startYaz(int startYaz) {
            this.startYaz.set(startYaz);
            return this;
        }

        public LoanModelBuilder endYaz(int endYaz) {
            this.endYaz.set(endYaz);
            return this;
        }

        public LoanModelBuilder nextPaymentInYaz(int nextPaymentInYaz) {
            this.nextPaymentInYaz.set(nextPaymentInYaz);
            return this;
        }

        public LoanModelBuilder finalAmount(int finalAmount) {
            this.finalAmount.set(finalAmount);
            return this;
        }

        public LoanModel build() {
            LoanModel loanModel = new LoanModel(this);
            return loanModel;
        }
    }


}
