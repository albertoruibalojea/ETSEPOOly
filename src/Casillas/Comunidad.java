//© 2019 Alberto Ruibal Ojea && Lara Padrón Cousillas

package Casillas;

import Jugadores.Avatar;
import monopoly.Tablero;
import java.util.Random;
import java.util.Scanner;
import Excepciones.ExcepcionPropiedad;
import static monopoly.Juego.consola;

public final class Comunidad extends Cartas{

    public Comunidad(String texto){
        super(texto);
    }


    //Métodos
    //Elegir una carta de comunidad
    public void accion(Tablero tab, Integer lectura) throws ExcepcionPropiedad{
        Random r = new Random();
        int n = r.nextInt(lectura % 5);

        switch (n) {
            case 0:
                //Pagar 500K
                consola.imprimirln("Acción: Paga 500K por un fin de semana de relax de tantas entregas (ejem ejem).");
                if (tab.getJugadorActual().getFortuna() >= 500000)
                    tab.getJugadorActual().setFortuna(tab.getJugadorActual().getFortuna() - 500000);
                else {
                    while (tab.getJugadorActual().getFortuna() < 500000) {
                        consola.imprimirln("Tienes que hipotecar propiedades porque no tienes suficiente fortuna. ¿Qué propiedad hipotecas?");
                        Scanner scanner = new Scanner(System.in);
                        String nombre = scanner.nextLine();
                        Casilla casilla0;

                        for (int i = 0; i < 40; i++) {
                            casilla0 = tab.getCasillaPosicion(i);
                            if (casilla0.getNombre().compareToIgnoreCase(nombre) == 0)
                                if (casilla0 instanceof Solar) {
                                    Solar solar0 = (Solar) casilla0;
                                    solar0.hipotecar(tab.getJugadorActual());
                                } else if (casilla0 instanceof Transporte) {
                                    Transporte transporte0 = (Transporte) casilla0;
                                    transporte0.hipotecar(tab.getJugadorActual());
                                } else if (casilla0 instanceof Servicio) {
                                    Servicio servicio0 = (Servicio) casilla0;
                                    servicio0.hipotecar(tab.getJugadorActual());
                                }
                        }
                    }

                    tab.getJugadorActual().setFortuna(tab.getJugadorActual().getFortuna() - 500000);
                }
                tab.getJugadorActual().setPagoAlquileres(tab.getJugadorActual().getPagoAlquileres() + 500000);
                break;
            case 1:
                Avatar avatar1 = tab.getJugadorActual().getAvatar();
                int posicion1 = avatar1.getPosicion();

                //Movemos el Avatar a salida
                consola.imprimirln("Acción: Colócate en la casilla de salida. Cobra 2M.");
                if (tab.getCasillas().get(posicion1).estaAvatar(avatar1))
                    tab.getCasillas().get(posicion1).getAvatares().remove(avatar1);
                Casilla casilla1 = tab.getCasillas().get(0);
                casilla1.getAvatares().add(avatar1);

                avatar1.getJugador().setFortuna(avatar1.getJugador().getFortuna() + tab.getCobro_salida());
                avatar1.getJugador().setPasarPorCasillaSalida(avatar1.getJugador().getPasarPorCasillaSalida() + tab.getCobro_salida());
                break;
            case 2:
                //Cobrar 2M
                consola.imprimirln("Acción: Eduroam obtiene beneficios. Recibe 2M.");
                tab.getJugadorActual().setFortuna(tab.getJugadorActual().getFortuna() + 2000000);
                tab.getJugadorActual().setCobroAlquileres(tab.getJugadorActual().getCobroAlquileres() + 2000000);
                break;
            case 3:
                //Pagar 1M
                consola.imprimirln("Acción: Paga 1M por invitar a todos tus amigos al prefinde de informática.");
                if (tab.getJugadorActual().getFortuna() >= 1000000)
                    tab.getJugadorActual().setFortuna(tab.getJugadorActual().getFortuna() - 1000000);
                else {
                    while (tab.getJugadorActual().getFortuna() < 1500000) {
                        consola.imprimirln("Tienes que hipotecar propiedades porque no tienes suficiente fortuna. ¿Qué propiedad hipotecas?");
                        Scanner scanner = new Scanner(System.in);
                        String nombre = scanner.nextLine();
                        Casilla casilla4;

                        for (int i = 0; i < 40; i++) {
                            casilla4 = tab.getCasillaPosicion(i);
                            if (casilla4.getNombre().compareToIgnoreCase(nombre) == 0){
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
            case 4:
                //Cobrar 500K
                consola.imprimirln("Acción: Devolución de la UXA. Cobra 500K.");
                tab.getJugadorActual().setFortuna(tab.getJugadorActual().getFortuna() + 500000);
                tab.getJugadorActual().setPremiosInversionesOBote(tab.getJugadorActual().getPremiosInversionesOBote() + 500000);
                break;
            case 5:
                Avatar avatar5 = tab.getJugadorActual().getAvatar();
                int posicion5 = avatar5.getPosicion();

                //Movemos el Avatar a Vigo
                consola.imprimirln("Acción: Vas a la biblioteca de Historia. Si pasas por la casilla de salida, acuérdate de cobrar.");
                if (tab.getCasillas().get(posicion5).estaAvatar(avatar5))
                    tab.getCasillas().get(posicion5).getAvatares().remove(avatar5);

                Casilla casilla5 = tab.getCasillas().get(23);
                casilla5.getAvatares().add(avatar5);
                tab.getJugadorActual().getAvatar().setCasilla(casilla5);

                if ((avatar5.getPosicion() > 0 && (avatar5.getPosicion() < posicion5))) {
                    avatar5.getJugador().setFortuna(avatar5.getJugador().getFortuna() + tab.getCobro_salida());
                    avatar5.getJugador().setPasarPorCasillaSalida(avatar5.getJugador().getPasarPorCasillaSalida() + tab.getCobro_salida());
                }
                break;
        }
    }

}
