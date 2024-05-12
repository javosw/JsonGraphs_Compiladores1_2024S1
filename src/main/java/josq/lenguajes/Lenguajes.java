/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package josq.lenguajes;

import java.util.ArrayList;

import josq.lenguajes.automatas.Ejecucion;
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
    
    static void leerDashb()
    {
        String rutaIn = "C:\\DASHB\\todas-error@props";
        
        try
        {
            Dashb miDash = Ejecucion.getDashbDesdeArchivo(rutaIn);
            String page = HTMLinador.getPage(miDash);
            String rutaOut = "C:\\Users\\JavierOswaldo\\Desktop\\MIPAGINA.html";
            HTMLinador.writeString(rutaOut, page);
        }
        catch (Exception ex)
        {
            System.out.println("@leer:" + ex.getMessage());
            ex.printStackTrace();
        }
    } 
}
