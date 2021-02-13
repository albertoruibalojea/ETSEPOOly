//© 2019 Alberto Ruibal Ojea && Lara Padrón Cousillas

package Casillas;

import java.util.Random;

public class Dados {
    private int dado1;
    private int dado2;
    private int dobles;
    private boolean poderTirar;

    public Dados(){
        this.dado1      = 0;
        this.dado2      = 0;
        this.dobles     = 0;
        this.poderTirar = true;
    }


    //Getters y setters

    public int getDado1() {
        return dado1;
    }

    public boolean isPoderTirar() {
        return poderTirar;
    }

    public int getDado2() {
        return dado2;
    }

    public int getDobles() {
        return dobles;
    }

    public void setDado1(int dado1) {
        this.dado1 = dado1;
    }

    public void setDado2(int dado2) {
        this.dado2 = dado2;
    }

    public void setDobles(int dobles) {
        this.dobles = dobles;
    }

    public void setPoderTirar(boolean poderTirar) {
        this.poderTirar = poderTirar;
    }


    //Métodos
    //Método para tirar dados
    public void tirarDados() {
        Random rand = new Random();
        this.setDado1(1 + rand.nextInt(6));
        this.setDado2(1 + rand.nextInt(6));

        if(this.getDado1() == this.getDado2()) this.setDobles(this.getDobles() + 1);
        else this.setPoderTirar(false);

        if(this.getDobles() >= 1 && this.getDobles() <= 2) {
            this.setDobles(this.getDobles() + 1);
            this.setPoderTirar(true);
        } else if(this.getDobles() == 3) this.setPoderTirar(false);
    }
}
