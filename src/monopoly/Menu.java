//© 2019 Alberto Ruibal Ojea && Lara Padrón Cousillas

package monopoly;

import Excepciones.ExcepcionJuego;

import java.util.Scanner;

import static monopoly.Juego.consola;

public class Menu {
    private Juego juego;

    public Menu() {
        
        /*BufferedReader buffRead = null;
        try {
            //String directorio = "C:\\Users\\bertl\\Dropbox\\ETSEPOOly\\ETSEPOOly\\";
            //FileReader fileRead = new FileReader(directorio + "comandos.txt");
            //buffRead = new BufferedReader(fileRead);
        } catch (FileNotFoundException notFound) {
            consola.imprimir(notFound.getMessage());
            System.exit(0);
        }*/
        
        this.juego = new Juego();
        this.juego.iniciarPartida();
        //this.funcionFantasma();

        
        //Comenzamos el juego
        int turno = 1;
        while (!this.getJuego().getTablero().haberGanador()) {
            //La Banca no tira los dados
            if (turno == this.getJuego().getNumJugadores() + 1) turno = 1;
            this.getJuego().getTablero().setTurnoActual(turno);
            this.getJuego().getResultado().setPoderTirar(true);

            String orden = "";
            String[] partes;

            do {
                consola.imprimirln("\n\n" + this.getJuego().getTablero().getJugadores().get(this.getJuego().getTablero().getTurnoActual()).getNombre() + " tiene el turno: ");
                /*try {
                    orden = buffRead.readLine();
                } catch (IOException io) {
                    consola.imprimirln(io.getMessage());
                }*/
                orden = consola.leer("¿Qué quieres hacer? (introduce ayuda para ver los comandos disponibles): ");
                if (orden == null) orden = "finalizar";
                partes = orden.split(" ");

                switch (partes[0]) {
                    case "lanzar":
                        if (partes.length == 2) {
                            if (partes[1].equalsIgnoreCase("dados"))
                                this.getJuego().tirar();
                        } else consola.imprimirln(consola.ROJO + "No has introducido el comando correctamente." + consola.RESET);
                        break;
                    case "ver":
                        if (partes.length == 2) {
                            if (partes[1].equalsIgnoreCase("tablero"))
                                consola.imprimirln("\n\n----------");
                            this.getJuego().getTablero().imprimirTablero();
                            consola.imprimirln("----------");
                        }
                        break;
                    case "jugador":
                    case "jugadora":
                        if (partes.length == 2) {
                            if (partes[1].equalsIgnoreCase("actual"))
                                consola.imprimirln(this.getJuego().getTablero().getJugadores().get(this.getJuego().getTablero().getTurnoActual()).getNombre() + " tiene el turno.");
                        }
                        break;
                    case "acabar":
                        if (partes.length == 2) {
                            if (partes[1].equalsIgnoreCase("turno"))
                                turno++;
                        }
                        break;
                    case "salir":
                        if (partes.length == 2) {
                            if (partes[1].equalsIgnoreCase("carcel"))
                                this.getJuego().getTablero().getAvatares().get(this.getJuego().getTablero().getTurnoActual()).salirCarcel(this.getJuego().getTablero());
                        }
                        break;
                    case "describir":
                        if (partes.length >= 2) {
                            String tipo = partes[1];
                            if (partes.length == 2)
                                this.getJuego().describir(tipo);
                            if (partes.length == 3) {
                                String nombre = partes[2];
                                this.getJuego().describir(tipo, nombre);
                            }
                        } else consola.imprimirln(consola.ROJO + "No has introducido el comando correctamente." + consola.RESET);
                        break;
                    case "listar":
                        if (partes.length >= 2) {
                            String tipoL = partes[1];
                            if (partes.length == 3) {
                                String tipoC = partes[2];
                                this.getJuego().listar(tipoL, tipoC);
                            } else this.getJuego().listar(tipoL);
                        } else consola.imprimirln(consola.ROJO + "No has introducido el comando correctamente." + consola.RESET);
                        break;
                    case "comprar":
                        this.getJuego().comprar();
                        break;
                    case "vender":
                        if (partes.length == 4) {
                            String tipoV = partes[1];
                            String nombreV = partes[2];
                            int numeroV = Integer.parseInt(partes[3]);
                            this.getJuego().vender(tipoV, nombreV, numeroV);
                        }
                        break;
                    case "hipotecar":
                        if (partes.length == 2) {
                            String nombreH = partes[1];
                            this.getJuego().hipotecar(nombreH);
                        } else consola.imprimirln(consola.ROJO + "No has introducido el comando correctamente." + consola.RESET);
                        break;
                    case "deshipotecar":
                        if (partes.length == 2) {
                            String nombreDH = partes[1];
                            this.getJuego().deshipotecar(nombreDH);
                        } else consola.imprimirln(consola.ROJO + "No has introducido el comando correctamente." + consola.RESET);
                        break;
                    case "edificar":
                        if (partes.length == 2) {
                            String tipoE = partes[1];
                            this.getJuego().construir(tipoE);
                        } else consola.imprimirln(consola.ROJO + "No has introducido el comando correctamente." + consola.RESET);
                        break;
                    case "estadisticas":
                        if (partes.length == 2) {
                            String nombreE1 = partes[1];
                            this.getJuego().estadisticasJugador(nombreE1);
                        } else consola.imprimirln(consola.ROJO + "No has introducido el comando correctamente." + consola.RESET);
                        break;
                    case "ayuda":
                        this.getJuego().ayuda();
                        break;
                    case "stop":
                        new Scanner(System.in).nextLine();
                        break;
                    case "finalizar":
                        consola.imprimirln("\nPartida finalizada a petición del jugador/a");
                        System.exit(0);
                        break;
                    case "dados2":
                        if(partes.length == 2) {
                            this.getJuego().getTablero().getJugadorActual().getAvatar().moverAvatar(this.getJuego().getTablero(), Integer.parseInt(partes[1]));
                            this.getJuego().pagar(Integer.parseInt(partes[1]));
                            try {
                                this.getJuego().caerSuerteComunidad();
                            } catch (ExcepcionJuego fallo) {
                                consola.imprimirln(consola.ROJO + fallo.getMessage() + consola.RESET);
                            }
                        }
                        break;
                    default:
                        consola.imprimirln(consola.ROJO + "OPCIÓN INCORRECTA." + consola.RESET);
                        break;
                }
            } while (orden.compareToIgnoreCase("acabar turno") != 0);
        }
    }

    //Getters y setters


    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }
}