package josq.lenguajes.modelos;

import java.util.ArrayList;

public class PastelXt {
    private ArrayList<Data> dataList;
    private Chart2 chart;
    
    public PastelXt(ArrayList<Data> dataList, Chart2 xt) {
        this.dataList = dataList;
        this.chart = xt;
    }

    public ArrayList<Data> getDataList() {
        return dataList;
    }
    public Chart2 getChart() {
        return chart;
    }

    public static class Data {
        private String label;
        private String value;
        private String color;
        public Data(String label, String value, String color) {
            this.label = label;
            this.value = value;
            this.color = color;
        }
        public String getLabel() {
            return label;
        }
        public String getValue() {
            return value;
        }
        public String getColor() {
            return color;
        }
    }

}
