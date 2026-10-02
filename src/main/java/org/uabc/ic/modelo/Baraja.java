package org.uabc.ic.modelo;

public class Baraja {
    private int palo;
    private int valor;

    /* Constructor predeterminada. La forma predeterminada en la que se mostrará una carta.
     */
    public Baraja(){
        palo = 1;
        valor = 1;
    }

    /*
     * Constructor a rellenar. El metodo para crear cartas.
     */
    public Baraja(int palo, int valor){
        this.palo = palo;
        this.valor = valor;
    }

    /*
     * get de la variable Valor. La forma en la que se regresa el valor de la baraja.
     */
    public int getValor(){
        return valor;
    }

    /*
     * set de la variable Valor. La forma en que se leera el valor de la baraja.
     */
    public void setValor(int valor){
        this.valor = valor;
    }

    /*
     * get de la variable Palo. La forma en la que se regresa el palo de la baraja.
     */
    public int getPalo(){
        return palo;
    }

    /*
     * set de la variable palo. La forma en que se leera el palo de la baraja.
     */
    public void setPalo(int palo){
        this.palo = palo;
    }

    /*
     * toString. Se encarga de mostrar correctamente los valores de las cartas.
     * Los "switch" internos su única función es mostrar correctamente los caballos, reinas y reyes
     * el "default" es cuando no es ninguno, y solo muestra el valor.
     * El switch principal se elaboro para no mostrar "1 de 2" y en su lugar mostrar, por ejemplo, "1 de copas"
     * 
     * 1 = oros
     * 2 = copas
     * 3 = espadas
     * 4 = bastos
     */
    public String toString(){
        switch (palo) {
            case 1:

                switch (valor) {
                    case 8:
                        return "caballero de oros";
                    case 9:
                        return "reina de oros";
                    case 10:
                        return "rey de oros";
                    default:
                        return valor + " de oros";
                }

            case 2:

                switch (valor) {
                    case 8:
                        return "caballero de copas";
                    case 9:
                        return "reina de copas";
                    case 10:
                        return "rey de copas";
                    default:
                        return valor + " de copas";
                }

            case 3:

                switch (valor) {
                    case 8:
                        return "caballero de espadas";
                    case 9:
                        return "reina de espadas";
                    case 10:
                        return "rey de espadas";
                    default:
                        return valor + " de espadas";
                }

            case 4:

                switch (valor) {
                    case 8:
                        return "caballero de bastos";
                    case 9:
                        return "reina de bastos";
                    case 10:
                        return "rey de bastos";
                    default:
                        return valor + " de bastos";
                }
                
            default:
                return "ERROR";
        }
    }


}
