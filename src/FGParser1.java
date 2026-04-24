import components.queue.Queue;
import components.queue.Queue1L;

/**
 * {@code FGParser} represented as a {@code Queue} with implementations of
 * primary methods.
 *
 * @convention <pre>
 * [all inputs of $this.rep are '0' through '9']
 * </pre>
 *
 * @author Daniel Shen
 *
 */
public class FGParser1 extends FGParserSecondary {

    /**
     * private members
     */

    /**
     * A Queue that stores everything.
     */
    private Queue<Integer> rep;

    /**
     * The maximum length this should be, as that's the max amount of inputs
     * that should be accounted for during parsing.
     */
    private final int maxLength = 7;

    /**
     * the maximum number that is one digit long.
     */
    private final int maxDigit = 9;

    /**
     * Constructor of initial representation.
     */
    private void createNewRep() {
        this.rep = new Queue1L<>();
    }

    /**
     * Only constructor, no argument.
     */
    public FGParser1() {
        this.createNewRep();
    }

    @Override
    public void addInput(int input) {
        assert input >= 1 : "Violation of: input >= 1";
        assert input <= this.maxDigit : "Violation of: input <= 9";
        this.rep.enqueue(input);

    }

    @Override
    public void extractOldInputs() {
        while (this.rep.length() > this.maxLength) {
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
