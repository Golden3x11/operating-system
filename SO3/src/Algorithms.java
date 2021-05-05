import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Algorithms {
    public int frameSize;
    ArrayList<Page> pageReferences;
    ArrayList<Page> pages;
    public int pagesFailed = 0;

    public Algorithms(int frameSize, ArrayList<Page> pageReferences) {
        this.frameSize = frameSize;
        this.pageReferences=pageReferences;
    }

    public String FIFO(){
        pagesFailed=0;
        ArrayList<Integer> frames=new ArrayList<>();
        pages= new ArrayList<>(pageReferences);
        while(!pages.isEmpty()) {
            Integer firstPage = pages.get(0).getNumber();
            pages.remove(0);
            if (!frames.contains(firstPage)) {
                if (frames.size() == frameSize)
                    frames.remove(0);
                pagesFailed++;
                frames.add(firstPage);
            }
        }
        return "FIFO " + pagesFailed;
    }
    public String RAND(){
        pagesFailed=0;
        ArrayList<Integer> frames=new ArrayList<>();
        pages= new ArrayList<>(pageReferences);
        while(!pages.isEmpty()) {
            Integer firstPage = pages.get(0).getNumber();
            pages.remove(0);
            if (!frames.contains(firstPage)) {
                if (frames.size() == frameSize) {
                    Random rnd=new Random();
                    frames.set(rnd.nextInt(frameSize),firstPage);
                }
                else{
                    frames.add(firstPage);
                }
                pagesFailed++;
            }
        }
        return "RAND " +pagesFailed;
    }
    public String OPT(){
        pagesFailed=0;
        ArrayList<Integer> frames=new ArrayList<>();
        pages= new ArrayList<>(pageReferences);
        while(!pages.isEmpty()) {
            Integer firstPage = pages.get(0).getNumber();
            pages.remove(0);
            if (!frames.contains(firstPage)) {
                if (frames.size() == frameSize) {
                    int latestIdx=latest(frames,pages);
                    frames.set(latestIdx,firstPage);
                }
                else{
                    frames.add(firstPage);
                }
                pagesFailed++;
            }
        }
        return "OPT "+ pagesFailed;
    }
    private int latest(ArrayList<Integer> frames,ArrayList<Page> pages){
        int latest=0;
        ArrayList<Integer> framesCopy=new ArrayList<>(frames);
        ArrayList<Page> pagesCopy=new ArrayList<>(pages);
        while (!pagesCopy.isEmpty()){
            Integer firstPage = pagesCopy.get(0).getNumber();
            pagesCopy.remove(0);
            if(framesCopy.contains(firstPage)){
                latest=firstPage;
                framesCopy.remove((Integer) latest);
            }
        }
        if(framesCopy.isEmpty())
            return frames.indexOf(latest);
        else
            return frames.indexOf(framesCopy.get(0));
    }
    public String LRU(){
        pagesFailed=0;
        ArrayList<Page> frames=new ArrayList<>();
        pages= new ArrayList<>(pageReferences);
        int time=1;
        while(!pages.isEmpty()) {
            Page firstPage = pages.get(0);
            pages.remove(0);
            firstPage.setRef(time++);
            if (!frames.contains(firstPage)) {

                if (frames.size() == frameSize) {
                    Collections.sort(frames,Page.refComparator);
                    frames.set(0,firstPage);
                }
                else{
                    frames.add(firstPage);
                }
                pagesFailed++;
            }
        }
        return "LRU "+pagesFailed;
    }
    public String ALRU(){
        pagesFailed=0;
        ArrayList<Page> frames=new ArrayList<>();
        pages= new ArrayList<>(pageReferences);
        while(!pages.isEmpty()) {
            Page firstPage = pages.get(0);
            pages.remove(0);
            firstPage.setPriorityBit(true);
            if (!frames.contains(firstPage)) {
                if (frames.size() == frameSize) {
                    boolean isChanged=false;
                    for(Page frame: frames){
                        if(frame.isPriorityBit())
                            frame.setPriorityBit(false);
                        else
                            if(!isChanged) {
                                isChanged=true;
                                frames.set(frames.indexOf(frame), firstPage);
                            }
                    }

                }
                else{
                    frames.add(firstPage);
                }
                pagesFailed++;
            }
        }
        return "ALRU "+ pagesFailed;
    }
}
