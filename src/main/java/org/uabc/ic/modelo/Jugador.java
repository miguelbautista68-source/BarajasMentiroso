package org.uabc.ic.modelo;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private ArrayList<Baraja> mazo;
    private int posicion;

    public Jugador(String nombre, int posicion){
        this.nombre = nombre;
        this.posicion = posicion;
        mazo = new ArrayList<>();
    }

    public void soltarCartas(Baraja baraja){
        this.mazo.remove(baraja);
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public int getPosicion(){
        return posicion;
    }

    public void setPosicion(int posicion){
        this.posicion = posicion;
    }

    public List<Baraja> getMazo(){
        return mazo;
    }
}