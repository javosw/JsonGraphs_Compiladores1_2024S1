package josq.lenguajes.modelos;

public class Chart2 {
    private String title;
    private String legend;

    public Chart2(String title, String legend) {
        this.title = title;
        this.legend = legend;
    }
    
    public String getTitle() {
        return title;
    }
    public String getLegend() {
        return legend;
    }
}
