package assignment14_1;

/**
 *
 * @author Hendrik Werner // s4549775
 */
public class Assignment14_1 {

    public static void main(String[] args) {
        Simulation sim = new Simulation();
        while (!sim.ended()) {
            sim.step();
        }
        sim.showStatistics();
    }

}
