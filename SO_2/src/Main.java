import BasicAlgorithms.CSCAN;
import BasicAlgorithms.FCFS;
import BasicAlgorithms.SCAN;
import BasicAlgorithms.SSTF;

import EDF.*;
import FD_SCAN.*;
import Process.ProcessDisc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Main {
    private static int first_cylinder=0;
    private static int last_cylinder=250;
    private static int current_cylinder=65;
    private static int[] numbers={34,242,87,2,69,213,76,24,82,123,174,23,45,156};
    private static int[] numbersE={0,0,1,2,3,4,5,6,7,8,9,9,10,0};
    public static void main(String[] args) {
        ArrayList<ProcessDisc> list=new ArrayList<>();
        Random rnd=new Random();
        for(int i=0;i<100;i++)
            list.add(new ProcessDisc(rnd.nextInt(30), 1,rnd.nextInt(250)));
        Collections.sort(list,new EnteringTimeComparator());
        System.out.println(FCFS.compute(list,current_cylinder));
        System.out.println(SSTF.compute(list,current_cylinder));
        System.out.println(SCAN.compute(list,current_cylinder,last_cylinder));
        System.out.println(CSCAN.compute(list,current_cylinder,last_cylinder));

        for(int i=0;i<50;i++)
            list.add(new ProcessDisc(rnd.nextInt(30),1,rnd.nextInt(250),true,20));
        Collections.sort(list,new EnteringTimeComparator());

        System.out.println();
        System.out.println("EDF-all");
        System.out.println(FCFS_edf.compute(list,current_cylinder));
        System.out.println(SSTF_edf.compute(list,current_cylinder));
        System.out.println(SCAN_edf.compute(list,current_cylinder,last_cylinder));
        System.out.println(CSCAN_edf.compute(list,current_cylinder,last_cylinder));

        System.out.println();
        System.out.println("FD-SCAN-all");
        System.out.println(FCFS_fd_scan.compute(list,current_cylinder,last_cylinder));
        System.out.println(SSTF_fd_scan.compute(list,current_cylinder,last_cylinder));
        System.out.println(SCAN_fd_scan.compute(list,current_cylinder,last_cylinder));
        System.out.println(CSCAN_fd_scan.compute(list,current_cylinder,last_cylinder));

    }
    public static class EnteringTimeComparator implements Comparator<ProcessDisc>{
        @Override
        public int compare(ProcessDisc o1, ProcessDisc o2) {
            if (o1.getEnter_moment() > o2.getEnter_moment()) return 1;
            if (o1.getEnter_moment() < o2.getEnter_moment()) return -1;
            return 0;
        }
    }
}
