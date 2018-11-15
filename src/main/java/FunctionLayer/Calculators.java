package FunctionLayer;

/**
 *
 * @author porse
 */
public class Calculators {

    public static int postsCalc(int length, int width) {
        int posts = length / 100 + 1;
        if (width / 2 >= 5) {
            posts++;
        }
        return posts * 2;
    }

    public static int rafterCalc(int length) {
        int rafter = length / 50 + 1;

        return rafter;
    }
}
