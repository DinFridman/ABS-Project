package abs.servlets;

import http.constants.Constants;
import abs.utils.ServletUtils;
import abs.utils.SessionUtils;
import bank.logic.impl.exceptions.DataNotFoundException;
import bank.logic.manager.BankManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import manager.customers.CustomersData;
import manager.customers.CustomersWithVersion;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Accounts Servlet", urlPatterns = "/bank/accounts")
public class AccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String usernameFromSession = SessionUtils.getUsername(req);
        BankManager bankManager = ServletUtils.getBankManager(getServletContext());

        ServletOutputStream outputStream = resp.getOutputStream();
        if(usernameFromSession == null) {
            outputStream.print("You must login first.");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        else if(!SessionUtils.isAdmin(req)) {
            outputStream.print("Only admins are authorized for this request.");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        else {
            try {
                CustomersData customerDataList = bankManager.getCustomersData();
                int cVer = bankManager.getCustomersVersion();
                int lVer = bankManager.getLoansVersion();

                String jsonResponse = Constants.GSON_INSTANCE.toJson(new CustomersWithVersion(customerDataList, cVer, lVer));
                outputStream.print(jsonResponse);
                outputStream.flush();
                resp.setStatus(HttpServletResponse.SC_OK);

            } catch (DataNotFoundException e) {
                outputStream.print(e.getMessage());
                resp.setStatus(HttpServletResponse.SC_CONFLICT);
            }
        }
    }
}
