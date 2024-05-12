package josq.lenguajes.modelos;

public class TarjetaXt {
    String value;
    String label;
    String descr;
    String icon;
    String color;
    String link;
    public TarjetaXt(String value, String label, String descr, String icon, String color, String link) {
        this.value = value;
        this.label = label;
        this.descr = descr;
        this.icon = icon;
        this.color = color;
        this.link = link;
    }
    public String getValue() {
        return value;
    }
    public String getLabel() {
        return label;
    }
    public String getDescr() {
        return descr;
    }
    public String getIcon() {
        return icon;
    }
    public String getColor() {
        return color;
    }
    public String getLink() {
        return link;
    }
}
