package josq.lenguajes.modelos;

import java.util.ArrayList;

public class LineasXt {
    private ArrayList<Data> dataList;
    private Chart1 chart;    

    public LineasXt(ArrayList<Data> dataList, Chart1 chart) {
        this.dataList = dataList;
        this.chart = chart;
    }
    public ArrayList<Data> getDataList() {
        return dataList;
    }
    public Chart1 getChart() {
        return chart;
    }

    public static class Data {
        String name;
        ArrayList<Punto> puntos;
        String color;
        String lineStyle;

        public Data(String name, ArrayList<Punto> puntos, String color, String lineStyle) {
            this.name = name;
            this.puntos = puntos;
            this.color = color;
            this.lineStyle = lineStyle;
        }

        public String getName() {
            return name;
        }
        public ArrayList<Punto> getPuntos() {
            return puntos;
        }
        public String getColor() {
            return color;
        }
        public String getLineStyle() {
            return lineStyle;
        }

        public static class Punto {
            private String pX;
            private String pY;
            private String label;
            public Punto(String pX, String pY, String label) {
                this.pX = pX;
                this.pY = pY;
                this.label = label;
            }
            public String getpX() {
                return pX;
            }
            public String getpY() {
                return pY;
            }
            public String getLabel() {
                return label;
            }
            
        }
    }
}
