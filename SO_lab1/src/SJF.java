import Queue.EmptyQueueException;
import Queue.UnlimitedQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SJF {
    protected int waitingTime=0;
    protected int served=0;
    protected int summarizedTime=0;
    protected double averageTimeToWait=0;
    protected ArrayList<Process> arrayList=null;
    protected static ProcessCompareDuration Comparator1= new ProcessCompareDuration();


    public SJF(ArrayList<Process> toDo) {
        if(toDo!=null) {
            arrayList=new ArrayList<>();
            for(Process process:toDo){
                arrayList.add(process);
            }
            Collections.sort(arrayList,Comparator1);
        }
    }

    public void addProcesses(ArrayList<Process> toDo){
        if(toDo!=null) {
            if(arrayList==null)
                arrayList=new ArrayList<>();

            for(Process process:toDo){
                arrayList.add(process);
            }
        }
    }
    public void processing(){
        if(arrayList.size()>0){
            arrayList.get(0).increaseTimeLastedSJF(1);
            waitingTime++;
            if(arrayList.get(0).getDuration()==arrayList.get(0).getTimeLastedSJF()){
                served++;
                arrayList.remove(0);
                summarizedTime+=waitingTime;
                Collections.sort(arrayList,Comparator1);
                if(served==Simulation.getNumberOfProcesses()-1){
                    averageTimeToWait=(double) summarizedTime/(served+1);
                }
            }
        }
    }

    public double getAverageTimeToWait(){
        return averageTimeToWait;
    }
    public int getWaitingTime(){
        return waitingTime;
    }
    public boolean haveResult() {
        return averageTimeToWait!=0;
    }
    static class ProcessCompareDuration implements Comparator<Process> {
        public int compare(Process p1, Process p2){
            if (p1.getDuration()>p2.getDuration()) return 1;
            if (p1.getDuration()<p2.getDuration()) return -1;
            return 0;
        }
    }
    private static ProcessCompareDuration getComaprator1(){
        return Comparator1;
    }
}
