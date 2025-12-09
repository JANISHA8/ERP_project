package edu.univ.erp.api.auth;

import edu.univ.erp.auth.hash.UserAuth;
import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.domain.User;

public class LoginAPI
{
    private final UserAuth auth = new UserAuth();

    public User login(String email, String password, String role)
    {
        User user = auth.getUserByEmail(email);

        if (user == null)
        {
            System.out.println("User not found");
            return null;
        }

        if (!user.getRole().name().equalsIgnoreCase(role))
        {
            System.out.println("Role mismatch");
            return null;
        }
        int userId = user.getUserID();

        if (auth.isUserBlocked(userId))
        {
            System.out.println("User blocked due to too many wrong attempts");
            return null;
        }

        boolean ok = auth.verifyPassword(userId, password);

        if (!ok)
        {
            auth.recordFailedAttempt(userId);
            System.out.println("Incorrect password");
            return null;
        }
        auth.resetAttempts(userId);

        SessionInfo.start
        (
                user.getUserID(),
                user.getUsername(),
                user.getEmailID(),
                user.getRole()
        );

        return user;
    }
}
