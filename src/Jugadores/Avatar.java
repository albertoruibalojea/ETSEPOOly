//© 2019 Alberto Ruibal Ojea && Lara Padrón Cousillas

package Jugadores;

import Casillas.Casilla;
import monopoly.Tablero;

import java.util.ArrayList;

import static monopoly.Juego.consola;

public class Avatar {
    private Jugador jugador;
    private String id;
    private String tipo;
    private Casilla casilla;
    private int posicion;
    private int countCarcel;
    private int vueltas;
    private boolean carcel;
    private ArrayList<Integer> vecesPasadas;


    public Avatar (String id, String tipo){
        this.casilla = null;
        this.countCarcel = 0;
        this.id = id;
        this.jugador = null;
        this.posicion = 0;
        this.tipo = tipo;
        this.vueltas = 0;
        this.carcel = false;
        this.vecesPasadas = new ArrayList<>();
    }


    //Getters y setters

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Casilla getCasilla() {
        return casilla;
    }

    public int getCountCarcel() {
        return countCarcel;
    }

    public int getPosicion() {
        return posicion;
    }

    public int getVueltas() {
        return vueltas;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public String getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public ArrayList<Integer> getVecesPasadas() {
        return vecesPasadas;
    }

    public void setCasilla(Casilla casilla) {
        this.casilla = casilla;
    }

    public void setCountCarcel(int countCarcel) {
        this.countCarcel = countCarcel;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public void setVueltas(int vueltas) {
        this.vueltas = vueltas;
    }

    public void setCarcel(boolean carcel) {
        this.carcel = carcel;
    }

    public void setVecesPasadas(ArrayList<Integer> vecesPasadas) {
        this.vecesPasadas = vecesPasadas;
    }


    //Métodos
    //Describir a un avatar
    public void describir(){
        consola.imprimirln("\nID:" + this.getId());
        consola.imprimirln("Tipo: " + this.getTipo());
        consola.imprimirln("Casilla actual: " + this.getCasilla().getNombre());
        consola.imprimirln("Jugador/a: "+ this.getJugador().getNombre() + "\n");
    }

    //Método para dare el turno al siguiente jugador/a
    public void siguienteTurno(){
        this.setVueltas(this.getVueltas() + 1);
    }

    //Método para el movimiento del avatar
    public void moverAvatar(Tablero tab, int posiciones) {
        boolean vuelta = false;
        this.posicion = this.posicion + posiciones;
        //Si posicionActual es 40, volvemos a ponerla de acuerdo al tamaño del Tablero (mod 40)
        if (this.posicion >= 40) {
            vuelta = true;
            this.posicion = this.posicion - 40;
            this.getJugador().setFortuna(this.getJugador().getFortuna() + tab.getCobro_salida());
            this.getJugador().setPasarPorCasillaSalida(this.getJugador().getPasarPorCasillaSalida() + tab.getCobro_salida());
        }

        //Se comprueba que no está en la cárcel
        if (vuelta && (!this.isCarcel()))
            this.vueltas = this.vueltas + 1;

        //Movemos el Avatar
        if (this.casilla.getAvatares().contains(this))
            this.casilla.getAvatares().remove(this);
        this.casilla = tab.getCasillas().get(this.posicion);
        this.casilla.getAvatares().add(this);
        this.casilla.setNumVisitas(this.casilla.getNumVisitas()+1);

        //Sumamos 1 a la posición de vecesPasadas
        int vez= this.getVecesPasadas().get(this.posicion) + 1;
        this.getVecesPasadas().set(this.posicion, vez);
    }

    //Métodos para la cárcel
    public void IrCarcel(Tablero tab) {
        this.getCasilla().getAvatares().remove(tab.getJugadorActual().getAvatar());
        this.setCasilla(tab.getCasillas().get(10));
        this.getJugador().setVecesEnLaCarcel(this.getJugador().getVecesEnLaCarcel() + 1);
        this.setCarcel(true);
        consola.imprimirln(consola.ROJO + this.getId() + " está en la cárcel :(" + consola.RESET);
    }

    public void salirCarcel(Tablero tab){
        if(!this.isCarcel()){
            consola.imprimirln("No estás en la cárcel");
        } else {
            this.getJugador().gastarDinero(0.25 * tab.getCobro_salida());
            this.setCarcel(false);
        }
    }

    public boolean isCarcel(){
        return this.carcel;
    }
}
