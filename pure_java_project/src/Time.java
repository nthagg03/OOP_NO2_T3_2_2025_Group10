public class Time {
    private int hour, minute, second;

    public Time() { this(0, 0, 0); }
    public Time(int hour, int minute, int second) {
        this.hour = hour; this.minute = minute; this.second = second;
    }

    public int getHour() { return hour; }
    public void setHour(int hour) { this.hour = hour; }
    public int getMinute() { return minute; }
    public void setMinute(int minute) { this.minute = minute; }
    public int getSecond() { return second; }
    public void setSecond(int second) { this.second = second; }

    public int toSeconds() { return hour * 3600 + minute * 60 + second; }
    
    public void addSeconds(int sec) {
        int total = toSeconds() + sec;
        hour = total / 3600;
        minute = (total % 3600) / 60;
        second = total % 60;
    }

    @Override
    public String toString() { return String.format("%02d:%02d:%02d", hour, minute, second); }
}