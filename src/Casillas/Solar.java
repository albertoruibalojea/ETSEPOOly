//© 2019 Alberto Ruibal Ojea && Lara Padrón Cousillas

package Casillas;

import Excepciones.ExcepcionJugador;
import Excepciones.ExcepcionPropiedad;
import Jugadores.Avatar;
import Jugadores.Jugador;
import monopoly.Tablero;

import java.util.ArrayList;
import java.util.Iterator;

import static monopoly.Juego.consola;

public final class Solar extends Propiedad {
    private String color;
    private ArrayList<Edificio> edificios;

    //Constructor para casillas de solares

    public Solar(String tipo, String color, String nombre) {
        super(tipo, nombre);
        this.color = color;
        this.edificios = new ArrayList<>();
    }

    //Getters
    public double getAlquiler() {
        return 0.1 * super.getValor_inicial();
    }

    public String getColor() {
        return color;
    }

    public ArrayList<Edificio> getEdificios() {
        return edificios;
    }

    //Setters
    public void setColor(String color) {
        this.color = color;
    }

    public void setEdificios(ArrayList<Edificio> edificios) {
        this.edificios = edificios;
    }


    //Métodos
    //Describir el alquiler de una casilla
    public void describirAlquiler(){
        System.out.println("\nAlquiler: "                     +  0.1 * this.getValor_inicial());
        System.out.println("Alquiler para 1 casa: "           +   5  * this.getValor_inicial());
        System.out.println("Alquiler para 2 casas: "          +  15  * this.getValor_inicial());
        System.out.println("Alquiler para 3 casas: "          +  30  * this.getValor_inicial());
        System.out.println("Alquiler para 4 casas: "          +  50  * this.getValor_inicial());
        System.out.println("Alquiler para hotel: "            + 0.7  * this.getValor_inicial());
        System.out.println("Alquiler para piscina: "          + 0.25 * this.getValor_inicial());
        System.out.println("Alquiler para pista de deporte: " + 0.25 * this.getValor_inicial());
    }

    //Describir el valor de compra de los edificios
    public void describirValor() {
        System.out.println("Valor para una casa: "              + 0.6 * this.getValor_inicial());
        System.out.println("Valor para un hotel: "              + 0.6 * this.getValor_inicial());
        System.out.println("Valor para una piscina: "           + 0.4 * this.getValor_inicial());
        System.out.println("Valor para una pista de deportes: " + 1.25 * this.getValor_inicial());
    }

    //Comprueba si existen edificios en una casilla
    public boolean tenerEdificios() {
        return (this.getEdificios() != null) && (this.getEdificios().size() != 0);
    }

    //Cobrar el alquiler de una casilla con edificios
    public double alquilerEdificios() {
        double precio = 0.0;
        int numCasas = 0;

        for (int i = 0; i < this.getEdificios().size(); i++) {
            Edificio edificio = this.getEdificios().get(i);

            if (edificio instanceof Casa) {
                numCasas++;
                if (numCasas == 1) precio += 5 * this.getAlquiler();
                else if (numCasas == 2) precio += 15 * this.getAlquiler();
                else if (numCasas == 3) precio += 35 * this.getAlquiler();
                else if (numCasas == 4) precio += 50 * this.getAlquiler();
                else consola.imprimirln("Máximo número de casas: 4.");
            } else if (edificio instanceof Hotel) {
                precio += 70 * this.getAlquiler();
            } else if (edificio instanceof Piscina || edificio instanceof PistaDeporte)
                precio += 25 * this.getAlquiler();
        }

        this.getPropietario().setCobroAlquileres(this.getPropietario().getCobroAlquileres() + precio);
        return precio;
    }

    public void venderEdificios(String tipo, int numero) {
        int num = 0;

        Iterator buscar = this.getEdificios().iterator();
        while (buscar.hasNext()) {
            Edificio ed = (Edificio) buscar.next();
            if (tipo.equals(buscar.next())) num++;
        }

        if(numero <= num){
            int eliminadas=0;

            Iterator eliminar = this.getEdificios().iterator();
            while ((eliminar.hasNext()) && (eliminadas!=numero)) {
                Edificio edificio = (Edificio) eliminar.next();
                if (tipo.equals(eliminar.next())) {
                    double ADevolver = edificio.getPrecioCompra();
                    this.getPropietario().setFortuna(this.getPropietario().getFortuna() + ADevolver);
                    this.getPropietario().setDineroInvertido(this.getPropietario().getDineroInvertido() + ADevolver);

                    eliminar.remove();
                    eliminadas++;
                }
            }

            consola.imprimirln("Venta realizada con éxito.");
        }
    }

