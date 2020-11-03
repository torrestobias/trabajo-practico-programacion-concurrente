package clases;

public class Buffer {
    public WorkUnity[] data;
    private int begin = 0;
    private int end = 0;
    private int n;

    public Buffer(int capacity){
        this.n = capacity;
        this.data = new WorkUnity[this.n+1];
    }

    public synchronized void escribir (WorkUnity workUnity){
        while(isFull())
            try {
                wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        data [ begin ] = workUnity;
            begin = next (begin);
            notifyAll();
    }

    public synchronized WorkUnity leer(){
        while(isEmpty())
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        WorkUnity result = data [end];
            end = next(end);
            notifyAll();
            return result;

    }

    private boolean isEmpty () { return begin== end; }
    private boolean isFull() { return next (begin) == end;}
    private int next (int i) { return (i+1)%(this.n+1);}



}