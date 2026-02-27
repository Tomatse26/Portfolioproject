import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * A program that intakes fighting game input commands, cut down into strings
 * containing integers (representing joystick movements) and p's and k's,
 * representing punch and kick inputs respectively. Is proof of concept for
 * larger project.
 *
 * @author Daniel Shen
 *
 */
public final class ProofOfConcept {

    /**
     * Queue that tracks all joystick inputs.
     */
    private Queue<String> inputQueue = new Queue1L<>();
    /**
     * Map containing all the fighting game moves.
     */
    private Map<String, String> movesMap = new Map1L<>();

    /**
     * No argument constructor--private to prevent instan214tiation.
     */
    public ProofOfConcept() {
    }

    /**
     * Removes excess inputs from the queue.
     */
    private void removeInputs() {
        final int maxAmount = 7;
        while (this.inputQueue.length() > maxAmount) {
            this.inputQueue.dequeue();
        }

        //In the final method, will also dequeue inputs deemed "Too old".

    }

    /**
     * Intakes a Queue of strings and then returns string representation of it.
     *
     * @return A string representation of inputQueue.
     */
    private String getAllActiveInputs() {
        String returnVal = "";

        for (int i = 0; i < this.inputQueue.length(); i++) {
            String item = this.inputQueue.dequeue();
            returnVal += item;
            this.inputQueue.enqueue(item);
        }

        return returnVal;
    }

    /**
     * A method that intakes all necessary information and then returns the
     * thing.
     *
     * @param input
     *            The button to be pressed.
     * @return Generated Attack
     */
    private String generateAttack(String input) {

        // Establishing correct Variables.
        String joystickInputs = this.getAllActiveInputs();
        String move = "None";
        Sequence<String> possibleInputs = new Sequence1L<String>();

        //Determining all the moves that are possible given an input.
        //sorted by priority through insertion sort.
        for (Map.Pair<String, String> pair : this.movesMap) {
            if (pair.key().indexOf(input) >= 0) {
                int index = 0;
                while (index < possibleInputs.length() && pair.key()
                        .length() > possibleInputs.entry(index).length()) {
                    index += 1;
                }
                possibleInputs.add(index, pair.key());
            }
        }

        //Determining and then selecting a possible move based on sorted sequence.
        for (String possibleInput : possibleInputs) {
            String numberInput = possibleInput.substring(0,
                    possibleInput.length() - 1);
            String joystickSubinput = joystickInputs;
            if (joystickSubinput.length() > possibleInput.length()) {
                joystickSubinput = joystickInputs.substring(
                        joystickInputs.length() - possibleInput.length());
            }
            if (joystickSubinput.indexOf(numberInput) >= 0) {
                move = this.movesMap.value(possibleInput);
            }
            this.inputQueue.clear();
        }
        return move;
    }

    /**
     *
     * Main method.
     *
     * the command line arguments
     *
     * @param args
     *
     */
    public void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        String input;

        this.movesMap.add("p", "punch");
        this.movesMap.add("2p", "Crouch Punch");
        this.movesMap.add("k", "kick");
        this.movesMap.add("2k", "Crouch Kick");
        this.movesMap.add("236p", "Hadouken");
        this.movesMap.add("214p", "Evil Hadouken");
        this.movesMap.add("236k", "AIR TATSUMAKI!!!");
        this.movesMap.add("214k", "Shoryuken");
        this.movesMap.add("214214p", "Summon Zangief");

        out.println("Please enter an input chain");
        input = in.nextLine();
        while (!(input.equals(""))) {
            for (int i = 0; i < input.length(); i++) {
                if (input.substring(i, i + 1).equals("k")
                        || input.substring(i, i + 1).equals("p")) {
                    out.println(this.generateAttack(input.substring(i, i + 1)));

                } else {
                    this.inputQueue.enqueue(input.substring(i, i + 1));
                    this.removeInputs();
                }
            }
            this.inputQueue.clear();
            out.println("Please enter an input chain");
            input = in.nextLine();
        }

        out.close();
        in.close();
    }

}
