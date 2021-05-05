package Queue;

public interface IQueue<T> {
    boolean isEmpty();
    boolean isFull();
    T dequeue() throws EmptyQueueException;
    void enqueue(T elem);
    int size();
    T first() throws EmptyQueueException;
}
