package org.uabc.ic.modelo;

import java.util.ArrayList;
import java.util.Random;
public class Mazo {
    private ArrayList<Baraja> mazo;

     /*
     * El constructor vacio de Mazo.
     * Genera automaticamente el mazo completo de la baraja española (unicamente sus valores numericos).
     */
    public Mazo(){
        mazo = new ArrayList<Baraja>();
        for (int i = 0; i < 7; i++){
            Baraja iteracion = new Baraja("espadas", i + 1);
            mazo.add(iteracion);

            if(i == 6){
                for(int j = 10; j < 13; j++){
                    Baraja iteracionDos = new Baraja("espadas", j + 1);
                    mazo.add(iteracionDos);
                }
            }
            
        }
    }
}
