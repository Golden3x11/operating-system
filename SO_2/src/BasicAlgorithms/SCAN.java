package BasicAlgorithms;

import Process.ProcessDisc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SCAN {
    private static int wayOfArm=0;
    private static int time=0;
    private static int current_cylinder;
    private static LongOfWayComparatorSCAN comparator =new LongOfWayComparatorSCAN();
    private static ArrayList<ProcessDisc> list=new ArrayList<>();
    private static ArrayList<ProcessDisc> listWaiting=new ArrayList<>();
    private static int end_cylinder;



    public static String compute(ArrayList<ProcessDisc> listO, int current, int end){
        list=new ArrayList<>();
        end_cylinder=end;
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
        return "SCAN "+"droga "+ wayOfArm+" czas "+time;
    }
    public static void compute(ArrayList<ProcessDisc> listO, int current,int timeG,int wayOfArmG,int end){
        time=timeG;
        wayOfArm=wayOfArmG;
        compute(listO,current,end);
    }
    public static int getWayOfArm() {
        return wayOfArm;
    }

    public static void setWayOfArm(int wayOfArm) {
        SCAN.wayOfArm = wayOfArm;
    }

    public static int getTime() {
        return time;
    }

    public static void setTime(int time) {
        SCAN.time = time;
    }

    public static int getCurrent_cylinder() {
        return current_cylinder;
    }

    public static void setCurrent_cylinder(int current_cylinder) {
        SCAN.current_cylinder = current_cylinder;
    }
    public static class LongOfWayComparatorSCAN implements Comparator<ProcessDisc>{

        @Override
        public int compare(ProcessDisc o1, ProcessDisc o2) {
            int way1 = o1.getCylinder()-current_cylinder;
            int way2 = o2.getCylinder()-current_cylinder;
            if(way1<0)
                way1=end_cylinder*2-o1.getCylinder();
            if(way2<0)
                way2=end_cylinder*2-o2.getCylinder();

            return Integer.compare(way1, way2);
        }
    }
    public static LongOfWayComparatorSCAN getComparator(){
        return comparator;
    }
}