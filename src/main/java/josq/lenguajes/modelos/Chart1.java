package josq.lenguajes.modelos;

public class Chart1 {
    
    private String title;
    private String xLabel;
    private String yLabel;

    public Chart1(String title, String xLabel, String yLabel) {
        this.title = title;
        this.xLabel = xLabel;
        this.yLabel = yLabel;
    }

    public String getTitle() {
        return title;
    }

    public String getxLabel() {
        return xLabel;
    }

    public String getyLabel() {
        return yLabel;
    }    
}
