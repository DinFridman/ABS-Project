package bank.logic.accounts.impl.exceptions;

public class NonPositiveAmountException extends Exception {
    private final String EXCEPTION_MESSAGE = "Non positive amount entered!";

    public NonPositiveAmountException() {}

    @Override
    public String getMessage(){return EXCEPTION_MESSAGE;}
}
