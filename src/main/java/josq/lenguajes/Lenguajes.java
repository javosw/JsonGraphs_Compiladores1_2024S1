/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package josq.lenguajes;

import java.util.ArrayList;

import josq.lenguajes.automatas.Ejecucion;
import josq.lenguajes.modelos.Dashb;
import josq.lenguajes.modelos.Par;

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
        String ruta = "C:\\DASHB\\todas";
        
        try
        {
            Dashb miDash = Ejecucion.getDashbDesdeArchivo(ruta);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    } 
}
