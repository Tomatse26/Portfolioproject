package components.FGParser;

import components.map.Map;
import components.map.Map1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Layered implementations of secondary methods for {@code SimpleReader}.
 */
public abstract class FGParserSecondary implements FGParser {

    /**
     * Map of all moves tht can be compiled.
     */
    private Map<String, String> movesMap;

    /**
     * A variable that keeps track of is movesMap exists to prevent errors with
     * trying to access non-declared variables.
     */
    private boolean movesMapExists = false;

    /**
     * Public members
     */

    /**
     * Common methods (from Object)
     */

    /**
     * the equals and hascode methods are not altered, while toString is. equals
     * is not altered because two FGParsers are equal if they point to the same
     * location, whilst hashcode is not overriden because there is on real
     * mathematical way to represent the item. toString is overriden because
     * there can be a string representation of the data.
     *
     */

    //CHECKSTYLE: ALLOWS THIS METHOD TO BE OVERRIDEN
    @Override
    public String toString() {
        StringBuilder returnVal = new StringBuilder("Current Input Queue: ");
        String inputs = this.getActiveInputs();

        returnVal.append("{");

        /**
         * This is probably unnecessary, but I'm feeling a little pedantic about
         * how the string will look. so... uh... yeah.
         */
        for (int i = 0; i < inputs.length(); i++) {
            returnVal.append(inputs.substring(i, i + 1));
            if (i + 1 < inputs.length()) {
                returnVal.append(", ");
            }
        }
        returnVal.append("}");

        if (this.movesMapExists) {
            returnVal.append(" MovesMap: " + this.movesMap.toString());
        }

        return returnVal.toString();
    }

    /**
     * Other non-kernel methods.
     */

    //CHECKSTYLE: ALLOWS THIS METHOD TO BE OVERRIDEN
    @Override
    public void newMovesMap() {
        this.movesMap = new Map1L<>();
        this.movesMapExists = true;
    }

    //CHECKSTYLE: ALLOWS THIS METHOD TO BE OVERRIDEN
    @Override
    public void addMove(String input, String name) {
        this.movesMap.add(name, input);
    }

    //CHECKSTYLE: ALLOWS THIS METHOD TO BE OVERRIDEN
    @Override
    public void deleteMove(String name) {
        this.movesMap.remove(name);
    }

    //CHECKSTYLE: ALLOWS THIS METHOD TO BE OVERRIDEN
    @Override
    public String getInput(String name) {
        return this.movesMap.value(name);
    }

    //CHECKSTYLE: ALLOWS THIS METHOD TO BE OVERRIDEN
    @Override
    public String getMove(String input) {
        return this.movesMap.key(input);
    }

    //CHECKSTYLE: ALLOWS THIS METHOD TO BE OVERRIDEN
    @Override
    public String generateAttack(String button) {

        // Establishing correct Variables.
        String joystickInputs = this.getActiveInputs();
        String move = "None";
        Sequence<String> possibleInputs = new Sequence1L<String>();

        //Determining all the moves that are possible given an input.
        //sorted by priority through insertion sort.
        for (Map.Pair<String, String> pair : this.movesMap) {
            if (pair.key().indexOf(button) >= 0) {
                int index = 0;
                while (index < possibleInputs.length() && pair.key()
                        .length() > possibleInputs.entry(index).length()) {
                    index += 1;
                }
                possibleInputs.add(index, pair.value());
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
                move = this.movesMap.key(possibleInput);
            }
        }
        this.clearInputs();
        return move;
    }

}
