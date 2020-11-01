package clases;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Buffer buffer;
    private static Scanner scanner;
    private static ThreadPool threadPool;

    static HashMap<Long,Long> mapearRangos(int cantWorkers){
        HashMap<Long, Long> rangos = new HashMap<Long, Long>();
        long min = 1;
        long max=0;
        long numGrande = (long) Math.pow(2,32);
        long numDividido = numGrande/cantWorkers;
        for(int i=0; i<cantWorkers; i++){
            rangos.put(min, min+(numDividido-1));
            min+=numDividido;
            max=min+numDividido;
        }
        return rangos;
    }


    public static void main(String[] args) {

        scanner = new Scanner(System.in);
        long time = System.currentTimeMillis();

        System.out.print("Indique la cantidad de Threads a utilizar: ");
	    String threads = scanner.nextLine();

	    System.out.print("Indique la dificultad a utilizar: ");
        String dificultad = scanner.nextLine();

        String texto = "";
        System.out.print("Indique un texto: ");
        texto = scanner.nextLine();


        int cantWorkers = Integer.parseInt(threads);
        buffer = new Buffer(cantWorkers);
        threadPool = new ThreadPool(cantWorkers, buffer);


        HashMap<Long,Long> prueba = mapearRangos(cantWorkers);
        


    }




}
