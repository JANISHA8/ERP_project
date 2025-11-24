package edu.univ.erp.auth.session;

import edu.univ.erp.domain.Role;

public class SessionInfo
{
    private static int userID;
    private static String username;
    private static Role role;

    public static void start(int id, String name, Role r)
    {
        userID = id;
        username = name;
        role = r;
    }

    public static int getUserID() { return userID; }
    public static String getUsername() { return username; }
    public static Role getRole() { return role; }

    public static void end()
    {
        userID = -1;
        username = null;
        role = null;
    }
    // get user role from DB and return it in the following function
    // can add more info about user (apart from just the role)
}
