//© 2019 Alberto Ruibal Ojea && Lara Padrón Cousillas

package Casillas;

import Jugadores.Avatar;
import monopoly.Consola;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Casilla {
    private String nombre;
    private ArrayList<Avatar> avatares;
    private int numVisitas;

    //Constructor común a todas sus clases hijas
    public Casilla(String tipo, String nombre) {
        this.nombre = nombre;
        this.avatares = new ArrayList<>();
        this.numVisitas = 0;
    }


    //Getters y setters
    public ArrayList<Avatar> getAvatares() {
        return avatares;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumVisitas() {
        return numVisitas;
    }

    public void setNumVisitas(int numVisitas) {
        this.numVisitas = numVisitas;
    }

    public void setAvatares(ArrayList<Avatar> avatares) {
        this.avatares = avatares;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    //Métodos
    //Comprobar si un Avatar determinado está en una casilla
    public boolean estaAvatar(Avatar avatar){
        Iterator it = this.getAvatares().iterator();
        while ((it.hasNext())) {
            if (avatar.equals(it.next())) return true;
        }

        return false;
    }

    public Integer frecuenciaVisita(){
        return this.getNumVisitas();
    }

    //Nos permite describir cada casilla
    public abstract void describir();

    //Sobreescribimos el método toString para imprimir las casillas
    @Override
    public String toString() {
        String pintar="";

        if(this instanceof Solar) {
            switch (((Solar) this).getColor()) {
                case "Gris":
                    pintar = Consola.GRIS;
                    break;
                case "Azul":
                    pintar = Consola.AZUL;
                    break;
                case "Rosa":
                    pintar = Consola.ROSA;
                    break;
                case "Rojo":
                    pintar = Consola.ROJO;
                    break;
                case "Naranja":
                    pintar = Consola.NARANJA;
                    break;
                case "Amarillo":
                    pintar = Consola.AMARILLO;
                    break;
                case "Verde":
                    pintar = Consola.VERDE;
                    break;
                case "Cobalto":
                    pintar = Consola.COBALTO;
                    break;
                default:
                    pintar = Consola.RESET;
                    break;
            }
        }

        String representacion = "|" + pintar + this.getNombre() + Consola.RESET;

        String representacionAvatares = " ";
        for(Avatar av : avatares){
            if(av.getId() != "€"){
                representacionAvatares += "&" + av.getId();
            }
        }
        representacion += representacionAvatares;

        for(int i=0; i <25 - this.getNombre().length() - representacionAvatares.length(); i++){
            representacion += " ";
        }
        representacion += "|";

        return representacion;
    }
}
