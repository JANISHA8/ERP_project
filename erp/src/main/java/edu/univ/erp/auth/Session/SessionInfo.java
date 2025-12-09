package edu.univ.erp.auth.session;

import edu.univ.erp.domain.Role;

import java.time.LocalDateTime;

public class SessionInfo
{
    private static int userID = -1;
    private static String username = null;
    private static String email = null;
    private static Role role = null;
    private static LocalDateTime loginTime = null;

    // START SESSION
    public static void start(int id, String name, String mail, Role r)
    {
        userID = id;
        username = name;
        email = mail;
        role = r;
        loginTime = LocalDateTime.now();

        new SessionData().updateLoginStatus(id, true, loginTime);
    }

    // END SESSION
    public static void end()
    {
        if(userID != -1)
            new SessionData().updateLoginStatus(userID, false, null);

        userID = -1;
        username = null;
        email = null;
        role = null;
        loginTime = null;
    }

    // SESSION CHECK
    public static boolean isLoggedIn()
    { return userID != -1 && role != null; }

    // GETTERS
    public static int getUserID() { return userID; }
    public static String getUsername() { return username; }
    public static String getEmail() { return email; }
    public static Role getRole() { return role; }
    public static LocalDateTime getLoginTime() { return loginTime; }

    // SETTERS
    public void setUserID(int userID) { SessionInfo.userID = userID; }
    public void setUsername(String username) { SessionInfo.username = username; }
    public void setEmail(String email) { SessionInfo.email = email; }
    public void setRole(Role role) { SessionInfo.role = role; }
    public void setLoginTime(LocalDateTime loginTime) { SessionInfo.loginTime = loginTime; }

    // PRINT SESSION
    public static String getInfo()
    {
        if(!isLoggedIn()) { return "No Active Session"; }

        return "Session Info:" +
                "\nUser ID : " + userID +
                "\nUsername : " + username +
                "\nEmail : " + email +
                "\nRole : " + role +
                "\nLogin Time : " + loginTime;
    }
}
