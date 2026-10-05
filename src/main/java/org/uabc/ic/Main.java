package org.uabc.ic;

import org.uabc.ic.modelo.Baraja;
import org.uabc.ic.modelo.Juego;
import org.uabc.ic.modelo.Jugador;
import org.uabc.ic.controlador.Mentiroso;

public class Main {

    public static void main(String[] args) {
        new Mentiroso().jugar();
    }
}