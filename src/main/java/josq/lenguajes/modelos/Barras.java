package josq.lenguajes.modelos;

import java.util.ArrayList;

public class Barras {
    private ArrayList<Data> dataList;

    public Barras(ArrayList<Data> dataList) {
        this.dataList = dataList;
    }

    public ArrayList<Data> getDataList() {
        return dataList;
    }

    public static class Data{
        private String category;
        private String value;

        public Data(String category, String value) {
            this.category = category;
            this.value = value;
        }
        public String getCategory() {
            return category;
        }
        public String getValue() {
            return value;
        }
    }

}
