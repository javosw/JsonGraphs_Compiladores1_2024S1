/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package josq.lenguajes;

import josq.lenguajes.automatas.Automata;

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
        String ruta = "C:\\DASHB\\test1.json";
        
        try
        {            
            Automata.getDashbDesdeArchivo(ruta);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        
        
    } 
}
