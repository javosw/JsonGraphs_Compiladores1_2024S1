package josq.lenguajes.modelos;

import java.util.ArrayList;

public class PuntosXt {
    private ArrayList<Data> dataList;
    private Chart1 chart;
    
    public PuntosXt(ArrayList<Data> dataList, Chart1 chart) {
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
        private String pX;
        private String pY;
        private String size;
        private String color;
        public Data(String pX, String pY, String size, String color) {
            this.pX = pX;
            this.pY = pY;
            this.size = size;
            this.color = color;
        }
        public String getpX() {
            return pX;
        }
        public String getpY() {
            return pY;
        }
        public String getSize() {
            return size;
        }
        public String getColor() {
            return color;
        }
    }
}
