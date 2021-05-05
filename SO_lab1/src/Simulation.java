import Queue.EmptyQueueException;
import Queue.UnlimitedQueue;

import java.util.ArrayList;
import java.util.Random;

public class Simulation {
    private static final int numberOfProcesses=1000;
    public static void createProcess(UnlimitedQueue<Process> queue,int numberOf){
        for(int i=0;i<numberOf;i++){
            queue.enqueue(new Process());
        }
    }
    public static void simulate(UnlimitedQueue<Process> queue) throws EmptyQueueException {
        ArrayList<Process> toDo=new ArrayList<>();
        Random rnd=new Random();
        int x=rnd.nextInt(2)+1;
        for(int i=0;i<x;i++){
            toDo.add(queue.dequeue());
        }

        FCFS fcfs=new FCFS(toDo);
        SJF sjf=new SJF(toDo);
        SJFwyw sjFwyw=new SJFwyw(toDo);
        RR rr=new RR(toDo,0.2);
        int a=1;
        while(!haveResults(fcfs,sjf,sjFwyw,rr)){
            if(!fcfs.haveResult())
                fcfs.processing();
            if(!sjf.haveResult())
                sjf.processing();
            if(!sjFwyw.haveResult())
                sjFwyw.processing();
            if(!rr.haveResult())
                rr.processing();

            if(!queue.isEmpty()) {
                x=rnd.nextInt(queue.size()+1);
                toDo.clear();
                for (int i = 0; i < x; i++) {
                    toDo.add(queue.dequeue());
                }

                if(x!=0) {
                    fcfs.addProcesses(toDo);
                    sjf.addProcesses(toDo);
                    sjFwyw.addProcesses(toDo);
                    rr.addProcesses(toDo);
                }
            }
        }
        System.out.println("SJF(wywłaszczony): "+sjFwyw.getAverageTimeToWait());
        System.out.println("SJF: "+sjf.getAverageTimeToWait());
        System.out.println("FCFS: "+fcfs.getAverageTimeToWait());
        System.out.println("Rotacyjny: "+rr.getAverageTimeToWait());

    }
    public static boolean haveResults(FCFS fcfs,SJF sjf,SJFwyw sjFwyw,RR rr){
        return fcfs.haveResult() && sjf.haveResult() && sjFwyw.haveResult() && rr.haveResult();
    }
    public static int getNumberOfProcesses(){
        return numberOfProcesses;
    }
    public static void main(String[] args) {
        UnlimitedQueue queue=new UnlimitedQueue(100);
        createProcess(queue,numberOfProcesses);
        try {
            simulate(queue);
        }catch (EmptyQueueException e){
            e.printStackTrace();
        }
    }
}
