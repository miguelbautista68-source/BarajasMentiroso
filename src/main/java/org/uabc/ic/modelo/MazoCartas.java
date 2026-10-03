package org.uabc.ic.modelo;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class MazoCartas {

    private List<Baraja> baraja;

    /*
     * Generacion de todas y cada una de las cartas de la baraja española, sin repetir.
     */
    public MazoCartas(){
        baraja = new ArrayList<>();
        for(int i = 1; i <= 4; i++){
            for (int j = i; j <= 10; j++){
                baraja.add(new Baraja(i, j));
            }
        }
    }

    /*
     * Barajeado del mazo.
     */
    public void barajear(){
        Collections.shuffle(baraja);
    }

    /*
     * get de la variable Baraja. La forma en la que se regresa la baraja.
     */
    public List<Baraja> getMazo(){
        return baraja;
    }

    /*
     * set de la variable Baraja. La forma en que se leera la baraja.
     */
    public void setBaraja(List<Baraja> baraja){
        this.baraja = baraja;
    }
}
