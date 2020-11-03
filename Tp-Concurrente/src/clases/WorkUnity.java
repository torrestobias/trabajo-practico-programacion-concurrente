package clases;

public class WorkUnity {

    public String texto;
    public Range rango;
    public Integer dificultad;

    public WorkUnity(String texto, Range rango, Integer dificultad){
        this.texto = texto;
        this.rango = rango;
        this.dificultad = dificultad;
    }

    public String getTexto() {
        return texto;
    }

    public Integer getDificultad() {
        return dificultad;
    }

    public Range getRango(){
        return rango;
    }

}
