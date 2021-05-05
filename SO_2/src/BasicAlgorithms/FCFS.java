package BasicAlgorithms;

import Process.ProcessDisc;

import java.util.ArrayList;

public class FCFS {
    private static int wayOfArm=0;
    private static int time=0;
    private static int current_cylinder;
    private static ArrayList<ProcessDisc> listTemp;
    public static String compute(ArrayList<ProcessDisc> listO, int current){
        current_cylinder=current;
        listTemp =new ArrayList<>(listO);
        while(!listTemp.isEmpty()){
            ProcessDisc currProcess= listTemp.get(0);
            if(currProcess.getEnter_moment()<=time){
                time+=currProcess.getDuration();
                wayOfArm+=Math.abs(current_cylinder-currProcess.getCylinder());
                current_cylinder=currProcess.getCylinder();
                listTemp.remove(0);
            }
            else
                time++;
        }
        return "FCFS "+"droga "+ wayOfArm+" czas "+time;
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
        FCFS.wayOfArm = wayOfArm;
    }

    public static int getTime() {
        return time;
    }

    public static void setTime(int time) {
        FCFS.time = time;
    }

    public static int getCurrent_cylinder() {
        return current_cylinder;
    }

    public static void setCurrent_cylinder(int current_cylinder) {
        FCFS.current_cylinder = current_cylinder;
    }
}
