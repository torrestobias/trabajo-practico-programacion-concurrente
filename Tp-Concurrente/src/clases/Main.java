package clases;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Buffer buffer;
    private static Scanner scanner;
    private static ThreadPool threadPool;
    static long time;

    public static void setHayNonce() {
        Main.hayNonce = true;
    }

    private static Boolean hayNonce = false;

    static ArrayList<Range> mapearRangos(int cantWorkers){
       ArrayList<Range> rangos = new ArrayList<Range>();
        long min = 1;
        long max=0;
        long numGrande = (long) Math.pow(2,32);
        long numDividido = numGrande/cantWorkers;
        for(int i=0; i<cantWorkers; i++){
            Range rango = new Range(min, min+(numDividido-1));
            rangos.add(rango);
            min += numDividido;
            max = min+numDividido;
        }
        return rangos;
    }

    static Boolean nonceFound(){
        return hayNonce;
    }

    static void timeEnd(){
        long tiempoFinal =((System.currentTimeMillis() ) / 1000)%60;
        if(tiempoFinal>60){
            System.out.println("Se tardó " + tiempoFinal + " segundos.");
        }
        else{System.out.println("Se tardó " + tiempoFinal + " milisegundos.");}
    }

    public static void main(String[] args) {

        scanner = new Scanner(System.in);
        long time = System.currentTimeMillis();
        time = System.currentTimeMillis();


        System.out.print("Indique la cantidad de Threads a utilizar: ");
	    String threads = scanner.nextLine();

	    System.out.print("Indique la dificultad a utilizar: ");
        String dificultad = scanner.nextLine();

        String texto = "";
        System.out.print("Indique un texto: ");
        texto = scanner.nextLine();

        int cantWorkers = Integer.parseInt(threads);
        buffer = new Buffer(2);
        threadPool = new ThreadPool(cantWorkers, buffer);
        threadPool.instanciarPowWorkers();
        ArrayList<Range> rango = mapearRangos(cantWorkers);


        for (int i = 0; i < cantWorkers; i++) {

            buffer.escribir(new WorkUnity(texto, rango.get(i), Integer.parseInt(dificultad)));

        }



    }


}
