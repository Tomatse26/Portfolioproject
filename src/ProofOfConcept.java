import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
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
     * A method that intakes all necessary information and then returns the
     * thing.
     *
     * @param movesMap
     *            The map containing all the moves.
     * @param inputQueue
     *            All the viable controller inputs.
     * @param input
     *            The button to be pressed.
     * @return
     */
    private static String findOutput(Map<String, String> movesMap,
            Queue<String> inputQueue, String input) {

        String joystickInputs = quickRemove(inputQueue);
        return "booger aids";

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
        movesMap.add("pk", "Grab");
        movesMap.add("236pk", "Command Grab");
        movesMap.add("214214p", "Summon Zangief");

        out.println("Please enter an input chain");
        input = in.nextLine();
        while (!(input.equals(""))) {
            for (int i = 0; i < input.length(); i++) {
                if (input.substring(i, i + 1).equals("k")
                        || input.substring(i, i + 1).equals("p")) {
                    out.println(findOutput(movesMap, inputQueue,
                            input.substring(i, i + 1)));

                } else {
                    inputQueue.enqueue(input.substring(i, i + 1));
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
