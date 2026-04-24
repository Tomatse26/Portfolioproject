package Portfolioproject.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.FGParser.FGParser;
import components.FGParser.FGParser1;

/**
 * JUnit test fixture for {@code FGParser}'s secondary and kernel methods.
 *
 * @author Daniel Shen
 *
 */
public class FGParserTest {

    /**
     * testing adding a single item to input queue.
     */
    @Test
    public final void testConstructor() {
        FGParser test = new FGParser1();
        assertEquals(test.toString(), "Current Input Queue: {}");

    }

    // Testing Kernel Methods ----------------------------------------------------------

    /**
     * testing adding a single item to input queue.
     */
    @Test
    public final void testAddSingleInput() {
        FGParser test = new FGParser1();

        test.addInput(1);

        assertEquals(test.toString(), "Current Input Queue: {1}");

    }

    /**
     * testing adding two items to input queue.
     */
    @Test
    public final void testAddTwoInputs() {
        final int num1 = 6;
        final int num2 = 7;
        FGParser test = new FGParser1();

        test.addInput(num1);
        test.addInput(num2);

        assertEquals(test.toString(), "Current Input Queue: {6, 7}");

    }

    /**
     * testing adding several numbers to input queue.
     */
    @Test
    public final void testAddManyInputs() {
        FGParser test = new FGParser1();

        final int numOfInputs = 5;
        for (int i = 1; i <= numOfInputs; i++) {
            test.addInput(i);
        }

        assertEquals(test.toString(), "Current Input Queue: {1, 2, 3, 4, 5}");

    }

    /**
     * testing adding a single item to input queue twice.
     */
    @Test
    public final void testAddRepeatingNumbers() {
        FGParser test = new FGParser1();

        test.addInput(1);
        test.addInput(1);

        assertEquals(test.toString(), "Current Input Queue: {1, 1}");

    }

    /**
     * testing adding a pattern to the input queue.
     */
    @Test
    public final void testAddRepeatingPattern() {
        FGParser test = new FGParser1();
        final int numOfInputs = 3;
        final int numOfRepetitions = 2;

        for (int i = 1; i <= numOfRepetitions; i++) {
            for (int j = 1; j <= numOfInputs; j++) {
                test.addInput(j);
            }
        }

        assertEquals(test.toString(),
                "Current Input Queue: {1, 2, 3, 1, 2, 3}");

    }

    /**
     * testing extract old inputs with an emppty parser.
     */
    @Test
    public final void testExtractOldInputsEmpty() {
        FGParser test = new FGParser1();
        FGParser base = new FGParser1();

        test.extractOldInputs(); //should do nothing

        assertEquals(test.toString(), base.toString());

    }

    /**
     * testing extract old inputs with a parser with 1 input.
     */
    @Test
    public final void testextractOldInputs1() {
        FGParser test = new FGParser1();
        FGParser base = new FGParser1();

        test.addInput(1);
        base.addInput(1);

        test.extractOldInputs(); //should do nothing.

        assertEquals(test.toString(), base.toString());
    }

    /**
     * testing extract old inputs with a parser with 4 inputs.
     */
    @Test
    public final void testExtractOldInputs4() {
        FGParser test = new FGParser1();
        FGParser base = new FGParser1();
        final int numInputs = 4;

        for (int i = 1; i <= numInputs; i++) {
            test.addInput(i);
            base.addInput(i);
        }

        test.extractOldInputs(); //should do nothing.

        assertEquals(test.toString(), base.toString());

    }

    /**
     * testing extract old inputs with a parser with 18 inputs.
     */
    @Test
    public final void testExtractOldInputs18() {
        FGParser test = new FGParser1();
        FGParser base = new FGParser1();

        final int numOfRepetitionsTest = 2;
        final int numOfDigits = 9;
        final int startDigitBase = 3;

        for (int i = 1; i <= numOfRepetitionsTest; i++) {
            for (int j = 1; j <= numOfDigits; j++) {
                test.addInput(j);
            }
        }

        for (int i = startDigitBase; i <= numOfDigits; i++) {
            base.addInput(i);
        }

        test.extractOldInputs();

        assertEquals(test.toString(), base.toString());

    }

