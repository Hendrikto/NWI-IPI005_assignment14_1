package assignment14_1;

/**
 *
 * @author Sjaak Smetsers
 */
public class Train {

    private int nrOfPassengers;
    private final Station station;
    private int nrOfTrips = 0;

    public Train(Station station) {
        this.station = station;
        this.nrOfPassengers = 0;
    }

    /**
     * Populate this train with number Passengers.
     *
     * @param number the number of passengers of this train
     */
    public void getIn(int number) {
        nrOfPassengers = number;
    }

    /**
     * Empty this train and augment the number of Passengers at the station
     * with this nrOfPassenegers.
     */
    public void getOff() {
        nrOfTrips += 1;
        station.enterStation(nrOfPassengers);
    }

    public void closeStation() {
        station.close();
    }

    public int getNrOfTrips() {
        return nrOfTrips;
    }

}
