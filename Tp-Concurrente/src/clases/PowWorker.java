package clases;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PowWorker extends Thread {


    private Buffer buff;
    private WorkUnity variablework;
    Long min;
    Long max;

    public PowWorker(Buffer buff){
        this.buff = buff;
    }

    public void run() {
        this.variablework = buff.leer();
        String cadenaTexto = variablework.getTexto();
        Range rango = variablework.getRango();
        Integer dificultad = variablework.getDificultad();
        min = rango.getInicio();
        max = rango.getFin();
        Long actual = min;

        while ( rangoNotEnded() && !Main.nonceFound() ) {
            try {
               byte[] hashed =  MessageDigest.getInstance("SHA-256").digest((cadenaTexto + actual.toString()).getBytes());
               if(chequearNonce(dificultad,hashed)){
                   Main.setHayNonce();
                   Main.timeEnd();
                   System.out.println("Nonce encontrado con el número: "+actual);
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

    public Boolean rangoNotEnded(){
        return min != max;
    }
}
