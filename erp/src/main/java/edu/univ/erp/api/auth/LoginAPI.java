package edu.univ.erp.api.auth;

import edu.univ.erp.auth.hash.UserAuth;
import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.domain.User;

public class LoginAPI
{
    private final UserAuth auth = new UserAuth();

    public User login(String email, String password, String role)
    {
        // String hashedPassword = hashPassword(password);
        boolean valid = auth.checkUser(email, password, role);
        if (!valid)
        { return null; }

        User user = auth.getUserByEmail(email);
        if (user != null)
        {
            SessionInfo.start(
                user.getUserID(),
                user.getUsername(),
                user.getEmailID(),
                user.getRole());
        }
        return user;
    }
}
