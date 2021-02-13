//© 2019 Alberto Ruibal Ojea && Lara Padrón Cousillas

package Casillas;

import Excepciones.ExcepcionPropiedad;
import Jugadores.Avatar;
import monopoly.Tablero;

import java.util.Random;
import java.util.Scanner;

import static monopoly.Juego.consola;

public final class Suerte extends Cartas{

    public Suerte(String texto){
        super(texto);
    }

    //Métodos
    //Elegir una carta de suerte
    public void accion(Tablero tab, Integer lectura)  throws ExcepcionPropiedad {
        Random r = new Random();
        int n = r.nextInt(lectura % 5);

        switch (n) {
            case 0:
                Avatar avatar = tab.getJugadorActual().getAvatar();
                int posicion = avatar.getPosicion();

                //Movemos el Avatar a Aeropuerto
                consola.imprimirln("Acción: Ve a Lavacolla y coge un avión. Si pasas por la casilla de salida, acuérdate de cobrar.");
                if (tab.getCasillas().get(posicion).estaAvatar(avatar))
                    tab.getCasillas().get(posicion).getAvatares().remove(avatar);

                Casilla casilla = tab.getCasillas().get(5);
                casilla.getAvatares().add(avatar);
                tab.getJugadorActual().getAvatar().setCasilla(casilla);

                if ((avatar.getPosicion() > 0 && (avatar.getPosicion() < posicion))) {
                    avatar.getJugador().setFortuna(avatar.getJugador().getFortuna() + tab.getCobro_salida());
                    avatar.getJugador().setPasarPorCasillaSalida(avatar.getJugador().getPasarPorCasillaSalida() + tab.getCobro_salida());
                }
                break;
            case 1:
                Avatar avatar1 = tab.getJugadorActual().getAvatar();
                int posicion1 = avatar1.getPosicion();

                //Movemos el Avatar a Veterinaria
                consola.imprimirln("Acción: Decides hacerle una visita a tu amiga de Veterinaria. Avanzas hasta Veterinaria.");
                if (tab.getCasillas().get(posicion1).estaAvatar(avatar1))
                    tab.getCasillas().get(posicion1).getAvatares().remove(avatar1);

                Casilla casilla1 = tab.getCasillas().get(9);
                casilla1.getAvatares().add(avatar1);
                tab.getJugadorActual().getAvatar().setCasilla(casilla1);

                if ((avatar1.getPosicion() > 0 && (avatar1.getPosicion() < posicion1))) {
                    avatar1.getJugador().setFortuna(avatar1.getJugador().getFortuna() + tab.getCobro_salida());
                    avatar1.getJugador().setPasarPorCasillaSalida(avatar1.getJugador().getPasarPorCasillaSalida() + tab.getCobro_salida());
                }
                break;
            case 2:
                //Cobrar 500K
                consola.imprimirln("Acción: Vendes tus apuntes en una página web de la carrera. Cobra 500K.");
                tab.getJugadorActual().setFortuna(tab.getJugadorActual().getFortuna() + 500000);
                tab.getJugadorActual().setPremiosInversionesOBote(tab.getJugadorActual().getPremiosInversionesOBote() + 500000);
                break;
            case 3:
                Avatar avatar3 = tab.getJugadorActual().getAvatar();
                int posicion3 = avatar3.getPosicion();

                //Movemos el Avatar a Políticas
                consola.imprimirln("Acción: Ve a Políticas. Si pasas por la casilla de salida, acuérdate de cobrar.");
                if (tab.getCasillas().get(posicion3).estaAvatar(avatar3))
                    tab.getCasillas().get(posicion3).getAvatares().remove(avatar3);

                Casilla casilla3 = tab.getCasillas().get(11);
                casilla3.getAvatares().add(avatar3);
                tab.getJugadorActual().getAvatar().setCasilla(casilla3);

                if ((avatar3.getPosicion() > 0 && (avatar3.getPosicion() < posicion3))) {
                    avatar3.getJugador().setFortuna(avatar3.getJugador().getFortuna() + tab.getCobro_salida());
                    avatar3.getJugador().setPasarPorCasillaSalida(avatar3.getJugador().getPasarPorCasillaSalida() + tab.getCobro_salida());
                }
                break;
            case 4:
                //Pagar 1,5M
                consola.imprimirln("Acción: Paga 1,5M por la matrícula de la cuarta convocatoria de álgebra");
                if (tab.getJugadorActual().getFortuna() >= 1500000)
                    tab.getJugadorActual().setFortuna(tab.getJugadorActual().getFortuna() - 1500000);
                else {
                    while (tab.getJugadorActual().getFortuna() < 1500000) {
                        consola.imprimirln("Tienes que hipotecar propiedades porque no tienes suficiente fortuna. ¿Qué propiedad hipotecas?");
                        Scanner scanner = new Scanner(System.in);
                        String nombre = scanner.nextLine();
                        Casilla casilla4;

                        for (int i = 0; i < 40; i++) {
                            casilla4 = tab.getCasillaPosicion(i);
                            if (casilla4.getNombre().compareToIgnoreCase(nombre) == 0) {
                                if(casilla4 instanceof Solar){
                                    Solar solar4 = (Solar) casilla4;
                                    solar4.hipotecar(tab.getJugadorActual());
                                }
                                else if(casilla4 instanceof Transporte){
                                    Transporte transporte4 = (Transporte) casilla4;
                                    transporte4.hipotecar(tab.getJugadorActual());
                                }
                                else if(casilla4 instanceof Servicio){
                                    Servicio servicio4 = (Servicio) casilla4;
                                    servicio4.hipotecar(tab.getJugadorActual());
                                }
                            }
                        }
                    }
                    tab.getJugadorActual().setFortuna(tab.getJugadorActual().getFortuna() - 1500000);
                }
                tab.getJugadorActual().setPagoAlquileres(tab.getJugadorActual().getPagoAlquileres() + 1500000);
                break;
            case 5:
                //Pagar 150K
                consola.imprimirln("Acción: Te abren expediente por plagio. Sobornas con 150K.");
                if (tab.getJugadorActual().getFortuna() >= 150000)
                    tab.getJugadorActual().setFortuna(tab.getJugadorActual().getFortuna() - 150000);
                else {
                    while (tab.getJugadorActual().getFortuna() < 150000) {
                        consola.imprimirln("Tienes que hipotecar propiedades porque no tienes suficiente fortuna. ¿Qué propiedad hipotecas?");
                        Scanner scanner = new Scanner(System.in);
                        String nombre5 = scanner.nextLine();
                        Casilla casilla5;

                        for (int i = 0; i < 40; i++) {
                            casilla5 = tab.getCasillaPosicion(i);
                            if (casilla5.getNombre().compareToIgnoreCase(nombre5) == 0) {
                                if (casilla5 instanceof Solar) {
                                    Solar solar5 = (Solar) casilla5;
                                    solar5.hipotecar(tab.getJugadorActual());
                                } else if (casilla5 instanceof Transporte) {
                                    Transporte transporte5 = (Transporte) casilla5;
                                    transporte5.hipotecar(tab.getJugadorActual());
                                } else if (casilla5 instanceof Servicio) {
                                    Servicio servicio5 = (Servicio) casilla5;
                                    servicio5.hipotecar(tab.getJugadorActual());
                                }
                            }
                        }
                    }
                    tab.getJugadorActual().setFortuna(tab.getJugadorActual().getFortuna() - 150000);
                }
                tab.getJugadorActual().setPagoAlquileres(tab.getJugadorActual().getPagoAlquileres() + 150000);
                break;
        }
    }
}
