package org.uabc.ic.modelo;

public class Baraja {
    private String palo;
    private int valor;

    public Baraja(){
        palo = "espadas";
        valor = 10;
    }

    public Baraja(String palo, int valor){
        this.palo = palo;
        this.valor = valor;
    }

    public int getValor(){
        return valor;
    }

    public void setValor(int valor){
        this.valor = valor;
    }

    public String getPalo(){
        return palo;
    }

    public void setPalo(String palo){
        this.palo = palo;
    }


}
