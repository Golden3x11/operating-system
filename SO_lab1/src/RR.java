import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RR {
    private double waitingTime=0;
    private int served=0;
    private double timeToChange;
    private int whichProcess=0;
    private int summarizedTime=0;
    private double averageTimeToWait=0;
    private ArrayList<Process> arrayList=null;
    private boolean result=false;


    public RR(ArrayList<Process> toDo,double timeToChange) {
        this.timeToChange=timeToChange;
        if(toDo!=null) {
            arrayList=new ArrayList<>();
            for(Process process:toDo){
                arrayList.add(process);
            }
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
            arrayList.get(whichProcess).increaseTimeLastedRR(timeToChange);
            waitingTime+=timeToChange;
            if(arrayList.get(whichProcess).getDuration()<=arrayList.get(whichProcess).getTimeLastedRR()){
                served++;
                arrayList.remove(whichProcess);
                summarizedTime+=waitingTime;
                if(served==Simulation.getNumberOfProcesses()-1) {
                    averageTimeToWait=(double) summarizedTime/(served+1);
                    result=true;
                }
            }
            else
                whichProcess++;

            if(whichProcess!=0)
                whichProcess%=arrayList.size();
        }
    }

    public double getAverageTimeToWait(){
        return averageTimeToWait;
    }
    public double getWaitingTime(){
        return waitingTime;
    }
    public boolean haveResult() {
        return result;
    }

}
