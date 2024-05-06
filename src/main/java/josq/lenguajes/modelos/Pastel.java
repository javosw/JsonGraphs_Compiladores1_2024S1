package josq.lenguajes.modelos;

import java.util.ArrayList;

public class Pastel {
    ArrayList<Data> dataList;

    public Pastel(ArrayList<Data> dataList) {
        this.dataList = dataList;
    }
    
    public ArrayList<Data> getDataList() {
        return dataList;
    }

    public static class Data {
        private String label;
        private String value;
        public Data(String label, String value) {
            this.label = label;
            this.value = value;
        }
        public String getLabel() {
            return label;
        }
        public String getValue() {
            return value;
        }
    }
}
