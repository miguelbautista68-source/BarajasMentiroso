package org.uabc.ic;

import org.uabc.ic.modelo.Baraja;
import org.uabc.ic.modelo.Juego;
import org.uabc.ic.modelo.Jugador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== EL MENTIROSO ===");

        // 1. Pedir cuántos jugadores (mínimo 2, máximo 10)
        int cantidad = leerEntero("¿Cuántos jugadores? (2-10): ", 2, 10);

        //Pedir el nombre de cada jugador
        List<String> nombres = new ArrayList<>();
        for (int i = 1; i <= cantidad; i++) {
            System.out.print("Nombre del jugador " + i + ": ");
            nombres.add(sc.nextLine());
        }

        // Crear el juego y repartir las cartas
        Juego juego = new Juego(nombres);
        juego.iniciarJuego();
        System.out.println("Cartas repartidas. ¡Empieza el juego!");

        //Ciclo del juego: se repite mientras nadie se quede sin cartas
        while (juego.obtenerGanador() == null) {
            Jugador actual = juego.getJugadorActual();

            System.out.println("\nPásale la compu a " + actual.getNombre() + " y presiona Enter...");
            sc.nextLine();

            System.out.println("--- Turno de " + actual.getNombre() + " ---");
            System.out.println("Cartas en la mesa: " + juego.getMesa().size());
            mostrarMano(actual);

            // Elegir cuántas cartas va a soltar
            int maximo = Math.min(4, actual.getMazo().size());
            int cuantas = leerEntero("¿Cuántas cartas vas a soltar? (1-" + maximo + "): ", 1, maximo);

        }

        // ganador
        Jugador ganador = juego.obtenerGanador();
        System.out.println("\n¡" + ganador.getNombre() + " se quedó sin cartas y GANÓ!");
    }

        // Muestra las cartas del jugador numeradas desde 1
        static void mostrarMano (Jugador jugador){
            System.out.println("Tu mano:");
            for (int i = 0; i < jugador.getMazo().size(); i++) {
                System.out.println("  " + (i + 1) + ". " + jugador.getMazo().get(i));
            }
        }

        // Pide un número hasta que sea válido y esté entre min y max
        static int leerEntero (String mensaje,int min, int max){
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
