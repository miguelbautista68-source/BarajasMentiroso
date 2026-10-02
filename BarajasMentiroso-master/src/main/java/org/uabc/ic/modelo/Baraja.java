package org.uabc.ic.modelo;


/**
 * Baraja es el valor de una carta de la baraja española.
 * 
 * @author 
 * @version 1
 */
public class Baraja {
    private String palo;
    private int valor;

    /*
     * Constructor predeterminado.
     */
    public Baraja(){
        palo = "espadas";
        valor = 10;
    }

    /*
     * Constructor a rellenar. Lugar donde irá el valor del 1-7 y del 10-12. 
     * Y de palo irá Oros, Copas, Espadas y Basto. 
     * Recalcando que los valores de 10-12 iría con un simbolo especial de Sota, Caballo y Rey.
     */
    public Baraja(String palo, int valor){
        this.palo = palo;
        this.valor = valor;
    }

    /*
     * get de Valor. Metodo para regresar el valor de la variable valor.
     */
    public int getValor(){
        return valor;
    }

    /*
     * set de Valor. Metodo para escribir el valor de la variable valor.
     */
    public void setValor(int valor){
        this.valor = valor;
    }

    /*
     * get de Palo. Metodo para regresar el valor de la variable palo.
     */
    public String getPalo(){
        return palo;
    }

    /*
     * set de Palo. Metodo para escribir el valor de la variable palo.
     */
    public void setPalo(String palo){
        this.palo = palo;
    }

    /*
     * toString. La manera en que se regresara una carta de la baraja
     */
    public String toString(){
        return valor + " de " + palo; 
    }

}
