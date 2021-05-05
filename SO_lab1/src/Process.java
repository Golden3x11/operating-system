import java.util.Random;

public class Process {
    private int duration;
    private int timeLastedFCFS;
    private int timeLastedSJF;
    private int timeLastedSJFwyw;
    private double timeLastedRR;

    private int timeLeftSJFwyw;


    public Process(int duration){
        this.duration=duration;
    }
    public Process(){
        Random rnd= new Random();
        duration= rnd.nextInt(100)+1;
        timeLeftSJFwyw=duration;
    }
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getTimeLastedFCFS() {
        return timeLastedFCFS;
    }

    public void setTimeLastedFCFS(int timeLastedFCFS) {
        this.timeLastedFCFS = timeLastedFCFS;
    }
    public void increaseTimeLastedFCFS(int time) {
        this.timeLastedFCFS= timeLastedFCFS+time;
    }

    public int getTimeLastedSJF() {
        return timeLastedSJF;
    }

    public void setTimeLastedSJF(int timeLastedSJF) {
        this.timeLastedSJF = timeLastedSJF;
    }
    public void increaseTimeLastedSJF(int time) {
        this.timeLastedSJF= timeLastedSJF+time;
    }

    public int getTimeLastedSJFwyw() {
        return timeLastedSJFwyw;
    }

    public void setTimeLastedSJFwyw(int timeLastedSJFwyw) {
        this.timeLastedSJFwyw = timeLastedSJFwyw;
    }
    public void increaseTimeLastedSJFwyw(int time) {
        this.timeLastedSJFwyw= timeLastedSJFwyw+time;
    }

    public int getTimeLeftSJFwyw() {
        return timeLeftSJFwyw;
    }

    public void setTimeLeftSJFwyw(int timeLeftSJFwyw) {
        this.timeLeftSJFwyw = timeLeftSJFwyw;
    }
    public void decreaseTimeLeftSJFwyw(int time) {
        this.timeLeftSJFwyw= timeLeftSJFwyw-time;
    }

    public double getTimeLastedRR() {
        return timeLastedRR;
    }

    public void setTimeLastedRR(double timeLastedRR) {
        this.timeLastedRR = timeLastedRR;
    }
    public void increaseTimeLastedRR(double time) {
        this.timeLastedRR = timeLastedRR + time;
    }
}
