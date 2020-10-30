package clases;

public class ThreadPool {

    private int cantWorkers;
    private Buffer buffer;

    public ThreadPool(int cantWorkers, Buffer buffer){
        this.cantWorkers = cantWorkers;
        this.buffer = buffer;
    }
}
