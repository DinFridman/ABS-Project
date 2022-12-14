package console.menus;

import bank.logic.Bank;
import bank.logic.accounts.impl.exceptions.NoMoneyException;
import bank.logic.accounts.impl.exceptions.NonPositiveAmountException;
import bank.logic.impl.BankImpl;
import bank.logic.impl.exceptions.DataNotFoundException;
import bank.logic.impl.holder.BooleanHolder;
import console.menus.exceptions.NoOptionException;
import console.menus.exceptions.XmlNotLoadedException;
import files.xmls.exceptions.*;
import manager.customers.CustomerDTO;
import manager.time.YazSystemDTO;
import utils.PrintUtils;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {
    private final Bank bankInstance;
    private final BooleanHolder hasValidData;

    private static final String MENU_MESSAGE = "Bank Actions:\n" +
            "1.Read system data from XML.\n" +
            "2.Show loans details.\n" +
            "3.Show customers Details.\n" +
            "4.Deposit money to account.\n" +
            "5.Withdraw money from an account.\n" +
            "6.Loan integration.\n" +
            "7.Advance timeline.\n" +
            "8.Bonus Features.\n\n" +
            "9.Exit System.";

    public MainMenu() {
        bankInstance = new BankImpl();
        hasValidData = new BooleanHolder(false);
    }
    public static void main(String[] args) {
        MainMenu a = new MainMenu();

        a.printMenu();
    }

    public void readXml() {
        System.out.println("Enter an XML file's full path:");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        try {
            bankInstance.loadData(fileName);
            hasValidData.setaBoolean(true);
            System.out.println("Loaded XML Successfully.");
        } catch (NotXmlException | XmlNoLoanOwnerException | XmlNoCategoryException | XmlPaymentsException | XmlAccountExistsException | FileNotFoundException | XmlNotFoundException | DataNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printLoans() throws XmlNotLoadedException, DataNotFoundException {
        if(!hasValidData.value())
            throw new XmlNotLoadedException();

        PrintUtils.printAllLoans(bankInstance.getLoansData());
    }

    public void printCustomers() throws XmlNotLoadedException {
        if(!hasValidData.value())
            throw new XmlNotLoadedException();

        try {
            PrintUtils.printCustomersDetails(bankInstance.getCustomersDTO());
        } catch (DataNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void withdraw () throws XmlNotLoadedException, DataNotFoundException {

        if(!hasValidData.value())
            throw new XmlNotLoadedException();

        PrintUtils.printCustomersNames(bankInstance.getCustomersDTO());
        System.out.println("Enter a customer name:");
        Scanner scanner = new Scanner(System.in);
        String customerName = scanner.nextLine();


        System.out.println("Enter an amount to withdraw:");
        int amount = scanner.nextInt();
        try {
            bankInstance.withdraw(customerName, amount,"Basic Withdraw");
            System.out.println("Successfully withdrew " + amount + " from " + customerName);
        } catch (NoMoneyException | NonPositiveAmountException | DataNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deposit () throws XmlNotLoadedException, DataNotFoundException {

        if(!hasValidData.value())
            throw new XmlNotLoadedException();

        PrintUtils.printCustomersNames(bankInstance.getCustomersDTO());

        System.out.println("Enter a customer name:");
        Scanner scanner = new Scanner(System.in);
        String customerName = scanner.nextLine();


        System.out.println("Enter an amount to deposit:");
        int amount = scanner.nextInt();
        try {
            bankInstance.deposit(customerName, amount, "Basic Deposit");
            System.out.println("Successfully deposited " + amount + " to " + customerName);
        } catch (DataNotFoundException | NonPositiveAmountException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printMenu() {
        boolean exit = false;

        while(!exit) {
            try {
                System.out.println(MENU_MESSAGE);
                System.out.println("Choose an option:");

                Scanner scanner = new Scanner(System.in);
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        readXml();
                        break;
                    case 2:
                        printLoans();
                        break;
                    case 3:
                        printCustomers();
                        break;
                    case 4:
                        deposit();
                        break;
                    case 5:
                        withdraw();
                        break;
                    case 6:
                        setLoan();
                        break;
                    case 7:
                        advanceTime();
                        break;
                    case 8:
                        bonusMenu();
                        break;
                    case 9:
                        exit = true;
                        break;
                    default:
                        throw new NoOptionException();
                }
            } catch (XmlNotLoadedException | NoOptionException | DataNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Line entered is not matching what expected.");
            }
        }
    }

    private void bonusMenu() throws XmlNotLoadedException {

        BonusMenu menu = new BonusMenu(bankInstance, hasValidData);

        menu.printMenu();
    }

    private void setLoan() throws DataNotFoundException {

        PrintUtils.printCustomersNames(bankInstance.getCustomersDTO());
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a customer:");
        String customerName = scanner.nextLine();

        CustomerDTO customerDTO = bankInstance.getCustomerDTO(customerName);

        SetLoanMenu loanMenu = new SetLoanMenu(bankInstance, customerDTO);

        loanMenu.printMenu();

    }
    public void advanceTime() throws XmlNotLoadedException {

        if(!hasValidData.value())
            throw new XmlNotLoadedException();
        try {
            bankInstance.advanceOneYaz();
            YazSystemDTO yazSystemDTO = bankInstance.getYazSystemDTO();
            int currYaz = yazSystemDTO.getCurrentYaz();
            System.out.println("Advancing from Yaz " + yazSystemDTO.getPreviousYaz() + " to Yaz " + currYaz + ".");
        } catch (DataNotFoundException | NonPositiveAmountException e) {
            System.out.println(e.getMessage());
        }
    }
}
