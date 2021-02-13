//© 2019 Alberto Ruibal Ojea && Lara Padrón Cousillas

package Casillas;

import Excepciones.ExcepcionJugador;
import Excepciones.ExcepcionPropiedad;
import Jugadores.Jugador;
import monopoly.Tablero;

import java.util.Iterator;

import static monopoly.Juego.consola;

public final class Servicio extends Propiedad {

    //Constructor para casillas de servicios
    public Servicio(String tipo, String nombre, double valor_inicial) {
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

        if (this.getPropietario() != tab.getJugadorActual()){
            if (!this.isHipotecada()) {
                if (casilla instanceof Servicio) {
                    if (!this.getPropietario().isBanca()) {
                        String servicios = "Servicios";
                        double pago = 0;
                        Iterator it = this.getPropietario().getPropiedades().iterator();
                        while (it.hasNext())
                            if (servicios.equals(it.next()))
                                pago = movimiento * 10 * (tab.getCobro_salida() / 200);
                        if (pago == 0) pago = movimiento * 4 * (tab.getCobro_salida() / 200);

                        tab.getJugadorActual().gastarDinero(pago);
                        tab.getJugadorActual().setPagoAlquileres(tab.getJugadorActual().getPagoAlquileres() + this.getAlquiler());
                        this.getPropietario().setCobroAlquileres(this.getPropietario().getCobroAlquileres() + this.getAlquiler());
                        consola.imprimirln("ALQUILER: Has tenido que pagar " + pago + " €");
                    }
                }
            } else throw new ExcepcionPropiedad("Esta casilla está hipotecada, no pagas por caer aquí.");
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
        consola.imprimirln("Tipo: Servicio");
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

