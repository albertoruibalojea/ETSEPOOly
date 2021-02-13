//© 2019 Alberto Ruibal Ojea && Lara Padrón Cousillas

package monopoly;

public interface Consola {
    //Colores del Tablero
    public final static String GRIS = "\u001b[37;1m";
    public final static String AZUL = "\u001b[36;1m";
    public final static String ROSA = "\u001b[35;1m";
    public final static String NARANJA = "\u001b[31;1m";
    public final static String ROJO = "\u001b[31m";
    public final static String AMARILLO = "\u001b[33;1m";
    public final static String VERDE = "\u001b[32;1m";
    public final static String COBALTO = "\u001b[34m";
    public final static String RESET = "\u001b[0m";

    //Métodos
    public void imprimirln(String texto);
    public void imprimir(String texto);
    public String leer (String texto);
}
