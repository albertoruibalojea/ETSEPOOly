//© 2019 Alberto Ruibal Ojea && Lara Padrón Cousillas

package Casillas;

import static monopoly.Juego.consola;

public final class Especial extends Casilla{
    //Constructor para casillas especiales
    public Especial(String tipo, String nombre) {
        super(tipo, nombre);
    }

    @Override
    public void describir(){
        consola.imprimirln("Tipo: Especial");
        consola.imprimir("\"    Avatares: ");
        for (int a = 1; a < this.getAvatares().size(); a++) {
            consola.imprimir(this.getAvatares().get(a).getId() + " ");
        }
        consola.imprimir("Número de visitas: ");
        consola.imprimir(Integer.toString(this.frecuenciaVisita()));
    }
}
