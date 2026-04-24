import components.FGParser.FGParser;
import components.FGParser.FGParser1;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * A Demo of FGParser, with it demonstrating it's capabilities in a simple test
 * case where it is given a series of inputs that are then compiled into a
 * series of outputs.
 *
 * Nearly identical to proof of concept in terms of functionality.
 *
 * @author Daniel Shen
 */
public final class FGParserDemo1 {

    /**
     * No argument constructor.
     */
    public FGParserDemo1() {
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

        FGParser parser = new FGParser1();
        parser.newMovesMap();

        String input;
        String digits = "123456789";

        parser.addMove("p", "punch");
        parser.addMove("2p", "Crouch Punch");
        parser.addMove("k", "kick");
        parser.addMove("2k", "Crouch Kick");
        parser.addMove("236p", "Hadouken");
        parser.addMove("214p", "Evil Hadouken");
        parser.addMove("236k", "AIR TATSUMAKI!!!");
        parser.addMove("214k", "Shoryuken");
        parser.addMove("214214p", "Summon Zangief");

        out.println("Please enter an input chain");
        input = in.nextLine();
        while (!(input.equals(""))) {
            for (int i = 0; i < input.length(); i++) {
                String curInput = input.substring(i, i + 1);
                if (digits.indexOf(curInput) >= 0) {
                    parser.addInput(Integer.parseInt(curInput));
                    out.println(parser.getActiveInputs());
                } else {
                    out.println(parser.generateAttack(curInput));
                }
            }
            out.println("Please enter an input chain");
            input = in.nextLine();
        }

        out.close();
        in.close();
    }

}
