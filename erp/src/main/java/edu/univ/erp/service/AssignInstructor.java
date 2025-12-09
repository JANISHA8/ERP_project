package edu.univ.erp.service;

import edu.univ.erp.access.Allowed;
import edu.univ.erp.data.ERPDB;
import edu.univ.erp.domain.ActionResult;
import edu.univ.erp.domain.Task;
import edu.univ.erp.domain.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AssignInstructor
{
    public ActionResult assign(int adminId, Task task, int sectionId, int instructorId)
    {
        if (!Allowed.can_operate_rs(Role.ADMIN, task)) { return ActionResult.NOT_ALLOWED; }
        String sql = "UPDATE sections SET instructor_id = ? WHERE section_id = ?";

        try (Connection c = ERPDB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql))
        {
            ps.setInt(1, instructorId);
            ps.setInt(2, sectionId);
            boolean ok = ps.executeUpdate() > 0;
            return ok ? ActionResult.SUCCESS : ActionResult.DB_ERROR;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ActionResult.DB_ERROR;
        }
    }
}
