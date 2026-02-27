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
     * No argument constructor--private to prevent instantiation.
     */
    private ProofOfConcept() {
    }

    /**
     * Removes excess inputs from the queue.
     *
     * @param inputQueue
     *            the input queue.
     */
    private static void removeInputs(Queue<String> inputQueue) {
        final int maxAmount = 7;
        while (inputQueue.length() > maxAmount) {
            inputQueue.dequeue();
        }

        //In the final method, will also dequeue inputs deemed "Too old".

    }

    /**
     * Intakes a Queue of strings and then returns string representation of it.
     *
     * @param inputQueue
     *            Queue that is inported to be converted into a string.
     * @return A string representation of inputQueue.
     */
    private static String getAllActiveInputs(Queue<String> inputQueue) {
        String returnVal = "";

        for (int i = 0; i < inputQueue.length(); i++) {
            String item = inputQueue.dequeue();
            returnVal += item;
            inputQueue.enqueue(item);
        }

        return returnVal;
    }

    /**
     * A method that intakes all necessary information and then returns the
     * thing.
     *
     * @param movesMap
     *            The map containing all the moves.
     * @param inputQueue
     *            All the viable controller inputs.
     * @param input
     *            The button to be pressed.
     * @return The proper move that should be outputted based off of the given
     *         inputs.
     */
    private static String generateAttack(Map<String, String> movesMap,
            Queue<String> inputQueue, String input) {

        // Establishing correct Variables.
        String joystickInputs = getAllActiveInputs(inputQueue);
        String move = "None";
        Sequence<String> possibleInputs = new Sequence1L<String>();

        //Determining all the moves that are possible given an input.
        //sorted by priority through insertion sort.
        for (Map.Pair<String, String> pair : movesMap) {
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
                move = movesMap.value(possibleInput);
            }
            inputQueue.clear();
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
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        Map<String, String> movesMap = new Map1L<>();
        Queue<String> inputQueue = new Queue1L<>();
        String input;

        movesMap.add("p", "punch");
        movesMap.add("2p", "Crouch Punch");
        movesMap.add("k", "kick");
        movesMap.add("2k", "Crouch Kick");
        movesMap.add("236p", "Hadouken");
        movesMap.add("214p", "Evil Hadouken");
        movesMap.add("236k", "AIR TATSUMAKI!!!");
        movesMap.add("214k", "Shoryuken");
        movesMap.add("214214p", "Summon Zangief");

        out.println("Please enter an input chain");
        input = in.nextLine();
        while (!(input.equals(""))) {
            for (int i = 0; i < input.length(); i++) {
                if (input.substring(i, i + 1).equals("k")
                        || input.substring(i, i + 1).equals("p")) {
                    out.println(generateAttack(movesMap, inputQueue,
                            input.substring(i, i + 1)));

                } else {
                    inputQueue.enqueue(input.substring(i, i + 1));
                    removeInputs(inputQueue);
                }
            }
            inputQueue.clear();
            out.println("Please enter an input chain");
            input = in.nextLine();
        }

        out.close();
        in.close();
    }

}
