import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;

/**
 * @author Emily Canto
 * for my technical assessment January 2021
 *
 */
public class rotInterface {
    public static void main(String[] args) {

        //creates two text fields for the input
        JTextField stringField = new JTextField(15);
        JTextField numberField = new JTextField(5);

        //creates a new panel alongside two labels and the created fields
        JPanel firstPanel = new JPanel();
        firstPanel.add(new JLabel("String: "));
        firstPanel.add(stringField);
        firstPanel.add(new JLabel("Number: "));
        firstPanel.add(numberField);

        //second panel for the output
        JPanel secondPanel = new JPanel();
        JLabel resultText = new JLabel("");
        secondPanel.add(resultText);

        String rotated = "";
        boolean running = true;

        // while loop detects if a closed option has been selected (using a boolean) on the panels
        // closes the program if running is false
        while (running) {

            //implements the panel with a default option and a plain ui
            int computeResult = JOptionPane.showConfirmDialog(null, firstPanel,
                    "Please input a string and a number to rotate by; ",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            // if the 'OK' option is clicked after inputting dialog, it will run through the if statement
            // adds the stringField value to a string to be used in the statement
            // does the same as above but with an integer, requiring a parseInt as swing outputs as a string by default
            if (computeResult == JOptionPane.OK_OPTION) {
                String input = stringField.getText();
                int rotate = Integer.parseInt(numberField.getText());
                for (int i = 0; i < input.length(); i++) {
                    if (Character.isUpperCase(input.charAt(i))) {
                        // Casts character to integer, shifts by the rotate value,
                        // keeping in range of uppercase alphabet which is 65 to 90 in ascii
                        rotated += (char) (((int) (input.charAt(i)) + rotate - 'A') % 26 + 'A');
                    } else if (Character.isLowerCase(input.charAt(i))) {
                        // as above but with lowercase, in the ascii range of 97 to 122
                        rotated += (char) (((int) (input.charAt(i)) + rotate - 'a') % 26 + 'a');
                    } else {
                        // if its neither lowercase or uppercase, it just rotates it as normal
                        rotated += input.charAt(i);
                    }
                }
                //closes the panel if the exit button is pressed
            } else if (computeResult == JOptionPane.CLOSED_OPTION) {
                System.exit(0);
            }

            // second panel created after the first one closes, sets the empty label to the result
            // and then outputs it to screen
            resultText.setText(rotated);
            int showResult = JOptionPane.showConfirmDialog(null, secondPanel,
                    ("Here is your rotated string"),
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if (showResult == JOptionPane.CLOSED_OPTION){
                System.exit(0);
            }
            // creates a panel similar to above but this time gives a yes or no option to loop the program
            // to enter another string/number
            int afterResult = JOptionPane.showOptionDialog(new JFrame(),
                    "Would you like to input another string?",
                    "Title", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
            if (afterResult == JOptionPane.YES_OPTION) {
                // sets the fields to empty to allow new inputs to be added
                stringField.setText("");
                numberField.setText("");
                rotated = "";
            } else {
                // if no option/exit selected, sets boolean to false and exits the program
                running = false;
            }
        }
    }
}
