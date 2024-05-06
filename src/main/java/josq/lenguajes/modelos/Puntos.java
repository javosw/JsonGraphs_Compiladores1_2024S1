package josq.lenguajes.modelos;

import java.util.ArrayList;

public class Puntos {
    private ArrayList<Data> dataList;

    public Puntos(ArrayList<Data> dataList) {
        this.dataList = dataList;
    }

    public ArrayList<Data> getDataList() {
        return dataList;
    }

    public static class Data {
        private String pX;
        private String pY;
        public Data(String pX, String pY) {
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
