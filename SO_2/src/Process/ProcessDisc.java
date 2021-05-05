package Process;

public class ProcessDisc {
    private int enter_moment;
    private int duration;
    private int cylinder;
    private int deadline;
    private boolean isRealTime=false;

    public ProcessDisc(int enter_moment, int duration, int cylinder, boolean isRealTime, int deadline) {
        this.enter_moment = enter_moment;
        this.duration = duration;
        this.cylinder = cylinder;
        this.deadline = deadline;
        this.isRealTime = isRealTime;
    }

    public ProcessDisc(int enter_moment, int duration, int cylinder) {
        this.enter_moment = enter_moment;
        this.duration = duration;
        this.cylinder = cylinder;
    }
    public boolean isRealTime() {
        return isRealTime;
    }

    public void setRealTime(boolean realTime) {
        isRealTime = realTime;
    }
    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public int getEnter_moment() {
        return enter_moment;
    }

    public void setEnter_moment(int enter_moment) {
        this.enter_moment = enter_moment;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCylinder() {
        return cylinder;
    }

    public void setCylinder(int cylinder) {
        this.cylinder = cylinder;
    }
}