    //Construir edificios
    public boolean ConstruirEdificio(Tablero tab, String tipo, String id) throws ExcepcionJugador, ExcepcionPropiedad {
        double precio = 0.6 * this.getValor_inicial();

        //Contamos el número de casas y de hoteles
        int numCasas = 0, numHoteles = 0, numPiscinas = 0, numPistas = 0, num = 0;

        if(this.tenerEdificios()) {
            Iterator iterator = edificios.iterator();
            while (iterator.hasNext()) {
                if (iterator.next() instanceof Casa) numCasas++;
                if (iterator.next() instanceof Hotel) numHoteles++;
                if (iterator.next() instanceof Piscina) numPiscinas++;
                if (iterator.next() instanceof PistaDeporte) numPistas++;
            }
        }

        Avatar avatar = tab.getJugadorActual().getAvatar();
        int posicion = avatar.getPosicion();
        Casilla casilla = tab.getCasillas().get(posicion);
        if (casilla instanceof Solar) {
            if ((this.getColor().contains("Azul")) && this.getColor().contains("Cobalto")) num = 2;
            else num = 3;
        }

        //if((numCasas < num) && (numHoteles < num) && (numPiscinas < num) && (numPistas < num)) {
        switch (tipo) {
            case "casa":
                if((numHoteles < num) && (numCasas <= 3)) {
                    //Se construye casa si ha pasado más de dos veces o si es propietario del grupo entero
                    if (avatar.getVecesPasadas().get(avatar.getPosicion()) > 1 || tab.getJugadorActual().isPropietarioGrupo(color)) {
                        if (this.getPropietario().getFortuna() >= precio) {
                            this.getPropietario().setFortuna(this.getPropietario().getFortuna() - precio);
                            this.getPropietario().setDineroInvertido(this.getPropietario().getDineroInvertido() + precio);

                            consola.imprimirln("Fortuna actual: " + super.getPropietario().getFortuna());

                            Casa casa = new Casa(precio);
                            casa.setId(id);
                            this.edificios.add(casa);

                            return true;
                        }
                    } else throw new ExcepcionJugador("No has pasado dos o más veces por esta casilla, prueba más tarde.");
                } else throw new ExcepcionPropiedad("Necesitas sustituir las casas por un hotel.");
                break;
            case "hotel":
                //Si hay más de 4 casas, se puede construir
                if ((numHoteles < num) && (numCasas >= 4)) {
                    if (super.getPropietario().getFortuna() >= precio) {
                        super.getPropietario().setFortuna(super.getPropietario().getFortuna() - precio);
                        super.getPropietario().setDineroInvertido(super.getPropietario().getDineroInvertido() + precio);

                        consola.imprimirln("Fortuna actual: " + super.getPropietario().getFortuna());

                        //Recorremos el ArrayList de edificios y eliminamos 4 casas
                        Iterator it = edificios.iterator();
                        while (it.hasNext())
                            if (it.next() instanceof Casa) it.remove();

                        Hotel hotel = new Hotel(precio);
                        hotel.setId(id);
                        this.edificios.add(hotel);

                    } else throw new ExcepcionJugador("No tienes suficiente fortuna para comprar.");
                } else throw new ExcepcionPropiedad("Tienes que tener menos de " + num + " hoteles y más de 4 casas.");
                break;
            case "piscina":
                if ((numCasas >= 2) && (numHoteles >= 1)) {
                    double precioPiscina = 0.4 * super.getValor_inicial();
                    if (super.getPropietario().getFortuna() >= precioPiscina) {
                        super.getPropietario().setFortuna(super.getPropietario().getFortuna() - precioPiscina);
                        super.getPropietario().setDineroInvertido(super.getPropietario().getDineroInvertido() + precioPiscina);

                        consola.imprimirln("Fortuna actual: " + super.getPropietario().getFortuna());

                        Piscina piscina = new Piscina(precio);
                        piscina.setId(id);
                        this.edificios.add(piscina);

                    } else throw new ExcepcionJugador("No tienes suficiente fortuna para comprar.");
                } else throw new ExcepcionPropiedad("Tienes que tener al menos 2 casas y 1 hotel para construir la piscina.");
                break;
            case "pistaDeDeporte":
                if (numHoteles >= 2) {
                    double precioPista = 1.25 * super.getValor_inicial();
                    if (super.getPropietario().getFortuna() >= precioPista) {
                        super.getPropietario().setFortuna(super.getPropietario().getFortuna() - precioPista);
                        super.getPropietario().setDineroInvertido(super.getPropietario().getDineroInvertido() + precioPista);

                        consola.imprimirln("Fortuna actual: " + super.getPropietario().getFortuna());

                        PistaDeporte pista = new PistaDeporte(precio);
                        pista.setId(id);
                        this.edificios.add(pista);

                    } else throw new ExcepcionJugador("No tienes suficiente fortuna para comprar.");
                } else throw new ExcepcionPropiedad("Tienes que tener al menos 2 hoteles para construir la pista de deporte.");
                break;
        }

        return false;
    }

