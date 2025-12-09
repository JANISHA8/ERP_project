package edu.univ.erp.service;

import edu.univ.erp.access.Allowed;
import edu.univ.erp.domain.Task;
import edu.univ.erp.domain.Role;
import edu.univ.erp.data.SettingsData;

public class ToggleMaintenance
{
    public boolean toggle(int adminId, Task task, boolean status)
    {
        if(!Allowed.can_operate_rs(Role.ADMIN, task)) { return false; }
        SettingsData data = new SettingsData();
        return data.updateSetting("Maintenance_Mode", String.valueOf(status));
    }
}
