package josq.lenguajes.modelos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dashb {

    private Map<Index,Object> properties;
    private ArrayList<Object> graficos;

    public Dashb(Map<Index,Object> properties, ArrayList<Object> graficos) {
        this.properties = properties;
        this.graficos = graficos;
    }

    public Map<Index,Object> getProperties() {
        return properties;
    }
    public ArrayList<Object> getGraficos() {
        return graficos;
    }

    public static Dashb newDashb(ArrayList<Par> content)
    {
        // grafico:Object -> graficos:ArrayList<Object> -> content:ArraList<Par(Index,ArrayList<Object>)>
        ArrayList<Par> contentGraficos = new ArrayList<>();
        ArrayList<Par> contentProperties = new ArrayList<>();

        for(Par p : content) {
            if(p.getKey() == Index.SD_GRAFICOS) contentGraficos.add(p);
            else if(p.getKey() == Index.SD_PROPERTY) contentProperties.add(p);
        }

        ArrayList<Object> allGraficos = new ArrayList<>();
        for(Par p : contentGraficos) {
            ArrayList<Object> graficos = (ArrayList<Object>) p.getValue();
            allGraficos.addAll(graficos);
        }

        // property:Par -> properties:ArrayList<Par> -> content:ArraList<Par(Index,ArrayList<Par>)>
        Map<Index,Object> allProperties = new HashMap<>();
        for(Par c : contentProperties) {
            ArrayList<Par> properties = (ArrayList<Par>) c.getValue();
            for (Par p : properties) allProperties.put(p.getKey(), p.getValue());
        }

        return new Dashb(allProperties,allGraficos);
    }

    
    /*
    <<< PROPERTIES >>>
    titlePage;
    descr;
    keywords[];
    titleHeader;
    rights;
    colorBackgr;
    fonts;
    fontSize;

    <<< GRAFICOS >>>
    barras
    barrasXt
    pastel
    pastelXt
    puntos
    puntosXt
    lineas
    lineasXt
    tarjeta
    tarjetaXt
*/

}
