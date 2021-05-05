package EDF;

import java.util.ArrayList;
import Process.ProcessDisc;

public class FCFS_edf {
    private static int wayOfArm=0;
    private static int time=0;
    private static int current_cylinder;
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
                ProcessDisc currProcess= list.get(0);
                time+=currProcess.getDuration();
                wayOfArm+=Math.abs(current_cylinder-currProcess.getCylinder());
                current_cylinder=currProcess.getCylinder();
                list.remove(0);
            }
            else
                time++;
         }
        return "FCFS EDF: "+"droga "+ wayOfArm+" czas "+time;
    }
}
