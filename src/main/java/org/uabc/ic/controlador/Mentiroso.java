package org.uabc.ic.controlador;

import org.uabc.ic.modelo.Baraja;
import org.uabc.ic.modelo.Juego;
import org.uabc.ic.modelo.Jugador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Controla toda la partida de "El Mentiroso" por consola:
 * pide los datos, corre el ciclo de turnos y muestra el resultado.
 */
public class Mentiroso {

    private final Scanner sc = new Scanner(System.in);

    // Punto de entrada de la partida
    public void jugar() {
        System.out.println("=== EL MENTIROSO ===");

        // 1. Pedir cuántos jugadores (mínimo 2, máximo 10)
        int cantidad = leerEntero("¿Cuántos jugadores? ", 2, 10);

        // Pedir el nombre de cada jugador
        List<String> nombres = new ArrayList<>();
        for (int i = 1; i <= cantidad; i++) {
            System.out.print("Nombre del jugador " + i + ": ");
            nombres.add(sc.nextLine());
        }

        // Crear el juego y repartir las cartas
        Juego juego = new Juego(nombres);
        juego.iniciarJuego();
        System.out.println("Empieza el juego");

        // Ciclo del juego: cada vuelta es el turno de UN jugador
        while (true) {
            Jugador actual = juego.getJugadorActual();

            System.out.println("\nPásale la compu a " + actual.getNombre() + " y presiona Enter para continuar");
            sc.nextLine();

            System.out.println("--- Turno de " + actual.getNombre() + " ---");
            System.out.println("Cartas en la mesa: " + juego.getMesa().size());

            // Si ya hay cartas en la mesa, se puede acusar o seguir jugando
            if (!juego.getMesa().isEmpty()) {
                Jugador anterior = juego.getUltimoJugador();
                System.out.println(anterior.getNombre() + " soltó "
                        + juego.getUltimaCarta().size() + " carta(s) y declaró que son: "
                        + nombreValor(juego.getValorDeclaro()));

                System.out.println("1. Declarar mentiroso a " + anterior.getNombre());
                System.out.println("2. Tirar cartas");
                int opcion = leerEntero("Elige una opción: ", 1, 2);

                if (opcion == 1) {
                    acusar(juego, actual, anterior);

                    // Si el acusado dijo la verdad y se quedó sin cartas, gana
                    if (juego.obtenerGanador() != null) {
                        break;
                    }
                    continue; // el perdedor es el siguiente turno
                }

                // Si no acusó y el anterior ya no tiene cartas, nadie lo desafió: gana
                if (juego.obtenerGanador() != null) {
                    break;
                }
            }

            tirarCartas(juego, actual);
        }

        // Ganador
        Jugador ganador = juego.obtenerGanador();
        System.out.println("\n" + ganador.getNombre() + " se quedó sin cartas y gano");
    }

    // El jugador actual acusa al anterior. Se revelan las cartas y se reparte la mesa.
    private void acusar(Juego juego, Jugador acusador, Jugador acusado) {
        List<Baraja> reveladas = juego.getUltimaCarta();
        int enMesa = juego.getMesa().size();

        System.out.println("\n" + acusador.getNombre() + " dice que " + acusado.getNombre() + " es un mentiroso");
        System.out.println("Las cartas eran: " + reveladas);

        // Mentiroso recibe al que ACUSA, no al acusado
        boolean mintio = juego.Mentiroso(acusador);

        if (mintio) {
            System.out.println(acusado.getNombre() + " si mintió y se lleva " + enMesa + " cartas.");
        } else {
            System.out.println(acusado.getNombre() + " decía la verdad. " + acusador.getNombre()
                    + " se lleva " + enMesa + " cartas.");
        }
    }

