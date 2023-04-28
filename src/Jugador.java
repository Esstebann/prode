public class Jugador {
    private String nombre;
    private int apuestaGolesLocal;
    private int apuestaGolesVisitante;

    public Jugador(String nombre, int apuestaGolesLocal, int apuestaGolesVisitante) {
        this.nombre = nombre;
        this.apuestaGolesLocal = apuestaGolesLocal;
        this.apuestaGolesVisitante = apuestaGolesVisitante;
    }

    public String getNombre() {
        return nombre;
    }

    public int getApuestaGolesLocal() {
        return apuestaGolesLocal;
    }

    public int getApuestaGolesVisitante() {
        return apuestaGolesVisitante;
    }
}

