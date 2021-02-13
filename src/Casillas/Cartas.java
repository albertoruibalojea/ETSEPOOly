//© 2019 Alberto Ruibal Ojea && Lara Padrón Cousillas

package Casillas;

import Excepciones.ExcepcionPropiedad;
import monopoly.Tablero;

public abstract class Cartas {
    private String texto;

    public Cartas(String texto){
        this.texto = texto;
    }

    //Getters
    public String getTexto() {
        return texto;
    }

    //Setters
    public void setTexto(String texto) {
        this.texto = texto;
    }


    //Métodos
    public abstract void accion(Tablero tab, Integer n) throws ExcepcionPropiedad;

}
