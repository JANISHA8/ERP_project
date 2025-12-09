package edu.univ.erp.domain;

public class TimeTable
{
    private String courseCode;
    private String day;
    private String startTime;
    private String endTime;
    private String room;

    public TimeTable(String courseCode, String day, String startTime, String endTime, String room)
    {
        this.courseCode = courseCode;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
    }

    public String getCourseCode() { return courseCode; }
    public String getDay() { return day; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
    public String getRoom() { return room; }
}
