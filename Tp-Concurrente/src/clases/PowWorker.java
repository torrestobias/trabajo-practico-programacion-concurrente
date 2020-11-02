package clases;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PowWorker extends Thread {

    private Buffer buffer;

    public PowWorker(Buffer buffer){
        this.buffer = buffer;
    }


    WorkUnity variablework = buffer.leer();

    String cadenaTexto = variablework.getTexto();
    Range rango = variablework.getRango();
    Integer dificultad = variablework.getDificultad();
    Long min = rango.getInicio();
    Long max = rango.getFin();
    Long actual = min;

    public Boolean rangoNotEnded(){
        return min != max;
    }

    public void run() {

        while (Main.nonceFound() && rangoNotEnded()) {
            try {
               byte[] hashed =  MessageDigest.getInstance("SHA-256").digest((cadenaTexto + actual.toString()).getBytes());
               if(chequearNonce(dificultad,hashed)){
                   Main.setHayNonce();
                   System.out.println(actual);
               }
               actual++;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }

    Boolean chequearNonce(Integer dificultad, byte[] hashed){
        Boolean encontro = true;
        for(int i=0; i<dificultad; i++) {
            encontro = encontro && (hashed[i] == 0);
        }
        return encontro;
    }


}