    //Pagar al caer en una propiedad
    public void pagar(Tablero tab, int movimiento) throws ExcepcionPropiedad, ExcepcionJugador{
        Casilla casilla = tab.getCasillaPosicion(tab.getJugadorActual().getAvatar().getPosicion());

        if (this.getPropietario() != tab.getJugadorActual()) {
            if (!this.isHipotecada()) {
                if (casilla instanceof Solar) {
                    if (!this.getPropietario().isBanca()) {
                        if (!this.tenerEdificios()) {
                            tab.getJugadorActual().gastarDinero(this.getAlquiler());
                            this.getPropietario().setFortuna(this.getPropietario().getFortuna() + this.getAlquiler());
                            tab.getJugadorActual().setPagoAlquileres(tab.getJugadorActual().getPagoAlquileres() + this.getAlquiler());
                            this.getPropietario().setCobroAlquileres(this.getPropietario().getCobroAlquileres() + this.getAlquiler());
                            consola.imprimirln(consola.ROJO + "ALQUILER: Has tenido que pagar " + Math.round(this.getAlquiler()) + " €" + consola.RESET);
                        } else {
                            double pago = this.alquilerEdificios();
                            tab.getJugadorActual().gastarDinero(pago);
                            this.getPropietario().setFortuna(this.getPropietario().getFortuna() + pago);
                            tab.getJugadorActual().setPagoAlquileres(tab.getJugadorActual().getPagoAlquileres() + pago);
                            this.getPropietario().setCobroAlquileres(this.getPropietario().getCobroAlquileres() + pago);
                            consola.imprimirln("ALQUILER: Has tenido que pagar " + pago + " €");

                        }
                    }
                }
            } else throw new ExcepcionPropiedad("Esta casilla está hipotecada, no pagas por caer en ella.");
        }else throw new ExcepcionJugador("Has caído en una casilla de tu propiedad, no pagas por caer aquí.");
    }

    //Hipotecar un Solar
    @Override
    public void hipotecar(Jugador jactual)  throws ExcepcionPropiedad {
        if (!this.isHipotecada()) {
            if (jactual == this.getPropietario()) {
                if (this.getEdificios().size() > 0) {

                    String casa = "casa", hoteles = "hotel", piscinas = "piscina", pistas = "pistasDeDeporte";
                    int numCasas = 0, numHoteles = 0, numPiscinas = 0, numPistas = 0, num = 0;
                    Iterator it = this.getEdificios().iterator();
                    while (it.hasNext()) {
                        if (it.next() instanceof Casa) numCasas++;
                        else if (it.next() instanceof Hotel) numHoteles++;
                        else if (it.next() instanceof Piscina) numPiscinas++;
                        else if (it.next() instanceof PistaDeporte) numPistas++;
                    }

                    if (numCasas > 0) this.venderEdificios(casa, numCasas);
                    if (numHoteles > 0) this.venderEdificios(hoteles, numHoteles);
                    if (numPiscinas > 0) this.venderEdificios(piscinas, numPiscinas);
                    if (numPistas > 0) this.venderEdificios(pistas, numPistas);
                }
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
        consola.imprimirln("Tipo: Solar");
        consola.imprimir("\"    Avatares: ");
        for (int a = 1; a < this.getAvatares().size(); a++) {
            consola.imprimir(this.getAvatares().get(a).getId() + " ");
        }

        consola.imprimirln("\"    Color: " + this.getColor());
        consola.imprimirln("\"    Propietarix: " + this.getPropietario().getNombre());
        consola.imprimir("Número de visitas: ");
        consola.imprimir(Integer.toString(this.frecuenciaVisita()));
        if (this.isHipotecada()) consola.imprimirln("\"    Hipotecada: Sí");
        else consola.imprimirln("\"    Hipotecada: No");
        consola.imprimirln("\"    Valor: " + this.getValor_actual());
        if (this.tenerEdificios()) {
            consola.imprimirln("\"    Alquiler actual: " + this.alquilerEdificios());
            consola.imprimir("\"    Edificios: ");
            for (int i = 0; i < this.getEdificios().size(); i++) {
                consola.imprimir(this.getEdificios().get(i).getId() + " ");
            }
        } else consola.imprimirln("\"    Alquiler actual: " + this.getAlquiler());
        consola.imprimir("\n");
        describirAlquiler();
        describirValor();

        consola.imprimirln("\n");
    }
}
