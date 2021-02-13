//© 2019 Alberto Ruibal Ojea && Lara Padrón Cousillas

package Casillas;

import monopoly.Tablero;

import static monopoly.Juego.consola;

public final class Impuesto extends Casilla{
    private double valor_inicial;
    private double valor_actual;

    //Constructor para casillas de impuestos
    public Impuesto(String tipo, String nombre, double valor_inicial) {
        super(tipo, nombre);
        this.valor_inicial = valor_inicial;
    }

    //Getters
    public double getValor_inicial() {
        return Math.round(valor_inicial);
    }

    public double getValor_actual() {
        return Math.round(valor_actual);
    }

    //Setters
    public void setValor_inicial(double valor_inicial) {
        this.valor_inicial = valor_inicial;
    }

    public void setValor_actual(double valor_actual) {
        this.valor_actual = valor_actual;
    }

    //Métodos
    //Pagar al caer en una propiedad
    public void pagar(Tablero tab) {
        Casilla casilla = tab.getCasillaPosicion(tab.getJugadorActual().getAvatar().getPosicion());
        if (casilla instanceof Impuesto) {
            tab.getJugadorActual().gastarDinero(this.getValor_inicial());
            tab.getJugadorActual().setPagoAlquileres(tab.getJugadorActual().getPagoAlquileres() + this.getValor_inicial());
            consola.imprimirln("ALQUILER: Has tenido que pagar " + this.getValor_inicial() + " €");
        }
    }

    @Override
    public void describir() {
        consola.imprimirln("Tipo: Impuesto");
        consola.imprimir("\"    Avatares: ");
        for (int a = 1; a < this.getAvatares().size(); a++) {
            consola.imprimir(this.getAvatares().get(a).getId() + " ");
        }
        consola.imprimir("Número de visitas: ");
        consola.imprimir(Integer.toString(this.frecuenciaVisita()));
    }
}