    /**
     * testing clearinputs with an empty input stream.
     */
    @Test
    public final void testClearInputsEmpty() {
        FGParser test = new FGParser1();
        FGParser base = new FGParser1();

        test.clearInputs();

        assertEquals(test.toString(), base.toString());

    }

    /**
     * testing clearinputs with one item in input stream.
     */
    @Test
    public final void testClearInputs1() {
        FGParser test = new FGParser1();
        FGParser base = new FGParser1();

        test.addInput(1);
        test.clearInputs();

        assertEquals(test.toString(), base.toString());

    }

    /**
     * testing clearinputs with multiple items in input stream.
     */
    @Test
    public final void testClearInputsMany() {
        FGParser test = new FGParser1();
        FGParser base = new FGParser1();
        final int numOfRepetitions = 3;

        for (int i = 0; i < numOfRepetitions; i++) {
            test.addInput(1);
        }

        test.clearInputs();

        assertEquals(test.toString(), base.toString());

    }

    /**
     * testing getActiveInputs with an empty input stream.
     */
    @Test
    public final void testGetActiveInputsEmpty() {
        FGParser test = new FGParser1();
        FGParser base = new FGParser1();

        String testResult = test.getActiveInputs();

        assertEquals(test.toString(), base.toString());
        assertEquals(testResult, "");

    }

    /**
     * testing getActiveInputs with an input stream of length 1.
     */
    @Test
    public final void testGetActiveInputs1() {
        FGParser test = new FGParser1();
        FGParser base = new FGParser1();

        test.addInput(1);
        base.addInput(1);

        String testResult = test.getActiveInputs();

        assertEquals(test.toString(), base.toString());
        assertEquals(testResult, "1");

    }

    /**
     * testing getActiveInputs with an input stream of length 18.
     */
    @Test
    public final void testGetActiveInputs18() {
        FGParser test = new FGParser1();
        FGParser base = new FGParser1();

        final int numOfRepetitionsTest = 2;
        final int numOfDigits = 9;
        final int startDigitBase = 3;

        for (int i = 1; i <= numOfRepetitionsTest; i++) {
            for (int j = 1; j <= numOfDigits; j++) {
                test.addInput(j);
            }
        }

        for (int i = startDigitBase; i <= numOfDigits; i++) {
            base.addInput(i);
        }

        String testResult = test.getActiveInputs();

        assertEquals(test.toString(), base.toString());
        assertEquals(testResult, "3456789");

    }

    // testing secondary methods ------------------------------------------

    /**
     * testing newMovesMap to create an empty movesMap.
     */
    @Test
    public final void testNewMovesMap() {
        FGParser test = new FGParser1();

        test.newMovesMap();

        assertEquals(test.toString(), "Current Input Queue: {} MovesMap: {}");
    }

    /**
     * testing newMovesMap to create an empty movesMap with input in input
     * queue.
     */
    @Test
    public final void testNewMovesMapWithInputs() {
        FGParser test = new FGParser1();

        test.addInput(1);
        test.newMovesMap();

        assertEquals(test.toString(), "Current Input Queue: {1} MovesMap: {}");
    }

    /**
     * testing addMove to add a move.
     */
    @Test
    public final void testAddMoveOnce() {
        FGParser test = new FGParser1();

        test.newMovesMap();
        test.addMove("236p", "hadouken");

        assertEquals(test.toString(),
                "Current Input Queue: {} MovesMap: {(hadouken,236p)}");
    }

    /**
     * testing deleteMove with only one item.
     */
    @Test
    public final void testDeleteMoveEmpty() {
        FGParser test = new FGParser1();
        FGParser base = new FGParser1();

        test.newMovesMap();
        base.newMovesMap();
        test.addMove("236p", "hadouken");
        test.deleteMove("hadouken");

        assertEquals(test.toString(), base.toString());
    }

