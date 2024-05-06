package josq.lenguajes.modelos;

public class Tarjeta {
    String value;
    String label;
    String descr;
    public Tarjeta(String value, String label, String descr) {
        this.value = value;
        this.label = label;
        this.descr = descr;
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
}
