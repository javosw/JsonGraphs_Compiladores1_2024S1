package josq.lenguajes.automatas;

public class Registros {
    public static final StringBuilder html = new StringBuilder();
    public static final StringBuilder sintactico = new StringBuilder();
    public static final StringBuilder semantico = new StringBuilder();

    public static void clearRegistros() {
        html.delete(0, html.length());
        sintactico.delete(0, sintactico.length());
        semantico.delete(0, semantico.length());
    }
}