    /**
     * testing deleteMove with two items.
     */
    @Test
    public final void testDeleteMoveOne() {
        FGParser test = new FGParser1();
        FGParser base = new FGParser1();

        test.newMovesMap();
        base.newMovesMap();
        test.addMove("236p", "hadouken");
        test.addMove("214k", "Tatsumaki");
        base.addMove("214k", "Tatsumaki");
        test.deleteMove("hadouken");

        assertEquals(test.toString(), base.toString());
    }

    /**
     * testing deleting multiple moves.
     */
    @Test
    public final void testDeleteMoveMultiple() {
        FGParser test = new FGParser1();
        FGParser base = new FGParser1();

        test.newMovesMap();
        base.newMovesMap();
        test.addMove("236p", "hadouken");
        test.addMove("214k", "Tatsumaki");
        test.addMove("p", "punch");
        base.addMove("214k", "Tatsumaki");
        test.deleteMove("hadouken");
        test.deleteMove("punch");

        assertEquals(test.toString(), base.toString());
    }

    /**
     * testing getting input with one item in map.
     */
    @Test
    public final void testGetInputSingular() {
        FGParser test = new FGParser1();
        FGParser base = new FGParser1();

        test.newMovesMap();
        base.newMovesMap();
        test.addMove("236p", "hadouken");
        base.addMove("236p", "hadouken");

        String testResult = test.getInput("hadouken");

        assertEquals(testResult, "236p");
        assertEquals(base.toString(), test.toString());

    }

    /**
     * testing getting input with two items in map.
     */
    @Test
    public final void testGetInputTwo() {
        FGParser test = new FGParser1();
        FGParser base = new FGParser1();

        test.newMovesMap();
        base.newMovesMap();
        test.addMove("236p", "hadouken");
        test.addMove("214k", "Tatsumaki");
        base.addMove("236p", "hadouken");
        base.addMove("214k", "Tatsumaki");

        String testResult = test.getInput("Tatsumaki");

        assertEquals(testResult, "214k");
        assertEquals(base.toString(), test.toString());

    }

    /**
     * testing getting input with many items in map.
     */
    @Test
    public final void testGetInputMany() {
        FGParser test = new FGParser1();
        FGParser base = new FGParser1();

        test.newMovesMap();
        base.newMovesMap();

        test.addMove("236p", "hadouken");
        test.addMove("p", "punch");
        test.addMove("k", "kick");
        test.addMove("2p", "dick punch");
        test.addMove("214k", "Tatsumaki");
        base.addMove("236p", "hadouken");
        base.addMove("214k", "Tatsumaki");
        base.addMove("p", "punch");
        base.addMove("k", "kick");
        base.addMove("2p", "dick punch");

        String testResult = test.getInput("dick punch");

        assertEquals(testResult, "2p");
        assertEquals(base.toString(), test.toString());

    }

    /**
     * testing getting name with one item in map.
     */
    @Test
    public final void testGetNameSingular() {
        FGParser test = new FGParser1();
        FGParser base = new FGParser1();

        test.newMovesMap();
        base.newMovesMap();
        test.addMove("236p", "hadouken");
        base.addMove("236p", "hadouken");

        String testResult = test.getMove("236p");

        assertEquals(testResult, "hadouken");
        assertEquals(base.toString(), test.toString());

    }

    /**
     * testing getting name with two items in map.
     */
    @Test
    public final void testGetNameTwo() {
        FGParser test = new FGParser1();
        FGParser base = new FGParser1();

        test.newMovesMap();
        base.newMovesMap();
        test.addMove("236p", "hadouken");
        test.addMove("214k", "Tatsumaki");
        base.addMove("236p", "hadouken");
        base.addMove("214k", "Tatsumaki");

        String testResult = test.getMove("214k");

        assertEquals(testResult, "Tatsumaki");
        assertEquals(base.toString(), test.toString());

    }

    /**
     * testing getting name with many items in map.
     */
    @Test
    public final void testGetNameMany() {
        FGParser test = new FGParser1();
        FGParser base = new FGParser1();

        test.newMovesMap();
        base.newMovesMap();

        test.addMove("236p", "hadouken");
        test.addMove("p", "punch");
        test.addMove("k", "kick");
        test.addMove("2p", "dick punch");
        test.addMove("214k", "Tatsumaki");
        base.addMove("236p", "hadouken");
        base.addMove("214k", "Tatsumaki");
        base.addMove("p", "punch");
        base.addMove("k", "kick");
        base.addMove("2p", "dick punch");

        String testResult = test.getMove("p");

        assertEquals(testResult, "punch");
        assertEquals(base.toString(), test.toString());

    }

