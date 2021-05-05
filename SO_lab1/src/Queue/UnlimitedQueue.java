package Queue;

public class UnlimitedQueue<T> implements IQueue<T>{
    private T[] array;
    private int index;

    @SuppressWarnings("unchecked")
    public UnlimitedQueue(int size) {
        this.array=(T[]) new Object[size];
    }

    @Override
    public boolean isEmpty() {
        return index==0;
    }

    @Override
    public boolean isFull() {
        return index==array.length;
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if(isEmpty())
            throw new EmptyQueueException();
        T first=array[0];
        index--;
        for(int i=0;array[index]!=null;i++){
            array[i]=array[i+1];
            array[i+1]=null;
        }
        return first;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void enqueue(T elem) {
        if(isFull()){
            //T[] arrayCopy=array;
            //array= Arrays.copyOf(arrayCopy,arrayCopy.length*2);
            T[] copy = (T[])(new Object[size()*2]);
            System.arraycopy(array, 0, copy, 0, size());
            array = copy;
        }
        array[index++]=elem;
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    public T first() throws EmptyQueueException {
        return array[0];
    }
}
