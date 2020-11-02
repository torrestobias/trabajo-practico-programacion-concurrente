package clases;

public class ThreadPool {

    private int cantWorkers;
    private Buffer buffer;

    public ThreadPool(int cantWorkers, Buffer buffer){
        this.cantWorkers = cantWorkers;
        this.buffer = buffer;
    }

    public void instanciarPowWorkers(Buffer buffer) {
        for (int i = 0; i < cantWorkers; i++) {
           PowWorker worker = new PowWorker(buffer);
           worker.start();
        }
    }
}
