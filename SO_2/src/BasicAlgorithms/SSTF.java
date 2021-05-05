package BasicAlgorithms;

import Process.ProcessDisc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SSTF {
    private static int wayOfArm=0;
    private static int time=0;
    private static int current_cylinder;
    private static LongOfWayComparator comparator =new LongOfWayComparator();
    private static ArrayList<ProcessDisc> list=new ArrayList<>();
    private static ArrayList<ProcessDisc> listWaiting=new ArrayList<>();
    public static String compute(ArrayList<ProcessDisc> listO, int current){
        list=new ArrayList<>();
        current_cylinder=current;
        listWaiting=new ArrayList<>(listO);
        while(!listWaiting.isEmpty() || !list.isEmpty()){
            while(!listWaiting.isEmpty()){
                if(listWaiting.get(0).getEnter_moment()<=time) {
                    list.add(listWaiting.get(0));
                    listWaiting.remove(0);
                }
                else break;
            }
            Collections.sort(list,getComparator());
            if(!list.isEmpty()) {
                ProcessDisc currProcess = list.get(0);
                if (currProcess.getEnter_moment() <= time) {
                    time += currProcess.getDuration();
                    wayOfArm += Math.abs(current_cylinder - currProcess.getCylinder());
                    current_cylinder = currProcess.getCylinder();
                    list.remove(0);
                }
                else
                    time++;
            }
            else
            time++;
        }
        return "SSTF "+"droga "+ wayOfArm+" czas "+time;
    }
    public static void compute(ArrayList<ProcessDisc> listO, int current,int timeG,int wayOfArmG){
        time=timeG;
        wayOfArm=wayOfArmG;
        compute(listO,current);
    }

    public static int getWayOfArm() {
        return wayOfArm;
    }

    public static void setWayOfArm(int wayOfArm) {
        SSTF.wayOfArm = wayOfArm;
    }

    public static int getTime() {
        return time;
    }

    public static void setTime(int time) {
        SSTF.time = time;
    }

    public static int getCurrent_cylinder() {
        return current_cylinder;
    }

    public static void setCurrent_cylinder(int current_cylinder) {
        SSTF.current_cylinder = current_cylinder;
    }

    public static class LongOfWayComparator implements Comparator<ProcessDisc>{

        @Override
        public int compare(ProcessDisc o1, ProcessDisc o2) {
                int way1 = Math.abs(current_cylinder - o1.getCylinder());
                int way2 = Math.abs(current_cylinder - o2.getCylinder());
                if (way1 > way2) return 1;
                if (way1 < way2) return -1;
                return 0;
        }
    }
    public static LongOfWayComparator getComparator(){
        return comparator;
    }
}