package clases;

import java.math.BigInteger;
import java.util.ArrayList;

public class Buffer {
   private int size;
   private ArrayList<BigInteger> numbers;
   private int currenNumbers;

    public Buffer(int size){
        this.size = size;
        this.numbers = new ArrayList<>();
        this.currenNumbers = 0;
    }

    public ArrayList<BigInteger> getNumbers(){ return this.numbers;}

    public Boolean isEmpty() { return this.currenNumbers == 0;}

    public Boolean isFull() { return this.currenNumbers == this.size;}

    public synchronized BigInteger get(){
        while (this.isEmpty()){
            try{ wait();}
            catch (Exception e){e.printStackTrace();}
        }
        this.currenNumbers--;
        BigInteger number = this.numbers.get(0);
        this.numbers.remove(0);
        notifyAll();
        return number;
    }

    public synchronized void put(BigInteger number){
        while(this.isFull()){
            try {
                wait();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        this.numbers.add(number);
        this.currenNumbers += 1;
        notifyAll();
    }
}