import Queue.EmptyQueueException;
import Queue.UnlimitedQueue;

import java.util.ArrayList;

public class FCFS {
    private int waitingTime=0;
    private int served=0;
    private int summarizedTime=0;
    private double averageTimeToWait=0;
    private UnlimitedQueue<Process> queue=new UnlimitedQueue(2);

    public FCFS(ArrayList<Process> toDo) {
        for(Process process:toDo){
            queue.enqueue(process);
        }
    }

    public void addProcesses(ArrayList<Process> toDo){
        if(toDo!=null) {
            for (Process process : toDo) {
                queue.enqueue(process);
            }
        }
    }
    public void processing() throws EmptyQueueException {
        if(!queue.isEmpty()) {
            queue.first().increaseTimeLastedFCFS(1);
            waitingTime++;
            if(queue.first().getDuration()==queue.first().getTimeLastedFCFS()){
                served++;
                queue.dequeue();
                summarizedTime+=waitingTime;
                if(served==Simulation.getNumberOfProcesses()-1) {
                    averageTimeToWait= (double) summarizedTime/(served+1);
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
}
