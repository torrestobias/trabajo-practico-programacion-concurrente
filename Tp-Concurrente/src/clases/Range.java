package clases;

public class Range {

    private Long inicio;
    private Long fin;

    public Range(Long inicio, Long fin){
        this.inicio = inicio;
        this.fin = fin;
    }

    public Long getInicio() {
        return inicio;
    }

    public Long getFin() {
        return fin;
    }
}
