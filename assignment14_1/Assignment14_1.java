package assignment14_1;

/**
 * @author Hendrik Werner // s4549775
 * @author Jasper Haasdijk // s4449754
 * @author Sjaak Smetsers
 */
public class Assignment14_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Simulation sim = new Simulation();
        while (!sim.ended()) {
            sim.step();
        }
        sim.showStatistics();
    }

}
