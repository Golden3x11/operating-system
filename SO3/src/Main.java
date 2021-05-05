import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

public class Main {
    private static int numberOfFrames[]={3,5,10};
    private static int numberOfPages=25;
    private static int numberOfReferences=2500;

    public static void main(String[] args) {
        Page[] pages=new Page[numberOfPages];
        for(int i=0;i<numberOfPages;i++)
            pages[i]=new Page(i,false,0);
        ArrayList<Page> references=new ArrayList();
        Random rnd=new Random();
        while (references.size()<numberOfReferences){
            references.add(pages[rnd.nextInt(numberOfPages)]);
        }
        for(int i=0;i< numberOfFrames.length;i++){
            System.out.println("Liczba ramek"+ numberOfFrames[i]);
            Algorithms algorithms=new Algorithms(numberOfFrames[i],references);
            System.out.println(algorithms.FIFO());
            System.out.println(algorithms.RAND());
            System.out.println(algorithms.OPT());
            System.out.println(algorithms.LRU());
            System.out.println(algorithms.ALRU());
            System.out.println();
        }
    }
}
