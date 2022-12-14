package abs.servlets;

import http.constants.Constants;
import abs.utils.ServletUtils;
import abs.utils.SessionUtils;
import bank.logic.manager.BankManager;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import manager.transactions.TransactionsData;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

@WebServlet(name = "Transaction Servlet", urlPatterns = "/bank/transactions")
public class TransactionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        String usernameFromSession = SessionUtils.getUsername(request);
        ServletOutputStream outputStream = response.getOutputStream();
        BankManager bankManager = ServletUtils.getBankManager(getServletContext());

        if (usernameFromSession == null) { //user is not logged in yet
            outputStream.print("Not logged in yet.");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        }  else if (SessionUtils.isAdmin(request)) {
            outputStream.print("Only customers are authorized for this request.");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
                TransactionsData transactions = bankManager.getTransactionsData(usernameFromSession);
                String jsonResponse = Constants.GSON_INSTANCE.toJson(transactions);

                outputStream.print(jsonResponse);
                outputStream.flush();
                response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    private void logServerMessage(String message) {
        System.out.println(message);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain;charset=UTF-8");
        String usernameFromSession = SessionUtils.getUsername(request);
        ServletOutputStream outputStream = response.getOutputStream();
        BankManager bankManager = ServletUtils.getBankManager(getServletContext());

        if (usernameFromSession == null) { //user is not logged in yet
            outputStream.print("Not logged in yet.");
        } else {
            //user is already logged in
            Properties prop = new Properties();
            prop.load(request.getInputStream());
            String test = prop.toString();
            String property = prop.getProperty(Constants.AMOUNT);
            int amount = Integer.parseInt(property);
            String type = prop.getProperty(Constants.TYPE);

            if (amount <= 0 || type == null) {
                outputStream.print("Invalid Parameters!");
                response.setStatus(HttpServletResponse.SC_CONFLICT);
            } else {
                try {
                    switch (type) {
                        case (Constants.TRANSACTION_WITHDRAW): {
                            bankManager.withdraw(usernameFromSession, amount, Constants.TRANSACTION_WITHDRAW);
                            break;
                        }
                        case (Constants.TRANSACTION_DEPOSIT): {
                            bankManager.deposit(usernameFromSession, amount, Constants.TRANSACTION_DEPOSIT);
                            break;
                        }
                    }
                    response.setStatus(HttpServletResponse.SC_OK);
                } catch (Exception e) {
                    outputStream.print(e.getMessage());
                    response.setStatus(HttpServletResponse.SC_CONFLICT);
                }
            }
        }
    }


}
