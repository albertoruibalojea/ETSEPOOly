//© 2019 Alberto Ruibal Ojea && Lara Padrón Cousillas

package Jugadores;

import Casillas.Casilla;
import Casillas.Solar;

import java.util.ArrayList;

import static monopoly.Juego.consola;

public class Jugador {
    private Avatar avatar;
    private String nombre;
    private double fortuna;
    private boolean bancarrota;
    private boolean banca;
    private ArrayList<Casilla> propiedades;

    private double dineroInvertido;
    private double pagoAlquileres;
    private double cobroAlquileres;
    private double pasarPorCasillaSalida;
    private double premiosInversionesOBote;
    private int vecesEnLaCarcel;


    public Jugador(String nombre, Avatar avatar) {
        this.avatar = avatar;
        this.banca = false;
        this.bancarrota = false;
        this.fortuna = 0;
        this.nombre = nombre;
        this.propiedades = new ArrayList<>();
    }

    public Jugador(Avatar avatar, String nombre, double fortuna, boolean banca) {
        this.avatar = avatar;
        this.banca = banca;
        this.bancarrota = false;
        this.fortuna = fortuna;
        this.nombre = nombre;
        this.propiedades = new ArrayList<>();
    }


    //Getters y setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Casilla> getPropiedades() {
        return propiedades;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public boolean isBanca() {
        return banca;
    }

    public boolean isBancarrota() {
        return bancarrota;
    }

    public double getFortuna() {
        return fortuna;
    }

    public double getDineroInvertido() {
        return dineroInvertido;
    }

    public double getCobroAlquileres() {
        return cobroAlquileres;
    }

    public double getPagoAlquileres() {
        return pagoAlquileres;
    }

    public double getPasarPorCasillaSalida() {
        return pasarPorCasillaSalida;
    }

    public double getPremiosInversionesOBote() {
        return premiosInversionesOBote;
    }

    public int getVecesEnLaCarcel() {
        return vecesEnLaCarcel;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public void setBanca(boolean banca) {
        this.banca = banca;
    }

    public void setBancarrota(boolean bancarrota) {
        this.bancarrota = bancarrota;
    }

    public void setFortuna(double fortuna) {
        this.fortuna = Math.round(fortuna);
    }

    public void setPropiedades(ArrayList<Casilla> propiedades) {
        this.propiedades = propiedades;
    }

    public void setCobroAlquileres(double cobroAlquileres) {
        this.cobroAlquileres = cobroAlquileres;
    }

    public void setDineroInvertido(double dineroInvertido) {
        this.dineroInvertido = dineroInvertido;
    }

    public void setPagoAlquileres(double pagoAlquileres) {
        this.pagoAlquileres = pagoAlquileres;
    }

    public void setPasarPorCasillaSalida(double pasarPorCasillaSalida) {
        this.pasarPorCasillaSalida = pasarPorCasillaSalida;
    }

    public void setPremiosInversionesOBote(double premiosInversionesOBote) {
        this.premiosInversionesOBote = premiosInversionesOBote;
    }

    public void setVecesEnLaCarcel(int vecesEnLaCarcel) {
        this.vecesEnLaCarcel = vecesEnLaCarcel;
    }


    //Métodos
    //Permite describir a un jugador/a
    public void describir() {
        consola.imprimirln("\nJugador/a: " + this.getNombre());
        consola.imprimirln("Avatar: " + this.getAvatar().getId());
        consola.imprimirln("Fortuna: " + Math.round(this.getFortuna()));
        consola.imprimirln("Propiedades: " + this.getPropiedades());
        for (int i = 0; i < this.getPropiedades().size(); i++) {
            if (this.getPropiedades().get(i) instanceof Solar) {
                Solar solar = (Solar) this.getPropiedades().get(i);
                if (solar.tenerEdificios()) {
                    consola.imprimir("    Edificios: ");
                    for (int e = 0; e < solar.getEdificios().size(); e++) {
                        consola.imprimir(solar.getEdificios().get(e).getId());
                    }
                }
            }
        }
    }

        //Retirar los fondos de un jugador/a
        public void gastarDinero ( double cantidad){
            if (!isBancarrota()) {
                //Si puede permitirse este gasto
                if (this.getFortuna() >= cantidad) {
                    this.setFortuna(this.getFortuna() - cantidad);
                    consola.imprimirln("Fortuna actual: " + Math.round(this.getFortuna()));
                } else {
                    //Si no puede permitírselo, BANCARROTA
                    if (this.getPropiedades().size() > 0)
                        consola.imprimirln(consola.ROJO + "Debes hipotecar propiedades para seguir jugando." + consola.RESET);
                    else {
                        this.setBancarrota(true);
                        consola.imprimirln(consola.ROJO + "Has sido declarado en bancarrota. :(" + consola.RESET);
                    }
                }
            }
        }


        //Comprueba si el Jugador posee un grupo de color entero
        public boolean isPropietarioGrupo (String color){
            Solar solar;
            int c = 0;

            for (int i = 0; i < this.propiedades.size(); i++) {
                if(this.propiedades.get(i) instanceof Solar) {
                    solar = (Solar) this.propiedades.get(i);
                    switch (color) {
                        case "Gris":
                        case "Cobalto":
                            if (color.equals(solar.getColor())) {
                                c++;
                                if (c == 2) return true;
                            }
                            break;
                        case "Azul":
                        case "Rosa":
                        case "Naranja":
                        case "Rojo":
                        case "Amarillo":
                        case "Verde":
                            if (color.equals(solar.getColor())) {
                                c++;
                                if (c == 3) return true;
                            }
                            break;
                    }
                }
            }

            //Si llega hasta aquí se entiende que no se ha cumplido ninguna condición del switch
            return false;
        }
    }
