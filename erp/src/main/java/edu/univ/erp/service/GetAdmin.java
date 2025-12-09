package edu.univ.erp.service;

import edu.univ.erp.domain.Admin;
import edu.univ.erp.data.AdminsData;;

public class GetAdmin
{
    public Admin getAdmin(String email)
    {
        AdminsData ad = new AdminsData();
        return ad.getAdminByUserEmail(email);
    }
}
