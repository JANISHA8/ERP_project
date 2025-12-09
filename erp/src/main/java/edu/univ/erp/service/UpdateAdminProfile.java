package edu.univ.erp.service;

import edu.univ.erp.data.AdminsData;
import edu.univ.erp.domain.ActionResult;
import edu.univ.erp.domain.Admin;

public class UpdateAdminProfile
{
    public ActionResult update(Admin admin)
    {
        AdminsData data = new AdminsData();
        boolean result = data.updateAdmin(admin);
        System.out.println("ADMIN PROFILE UPDATE RESULT = " + result);
        return result ? ActionResult.SUCCESS : ActionResult.DB_ERROR;
    }
}
