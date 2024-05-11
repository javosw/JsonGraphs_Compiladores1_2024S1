package josq.lenguajes.traduccion;

import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

import josq.lenguajes.modelos.Barras;
import josq.lenguajes.modelos.BarrasXt;
import josq.lenguajes.modelos.Chart1;
import josq.lenguajes.modelos.Dashb;
import josq.lenguajes.modelos.Index;
import josq.lenguajes.modelos.Par;

public class HTMLinador {

    public static String getPage(Dashb miDashb)
    {
        Map<Index,Object> props = miDashb.getProperties();
        ArrayList<Object> graficos = miDashb.getGraficos();

        StringBuilder miHTML = new StringBuilder();
        miHTML.append("<!doctype html>");
        miHTML.append("<html lang=\"en\">");

        miHTML.append("<head>");
        miHTML.append("<meta charset=\"UTF-8\" />");
        miHTML.append(getProp(Index.KD_TITLE, props.get(Index.KD_TITLE)));
        miHTML.append("<style> ");
        miHTML.append(".grafico { max-width:600px; max-height:660px; }");
        
        miHTML.append(".texto {");
        miHTML.append(getProp(Index.KD_FONTSIZE, props.get(Index.KD_FONTSIZE)));
        miHTML.append(getProp(Index.KD_FONTFAM, props.get(Index.KD_FONTFAM)));
        miHTML.append("");
        miHTML.append("}");

        miHTML.append("body { padding-left: 5%; padding-right: 5%;").append(getProp(Index.KD_BACKGR, props.get(Index.KD_BACKGR))).append("}");
        miHTML.append("</style>");
        miHTML.append("</head>");

        miHTML.append("<body>");
        miHTML.append(getProp(Index.KD_HEADER, props.get(Index.KD_HEADER)));
        miHTML.append("<main>");
        miHTML.append("<div class=\"texto\">");
        miHTML.append(getProp(Index.KD_KEYWORDS, props.get(Index.KD_KEYWORDS)));
        miHTML.append(getProp(Index.KD_DESCRIP, props.get(Index.KD_DESCRIP)));
        miHTML.append("</div>");

        for (int i = 0; i < graficos.size(); i++) {
            miHTML.append("<canvas id=\"graf"+i+"\" class=\"grafico\"></canvas>");
        }

        miHTML.append("</main>");
        miHTML.append(getProp(Index.KD_FOOTER, props.get(Index.KD_FOOTER)));
        miHTML.append("<script src=\"https://cdn.jsdelivr.net/npm/chart.js\"></script>");
        miHTML.append("<script>");

        miHTML.append(getGraficos(graficos));
        miHTML.append("</script>");
        miHTML.append("</body>");

        miHTML.append("</html>");
        return miHTML.toString();
    }

    private static String getGraficos(ArrayList<Object> graficos)
    {
        StringBuilder html = new StringBuilder();
        int conteo = graficos.size();

        for(int i = 0; i < conteo; i = i + 1) {
            if (graficos.get(i) instanceof Barras) html.append(getBarras("graf"+i,(Barras) graficos.get(i)));
            else if (graficos.get(i) instanceof BarrasXt) html.append(getBarrasXt("graf"+i,(BarrasXt) graficos.get(i)));
        }
        return html.toString();
    }

    // quiero esta prop de este conjunto de props
    private static String getProp(Index propID, Object propRaw)
    {
        if (propRaw == null) return "";

        if(propID == Index.KD_KEYWORDS) {
            ArrayList<String> labels = (ArrayList<String>) propRaw;
            StringBuilder labelsHTML = new StringBuilder();
            labelsHTML.append("<ul>");
            for(String l : labels) labelsHTML.append("<li>"+l+"</li>");
            labelsHTML.append("</ul>");

            return labelsHTML.toString();
        }

        StringBuilder text = new StringBuilder((String) propRaw);
        text.deleteCharAt(0);
        text.deleteCharAt(text.length()-1);

        if(propID == Index.KD_TITLE) return "<title>"+text.toString()+"</title>";
        else if(propID == Index.KD_DESCRIP) return text.toString();
        else if(propID == Index.KD_HEADER) return "<header class=\"texto\"><h1>"+text.toString()+"</h1></header>";
        else if(propID == Index.KD_FOOTER) return "<footer class=\"texto\"><h4>"+text.toString()+"</h4></footer>";
        else if(propID == Index.KD_BACKGR) return "background-color:"+text.toString()+";";
        else if(propID == Index.KD_FONTFAM) return "font-family:"+text.toString()+";";
        else if(propID == Index.KD_FONTSIZE) return "font-size:"+text.toString()+";";

        return "";
    }
        
    private static String getBarrasXt(String id, BarrasXt miGrafico)
    {
        ArrayList<BarrasXt.Data> dataList = miGrafico.getDataList();
        Chart1 xt = miGrafico.getXt();

        StringBuilder js = new StringBuilder();

        js.append("new Chart(document.getElementById('"+id+"'),{ type: 'bar', data: {");
        
        StringBuilder labels = new StringBuilder();
        for (BarrasXt.Data d: dataList) labels.append(d.getCategory()+",");
        js.append("labels: ["+labels.toString()+"],");
        js.append("datasets: [ {");
        js.append("label: "+xt.getTitle()+",");

        StringBuilder data = new StringBuilder();
        for (BarrasXt.Data d: dataList) data.append(d.getValue()+",");
        js.append("data: ["+data.toString()+"],");

        StringBuilder color = new StringBuilder();
        for (BarrasXt.Data d: dataList) color.append(d.getColor()+",");
        js.append("backgroundColor: ["+color.toString()+"],");
        js.append("}]}, options: { scales: {");
        js.append("y: { title: { display: true, text: "+xt.getyLabel()+" } },");
        js.append("x: { title: { display: true, text: "+xt.getxLabel()+" } },");
        js.append("} } } );");

        return js.toString();
    }

    private static String getBarras(String id, Barras miGrafico)
    {
        ArrayList<Barras.Data> dataList = miGrafico.getDataList();

        StringBuilder js = new StringBuilder();

        js.append("new Chart(document.getElementById('"+id+"'), { type: 'bar',");
        js.append("data: {");

        StringBuilder categories = new StringBuilder();
        for (Barras.Data d: dataList) categories.append(d.getCategory()+",");
        js.append("labels: ["+categories.toString()+"],");

        StringBuilder data = new StringBuilder();
        for (Barras.Data d: dataList) data.append(d.getValue()+",");
        js.append("datasets: [ { data: ["+data.toString()+"] } ]");
        js.append("}});");
        
        return js.toString();
    }


    public static void writeString(String file, String txt) throws Exception
    {
        FileWriter myWriter = new FileWriter(file);
        //FileWriter myWriter = new FileWriter(file,StandardCharsets.UTF_8);
        myWriter.write(txt);
        myWriter.close();
    }
    public static void writeStringAtEnd(String file, String txt) throws Exception
    {
        //FileWriter myWriter = new FileWriter(file,StandardCharsets.UTF_8,true);
        FileWriter myWriter = new FileWriter(file,true);
        myWriter.write(txt);
        myWriter.close();
    }
}
