package edu.univ.erp.api.auth;

import edu.univ.erp.auth.hash.UserAuth;
import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.domain.User;

public class LoginAPI
{
    private final UserAuth auth = new UserAuth();

    public User login(String username, String password, String role)
    {
        boolean valid = auth.checkUser(username, password, role);
        if (!valid)
        { return null; }

        User user = auth.getUserByUsername(username);
        if (user != null)
        {
            SessionInfo.start(
                user.getUserID(),
                user.getUsername(),
                user.getRole());
        }
        return user;
    }
}
