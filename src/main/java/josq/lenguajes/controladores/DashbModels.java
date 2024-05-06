package josq.lenguajes.controladores;

import java.util.ArrayList;

import josq.lenguajes.modelos.Barras;
import josq.lenguajes.modelos.Index;
import josq.lenguajes.modelos.Par;

public class DashbModels {
    

    void newDashb(ArrayList<Par> contenido)
    {
        ArrayList<Par> properties = new ArrayList<>();
        ArrayList<Par> graficos = new ArrayList<>();

        for(Par p : contenido){
            if(p.getKey() == Index.SD_GRAFICOS) graficos.add(p);
            else if(p.getKey() == Index.SD_PROPERTY) properties.add(p);
        }

    }


    void asldk()
    {
        Barras.Data k = new Barras.Data("","");

    }

}
