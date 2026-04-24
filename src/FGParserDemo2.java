import components.FGParser.FGParser;
import components.FGParser.FGParser1;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * A Demo of FGParser, with it demonstrating it's capabilities in a less
 * conventional situation, where it is used to parse a 3 digit numerical input
 * into number text instead.
 *
 * @author Daniel Shen
 */
public class FGParserDemo2 {

    /**
     * No argument constructor.
     */
    public FGParserDemo2() {
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
        final int three = 3;
        final int twenty = 20;
        final int ten = 10;
        final int oneHundred = 100;

        parser.addMove("n", " ");
        parser.addMove("1n", "one");
        parser.addMove("2n", "two");
        parser.addMove("3n", "three");
        parser.addMove("4n", "four");
        parser.addMove("5n", "five");
        parser.addMove("6n", "six");
        parser.addMove("7n", "seven");
        parser.addMove("8n", "eight");
        parser.addMove("9n", "nine");
        parser.addMove("u", "ten");
        parser.addMove("11n", "eleven");
        parser.addMove("12n", "twelve");
        parser.addMove("13n", "thirteen");
        parser.addMove("14n", "fourteen");
        parser.addMove("15n", "fifteen");
        parser.addMove("16n", "sixteen");
        parser.addMove("17n", "seventeen");
        parser.addMove("18n", "eighteen");
        parser.addMove("19n", "nineteen");

        parser.addMove("h", "hundred");

        parser.addMove("2d", "twenty");
        parser.addMove("3d", "thirty");
        parser.addMove("4d", "fourty");
        parser.addMove("5d", "fifty");
        parser.addMove("6d", "sixty");
        parser.addMove("7d", "seventy");
        parser.addMove("8d", "eighty");
        parser.addMove("9d", "ninety");

        parser.addMove("n", "  ");

        parser.addMove("0", ""); //genuine jank ahhh code wtf is this.
        //Ok i can actually explain. here,
        //0 is actually not a number but a key, similar to d or n.
        //it returns nothing because when zero appears in a number,
        // you don't return anything.

        out.println("Please enter a 3 digit or less number");
        input = in.nextLine();
        while (!(input.equals(""))) {
            boolean isFormatted = input.length() <= three;
            int formattedTestIndex = 0;
            while (isFormatted && formattedTestIndex < input.length()) {
                isFormatted = digits
                        .indexOf(input.substring(formattedTestIndex,
                                formattedTestIndex + 1)) >= 0
                        || input.substring(formattedTestIndex,
                                formattedTestIndex + 1).equals("0");
                formattedTestIndex++;
            }

            if (isFormatted && Integer.parseInt(input) != 0) {
                String finalInput = "";
                if (Integer.parseInt(input) == ten) {
                    // I'M HARDCODING 10
                    // I CAN'T FIND A SOLUTION
                    // I HAVE TO PUSH IN LIKE 5 MINUTES
                    // AAAAAAAAAHHHHHHHH
                    finalInput = "u";
                } else if (Integer.parseInt(input) < twenty) {
                    finalInput = input + "n";
                } else if (Integer.parseInt(input) < oneHundred) {
                    finalInput = input.substring(0, 1) + "d"
                            + input.substring(1) + "n";
                } else {
                    if (Integer.parseInt(input.substring(1)) == ten) {
                        finalInput = input.substring(0, 1) + "nh" + "u";
                    } else if (Integer.parseInt(input.substring(1)) < twenty) {
                        finalInput = input.substring(0, 1) + "nh"
                                + input.substring(1) + "n";
                    } else {
                        finalInput = input.substring(0, 1) + "nh"
                                + input.substring(1, 2) + "d"
                                + input.substring(2) + "n";
                    }
                }
                out.println(finalInput);

                for (int i = 0; i < finalInput.length(); i++) {
                    String curInput = finalInput.substring(i, i + 1);
                    if (digits.indexOf(curInput) >= 0) {
                        parser.addInput(Integer.parseInt(curInput));
                    } else {
                        out.print(parser.generateAttack(curInput) + " ");
                    }
                }
                out.println("");
            } else if (Integer.parseInt(input) == 0) {
                out.println("Zero");
            } else {
                out.println("Invalid Input.");
            }
            out.println("Please enter an input chain");
            input = in.nextLine();
        }

        out.close();
        in.close();
    }
}
