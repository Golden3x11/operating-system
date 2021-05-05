import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SJFwyw extends SJF{
    private static ProcessCompareTimeLasted Comparator2= new ProcessCompareTimeLasted();

    public SJFwyw(ArrayList<Process> toDo) {
        super(toDo);
        Collections.sort(arrayList,Comparator2);
    }

    @Override
    public void addProcesses(ArrayList<Process> toDo) {
        super.addProcesses(toDo);
    }

    @Override
    public void processing() {
        if(arrayList.size()>0){
            arrayList.get(0).increaseTimeLastedSJFwyw(1);
            arrayList.get(0).decreaseTimeLeftSJFwyw(1);
            waitingTime++;
            if(arrayList.get(0).getDuration()==arrayList.get(0).getTimeLastedSJFwyw()){
                served++;
                arrayList.remove(0);
                summarizedTime+=waitingTime;
                if(served==Simulation.getNumberOfProcesses()-1){
                    averageTimeToWait=(double) summarizedTime/(served+1);
                }
            }
            Collections.sort(arrayList,Comparator2);
        }
    }
    static class ProcessCompareTimeLasted implements Comparator<Process> {
        public int compare(Process p1, Process p2){
            if (p1.getTimeLeftSJFwyw()>p2.getTimeLeftSJFwyw()) return 1;
            if (p1.getTimeLeftSJFwyw()<p2.getTimeLeftSJFwyw()) return -1;
            return 0;
        }
    }
    private static ProcessCompareTimeLasted getComaprator2(){
        return Comparator2;
    }
}
