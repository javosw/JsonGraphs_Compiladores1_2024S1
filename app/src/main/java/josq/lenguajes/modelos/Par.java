package josq.lenguajes.modelos;

/**
 *
 * @author JavierOswaldo
 */
public class Par
{
    private Index key;
    private Object value;

    public Par(Index key, Object value)
    {
        this.key = key;
        this.value = value;
    }

    public Index getKey()
    {
        return key;
    }

    public Object getValue()
    {
        return value;
    }

    boolean hasValue()
    {
        return value != null;
    }

}
