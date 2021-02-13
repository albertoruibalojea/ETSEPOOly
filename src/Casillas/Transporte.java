//© 2019 Alberto Ruibal Ojea && Lara Padrón Cousillas

package Casillas;

import Excepciones.ExcepcionJugador;
import Excepciones.ExcepcionPropiedad;
import Jugadores.Jugador;
import monopoly.Tablero;

import static monopoly.Juego.consola;

public final class Transporte extends Propiedad {

    //Constructor para casillas de transporte
    public Transporte(String tipo, String nombre, double valor_inicial){
        super(tipo, nombre, valor_inicial);
    }

    //Getters
    public double getAlquiler() {
        return 0.1 * super.getValor_inicial();
    }


    //Métodos
    //Pagar al caer en una propiedad
    public void pagar(Tablero tab, int movimiento) throws ExcepcionPropiedad, ExcepcionJugador {
        Casilla casilla = tab.getCasillaPosicion(tab.getJugadorActual().getAvatar().getPosicion());

        if (this.getPropietario() != tab.getJugadorActual()) {
            if (!this.isHipotecada()) {
                if (casilla instanceof Transporte) {
                    if (!this.getPropietario().isBanca()) {
                        double pago = 1000;
                        /*Iterator it = this.getPropietario().getPropiedades().iterator();
                        while (it.hasNext())
                            if (transporte.equals(it.next())) var++;

                        if (var == 1) pago = 0.25 * tab.getCobro_salida();
                        else if (var == 2) pago = 0.5 * tab.getCobro_salida();
                        else if (var == 3) pago = 0.75 * tab.getCobro_salida();
                        else if (var == 4) pago = tab.getCobro_salida();*/

                        tab.getJugadorActual().gastarDinero(pago);
                        tab.getJugadorActual().setPagoAlquileres(tab.getJugadorActual().getPagoAlquileres() + this.getAlquiler());
                        this.getPropietario().setCobroAlquileres(this.getPropietario().getCobroAlquileres() + this.getAlquiler());
                        consola.imprimirln(consola.ROJO + "ALQUILER: Has tenido que pagar " + pago + " €" + consola.RESET);
                    }
                }
            } else throw new ExcepcionPropiedad("Esta casilla está hipotecada, no pagas por caer en ella.");
        } else throw new ExcepcionJugador("Has caído en una casilla de tu propiedad, no pagas por caer aquí.");
    }

    //Hipotecar una casilla de Servicios
    @Override
    public void hipotecar(Jugador jactual) throws ExcepcionPropiedad {
        if (!this.isHipotecada()) {
            if (jactual == this.getPropietario()) {
                this.setHipotecada(true);
                jactual.setFortuna(jactual.getFortuna() + 0.5 * this.getValor_inicial());
            } else throw new ExcepcionPropiedad("Esta casilla no es de tu propiedad.");
        } else throw new ExcepcionPropiedad("Ya está hipotecada");
    }

    //Deshipotecar una Propiedad
    @Override
    public void deshipotecar(Jugador jactual) throws ExcepcionPropiedad, ExcepcionJugador{
        if (this.isHipotecada()) {
            if (jactual == this.getPropietario()) {
                double APagar = 0.5 * this.getValor_inicial();
                if (this.getPropietario().getFortuna() >= APagar) {
                    this.getPropietario().setFortuna(this.getPropietario().getFortuna() - APagar);
                    this.getPropietario().setDineroInvertido(this.getPropietario().getDineroInvertido() + APagar);
                    this.setHipotecada(false);
                } else throw new ExcepcionJugador("Aun no tienes suficiente fortuna para deshipotecarla.");
            } else throw new ExcepcionPropiedad("Esta casilla no es de tu propiedad.");
        } else throw new ExcepcionPropiedad("No está hipotecada.");
    }

    @Override
    //Nos permite describir cada casilla
    public void describir() {
        consola.imprimirln("Tipo: Transporte");
        consola.imprimir("\"    Avatares: ");
        for (int a = 1; a < this.getAvatares().size(); a++) {
            consola.imprimir(this.getAvatares().get(a).getId() + " ");
        }

        if (this.isHipotecada()) consola.imprimirln("\"    Hipotecada: Sí");
        else consola.imprimirln("\"    Hipotecada: No");
        consola.imprimirln("\"    Valor: " + this.getValor_actual());
        consola.imprimirln("\"    A pagar: " + this.getValor_actual());
        consola.imprimirln("\"    Propietarix: " + this.getPropietario().getNombre());
        consola.imprimir("Número de visitas: ");
        consola.imprimir(Integer.toString(this.frecuenciaVisita()));

        consola.imprimirln("\n");
    }
}