    // El jugador actual elige cartas, y decide si miente o es sincero
    private void tirarCartas(Juego juego, Jugador actual) {
        while (true) {
            mostrarMano(actual);

            // Elegir cuántas cartas va a soltar
            int maximo = Math.min(4, actual.getMazo().size());
            int cuantas = leerEntero("¿Cuántas cartas vas a soltar? (1-" + maximo + "): ", 1, maximo);

            // Elegir cuáles (sin repetir)
            List<Baraja> elegidas = new ArrayList<>();
            while (elegidas.size() < cuantas) {
                int indice = leerEntero("Número de la carta " + (elegidas.size() + 1) + " de " + cuantas
                        + " (1-" + actual.getMazo().size() + "): ", 1, actual.getMazo().size());
                Baraja carta = actual.getMazo().get(indice - 1);

                if (elegidas.contains(carta)) {
                    System.out.println("Ya elegiste esa carta.");
                } else {
                    elegidas.add(carta);
                }
            }

            System.out.println("1. Ser sincero");
            System.out.println("2. Mentir");
            int modo = leerEntero("¿Qué quieres hacer? ", 1, 2);

            int valorDeclarado;
            if (modo == 1) {
                valorDeclarado = valorSincero(elegidas);
                if (valorDeclarado == -1) {
                    System.out.println("Tus cartas no tienen el mismo valor, no puedes esta opción con ellas.");
                    System.out.println("Elige otras cartas o miente.");
                    continue;
                }
                System.out.println("Declaras: " + nombreValor(valorDeclarado));
            } else {
                valorDeclarado = leerValorDeclarado();
            }

            if (juego.turno(elegidas, valorDeclarado)) {
                System.out.println("Soltaste " + cuantas + " cartas boca abajo, declarando: "
                        + nombreValor(valorDeclarado));
                return;
            }
            System.out.println("Jugada inválida, intenta de nuevo.");
        }


    }

    // Si todas las cartas tienen el mismo valor (el 1 es comodín) regresa ese valor; si no, -1
    private int valorSincero(List<Baraja> cartas) {
        int valor = 1;
        for (Baraja carta : cartas) {
            if (carta.getValor() != 1) {
                if (valor != 1 && carta.getValor() != valor) {
                    return -1;
                }
                valor = carta.getValor();
            }
        }
        return valor;
    }

    /*
     * Pide el valor tal como se ve en la baraja española: 1-7 y 10-12 (el 8 y el 9 no existen).
     * Internamente el programa maneja 1-10, asi que 10, 11 y 12 se convierten a 8, 9 y 10
     * (caballero, reina y rey).
     */
    private int leerValorDeclarado() {
        int n;
        do {
            n = leerEntero("¿Qué valor declaras? (1-7 o 10-12): ", 1, 12);
            if (n == 8 || n == 9) {
                System.out.println("El 8 y el 9 no existen en la baraja, usa 1-7 o 10-12.");
            }
        } while (n == 8 || n == 9);

        if (n >= 10) {
            return n - 2;
        }
        return n;
    }

    // Muestra el valor como lo dice el juego, con el numero que se usa al declararlo
    private String nombreValor(int valor) {
        switch (valor) {
            case 8:
                return "caballero (10)";
            case 9:
                return "reina (11)";
            case 10:
                return "rey (12)";
            default:
                return String.valueOf(valor);
        }
    }

    // Muestra las cartas del jugador numeradas desde 1
    private void mostrarMano(Jugador jugador) {
        System.out.println("Tu mano:");
        for (int i = 0; i < jugador.getMazo().size(); i++) {
            System.out.println("  " + (i + 1) + ". " + jugador.getMazo().get(i));
        }
    }

    // Pide un número hasta que sea válido y esté entre min y max
    private int leerEntero(String mensaje, int min, int max) {
        int n;
        do {
            System.out.print(mensaje);

            while (!sc.hasNextInt()) {
                System.out.println("Eso no es un número, intenta de nuevo.");
                sc.next();
                System.out.print(mensaje);
            }

            n = sc.nextInt();
            sc.nextLine();

            if (n < min || n > max) {
                System.out.println("Debe estar entre " + min + " y " + max + ".");
            }
        } while (n < min || n > max);

        return n;
    }
}