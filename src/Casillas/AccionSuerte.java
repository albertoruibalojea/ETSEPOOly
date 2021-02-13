//© 2019 Alberto Ruibal Ojea && Lara Padrón Cousillas

package Casillas;

import static monopoly.Juego.consola;

public class AccionSuerte extends Accion{
    public AccionSuerte(String tipo, String nombre) {
        super(tipo, nombre);
    }

    //Métodos
    @Override
    public void describir() {
        consola.imprimirln("Tipo: Suerte");
        consola.imprimir("\"    Avatares: ");
        for (int a = 1; a < this.getAvatares().size(); a++) {
            consola.imprimir(this.getAvatares().get(a).getId() + " ");
        }
        consola.imprimir("Número de visitas: ");
        consola.imprimir(Integer.toString(this.frecuenciaVisita()));
        consola.imprimir("\n");
    }
}
