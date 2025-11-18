package edu.univ.erp.auth.session;

import edu.univ.erp.domain.Role;

public class SessionInfo
{
    // get user role from DB and return it in the following function
    // can add more info about user (apart from just the role)
    public Role getRole()
    {
        return Role.ADMIN; // hardcoded for now
    }
}
