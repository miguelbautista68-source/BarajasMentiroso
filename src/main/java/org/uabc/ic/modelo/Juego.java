package org.uabc.ic.modelo;

import java.util.ArrayList;
import java.util.List;

public class Juego {
    //Atributos del juego
    private List<Jugador> jugadores;
    private MazoCartas mazoPrincipal;
    private List<Baraja> mesa;
    private int turnoActual;
    private int valorDeclaro;
    private List<Baraja> ultimaCarta;
    private Jugador ultimoJugador;

    //Cnstructor base en cual se ejcuta las odenres del main
    public Juego(List<String> nombres){
        this.jugadores = new ArrayList<>();
        nombres.forEach(nombre -> jugadores.add(new Jugador(nombre, jugadores.size())));

        this.mazoPrincipal = new MazoCartas();
        this.mesa = new ArrayList<>();
        this.ultimaCarta = new ArrayList<>();
        this.turnoActual = 0;
        this.valorDeclaro = -1;
    }

    //Metodo para el juego mentiroso
    public void iniciarJuego(){
        mazoPrincipal.barajear();
        List<Baraja> mazo = mazoPrincipal.getMazo();

        //Repartir cartas equivalentemente
        for(int i = 0; i < mazo.size(); i++){
            jugadores.get(i % jugadores.size()).getMazo().add(mazo.get(i));
        }
    }

    //Metodo que llevara el orden el jugador
    public boolean turno(List<Baraja> cartasJugadas, int valorDeclarado ){
        Jugador actual = getJugadorActual();

        /*Validar que cada jugador juegue con sus cartas
            allMatch intatara validar que todas las cartas cupmlan con la siguinet orden a mandar
            .contains Segun el mazo del jugador actual valida que si esten las cartas que especifica
        */
        boolean tieneTodas = cartasJugadas.stream().allMatch(carta -> actual.getMazo().contains(carta));

        if(!tieneTodas) {
            return false;
        }

        //Mueve las cartas de la mano a la mesa, donde este las cartas jugadas
        cartasJugadas.forEach(carta ->{
            actual.soltarCartas(carta);
            mesa.add(carta);
            });

        //Registra el movimiento hecho
        this.ultimaCarta = new ArrayList<>(cartasJugadas);
        this.ultimoJugador = actual;
        this.valorDeclaro = valorDeclarado;

        turnoActual = (turnoActual + 1) % jugadores.size();
        return true;
    }

    /*
     * Se le pasa el jugador que ACUSA (el jugador actual), no al acusado.
     * Si el ultimo jugador mintio, el pierde la mesa; si no, la pierde quien acuso.
     */
    public boolean Mentiroso(Jugador acusado) {

        /*
        * anyMatch devuele un true si encuntra al menos uno que sea verdad, mostrando que mintio
        * */
        boolean mintio = ultimaCarta.stream().anyMatch(carta -> carta.getValor() != valorDeclaro && carta.getValor() != 1);

        // Se le dan las cartas acumuladas de la mesa al perdedor
        Jugador perdedor = mintio ? ultimoJugador : acusado;
        perdedor.getMazo().addAll(mesa);

        // El perdedor toma el siguiente turno
        turnoActual = jugadores.indexOf(perdedor);

        // Reiniciar la mesa
        mesa.clear();
        ultimaCarta.clear();
        valorDeclaro = -1;

        return mintio;
    }

    public Jugador obtenerGanador(){
        return jugadores.stream().filter(j -> j.getMazo().isEmpty()).findFirst().orElse(null);
    }

    public  Jugador getJugadorActual(){
        return jugadores.get(turnoActual);
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public List<Baraja> getMesa(){
        return mesa;
    }

    //getters nuevos para que el Main pueda mostrar la ultima jugada

    public int getValorDeclaro(){
        return valorDeclaro;
    }

    public Jugador getUltimoJugador(){
        return ultimoJugador;
    }

    // Regresa una copia, porque Mentiroso() limpia la lista original
    public List<Baraja> getUltimaCarta(){
        return new ArrayList<>(ultimaCarta);
    }
}