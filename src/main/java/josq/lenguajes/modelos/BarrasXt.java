package josq.lenguajes.modelos;

import java.util.ArrayList;

public class BarrasXt {
    private ArrayList<Data> dataList;
    private Chart1 xt;

    public BarrasXt(ArrayList<Data> dataList, Chart1 xt) {
        this.dataList = dataList;
        this.xt = xt;
    }

    public Chart1 getXt() {
        return xt;
    }
    public ArrayList<Data> getDataList() {
        return dataList;
    }

    public static class Data {
        private String category;
        private String value;
        private String color;
        public Data(String category, String value, String color) {
            this.category = category;
            this.value = value;
            this.color = color;
        }
        public String getCategory() {
            return category;
        }
        public String getValue() {
            return value;
        }
        public String getColor() {
            return color;
        }
    }
}
