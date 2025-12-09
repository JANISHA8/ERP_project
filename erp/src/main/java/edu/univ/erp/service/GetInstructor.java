package edu.univ.erp.service;

import edu.univ.erp.domain.Instructor;
import edu.univ.erp.data.InstructorsData;;

public class GetInstructor
{
    public Instructor getInstructor(String email)
    {
        InstructorsData ad = new InstructorsData();
        return ad.getInstructorByUserEmail(email);
    }
}
