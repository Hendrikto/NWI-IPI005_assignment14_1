package assignment14_1;

/**
 * A runnable wrapper of taxis to allow for concurrency.
 *
 * @author Hendrik Werner // s4549775
 * @author Jasper Haasdijk // s4449754
 */
public class TaxiRunner implements Runnable {

    private final Taxi taxi;

    /**
     * @param t the taxi
     */
    public TaxiRunner(Taxi t) {
        taxi = t;
    }

    @Override
    public void run() {
        taxi.takePassengers();
    }

}
