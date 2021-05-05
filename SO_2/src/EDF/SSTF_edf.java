package EDF;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import BasicAlgorithms.SSTF;
import Process.ProcessDisc;

public class SSTF_edf {
    private static int wayOfArm=0;
    private static int time=0;
    private static int current_cylinder;
    private static LongOfWayComparator comparator =new LongOfWayComparator();
    private static ArrayList<ProcessDisc> listRealTime;
    private static ArrayList<ProcessDisc> list;
    private static ArrayList<ProcessDisc> listTemp;
    public static String compute(ArrayList<ProcessDisc> listO, int current) {
        listTemp = new ArrayList<>(listO);
        list = new ArrayList<>();
        listRealTime = new ArrayList<>();
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
        return "SSTF EDF: "+"droga "+ wayOfArm+" czas "+time;
    }
    public static class LongOfWayComparator implements Comparator<ProcessDisc> {

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
