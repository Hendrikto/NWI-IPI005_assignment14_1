package assignment14_1;

import java.util.Random;

/**
 * A class to provide useful utilities.
 *
 * @author Hendrik Werner // s4549775
 * @author Jasper Haasdijk // s4449754
 * @author Sjaak Smetsers
 */
public class Util {

    private static final Random generator = new Random();

    /**
     * Generate a random number between from and to.
     *
     * @param from lower limit
     * @param to upper limit
     * @return random number between from and to
     */
    public static int getRandomNumber(int from, int to) {
        if (from == to) {
            return from;
        } else {
            return from + generator.nextInt(to - from);
        }
    }

}
