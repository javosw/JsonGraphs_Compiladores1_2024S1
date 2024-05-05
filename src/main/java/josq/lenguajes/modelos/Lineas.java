package josq.lenguajes.modelos;

import java.util.ArrayList;

public class Lineas {
    ArrayList<Data> dataList;
    String title;
    String xLabel;
    String yLabel;
    
    public class Data {
        String name;
        ArrayList<Punto> puntos;
        
        public class Punto {
            String pX;
            String pY;
        }
    }

}
