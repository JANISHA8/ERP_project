package edu.univ.erp.data;

import edu.univ.erp.domain.TimeTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TimetableData
{
    public List<TimeTable> getTimetableForStudent(int studentId)
    {
        List<TimeTable> list = new ArrayList<>();
        String sql = "SELECT t.course_code, t.day_of_week, t.start_time, t.end_time, t.room_no " +
                    "FROM erp_db.timetable t " +
                    "JOIN erp_db.student_courses sc ON t.course_code = sc.course_code " +
                    "WHERE sc.student_id = ? " +
                    "ORDER BY FIELD(t.day_of_week,'Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday'), t.start_time";
        try (Connection c = ERPDB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql))
        {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                list.add(new TimeTable(rs.getString("course_code"),
                                        rs.getString("day_of_week"),
                                        rs.getTime("start_time").toString(),
                                        rs.getTime("end_time").toString(),
                                        rs.getString("room_no")));
            }
        }
        catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}
