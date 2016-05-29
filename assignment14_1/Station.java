package assignment14_1;

/**
 * Class that holds the number of persons arriving by train at the station and
 * waiting for a taxi.
 *
 * @author Hendrik Werner // s4549775
 * @author Jasper Haasdijk // s4449754
 * @author Sjaak Smetsers
 */
public class Station {

    private int nrOfPassengersAtStation;
    private int totalNrOfPassengers;
    private boolean isClosed;

    public Station() {
        this.nrOfPassengersAtStation = 0;
        this.totalNrOfPassengers = 0;
        this.isClosed = false;
    }

    /**
     * @param nrOfPassengers the number of passengers to enter the station
     */
    public void enterStation(int nrOfPassengers) {
        nrOfPassengersAtStation += nrOfPassengers;
        totalNrOfPassengers += nrOfPassengers;
        System.out.println("Train with " + nrOfPassengers + " passengers has arrived");
    }

    /**
     * @return the number of passengers currently waiting at the station
     */
    public synchronized int getNrOfPassengersWaiting() {
        return nrOfPassengersAtStation;
    }

    /**
     * Ask for as many Passengers as possible to leave the station.
     *
     * @param capacity the capacity of the taxi
     * @return the number of passengers
     */
    public synchronized int getMaxPassengers(int capacity) {
        int maxPassengers = Math.min(nrOfPassengersAtStation, capacity);
        nrOfPassengersAtStation -= maxPassengers;
        return maxPassengers;
    }

    /**
     * Close the station.
     */
    public void close() {
        isClosed = true;
    }

    /**
     * @return whether the station is closed
     */
    public boolean isClosed() {
        return isClosed;
    }

    /**
     * @return the total number of passengers that entered the station
     */
    public int getTotalNrOfPassengers() {
        return totalNrOfPassengers;
    }

}
