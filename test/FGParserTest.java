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

}
