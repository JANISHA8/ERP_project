package edu.univ.erp.service;

import edu.univ.erp.data.ManageUsersData;
import edu.univ.erp.domain.ActionResult;

import javax.swing.table.DefaultTableModel;

public class ManageUsersService
{
    public ActionResult updateUsers(DefaultTableModel model, String mode)
    {
        ManageUsersData data = new ManageUsersData();
        boolean ok = data.updateUsers(model, mode);
        return ok ? ActionResult.SUCCESS : ActionResult.DB_ERROR;
    }
}
