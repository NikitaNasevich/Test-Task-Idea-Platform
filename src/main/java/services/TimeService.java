package services;

public class TimeService {

    private static final int MINUTES_PER_HOUR = 60;

    public static int getHoursFromMinutes(long minutes) {
        return (int) (minutes / MINUTES_PER_HOUR);
    }

    public static int getRemainingMinutes(long minutes) {
        return (int) (minutes - getHoursFromMinutes(minutes) * MINUTES_PER_HOUR);
    }
}
