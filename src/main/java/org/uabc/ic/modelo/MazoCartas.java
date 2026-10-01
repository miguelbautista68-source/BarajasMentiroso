package org.uabc.ic.modelo;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class MazoCartas {

    private List<Baraja> baraja;

    public MazoCartas(){
        baraja = new ArrayList<>();
        for(int i = 1; i <= 4; i++){
            for (int j = i; j <= 10; j++){
                baraja.add(new Baraja(i, j));
            }
        }
    }

    public void barajear(){
        Collections.shuffle(baraja);
    }

}
