
/**
 * FG Comipler Kernel component with primary methods. Includes capability to
 * manage inputs.
 *
 * @initially <pre>
 * ():
 *  ensures
 *      this = {};
 * </pre>
 *
 */
public interface FGParserKernel {

    /**
     * Adds {@code k} to the back of {@code this}.
     *
     * @param k
     *            the {@code int} to be added to the end of the queue
     * @updates this
     * @requires {@code 1 <= k <= 9}
     * @ensures this = #this + 10;
     */
    void addInput(int k);

    /**
     * Removes and discards inputs from {@code this} until length <= 7.
     *
     * @updates this
     * @ensures this.length < 7
     */
    void extractOldInputs();

    /**
     * Returns {@code this} in form of a string, ordered oldest to newest.
     *
     * @return String interpertation of {@code this} ordered oldest to newest.
     * @updates this
     * @requires this.length >= 0
     * @ensures getActiveInputs = {@code this}
     */
    String getActiveInputs();

    /**
     * Clears {@code this}.
     * 
     * @clears this
     * @ensures this = {}
     */
    void clearInputs();

}
