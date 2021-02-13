//© 2019 Alberto Ruibal Ojea && Lara Padrón Cousillas

package Casillas;

import Excepciones.ExcepcionCasilla;
import Excepciones.ExcepcionJuego;
import Excepciones.ExcepcionJugador;
import Excepciones.ExcepcionPropiedad;
import Jugadores.Jugador;
import monopoly.Tablero;

import static monopoly.Juego.consola;

public abstract class Propiedad extends Casilla {
    private double valor_actual;
    private double valor_inicial;
    private boolean hipotecada;
    private Jugador propietario;

    //Constructor común a todas sus clases hijas
    public Propiedad(String tipo, String nombre) {
        super(tipo, nombre);
        this.hipotecada = false;
        this.propietario = null;
        this.valor_actual = 0;
        this.valor_inicial = 0;
    }

    public Propiedad(String tipo, String nombre, double valor_inicial) {
        super(tipo, nombre);
        this.hipotecada = false;
        this.propietario = null;
        this.valor_actual = 0;
        this.valor_inicial = valor_inicial;
    }

    //Getters
    public double getValor_actual() {
        return Math.round(valor_actual);
    }

    public double getValor_inicial() {
        return Math.round(valor_inicial);
    }

    public Jugador getPropietario() {
        return propietario;
    }

    //Setters
    public void setValor_actual(double valor_actual) {
        this.valor_actual = valor_actual;
    }

    public void setPropietario(Jugador propietario) {
        this.propietario = propietario;
    }

    public void setValor_inicial(double valor_inicial) throws ExcepcionJuego {
        if(valor_inicial >= 0) this.valor_inicial = valor_inicial;
        else throw new ExcepcionJuego("Error al establecer el precio");
    }


    public boolean isHipotecada() {
        return hipotecada;
    }

    public void setHipotecada(boolean hipotecada) {
        this.hipotecada = hipotecada;
    }


    //Métodos
    //Comprobar si la casilla pertenece a un jugador dado
    public boolean perteneceAJugador(Jugador jugador) {
        return this.propietario.equals(jugador);
    }

    //Comprar una casilla
    public void comprar(Tablero tab) throws ExcepcionPropiedad, ExcepcionCasilla {
        Jugador jugador = tab.getJugadorActual();
        Casilla casilla = tab.getCasillaPosicion(jugador.getAvatar().getPosicion());
        //Compramos
        if ((casilla instanceof Servicio) || (casilla instanceof Transporte) || (casilla instanceof Solar)) {
            if (this.getPropietario().isBanca()) {
                double precio = this.getValor_actual();
                jugador.gastarDinero(precio);
                jugador.setDineroInvertido(jugador.getDineroInvertido() + precio);
                this.setPropietario(jugador);

                consola.imprimirln("Has comprado la casilla " + this.getNombre());
                consola.imprimirln("Se ha adquirido por " + this.getValor_actual());
                jugador.getPropiedades().add(this);

            } else  throw new ExcepcionPropiedad("Esta casilla ya ha sido comprada.");
        } else  throw new ExcepcionCasilla("No puedes comprar esta casilla.");
    }

    //Deshipotecar una Propiedad
    public abstract void deshipotecar(Jugador jactual) throws ExcepcionPropiedad, ExcepcionJugador;

    //Pagar al caer en una Propiedad
    public abstract void pagar(Tablero tab, int movimiento) throws ExcepcionPropiedad, ExcepcionJugador;

    //Hipotecar una Propiedad
    public abstract void hipotecar(Jugador jactual)  throws ExcepcionPropiedad;

    //Devolver el valor actual de una propiedad
    public Double valor(){
        return this.getValor_actual();
    }

    //Describir una Propiedad
    public abstract void describir();
}
