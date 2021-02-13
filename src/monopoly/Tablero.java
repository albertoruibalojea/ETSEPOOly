//© 2019 Alberto Ruibal Ojea && Lara Padrón Cousillas

package monopoly;

import Casillas.*;
import Excepciones.ExcepcionJuego;
import Excepciones.ExcepcionPropiedad;
import Jugadores.*;

import java.util.ArrayList;

import static monopoly.Juego.consola;

public class Tablero {
    public static int aumentoPrecio = 0;
    private ArrayList<Avatar> avatares;
    private ArrayList<Jugador> jugadores;
    private ArrayList<Casilla> casillas;
    private ArrayList<Cartas> cartas;
    private ArrayList<String> nombresCasillas;
    private Grupo grupo;
    private double cobro_salida;
    private int turnoActual;

    public Tablero() {
        this.avatares = new ArrayList<>();
        this.jugadores = new ArrayList<>();
        this.casillas = new ArrayList<>();
        this.cartas = new ArrayList<>();
        this.nombresCasillas = new ArrayList<>();
        this.cobro_salida = 0;
        this.turnoActual = 0;


        //Creación de las casillas
        for (int n = 0; n < 40; n++) {
            switch (n) {
                case 0:
                    this.nombresCasillas.add("Inicio");
                    break;
                case 1:
                    this.nombresCasillas.add("Física");
                    break;
                case 2:
                    this.nombresCasillas.add("Comunidad");
                    break;
                case 3:
                    this.nombresCasillas.add("Química");
                    break;
                case 4:
                    this.nombresCasillas.add("Banco");
                    break;
                case 5:
                    this.nombresCasillas.add("Lavacolla");
                    break;
                case 6:
                    this.nombresCasillas.add("Biología");
                    break;
                case 7:
                    this.nombresCasillas.add("Suerte");
                    break;
                case 8:
                    this.nombresCasillas.add("Matemáticas");
                    break;
                case 9:
                    this.nombresCasillas.add("Veterinaria");
                    break;
                case 10:
                    this.nombresCasillas.add("Carcel");
                    break;
                case 11:
                    this.nombresCasillas.add("Políticas");
                    break;
                case 12:
                    this.nombresCasillas.add("Eduroam");
                    break;
                case 13:
                    this.nombresCasillas.add("Laborales");
                    break;
                case 14:
                    this.nombresCasillas.add("Derecho");
                    break;
                case 15:
                    this.nombresCasillas.add("TUSSA");
                    break;
                case 16:
                    this.nombresCasillas.add("Psicología");
                    break;
                case 17:
                    this.nombresCasillas.add("Comunidad");
                    break;
                case 18:
                    this.nombresCasillas.add("Pedagogía");
                    break;
                case 19:
                    this.nombresCasillas.add("ED. Social");
                    break;
                case 20:
                    this.nombresCasillas.add("Parking");
                    break;
                case 21:
                    this.nombresCasillas.add("Filología");
                    break;
                case 22:
                    this.nombresCasillas.add("Suerte");
                    break;
                case 23:
                    this.nombresCasillas.add("Historia");
                    break;
                case 24:
                    this.nombresCasillas.add("Geografía");
                    break;
                case 25:
                    this.nombresCasillas.add("Renfe");
                    break;
                case 26:
                    this.nombresCasillas.add("Periodismo");
                    break;
                case 27:
                    this.nombresCasillas.add("Audiovisuales");
                    break;
                case 28:
                    this.nombresCasillas.add("Luz");
                    break;
                case 29:
                    this.nombresCasillas.add("Publicidad");
                    break;
                case 30:
                    this.nombresCasillas.add("Ir Carcel");
                    break;
                case 31:
                    this.nombresCasillas.add("Farmacia");
                    break;
                case 32:
                    this.nombresCasillas.add("Enfermería");
                    break;
                case 33:
                    this.nombresCasillas.add("Comunidad");
                    break;
                case 34:
                    this.nombresCasillas.add("Medicina");
                    break;
                case 35:
                    this.nombresCasillas.add("BlaBlaCar");
                    break;
                case 36:
                    this.nombresCasillas.add("Suerte");
                    break;
                case 37:
                    this.nombresCasillas.add("Ing. Química");
                    break;
                case 38:
                    this.nombresCasillas.add("Banco");
                    break;
                case 39:
                    this.nombresCasillas.add("Ing. Informática");
                    break;
            }
        }
        Casilla c= null;
        for (int j = 0; j < 40; j++) {
            switch (j) {
                //Casillas especiales
                case 0:
                    //c = (Casilla) new Especial("Especial", "Inicio");
                    casillas.add(j, new Especial("Especial", "Inicio"));
                    break;
                case 10:
                    c = new Especial("Especial", "Carcel");
                    casillas.add(j, c);
                    break;
                case 20:
                    c = new Especial("Especial", "Parking");
                    casillas.add(j, c);
                    break;
                case 30:
                    c = new Especial("Especial", "Ir Carcel");
                    casillas.add(j, c);
                    break;
                //Casillas de comunidad
                case 2:
                case 17:
                case 33:
                    c = new AccionComunidad("Comunidad", "Comunidad");
                    casillas.add(j, c);
                    break;
                //Casillas de suerte
                case 7:
                case 22:
                case 36:
                    c = new AccionSuerte("Suerte", "Suerte");
                    casillas.add(j, c);
                    break;
                //Casillas de transporte
                case 5:
                    c = new Transporte("Transporte", "Lavacolla", this.getCobro_salida());
                    casillas.add(j, c);
                    break;
                case 15:
                    c = new Transporte("Transporte", "TUSSA", this.getCobro_salida());
                    casillas.add(j, c);
                    break;
                case 25:
                    c = new Transporte("Transporte", "Renfe", this.getCobro_salida());
                    casillas.add(j, c);
                    break;
                case 35:
                    c = new Transporte("Transporte", "BlaBlaCar", this.getCobro_salida());
                    casillas.add(j, c);
                    break;
                //Casillas de servicios
                case 12:
                    c = new Servicio("Servicios", "Eduroam", (int) 0.75 * this.cobro_salida);
                    casillas.add(j, c);
                    break;
                case 28:
                    c = new Servicio("Servicios", "Luz", (int) 0.75 * this.getCobro_salida());
                    casillas.add(j, c);
                    break;
                //Casillas de impuestos
                case 4:
                    c = new Impuesto("Impuestos", "Banco", this.getCobro_salida());
                    casillas.add(j, c);
                    break;
                case 38:
                    c = new Impuesto("Impuestos", "Banco", (int) 0.5 * this.getCobro_salida());
                    casillas.add(j, c);
                    break;
                //Solares
                case 1:
                    c = new Solar("Solar", "Marron", "Física");
                    casillas.add(j, c);
                    break;
                case 3:
                    c = new Solar("Solar", "Marron", "Química");
                    casillas.add(j, c);
                    break;
                case 6:
                    c = new Solar("Solar", "Azul", "Biología");
                    casillas.add(j, c);
                    break;
                case 8:
                    c = new Solar("Solar", "Azul", "Matemáticas");
                    casillas.add(j, c);
                    break;
                case 9:
                    c = new Solar("Solar", "Azul", "Veterinaria");
                    casillas.add(j, c);
                    break;
                case 11:
                    c = new Solar("Solar", "Rosa", "Políticas");
                    casillas.add(j, c);
                    break;
                case 13:
                    c = new Solar("Solar", "Rosa", "Laborales");
                    casillas.add(j, c);
                    break;
                case 14:
                    c = new Solar("Solar", "Rosa", "Derecho");
                    casillas.add(j, c);
                    break;
                case 16:
                    c = new Solar("Solar", "Naranja", "Psicología");
                    casillas.add(j, c);
                    break;
                case 18:
                    c = new Solar("Solar", "Naranja", "Pedagogía");
                    casillas.add(j, c);
                    break;
                case 19:
                    c = new Solar("Solar", "Naranja", "ED. Social");
                    casillas.add(j, c);
                    break;
                case 21:
                    c = new Solar("Solar", "Rojo", "Filología");
                    casillas.add(j, c);
                    break;
                case 23:
                    c = new Solar("Solar", "Rojo", "Historia");
                    casillas.add(j, c);
                    break;
                case 24:
                    c = new Solar("Solar", "Rojo", "Geografía");
                    casillas.add(j, c);
                    break;
                case 26:
                    c = new Solar("Solar", "Amarillo", "Periodismo");
                    casillas.add(j, c);
                    break;
                case 27:
                    c = new Solar("Solar", "Amarillo", "Audiovisuales");
                    casillas.add(j, c);
                    break;
                case 29:
                    c = new Solar("Solar", "Amarillo", "Publicidad");
                    casillas.add(j, c);
                    break;
                case 31:
                    c = new Solar("Solar", "Verde", "Farmacia");
                    casillas.add(j, c);
                    break;
                case 32:
                    c = new Solar("Solar", "Verde", "Enfermería");
                    casillas.add(j, c);
                    break;
                case 34:
                    c = new Solar("Solar", "Verde", "Medicina");
                    casillas.add(j, c);
                    break;
                case 37:
                    c = new Solar("Solar", "Cobalto", "Ing. Química");
                    casillas.add(j, c);
                    break;
                case 39:
                    c = new Solar("Solar", "Cobalto", "Ing. Informática");
                    casillas.add(j, c);
                    break;
            }
        }


        Cartas carta;
        String texto;
        for (int i = 0; i < 6; i++) {
            switch (i) {
                case 0:
                    texto = "Ve a Lavacolla y coge un avión. Si pasas por la casilla de salida acuérdate de cobrar.";
                    carta = new Suerte(texto);
                    cartas.add(i, carta);
                    break;
                case 1:
                    texto = "Decides hacerle una visita a tu amiga de Veterinaria. Avanza hasta Veterinaria";
                    carta = new Suerte(texto);
                    cartas.add(i, carta);
                    break;
                case 2:
                    texto = "Vendes tus apuntes en una página web de la carrera. Cobra 500K.";
                    carta = new Suerte(texto);
                    cartas.add(i, carta);
                    break;
                case 3:
                    texto = "Ve a Políticas. Si pasas por la casilla de salida acuérdate de cobrar.";
                    carta = new Suerte(texto);
                    cartas.add(i, carta);
                    break;
                case 4:
                    texto = "Paga 1,5M por la matrícula de la cuarta convocatoria de álgebra";
                    carta = new Suerte(texto);
                    cartas.add(i, carta);
                    break;
                case 5:
                    texto = "Te abren expediente por plagio. Sobornas con 150K.";
                    carta = new Suerte(texto);
                    cartas.add(i, carta);
                    break;
            }
        }

        for (int z = 0; z < 10; z++) {
            switch (z) {
                case 0:
                    texto = "Paga 500K por un fin de semana de relax de tantas entregas (ejem ejem).";
                    carta = new Comunidad(texto);
                    cartas.add(z, carta);
                    break;
                case 1:
                    texto = "Colócate en la casilla de salida. Cobra 2M.";
                    carta = new Comunidad(texto);
                    cartas.add(z, carta);
                    break;
                case 2:
                    texto = "Eduroam obtiene beneficios. Recibe 2M.";
                    carta = new Comunidad(texto);
                    cartas.add(z, carta);
                    break;
                case 3:
                    texto = "Paga 1M por invitar a todos tus amigos al prefinde de informática.";
                    carta = new Comunidad(texto);
                    cartas.add(z, carta);
                    break;
                case 4:
                    texto = "Devolución de la UXA. Cobra 500K.";
                    carta = new Comunidad(texto);
                    cartas.add(z, carta);
                    break;
                case 5:
                    texto = "Vas a la biblioteca de Historia. Si pasas por la casilla de Salida, cobra lo que tengas que cobrar.";
                    carta = new Comunidad(texto);
                    cartas.add(z, carta);
                    break;
            }
        }


        grupo = new Grupo(this);
    }


