import components.queue.Queue;
import components.queue.Queue1L;

public class FGParser1 extends FGParserSecondary {

    /**
     * private members
     */

    /**
     * A Queue that stores everything.
     */
    private Queue<Integer> rep;

    /**
     * Constructor of initial representation.
     */
    private void createNewRep() {
        this.rep = new Queue1L<>();
    }

    public FGParser1() {
        this.createNewRep();
    }

    @Override
    public void addInput(int input) {
        this.rep.enqueue(input);

    }

    @Override
    public void extractOldInputs() {
        while (this.rep.length() > 7) {
            this.rep.dequeue();
        }

    }

    @Override
    public String getActiveInputs() {
        StringBuilder val = new StringBuilder();
        return null;
    }

    @Override
    public void clearInputs() {

    }
}
