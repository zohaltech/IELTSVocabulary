package com.zohaltech.app.ieltsvocabulary.serializables;

import java.io.Serializable;

public class ReminderSettings implements Serializable
{

    public enum Status
    {
        STOP, RUNNING, PAUSE, FINISHED
    }

    private String startTime;
    private int intervals;
    private Status status;
    private Reminder reminder;
    private boolean[] weekdays;

    public ReminderSettings(String startTime, int intervals, Reminder reminder, Status status, boolean[] weekdays)
    {
        setStartTime(startTime);
        setIntervals(intervals);
        setReminder(reminder);
        setStatus(status);
        setWeekdays(weekdays);
    }

    public String getStartTime()
    {
        return startTime;
    }

    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public int getIntervals()
    {
        return intervals;
    }

    public void setIntervals(int intervals)
    {
        this.intervals = intervals;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public Reminder getReminder()
    {
        return reminder;
    }

    public void setReminder(Reminder reminder)
    {
        this.reminder = reminder;
    }

    public boolean[] getWeekdays()
    {
        return weekdays;
    }

    public void setWeekdays(boolean[] weekdays)
    {
        this.weekdays = weekdays;
    }

}
