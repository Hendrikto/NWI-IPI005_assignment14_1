package assignment14_1;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simulate a station with trains, taxis, and passengers.
 *
 * @author Hendrik Werner // s4549775
 * @author Jasper Haasdijk // s4449754
 * @author Sjaak Smetsers
 */
public class Simulation {

    public static final int TRAIN_TRIPS = 10;
    public static final int MIN_TRAVELLERS = 60;
    public static final int MAX_TRAVELLERS = 90;
    public static final int CAPACITYSMALL = 4;
    public static final int CAPACITYLARGE = 7;
    public static final int TIMESMALL = 2;
    public static final int TIMELARGE = 3;
    public static final int NROFTAXIS = 4;
    public static final int NROFSMALLTAXIS = 2;

    private final Taxi[] taxis;
    private final Train train;
    private final Station station;
    private final ExecutorService executor;

    private boolean hasEnded = false;
    private int nextTaxi = 0;

    public Simulation() {
        station = new Station();
        taxis = new Taxi[NROFTAXIS];
        for (int i = 0; i < NROFTAXIS; i++) {
            if (i < NROFSMALLTAXIS) {
                taxis[i] = new Taxi(i + 1, CAPACITYSMALL, TIMESMALL, station);
            } else {
                taxis[i] = new Taxi(i + 1, CAPACITYLARGE, TIMELARGE, station);
            }
        }
        train = new Train(station);
        executor = Executors.newCachedThreadPool();
    }

    /**
     * Take one simulation step.
     */
    public void step() {
        if (station.getNrOfPassengersWaiting() > 0) {
            executor.submit(new TaxiRunner(taxis[nextTaxi]));
            nextTaxi = (nextTaxi + 1) % NROFTAXIS;
        } else if (train.getNrOfTrips() < TRAIN_TRIPS) {
            train.getIn(Util.getRandomNumber(MIN_TRAVELLERS, MAX_TRAVELLERS));
            train.getOff();
        } else {
            executor.shutdown();
            try {
                executor.awaitTermination(1, TimeUnit.MINUTES);
            } catch (InterruptedException ex) {
                Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
            }
            train.closeStation();
            hasEnded = true;
        }
    }

    /**
     * Check if the simulation has ended.
     *
     * @return if the simulation has ended
     */
    public boolean ended() {
        return hasEnded;
    }

    /**
     * Print statistics about the simulation.
     */
    public void showStatistics() {
        System.out.println("All persons have been transported");
        System.out.println("Total time of this simulation:" + calcTotalTime(taxis));
        System.out.println("Total nr of train travellers:" + station.getTotalNrOfPassengers());
        System.out.println("Total nr of persons transported in this simulation:" + calcTotalNrOfPassengers(taxis));
    }

    /**
     * Calculate the total time of the simulation by looping over all taxis.
     *
     * @param taxis
     * @return total time
     */
    private static int calcTotalTime(Taxi[] taxis) {
        return Arrays.stream(taxis)
                .mapToInt(Taxi::calcTotalTime)
                .sum();
    }

    /**
     * Calculate the total number of passengers that have been transported by
     * looping over all taxis.
     *
     * @param taxis
     * @return total number of passengers
     */
    private static int calcTotalNrOfPassengers(Taxi[] taxis) {
        return Arrays.stream(taxis)
                .mapToInt(Taxi::getTotalNrOfPassengers)
                .sum();
    }

}
