package clases;

public class ThreadPool {

    private int cantWorkers;
    private Buffer buffer;

    public ThreadPool(int cantWorkers, Buffer buff){
        this.cantWorkers = cantWorkers;
        this.buffer= buff;
    }

    public void instanciarPowWorkers() {
        for (int i = 0; i < cantWorkers; i++) {
           PowWorker worker = new PowWorker(buffer);
           worker.start();
        }
    }
}
