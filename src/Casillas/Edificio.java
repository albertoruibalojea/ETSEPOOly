//© 2019 Alberto Ruibal Ojea && Lara Padrón Cousillas

package Casillas;

import java.util.Iterator;

import static monopoly.Juego.consola;

public abstract class Edificio {
    private String id;
    private Solar solar;
    private double precioCompra;

    public Edificio(double precioCompra) {
        this.precioCompra =  precioCompra;
    }

    //Getters
    public String getId() {
        return id;
    }

    public Casilla getSolar() {
        return solar;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    //Setters

    public void setId(String id) {
        this.id = id;
    }

    public void setSolar(Solar solar) {
        this.solar = solar;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }


    //Métodos
    public void describir() {
        consola.imprimirln("ID: " + this.id);
        consola.imprimirln("Propietarix: " + this.solar.getPropietario().getNombre());
        consola.imprimirln("Casilla: " + this.getSolar().getNombre());
        consola.imprimirln("Grupo: " + this.solar.getColor());
        if (this.solar.tenerEdificios())
            consola.imprimirln("Alquiler actual: " + this.solar.alquilerEdificios());
        else consola.imprimirln("Alquiler actual: " + this.solar.getAlquiler());
    }

    public void describirGrupo() {
        consola.imprimirln("Casilla: " + this.getSolar().getNombre());
        if (this.solar.tenerEdificios())
            consola.imprimirln("Alquiler actual: " + this.solar.alquilerEdificios());
        else consola.imprimirln("Alquiler actual: " + this.solar.getAlquiler());

        String casa = "casa", hoteles = "hotel", piscinas = "piscina", pistas = "pistasDeDeporte";
        int numCasas = 0, numHoteles = 0, numPiscinas = 0, numPistas = 0, num = 0;
        Iterator it = this.solar.getEdificios().iterator();
        while (it.hasNext()) {
            if (casa.equals(it.next())) numCasas++;
            if (hoteles.equals(it.next())) numHoteles++;
            if (piscinas.equals(it.next())) numPiscinas++;
            if (pistas.equals(it.next())) numPistas++;
        }

        if ((this.solar.getColor().contains("Azul")) && this.solar.getColor().contains("Cobalto")) num = 2;
        else num = 3;

        if (numHoteles < num) consola.imprimirln("Aun puedes construir " + (num - numHoteles) + " hoteles.");
        else consola.imprimirln("No puedes construir más hoteles.");

        if (numHoteles <= num) {
            int var = ((num - numHoteles) * 4) + (num - numCasas);
            consola.imprimirln("Puedes construir " + var + " casas.");
        }

        if (numPiscinas < num) consola.imprimirln("Aun puedes construir " + (num - numPiscinas) + " piscinas.");
        else consola.imprimirln("No puedes construir más piscinas.");

        if (numPistas < num) consola.imprimirln("Aun puedes construir " + (num - numPistas) + " pistas de deporte.");
        else consola.imprimirln("No puedes construir más pistas de deporte.");
    }
}
