package abs.utils;

import http.constants.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUtils {

    public static String getUsername (HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Object sessionAttribute = session != null ? session.getAttribute(Constants.USERNAME) : null;
        return sessionAttribute != null ? sessionAttribute.toString() : null;
    }
    
    public static void clearSession (HttpServletRequest request) {
        request.getSession().invalidate();
    }

    public static boolean isAdmin(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        Object sessionAttribute = session != null ? session.getAttribute(Constants.IS_ADMIN) : null;
        return sessionAttribute != null ? (Boolean)sessionAttribute : false;
    }
}