//© 2019 Alberto Ruibal Ojea && Lara Padrón Cousillas

package monopoly;

import java.util.Scanner;

public class ConsolaNormal implements Consola{
    public void imprimirln(String texto){
        System.out.println(texto);
    }

    public void imprimir(String texto){ System.out.print(texto);}

    public String leer(String texto){
        System.out.print(texto);
        Scanner scanner = new Scanner(System.in);
        String lectura = scanner.nextLine();

        return lectura;
    }
}
