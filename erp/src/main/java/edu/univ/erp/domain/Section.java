package edu.univ.erp.domain;

import java.time.*;

class Day_Time
{
    private String day;
    private LocalTime startTime;
    private Duration duration;

    // constructor
    Day_Time(String day, LocalTime startTime, Duration duration)
    {
        this.day = day;
        this.startTime = startTime;
        this.duration = duration;
    }

    // getters
    public String getDay()
    { return day; }
    public LocalTime getStartTime()
    { return startTime; }
    public Duration getDuration()
    { return duration; }

    // setters
    public void setDay(String day)
    { this.day = day; }
    public void setStartTime(LocalTime startTime)
    { this.startTime = startTime; }
    public void setDuration(Duration duration)
    { this.duration = duration; }
}

public class Section
{
    private String course_id; // course code
    private String instructor_id; // instructor's user_id
    private Day_Time day_time; // <day, start_time, duration>
    private String room;
    private int capacity;
    private int semester; // monsoon, winter, summer
    private int year; // 2025, 2026

    // constructor
    Section(String course_id, String instructor_id, Day_Time day_time, String room, int capacity, int semester, int year)
    {
        this.course_id = course_id;
        this.instructor_id = instructor_id;
        this.day_time = day_time;
        this.room = room;
        this.capacity = capacity;
        this.semester = semester;
        this.year = year;
    }

    // getters
    public String getCourse_id() { return course_id; }
    public String getInstructor_id() { return instructor_id; }
    public Day_Time getDay_time() { return day_time; }
    public String getRoom() { return room; }
    public int getCapacity() { return capacity; }
    public int getSemester() { return semester; }
    public int getYear() { return year; }

    // setters
    public void setCourse_id(String course_id) { this.course_id = course_id; }
    public void setInstructor_id(String instructor_id) { this.instructor_id = instructor_id; }
    public void setDay_time(Day_Time day_time) { this.day_time = day_time; }
    public void setRoom(String room) { this.room = room; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public void setSemester(int semester) { this.semester = semester; }
    public void setYear(int year) { this.year = year;}
}
