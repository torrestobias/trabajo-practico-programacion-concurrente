package clases;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Buffer buffer;
    private static Scanner scanner;
    private static ThreadPool threadPool;
    static long timeInicial;


    public static void setHayNonce() {
        Main.hayNonce = true;
    }

    private static Boolean hayNonce = false;

    static ArrayList<Range> mapearRangos(int cantWorkers) {
        ArrayList<Range> rangos = new ArrayList<Range>();
        long min = 1;
        long max = 0;
        long numGrande = (long) Math.pow(2, 32);
        long numDividido = numGrande / cantWorkers;
        for (int i = 0; i < cantWorkers; i++) {
            Range rango = new Range(min, min + (numDividido - 1));
            rangos.add(rango);
            min += numDividido;
            max = min + numDividido;
        }
        return rangos;
    }

    static Boolean nonceFound() {
        return hayNonce;
    }


    public static void main(String[] args) {

        scanner = new Scanner(System.in);



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

    static void timeStart(){ timeInicial= System.currentTimeMillis(); }

    static void timeEnd() {
        double tiempoFinal =(( System.currentTimeMillis() - timeInicial)/1000.0);
        System.out.println( "Se tardó " +tiempoFinal + " segundos tardados.");
    }


}