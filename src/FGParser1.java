import components.queue.Queue1L

public class FGParser1 extends FGParserSecondary {

    /**
     * private members
     */

    /**
     * A Queue that stores everything.
     */
    private Queue<int> rep;

    /**
     * Constructor of initial representation.
     */
    private void createNewRep() {
        this.rep = new Queue1L<>();
    }

    public FGParser1() {
        this.createNewRep();
    }
}ß