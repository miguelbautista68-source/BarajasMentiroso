package org.uabc.ic.modelo;

public class Baraja {
    private int palo;
    private int valor;

    public Baraja(){
        palo = 2;
        valor = 10;
    }

    public Baraja(int palo, int valor){
        this.palo = palo;
        this.valor = valor;
    }

    public int getValor(){
        return valor;
    }

    public void setValor(int valor){
        this.valor = valor;
    }

    public int getPalo(){
        return palo;
    }

    public void setPalo(int palo){
        this.palo = palo;
    }

    public String toString(){
        return "La carta es: " + palo + valor;
    }


}