    //Getters y setters
    public ArrayList<Avatar> getAvatares() {
        return avatares;
    }

    public ArrayList<Casilla> getCasillas() {
        return casillas;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public ArrayList<String> getNombresCasillas() {
        return nombresCasillas;
    }

    public double getCobro_salida() {
        return cobro_salida;
    }

    public int getTurnoActual() {
        return turnoActual;
    }

    public ArrayList<Cartas> getCartas() {
        return cartas;
    }

    public void setAvatares(ArrayList<Avatar> avatares) {
        this.avatares = avatares;
    }

    public void setCasillas(ArrayList<Casilla> casillas) {
        this.casillas = casillas;
    }

    public void setCobro_salida(double cobro_salida) {
        this.cobro_salida = cobro_salida;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void setNombresCasillas(ArrayList<String> nombresCasillas) {
        this.nombresCasillas = nombresCasillas;
    }

    public void setTurnoActual(int turnoActual) {
        this.turnoActual = turnoActual;
    }

    public void setComunidad(ArrayList<Cartas> cartas) {
        this.cartas = cartas;
    }


    //Métodos
    public void calcularVueltasTotales() {
        int minimoVueltas = 0;

        //Si jugador i lleva hechas n vueltas, con n%4==0, minimoVueltas++
        //minimoVueltas es el número de jugadores que cumplen el requisito
        for (int i = 1; i < this.getAvatares().size(); i++) {
            if ((this.getAvatares().get(i).getVueltas() > 4) && (this.getAvatares().get(i).getVueltas() % 4 == 0))
                minimoVueltas++;
        }

        //Cuando tengan 4 vueltas mínimo
        if (minimoVueltas == this.getAvatares().size()) {
            aumentoPrecio += 0.05;
        }
    }

    public Casilla getCasillaPosicion(int i) {
        return this.casillas.get(i);
    }

    public void imprimirTablero() {
        String linea0 = getCasillaPosicion(20).toString() + getCasillaPosicion(21).toString() + getCasillaPosicion(22).toString() +
                getCasillaPosicion(23).toString() + getCasillaPosicion(24).toString() + getCasillaPosicion(25).toString() +
                getCasillaPosicion(26).toString() + getCasillaPosicion(27).toString() + getCasillaPosicion(28).toString() +
                getCasillaPosicion(29).toString() + getCasillaPosicion(30).toString();

        String linea10 = getCasillaPosicion(10).toString() + getCasillaPosicion(9).toString() + getCasillaPosicion(8).toString() +
                getCasillaPosicion(7).toString() + getCasillaPosicion(6).toString() + getCasillaPosicion(5).toString() +
                getCasillaPosicion(4).toString() + getCasillaPosicion(3).toString() + getCasillaPosicion(2).toString() +
                getCasillaPosicion(1).toString() + getCasillaPosicion(0).toString();


        //Impresión del tablero
        consola.imprimirln(linea0);

        int i = 19;
        for (int j = 31; j <= 39; j++) {
            if (i >= 11) {
                if (i != 15) {
                    consola.imprimirln(getCasillaPosicion(i).toString() +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " +
                            getCasillaPosicion(j).toString());
                } else {
                    char c = 0x2665;
                    String s = String.valueOf(c);
                    consola.imprimirln(getCasillaPosicion(i).toString() + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + s +
                            "\t\tU\t\t\tS\t\t\tC\t\t\tP\t\t\tO\t\t\tO\t\t\tL\t\t\tY\t\t" + s + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " +
                            getCasillaPosicion(j).toString());
                }
                i--;
            }
        }

        consola.imprimirln(linea10);
    }

    //Listar jugadores
    public void listarJugadores() {
        for (int i = 1; i < this.getJugadores().size(); i++)
            this.getJugadores().get(i).describir();
    }

    //Listar avatares
    public void listarAvatares() {
        for (int i = 1; i < this.getAvatares().size(); i++)
            this.getAvatares().get(i).describir();
    }

    //Listar propiedades en venta
    public void listarPropiedadesVenta() {
        for (int i = 1; i < this.getCasillas().size(); i++) {
            if(this.casillas.get(i) instanceof Solar) {
                Solar solar = (Solar) this.casillas.get(i);
                if (solar.getPropietario().isBanca()) {
                    this.casillas.get(i).describir();
                }
            }
        }
    }

    public void listarEdificios() {
        int count = 0;
        for (int i = 1; i < this.casillas.size(); i++) {
            if(this.casillas.get(i) instanceof Solar) {
                Solar solar = (Solar) this.casillas.get(i);
                if (solar.tenerEdificios()) {
                    for (int j = 0; j < solar.getEdificios().size(); j++) {
                        solar.getEdificios().get(j).describir();
                    }
                }
            }
        }
        if (count == 0) consola.imprimirln(consola.ROJO + "No hay edificios construidos en todo el tablero." + consola.RESET);
    }

    public void listarEdificiosGrupo(String color) throws ExcepcionPropiedad {
        ArrayList<Solar> buscado = buscarGrupo(color);

        for (int i = 0; i < buscado.size(); i++) {
            if (buscado.get(i).tenerEdificios()) {
                for (int j = 0; j < buscado.get(i).getEdificios().size(); j++) {
                    buscado.get(i).getEdificios().get(j).describirGrupo();
                }
            } else consola.imprimirln(consola.ROJO + "No hay edificios en " + buscado.get(i).getNombre() + "." + consola.RESET);
        }
    }

    public ArrayList<Solar> buscarGrupo(String color) {
        ArrayList<Solar> buscado = new ArrayList<>();
        int i = 0;

        if (grupo.getGRIS().get(i).getColor().compareToIgnoreCase(color) == 0) buscado = grupo.getGRIS();
        else if (grupo.getAZUL().get(i).getColor().compareToIgnoreCase(color) == 0) buscado = grupo.getAZUL();
        else if (grupo.getROSA().get(i).getColor().compareToIgnoreCase(color) == 0) buscado = grupo.getROSA();
        else if (grupo.getNARANJA().get(i).getColor().compareToIgnoreCase(color) == 0) buscado = grupo.getNARANJA();
        else if (grupo.getROJO().get(i).getColor().compareToIgnoreCase(color) == 0) buscado = grupo.getROJO();
        else if (grupo.getAMARILLO().get(i).getColor().compareToIgnoreCase(color) == 0) buscado = grupo.getAMARILLO();
        else if (grupo.getVERDE().get(i).getColor().compareToIgnoreCase(color) == 0) buscado = grupo.getVERDE();
        else if (grupo.getCOBALTO().get(i).getColor().compareToIgnoreCase(color) == 0) buscado = grupo.getCOBALTO();

        return buscado;
    }

    //Para saber el jugador/a actual
    public Jugador getJugadorActual() {
        return this.getJugadores().get(turnoActual);
    }

    //Comprobar la existencia de un ganador/a
    public boolean haberGanador() {
        int ganador = 0;

        for (Jugador jugador : this.getJugadores()) {
            if (!jugador.isBancarrota())
                ganador++;
        }

        if (ganador == 1) return true;
        else return false;
    }

    //Buscar al ganador
    public Jugador getGanador() {
        if (!this.haberGanador()) return null;

        for (Jugador jugador : this.getJugadores()) {
            if (!jugador.isBancarrota()) return jugador;
        }

        return null;
    }


    //Función para poner los precios iniciales de las casillas de solares y propiedad=banca
    public void setPreciosIniciales() {
        try {
            for (int i = 0; i < this.getCasillas().size(); i++) {
                if(this.casillas.get(i) instanceof Solar) {
                    Solar solar = (Solar) this.casillas.get(i);
                    switch (solar.getColor()) {
                        case "Marron":
                            solar.setValor_inicial(600000);
                            solar.setValor_actual(solar.getValor_inicial());
                            solar.setPropietario(this.jugadores.get(0));
                            break;
                        case "Azul":
                            Solar solar1 = (Solar) this.casillas.get(1);
                            solar.setValor_inicial((0.3 * (solar1.getValor_inicial() * 2) + (solar1.getValor_inicial()) * 2) / 3);
                            solar.setValor_actual(solar1.getValor_inicial());
                            solar.setPropietario(this.jugadores.get(0));
                            break;
                        case "Rosa":
                            Solar solar9 = (Solar) this.casillas.get(9);
                            solar.setValor_inicial((0.3 * (solar9.getValor_inicial() * 3) + (solar9.getValor_inicial()) * 3) / 3);
                            solar.setValor_actual(solar9.getValor_inicial());
                            solar.setPropietario(this.jugadores.get(0));
                            break;
                        case "Naranja":
                            Solar solar11 = (Solar) this.casillas.get(11);
                            solar.setValor_inicial((0.3 * (solar11.getValor_inicial() * 3) + (solar11.getValor_inicial()) * 3) / 3);
                            solar.setValor_actual(solar.getValor_inicial());
                            solar.setPropietario(this.jugadores.get(0));
                            break;
                        case "Rojo":
                            Solar solar19 = (Solar) this.casillas.get(19);
                            solar.setValor_inicial((0.3 * (solar19.getValor_inicial() * 3) + (solar19.getValor_inicial()) * 3) / 3);
                            solar.setValor_actual(solar.getValor_inicial());
                            solar.setPropietario(this.jugadores.get(0));
                            break;
                        case "Amarillo":
                            Solar solar21 = (Solar) this.casillas.get(21);
                            solar.setValor_inicial((0.3 * (solar21.getValor_inicial() * 3) + (solar21.getValor_inicial()) * 3) / 3);
                            solar.setValor_actual(solar.getValor_inicial());
                            solar.setPropietario(this.jugadores.get(0));
                            break;
                        case "Verde":
                            Solar solar26 = (Solar) this.casillas.get(26);
                            solar.setValor_inicial((0.3 * (solar26.getValor_inicial() * 3) + (solar26.getValor_inicial()) * 3) / 3);
                            solar.setValor_actual(solar.getValor_inicial());
                            solar.setPropietario(this.jugadores.get(0));
                            break;
                        case "Cobalto":
                            Solar solar31 = (Solar) this.casillas.get(31);
                            solar.setValor_inicial((0.3 * (solar31.getValor_inicial() * 3) + (solar31.getValor_inicial()) * 3) / 2);
                            solar.setValor_actual(solar.getValor_inicial());
                            solar.setPropietario(this.jugadores.get(0));
                            break;
                        default:
                            break;
                    }
                }

                if(this.casillas.get(i) instanceof Servicio){
                    Servicio servicio = (Servicio) this.casillas.get(i);
                    servicio.setValor_inicial(this.getCobro_salida());
                    servicio.setValor_actual(this.getCobro_salida());
                    servicio.setPropietario(this.jugadores.get(0));
                } else if(this.casillas.get(i) instanceof Transporte){
                    Transporte transporte = (Transporte) this.casillas.get(i);
                    transporte.setValor_inicial(this.getCobro_salida());
                    transporte.setValor_actual(this.getCobro_salida());
                    transporte.setPropietario(this.jugadores.get(0));
                }

                //Ponemos a cero las vecesPasadas
                for (int v = 0; v < this.casillas.size(); v++) {
                    for (int a = 0; a < this.avatares.size(); a++) {
                        if (v == 0) this.avatares.get(a).getVecesPasadas().add(1);
                        else this.avatares.get(a).getVecesPasadas().add(0);
                    }
                }
            }
        } catch (ExcepcionJuego fallo) {
            consola.imprimirln(consola.ROJO + fallo.getMessage() + consola.RESET);
        }
    }


    //Establecer el cobro de salida inicial y la fortuna inicial
    public void setCobros() {
        double sumaTotal = 0;
        for (int i = 0; i < 40; i++) {
            if (this.casillas.get(i) instanceof Solar) {
                Solar solar = (Solar) this.casillas.get(i);
                sumaTotal += solar.getValor_inicial();
            }
        }

        //Hacemos la media
        this.setCobro_salida(sumaTotal / 22.0);

        //Ponemos la fortuna inicial
        for (int j = 1; j < this.getJugadores().size(); j++) {
            this.getJugadores().get(j).setFortuna(sumaTotal / 3.0);
        }

        //Establecemos el precio inicial de estas casillas, que dependen del cobro de la salida
        for (int i = 0; i < this.getCasillas().size(); i++) {
            switch (i) {
                case 5:
                case 12:
                case 15:
                case 25:
                case 28:
                case 35:
                    try {
                        //Actualizamos el cobro de salida de Transporte, Servicios e Impuestos
                        Casilla casilla;
                        for (int k = 0; k < 40; k++) {
                            casilla = this.getCasillaPosicion(k);
                            if(this.getCasillaPosicion(k) instanceof Transporte){
                                Transporte transporte = (Transporte) casilla;
                                transporte.setValor_inicial(cobro_salida);
                                transporte.setValor_actual(transporte.getValor_inicial());
                            }
                            else if(this.getCasillaPosicion(k) instanceof Servicio){
                                Servicio servicio = (Servicio) casilla;
                                servicio.setValor_inicial(0.75 * cobro_salida);
                                servicio.setValor_actual(servicio.getValor_inicial());
                            }
                            else if(this.getCasillaPosicion(k) instanceof Impuesto){
                                if(k == 4){
                                    Impuesto impuesto4 = (Impuesto) casilla;
                                    impuesto4.setValor_inicial(0.5 * cobro_salida);
                                    impuesto4.setValor_actual(impuesto4.getValor_inicial());
                                }
                                else if(k == 38){
                                    Impuesto impuesto38 = (Impuesto) casilla;
                                    impuesto38.setValor_inicial(cobro_salida);
                                    impuesto38.setValor_actual(impuesto38.getValor_inicial());
                                }
                            }
                        }
                    } catch (ExcepcionJuego fallo) {
                        consola.imprimirln(consola.ROJO + fallo.getMessage() + consola.RESET);
                    }
                    break;
            }
        }
    }




}
