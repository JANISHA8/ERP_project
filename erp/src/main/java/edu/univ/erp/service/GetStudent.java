package edu.univ.erp.service;

import edu.univ.erp.domain.Student;
import edu.univ.erp.data.StudentsData;;

public class GetStudent
{
    public Student getStudent(String email)
    {
        StudentsData ad = new StudentsData();
        return ad.getStudentByUserEmail(email);
    }
}
