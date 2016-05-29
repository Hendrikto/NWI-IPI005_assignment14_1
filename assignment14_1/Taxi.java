package assignment14_1;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A Taxi with a given capacity that can take passengers from a station.
 *
 * @author Hendrik Werner // s4549775
 * @author Jasper Haasdijk // s4449754
 * @author Sjaak Smetsers
 */
public class Taxi {

    private static final long SLEEPTIME = 100;
    private final int taxiId;
    private final int maxNrOfPassengers;
    private final int transportationTime;
    private final Station station;
    private int totalNrOfPassengers = 0;
    private int nrOfRides = 0;

    /**
     * @param nr the id of this taxi
     * @param maxNumberOfPassengers the capacity
     * @param transportationTime the transportation time of a trip
     * @param station the station to take up passengers
     */
    public Taxi(int nr, int maxNumberOfPassengers, int transportationTime, Station station) {
        this.taxiId = nr;
        this.maxNrOfPassengers = maxNumberOfPassengers;
        this.transportationTime = transportationTime;
        this.station = station;
        System.out.println("New taxi " + nr + " created ");
    }

    /**
     * Try to take maxNrOfPassenegers from the station. If actual number is less
     * then that number is taken.
     */
    public void takePassengers() {
        int passengers = station.getMaxPassengers(maxNrOfPassengers);
        if (passengers > 0) {
            totalNrOfPassengers += passengers;
            nrOfRides++;
            System.out.println("Taxi " + taxiId + " takes " + passengers + " passengers");
        } else {
            System.out.println("Taxi " + taxiId + " takes no passengers");
            try {
                TimeUnit.MILLISECONDS.sleep(SLEEPTIME); // if no passengers at the station wait some time
            } catch (InterruptedException ex) {
                Logger.getLogger(Taxi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Calculate the total time of this taxi by multiplying the number of rides
     * by the transportation time.
     *
     * @return total time
     */
    public int calcTotalTime() {
        return transportationTime * nrOfRides;
    }

    /**
     * @return the total number of passengers picked up
     */
    public int getTotalNrOfPassengers() {
        return totalNrOfPassengers;
    }

}
