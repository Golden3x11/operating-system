package EDF;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import BasicAlgorithms.SCAN;
import Process.ProcessDisc;

public class SCAN_edf {
    private static int wayOfArm=0;
    private static int time=0;
    private static int current_cylinder;
    private static int end_cylinder;
    private static LongOfWayComparatorSCAN comparator =new LongOfWayComparatorSCAN();
    private static ArrayList<ProcessDisc> listRealTime;
    private static ArrayList<ProcessDisc> list;
    private static ArrayList<ProcessDisc> listTemp;
    public static String compute(ArrayList<ProcessDisc> listO, int current,int end) {
        listTemp = new ArrayList<>(listO);
        list = new ArrayList<>();
        listRealTime = new ArrayList<>();
        end_cylinder=end;
        current_cylinder=current;

        while (!listTemp.isEmpty() || !list.isEmpty() || !listRealTime.isEmpty()) {
            while (!listTemp.isEmpty()){
                if(listTemp.get(0).getEnter_moment()<=time){
                    if(listTemp.get(0).isRealTime()){
                        listRealTime.add(listTemp.get(0));
                        listTemp.remove(0);
                    }
                    else{
                        list.add(listTemp.get(0));
                        listTemp.remove(0);
                    }
                }
                else
                    break;
            }
            if(!listRealTime.isEmpty()){
                ProcessDisc currProcess= listRealTime.get(0);
                if(time<=currProcess.getDeadline())
                    time += currProcess.getDuration();
                wayOfArm+=Math.abs(current_cylinder-currProcess.getCylinder());
                current_cylinder=currProcess.getCylinder();
                listRealTime.remove(0);
            }
            else if(!list.isEmpty()) {
                Collections.sort(list,getComparator());
                ProcessDisc currProcess = list.get(0);
                time += currProcess.getDuration();
                wayOfArm += Math.abs(current_cylinder - currProcess.getCylinder());
                current_cylinder = currProcess.getCylinder();
                list.remove(0);
            }
            else
                time++;

        }
        return "SCAN EDF: "+"droga "+ wayOfArm+" czas "+time;
    }
    public static class LongOfWayComparatorSCAN implements Comparator<ProcessDisc> {

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
