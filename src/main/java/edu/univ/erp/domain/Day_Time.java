import java.util.HashMap;
import java.time.*;

public class Day_Time
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
    {
        return day;
    }
    public LocalTime getStartTime()
    {
        return startTime;
    }
    public Duration getDuration()
    {
        return duration;
    }

    // setters
    public void setDay(String day)
    {
        this.day = day;
    }
     public void setStartTime(LocalTime startTime)
    {
        this.startTime = startTime;
    }
    public void setDuration(Duration duration)
    {
        this.duration = duration;
    }
}

public class Section
{
    private String course_id; // course code
    private String instructor_id; // instructor's user_id
    private Day_Time day_time; // <day, start_time, duration>
    private String room;
    private int capacity;
    private int semester;
    private 
}
