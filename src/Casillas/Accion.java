//© 2019 Alberto Ruibal Ojea && Lara Padrón Cousillas

package Casillas;

public abstract class Accion extends Casilla{
    public Accion(String tipo, String nombre){
        super(tipo, nombre);
    }

    public abstract void describir();
}
