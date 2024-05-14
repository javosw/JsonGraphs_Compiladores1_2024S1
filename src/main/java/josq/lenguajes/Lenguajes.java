/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package josq.lenguajes;

import java.util.ArrayList;

import josq.lenguajes.automatas.Ejecucion;
import josq.lenguajes.automatas.Registros;
import josq.lenguajes.modelos.Dashb;
import josq.lenguajes.modelos.Par;
import josq.lenguajes.traduccion.HTMLinador;

/**
 *
 * @author JavierOswaldo
 */
public class Lenguajes {

    public static void main(String[] args) {
        leerDashb();
    }
    
    static void leerDashb() {
        String rutaIn = "C:\\DASHB\\todas&error@grafs";
        String rutaOut = "C:\\Users\\JavierOswaldo\\Desktop\\MIPAGINA.html";

        try {
            Dashb miDash = Ejecucion.getDashbDesdeArchivo(rutaIn);
            System.out.println("\n"+Registros.sintactico.toString());
            String page = HTMLinador.getPage(miDash);
            HTMLinador.writeString(rutaOut, page);
        }
        catch (Exception ex) {
            System.out.println("@leer:" + ex.getMessage());
            ex.printStackTrace();
        }
    } 
}
