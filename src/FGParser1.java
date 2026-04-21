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
        this.extractOldInputs();

        int length = this.rep.length();
        for (int i = 0; i < length; i++) {
            int num = this.rep.dequeue();
            val.append(num);
            this.rep.enqueue(num);
        }

        return val.toString();
    }

    @Override
    public void clearInputs() {
        this.rep.clear();

    }
}
