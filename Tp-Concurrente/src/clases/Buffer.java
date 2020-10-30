package clases;

public class Buffer {
    private Object[] data;
    private int begin = 0;
    private int end = 0;
    private int n;

    public Buffer(int capacity){
        this.n = capacity;
        this.data = new Object[this.n+1];
    }

    public synchronized void write (int o){
        while(isFull())
            try {
                wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        data [ begin ] = 0;
            begin = next (begin);
            notifyAll();
    }

    public synchronized Object read(){
        while(isEmpty())
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        Object result = data [end];
            end = next(end);
            notifyAll();
            return result;

    }

    private boolean isEmpty () { return begin== end; }
    private boolean isFull() { return next (begin) == end;}
    private int next (int i) { return (i+1)%(this.n+1);}



}