    /**
     * testing generate attack with only one move and no inputs.
     */
    @Test
    public final void testGenerateAttack1() {
        FGParser test = new FGParser1();
        FGParser base = new FGParser1();

        test.newMovesMap();
        base.newMovesMap();

        test.addMove("p", "punch");
        base.addMove("p", "punch");

        String testResult = test.generateAttack("p");

        assertEquals(test.toString(), base.toString());
        assertEquals(testResult, "punch");

    }

    /**
     * testing generate attack with two moves with different buttons.
     */
    @Test
    public final void testGenerateAttack2() {
        FGParser test = new FGParser1();
        FGParser base = new FGParser1();

        test.newMovesMap();
        base.newMovesMap();

        test.addMove("p", "punch");
        base.addMove("p", "punch");

        test.addMove("k", "kick");
        base.addMove("k", "kick");

        String testResult = test.generateAttack("k");

        assertEquals(test.toString(), base.toString());
        assertEquals(testResult, "kick");

    }

    /**
     * testing generate attack with two moves with same button, with one being
     * impossible.
     */
    @Test
    public final void testGenerateAttack3() {
        FGParser test = new FGParser1();
        FGParser base = new FGParser1();

        test.newMovesMap();
        base.newMovesMap();

        test.addMove("p", "punch");
        base.addMove("p", "punch");

        test.addMove("236p", "hadouken");
        base.addMove("236p", "hadouken");

        String testResult = test.generateAttack("p");

        assertEquals(test.toString(), base.toString());
        assertEquals(testResult, "punch");

    }

    /**
     * testing generate attack with button that does not exist.
     */
    @Test
    public final void testGenerateAttack4() {
        FGParser test = new FGParser1();
        FGParser base = new FGParser1();

        test.newMovesMap();
        base.newMovesMap();

        test.addMove("p", "punch");
        base.addMove("p", "punch");

        test.addMove("236p", "hadouken");
        base.addMove("236p", "hadouken");

        String testResult = test.generateAttack("k");

        assertEquals(test.toString(), base.toString());
        assertEquals(testResult, "None");

    }

    /**
     * testing generate attack with two moves, with possibility for most complex
     * move.
     */
    @Test
    public final void testGenerateAttack5() {
        FGParser test = new FGParser1();
        FGParser base = new FGParser1();

        test.newMovesMap();
        base.newMovesMap();

        test.addMove("p", "punch");
        base.addMove("p", "punch");

        test.addMove("236p", "hadouken");
        base.addMove("236p", "hadouken");

        String sampleInput = "236";
        for (int i = 0; i < sampleInput.length(); i++) {
            test.addInput(Integer.parseInt(sampleInput.substring(i, i + 1)));
        }

        String testResult = test.generateAttack("p");

        assertEquals(test.toString(), base.toString());
        assertEquals(testResult, "hadouken");

    }

    /**
     * testing generate attack occuring twice.
     */
    @Test
    public final void testGenerateAttack6() {
        FGParser test = new FGParser1();
        FGParser base = new FGParser1();

        test.newMovesMap();
        base.newMovesMap();

        test.addMove("p", "punch");
        base.addMove("p", "punch");

        test.addMove("236p", "hadouken");
        base.addMove("236p", "hadouken");

        test.addMove("k", "kick");
        base.addMove("k", "kick");

        String sampleInput = "236";
        for (int i = 0; i < sampleInput.length(); i++) {
            test.addInput(Integer.parseInt(sampleInput.substring(i, i + 1)));
        }

        String testResult1 = test.generateAttack("p");
        String testResult2 = test.generateAttack("k");

        assertEquals(test.toString(), base.toString());
        assertEquals(testResult1, "hadouken");
        assertEquals(testResult2, "kick");

    }

}
