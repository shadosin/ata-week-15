package com.kenzie.immutabletime;

public final class ImmutableTime {

    public static final int MAX_MINUTES_IN_HOUR = 60;
    public static final int MAX_HOURS_IN_DAY = 24;
    public static final int MIN_MINUTES_IN_HOUR = 0;
    public static final int MIN_HOURS_IN_DAY = 0;

    private final int hour;
    private final int minute;

    public ImmutableTime(int hour, int minute) {
        if (hour > (MAX_HOURS_IN_DAY - 1) || hour < MIN_HOURS_IN_DAY ||
                minute < MIN_MINUTES_IN_HOUR || minute > (MAX_MINUTES_IN_HOUR - 1)) {
            throw new IllegalArgumentException();
        }
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    // Add more methods here

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(hour < 10 ? "0" : "").append(hour);
        buf.append(minute < 10 ? ":0" : ":").append(minute);
        return buf.toString();
    }
}