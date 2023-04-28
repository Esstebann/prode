import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Prode {
    public static void main(String[] args) {
        // Leer el archivo de resultados de partidos
        ArrayList<Partido> partidos = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("resultados.txt"));
            while (scanner.hasNextLine()) {
                String[] campos = scanner.nextLine().split(",");
                Partido partido = new Partido(campos[0], campos[1], Integer.parseInt(campos[2]), Integer.parseInt(campos[3]));
                partidos.add(partido);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo encontrar el archivo de resultados.");
            return;
        }

        // Leer el archivo de apuestas de jugadores
        ArrayList<Jugador> jugadores = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("apuestas.txt"));
            while (scanner.hasNextLine()) {
                String[] campos = scanner.nextLine().split(",");
                Jugador jugador = new Jugador(campos[0], Integer.parseInt(campos[1]), Integer.parseInt(campos[2]));
                jugadores.add(jugador);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo encontrar el archivo de apuestas.");
            return;
        }

        // Pedir el nombre del jugador actual y sus apuestas
        Scanner input = new Scanner(System.in);
        System.out.print("Ingrese su nombre: ");
        String nombreJugador = input.nextLine();
        System.out.print("Ingrese la cantidad de goles que anotará el equipo local: ");
        int apuestaGolesLocal = input.nextInt();
        System.out.print("Ingrese la cantidad de goles que anotará el equipo visitante: ");
        int apuestaGolesVisitante = input.nextInt();
        input.close();

        // Comparar las apuestas con los resultados reales de los partidos
        int puntajeJugador = 0;
        for (Jugador jugador : jugadores) {
            int puntaje = 0;
            for (Partido partido : partidos) {
                if (jugador.getApuestaGolesLocal() == partido.getGolesLocal()
                        && jugador.getApuestaGolesVisitante() == partido.getGolesVisitante()) {
                    puntaje += 3; // El jugador acertó el resultado exacto
                } else if ((jugador.getApuestaGolesLocal() - jugador.getApuestaGolesVisitante())
                        == (partido.getGolesLocal() - partido.getGolesVisitante())) {
                    puntaje += 1; // El jugador acertó la diferencia de goles
                }
            }
            if (jugador.getNombre().equals(nombreJugador)) {
                puntajeJugador = puntaje;
            } else {
                System.out.println(jugador.getNombre() + " obtuvo " + puntaje + " puntos.");
            }
        }

        // Mostrar los resultados del jugador actual
        System.out.println("Usted obtuvo " + puntajeJugador + " puntos.");
    }
}

class Partido {
    private String equipoLocal;
    private String equipoVisitante;
    private int golesLocal;
    private int golesVisitante;

    public Partido(String equipoLocal, String equipoVisitante, int golesLocal, int golesVisitante) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
    }
}
