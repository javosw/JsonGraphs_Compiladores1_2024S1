package josq;

public class Permutations<T> {
    public void getAll(T[] array) {
        helper(array, 0);
    }

    private void helper(T[] array, int pos) {
        if (pos >= array.length - 1) {
            System.out.print("[");
            for (int i = 0; i < array.length - 1; i++) System.out.print(array[i] + ", ");
            if (array.length > 0) System.out.print(array[array.length - 1]);

            System.out.println("]");
            return;
        }

        for (int i = pos; i < array.length; i++) {

            T t = array[pos];
            array[pos] = array[i];
            array[i] = t;

            helper(array, pos + 1);

            t = array[pos];
            array[pos] = array[i];
            array[i] = t;
        }
    }

    public static void main(String args[]) {
        String[] cadenas = {"category:w1", "value:w2", "color:w3"};
        // 
        Permutations<String> myPerm = new Permutations<>();
        myPerm.getAll(cadenas);
    }
}
