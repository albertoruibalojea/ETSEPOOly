//© 2019 Alberto Ruibal Ojea && Lara Padrón Cousillas

package monopoly;

import Excepciones.ExcepcionJuego;

public interface Comando {

    public void iniciarPartida();
    //public void funcionFantasma();
    public void describir(String tipo, String nombre);
    public void describir(String nombre);
    public void listar(String tipo);
    public void listar(String tipo, String color);
    public void comprar();
    public void vender(String tipo, String nombre, int numero);
    public void construir(String tipoE);
    public void hipotecar(String nombre);
    public void deshipotecar(String nombre);
    public void tirar();
    public void caerSuerteComunidad() throws ExcepcionJuego;
    public void pagar(int movimiento);
    public void estadisticasJugador(String nombre);
    public void ayuda();
}
