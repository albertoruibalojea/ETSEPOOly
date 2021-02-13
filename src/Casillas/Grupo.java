//© 2019 Alberto Ruibal Ojea && Lara Padrón Cousillas

package Casillas;

import monopoly.Tablero;

import java.util.ArrayList;

public class Grupo {
    private ArrayList<Solar> GRIS;
    private ArrayList<Solar> AZUL;
    private ArrayList<Solar> ROSA;
    private ArrayList<Solar> NARANJA;
    private ArrayList<Solar> ROJO;
    private ArrayList<Solar> AMARILLO;
    private ArrayList<Solar> VERDE;
    private ArrayList<Solar> COBALTO;


    public Grupo(Tablero tab) {
       this.GRIS = new ArrayList<>();
       this.AZUL = new ArrayList<>();
       this.ROSA = new ArrayList<>();
       this.NARANJA = new ArrayList<>();
       this.ROJO = new ArrayList<>();
       this.AMARILLO = new ArrayList<>();
       this.VERDE = new ArrayList<>();
       this.COBALTO = new ArrayList<>();

        for(int i=0; i<40; i++){
            switch (i){
                case 1:
                case 3:
                    GRIS.add((Solar)tab.getCasillaPosicion(i));
                    break;
                case 6:
                case 8:
                case 9:
                    AZUL.add((Solar)tab.getCasillaPosicion(i));
                    break;
                case 11:
                case 13:
                case 14:
                    ROSA.add((Solar)tab.getCasillaPosicion(i));
                    break;
                case 16:
                case 18:
                case 19:
                    NARANJA.add((Solar)tab.getCasillaPosicion(i));
                    break;
                case 21:
                case 23:
                case 24:
                    ROJO.add((Solar)tab.getCasillaPosicion(i));
                    break;
                case 26:
                case 27:
                case 29:
                    AMARILLO.add((Solar)tab.getCasillaPosicion(i));
                    break;
                case 31:
                case 32:
                case 34:
                    VERDE.add((Solar)tab.getCasillaPosicion(i));
                    break;
                case 37:
                case 39:
                    COBALTO.add((Solar)tab.getCasillaPosicion(i));
                    break;
            }
        }

    }


    //Getters
    public ArrayList<Solar> getGRIS() {
        return GRIS;
    }

    public ArrayList<Solar> getAZUL() {
        return AZUL;
    }

    public ArrayList<Solar> getROSA() {
        return ROSA;
    }

    public ArrayList<Solar> getNARANJA() {
        return NARANJA;
    }

    public ArrayList<Solar> getROJO() {
        return ROJO;
    }

    public ArrayList<Solar> getAMARILLO() {
        return AMARILLO;
    }

    public ArrayList<Solar> getVERDE() {
        return VERDE;
    }

    public ArrayList<Solar> getCOBALTO() {
        return COBALTO;
    }

    //Setters
    public void setAMARILLO(ArrayList<Solar> AMARILLO) {
        this.AMARILLO = AMARILLO;
    }

    public void setAZUL(ArrayList<Solar> AZUL) {
        this.AZUL = AZUL;
    }

    public void setCOBALTO(ArrayList<Solar> COBALTO) {
        this.COBALTO = COBALTO;
    }

    public void setGRIS(ArrayList<Solar> GRIS) {
        this.GRIS = GRIS;
    }

    public void setNARANJA(ArrayList<Solar> NARANJA) {
        this.NARANJA = NARANJA;
    }

    public void setROJO(ArrayList<Solar> ROJO) {
        this.ROJO = ROJO;
    }

    public void setROSA(ArrayList<Solar> ROSA) {
        this.ROSA = ROSA;
    }

    public void setVERDE(ArrayList<Solar> VERDE) {
        this.VERDE = VERDE;
    }
}
