package josq.lenguajes.modelos;

import java.util.ArrayList;

public class Lineas {
    private ArrayList<Data> dataList;
    private Chart1 chart;
    
    public Lineas(ArrayList<Data> dataList, Chart1 chart) {
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
        private String name;
        private ArrayList<Punto> puntos;
        
        public Data(String name, ArrayList<Punto> puntos) {
            this.name = name;
            this.puntos = puntos;
        }

        public String getName() {
            return name;
        }

        public ArrayList<Punto> getPuntos() {
            return puntos;
        }

        public static class Punto {
            private String pX;
            private String pY;
            public Punto(String pX, String pY) {
                this.pX = pX;
                this.pY = pY;
            }
            public String getpX() {
                return pX;
            }
            public String getpY() {
                return pY;
            }
        }
    }

}
