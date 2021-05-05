import java.util.Comparator;

public class Page {
    private int number;
    private boolean priorityBit;
    public int reference;

    public Page(int number, boolean priorityBit, int ref) {
        this.number = number;
        this.priorityBit = priorityBit;
        this.reference = ref;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isPriorityBit() {
        return priorityBit;
    }

    public void setPriorityBit(boolean priorityBit) {
        this.priorityBit = priorityBit;
    }

    public int getRef() {
        return reference;
    }

    public void setRef(int ref) {
        this.reference = ref;
    }
    public static Comparator<Page> refComparator = (Page o1, Page o2)-> Integer.compare(o1.getRef(),o2.getRef());

}
