//© 2019 Alberto Ruibal Ojea && Lara Padrón Cousillas

package monopoly;

import Casillas.*;
import Excepciones.ExcepcionJuego;
import Jugadores.Avatar;
import Jugadores.Jugador;

import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class Juego implements Comando {
    public static ConsolaNormal consola;
    private Tablero tablero;
    private int numJugadores;
    private Dados resultado;

    public Juego() {
        consola = new ConsolaNormal();
        this.tablero = new Tablero();
        numJugadores = 0;
        resultado = new Dados();
    }

    //Getters
    public ConsolaNormal consola() {
        return consola;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public int getNumJugadores() {
        return numJugadores;
    }

    public Dados getResultado() {
        return resultado;
    }

    //Setters
    public void setConsola(ConsolaNormal consola) {
        this.consola = consola;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public void setNumJugadores(int numJugadores) {
        this.numJugadores = numJugadores;
    }

    public void setResultado(Dados resultado) {
        this.resultado = resultado;
    }


    //Métodos

    //Método para inicializar
    public void iniciarPartida() {
        //Preguntamos el número de jugadores
        Scanner scanner = new Scanner(System.in);
        while (numJugadores < 2 || numJugadores > 6) {
            consola.imprimirln("¡Bienvenidxs al USCpoly!");
            consola.imprimir("Comenzaremos introduciendo el número de jugadores (entre 2-6): ");
            numJugadores = scanner.nextInt();

            if (numJugadores < 2 || numJugadores > 6) {
                consola.imprimirln(consola.ROJO + "Woops, el número de jugadores introducido no es correcto ;(" + consola.RESET);
            }
        }

        //Aquí guardamos los nombres de los jugadores
        Scanner scanner1 = new Scanner(System.in);
        consola.imprimirln("¡Genial! Ahora introduciremos sus nombres.");

        String banca = "banca";
        Avatar ABanca = new Avatar("€", "banca");
        Jugador JBanca = new Jugador(banca, ABanca);
        JBanca.setBanca(true);
        this.tablero.getJugadores().add(0, JBanca);
        this.tablero.getAvatares().add(0, ABanca);
        this.tablero.getCasillas().get(0).getAvatares().add(0, ABanca);

        //Creamos el resto de jugadores
        for (int i = 1; i <= numJugadores; i++) {
            consola.imprimir("\nIntroduce el nombre del jugador " + i + ": ");
            String nombre = scanner1.nextLine();

            //Generamos un caracter ASCII de la A-Z para el ID
            Random r = new Random();
            String id = String.valueOf((char) (r.nextInt(26) + 'a')).toUpperCase();
            consola.imprimirln("ID del jugador: " + id);

            boolean ok = false;
            String tipo;
            do {
                consola.imprimir("Objeto identificador (coche, pelota, esfinge, sombrero): ");
                tipo = (scanner1.nextLine()).toLowerCase();

                if (tipo.equalsIgnoreCase("coche") || tipo.equalsIgnoreCase("pelota") ||
                        tipo.equalsIgnoreCase("esfinge") || tipo.equalsIgnoreCase("sombrero"))
                    ok = true;
            } while (!ok);

            Avatar avatar = new Avatar(id, tipo);
            Jugador jugador = new Jugador(nombre, avatar);
            avatar.setJugador(jugador);
            this.tablero.getJugadores().add(jugador);
            this.tablero.getAvatares().add(avatar);
            //set fortuna inicial

            //Ponemos a los avatares en la casilla inicial
            this.tablero.getAvatares().get(i).setCasilla(this.tablero.getCasillaPosicion(0));
            this.tablero.getCasillas().get(0).getAvatares().add(i, avatar);
        }

        this.tablero.setPreciosIniciales();
        this.tablero.setCobros();
    }

    /*public void funcionFantasma(){
            for (int i = 0; i < this.getTablero().getCasillas().size(); i++) {
                switch (this.getTablero().getCasillas().get(i).getColor()) {
                    case "Marron":
                    case "Azul":
                    case "Rosa":
                    case "Naranja":
                    case "Rojo":
                    case "Amarillo":
                    case "Verde":
                    case "Cobalto":
                        this.getTablero().getCasillas().get(i).setPropietario(this.getTablero().getJugadores().get(1));
                        break;
                    default:
                        break;
                }

                if (this.getTablero().getCasillas().get(i).getTipo().contentEquals("Especial") || this.getTablero().getCasillas().get(i).getTipo().contentEquals("Impuestos")
                        || this.getTablero().getCasillas().get(i).getTipo().contentEquals("Suerte") || this.getTablero().getCasillas().get(i).getTipo().contentEquals("Comunidad"))
                    this.getTablero().getCasillas().get(i).setPropietario(this.getTablero().getJugadores().get(1));
            }
    }*/

    //Para describir jugadores, avatares
    public void describir(String tipo, String nombre) {
        Jugador jugador = null;
        Avatar avatar = null;

        if (tipo.contains("jugador") || tipo.contains("jugadora")) {
            for (int j = 0; j < this.numJugadores; j++) {
                if (this.tablero.getJugadores().get(j).getNombre().contains(nombre)) {
                    jugador = this.tablero.getJugadores().get(j);
                    jugador.describir();
                }
            }
        } else if (tipo.contains("avatar")) {
            for (int a = 0; a < this.numJugadores; a++) {
                if (this.tablero.getAvatares().get(a).getId().equalsIgnoreCase(nombre)) {
                    avatar = this.tablero.getAvatares().get(a);
                    avatar.describir();
                }
            }
        }
    }

    //Para describir casillas
    public void describir(String nombre) {
        Casilla casilla = null;

        for (int c = 0; c < this.tablero.getCasillas().size(); c++) {
            if (this.tablero.getCasillas().get(c).getNombre().contains(nombre)) {
                casilla = this.tablero.getCasillas().get(c);
                casilla.describir();
            }
        }
    }

    //Listar jugadores, avatares o casillas en venta
    public void listar(String tipo) {

        if (tipo.equalsIgnoreCase("jugadores") || tipo.equalsIgnoreCase("jugadoras"))
            this.tablero.listarJugadores();
        else if (tipo.equalsIgnoreCase("avatares"))
            this.tablero.listarAvatares();
        else if (tipo.equalsIgnoreCase("disponibles"))
            this.tablero.listarPropiedadesVenta();
        else if (tipo.equalsIgnoreCase("edificios"))
            this.tablero.listarEdificios();
    }

    //Listar edificios construidos en un grupo
    public void listar(String tipo, String color) {
        if (tipo.equalsIgnoreCase("edificios"))
            try {
                this.tablero.listarEdificiosGrupo(color);
            } catch (ExcepcionJuego fallo) {
                this.consola().imprimirln(this.consola().ROJO + fallo.getMessage() + this.consola().RESET);
            }
    }

    //Comprar una casilla
    public void comprar() {
        Jugador jugador;
        Casilla casilla;

        jugador = this.tablero.getJugadorActual();
        casilla = this.tablero.getCasillaPosicion(jugador.getAvatar().getPosicion());

        //Compramos
        if (casilla instanceof Propiedad) {
            try {
                ((Propiedad) casilla).comprar(this.tablero);
            } catch (ExcepcionJuego fallo) {
                this.consola().imprimirln(this.consola().ROJO + fallo.getMessage() + this.consola().RESET);
            }
        } else consola.imprimirln( consola.ROJO + "No puedes comprar esta casilla." + consola.RESET);
    }

    //Vender edificios de una propiedad
    public void vender(String tipo, String nombre, int numero) {
        Casilla casilla;

        if (tipo.compareToIgnoreCase("casas") == 0) tipo = "casa";

        for (int i = 0; i < 40; i++) {
            casilla = this.tablero.getCasillas().get(i);
            if (casilla.getNombre().compareToIgnoreCase(nombre) == 0)
                ((Solar) casilla).venderEdificios(tipo, numero);
        }
    }

    public void construir(String tipoE) {
        Jugador jugador;
        Solar solar;
        int count = 0;
        String id = "";

        //Construir
        jugador = this.tablero.getJugadorActual();
        solar = (Solar) this.tablero.getCasillaPosicion(jugador.getAvatar().getPosicion());

        //Generamos su ID
        Iterator it = solar.getEdificios().iterator();
        while (it.hasNext()) {
            if (tipoE.equals(it.next())) {
                count++;
            } else id = tipoE + "-" + count + 1;
        }
        count++;
        id = tipoE + "-" + count;


        try {
            solar.ConstruirEdificio(this.tablero, tipoE, id);
            consola.imprimirln("Se ha edificado correctamente.");
        } catch (ExcepcionJuego fallo) {
            this.consola().imprimirln(this.consola().ROJO + fallo.getMessage() + this.consola().RESET);
            consola.imprimirln(consola.ROJO + "No se ha podido construir. Comprueba que: ");
            consola.imprimirln(" - Que el solar es de tu propiedad");
            consola.imprimirln(" - Que te encuentres en la casilla en la que quieres edificar");
            consola.imprimirln(" - Que hayas caído al menos dos veces en esta casilla");
            consola.imprimirln(" - Que no esté hipotecada");
            consola.imprimirln(" - Si lo anterior no ha funcionado...");
            consola.imprimirln("     + Quizás debas sustituir las casas por un hotel" + consola().RESET);
        }
    }

    public void hipotecar(String nombre) {
        Casilla casilla;

        for (int i = 0; i < 40; i++) {
            casilla = this.tablero.getCasillaPosicion(i);
            if (casilla.getNombre().compareToIgnoreCase(nombre) == 0) {
                try {
                    ((Propiedad) casilla).hipotecar(this.tablero.getJugadorActual());
                } catch (ExcepcionJuego fallo) {
                    this.consola().imprimirln(this.consola().ROJO + fallo.getMessage() + this.consola().RESET);
                }
            }
        }
    }

    public void deshipotecar(String nombre) {
        Casilla casilla;

        for (int i = 0; i < 40; i++) {
            casilla = this.tablero.getCasillaPosicion(i);
            if (casilla.getNombre().compareToIgnoreCase(nombre) == 0) {
                try {
                    ((Propiedad) casilla).deshipotecar(this.tablero.getJugadorActual());
                } catch (ExcepcionJuego fallo) {
                    this.consola().imprimirln(this.consola().ROJO + fallo.getMessage() + this.consola().RESET);
                }
            }
        }
    }

    //Tirar los dados
    public void tirar() {
        Avatar av = this.tablero.getJugadorActual().getAvatar();
        resultado.setDobles(0);
        if (!av.isCarcel()) {
            if (!resultado.isPoderTirar())
                this.consola().imprimirln(this.consola().ROJO + "No puedes volver a tirar los dados." + this.consola().RESET);
            else {
                this.resultado.tirarDados();

                if (resultado.getDobles() == 3) {
                    av.IrCarcel(this.tablero);
                } else {
                    int movimiento = resultado.getDado1() + resultado.getDado2();
                    this.consola().imprimirln("Has sacado un " + resultado.getDado1() + " y un " + resultado.getDado2() + ". Avanzas " + movimiento + " casillas.");
                    av.moverAvatar(this.tablero, movimiento);

                    this.pagar(movimiento);
                    try {
                        this.caerSuerteComunidad();
                    } catch (ExcepcionJuego fallo) {
                        this.consola().imprimirln(this.consola().ROJO + fallo.getMessage() + this.consola().RESET);
                    }

                    consola.imprimirln("\n\n----------");
                    this.tablero.imprimirTablero();
                    consola.imprimirln("\n----------");
                }
            }
        } else {
            //Comprobar si está en la cárcel para saber los turnos que le faltan para salir
            if (av.getCountCarcel() == 3) {
                consola.imprimirln("En tu próximo turno puedes salir.");
                double pago = this.tablero.getCobro_salida() * 0.25;
                this.tablero.getJugadorActual().setFortuna(this.tablero.getJugadorActual().getFortuna() - pago);

            } else {
                consola.imprimirln("Aun no puedes salir de la carcel.");
                av.setCountCarcel(av.getCountCarcel() + 1);
            }
        }
    }

    //Comprobar si cae en Suerte y Comunidad
    public void caerSuerteComunidad() throws ExcepcionJuego {
        Casilla casilla;
        Cartas carta = null;
        casilla = this.tablero.getCasillas().get(this.tablero.getJugadorActual().getAvatar().getPosicion());
        String lectura;
        Integer numero;


        if (casilla instanceof AccionSuerte) {
            do {
                lectura = this.consola.leer("Elige una carta de suerte del 1 al 10: ");
                numero = Integer.parseInt(lectura);
                if ((numero > 0) && (numero < 11)) {
                    carta = this.tablero.getCartas().get(numero);
                } else throw new ExcepcionJuego(consola.ROJO + "No se ha introducido un número válido" + consola.RESET);
            } while(carta instanceof Comunidad);

            try {
                carta.accion(this.tablero, numero);
            } catch(ExcepcionJuego fallo){
                this.consola().imprimirln(this.consola().ROJO + fallo.getMessage() + this.consola().RESET);
            }

        } else if (casilla instanceof AccionComunidad) {
            do {
                lectura = this.consola.leer("Elige una carta de comunidad del 1 al 10: ");
                numero = Integer.parseInt(lectura);
                if ((numero > 0) && (numero < 11)) {
                    carta = this.tablero.getCartas().get(numero);
                } else throw new ExcepcionJuego(consola.ROJO + "No se ha introducido un número válido" + consola.RESET);
            } while(carta instanceof Suerte);

            try {
                carta.accion(this.tablero, numero);
            } catch(ExcepcionJuego fallo){
                this.consola().imprimirln(this.consola().ROJO + fallo.getMessage() + this.consola().RESET);
            }
        }
    }

    //Para pagar al caer en una casilla
    public void pagar(int movimiento) {
        Casilla casilla;
        casilla = this.tablero.getCasillas().get(this.tablero.getJugadorActual().getAvatar().getPosicion());

        try {
            if (casilla instanceof Propiedad) ((Propiedad) casilla).pagar(this.tablero, movimiento);
            else if (casilla instanceof Impuesto) ((Impuesto) casilla).pagar(this.tablero);
        } catch(ExcepcionJuego fallo){
            this.consola().imprimirln(this.consola().ROJO + fallo.getMessage() + this.consola().RESET);
        }
    }

    public void estadisticasJugador(String nombre) {
        Jugador jugador = null;

        for (int j = 0; j < this.numJugadores; j++) {
            if (this.tablero.getJugadores().get(j).getNombre().contains(nombre))
                jugador = this.tablero.getJugadores().get(j);
        }

        consola.imprimirln("Dinero invertido: " + jugador.getDineroInvertido());
        consola.imprimirln("Pago de alquileres: " + jugador.getPagoAlquileres());
        consola.imprimirln("Cobro de alquileres: " + jugador.getCobroAlquileres());
        consola.imprimirln("Premios, inversiones o bote: " + jugador.getPremiosInversionesOBote());
        consola.imprimirln("Veces en la cárcel: " + jugador.getVecesEnLaCarcel());
    }

    //Para ver todos los comandos
    public void ayuda() {
        consola.imprimirln("\n-------------------------------USCPOOly HELP-------------------------------");
        consola.imprimirln("Acciones básicas.");
        consola.imprimirln("     acabar turno          -> acabar turno.");
        consola.imprimirln("     jugador/a actual      -> ver jugador/a actual.");
        consola.imprimirln("     lanzar dados          -> lanzar dados.");
        consola.imprimirln("     salir carcel          -> salir de la carcel.");
        consola.imprimirln("     ver tablero           -> ver tablero.");
        consola.imprimirln("     estadisticas J        -> mostrar estadísticas del jugador/a j");

        consola.imprimirln("Gestión de propiedades.");
        consola.imprimirln("     hipotecar p           -> hipotecar la propiedad p.");
        consola.imprimirln("     deshipotecar p        -> deshipotecar la propiedad p.");
        consola.imprimirln("     edificar tipo         -> construir un tipo de edificio en la casilla actual.");
        consola.imprimirln("                              (tipo = casa, hotel, piscina, pistaDeDeporte)");
        consola.imprimirln("     comprar               -> comprar casilla.");
        consola.imprimirln("     vender tipo Casilla n -> vender n tipos de edificios de la casilla.");
        consola.imprimirln("                              (tipo = casa, hotel, piscina, pistaDeDeporte)");

        consola.imprimirln("Descripciones.");
        consola.imprimirln("     describir avatar A    -> describir el avatar A.");
        consola.imprimirln("     describir C           -> describir la casilla C.");
        consola.imprimirln("     describir jugador/a J -> describir jugador/a J.");

        consola.imprimirln("Listar.");
        consola.imprimirln("     listar avatares       -> listar todos los avatares.");
        consola.imprimirln("     listar disponibles    -> listar las propiedades en venta.");
        consola.imprimirln("     listar jugadores/as   -> listar todos/as los/as jugadores/as.");
        consola.imprimirln("     listar edificios      -> listar los edificios construidos.");
        consola.imprimirln("     listar edificios g    -> listar edificios del grupo g.");


    }
}
