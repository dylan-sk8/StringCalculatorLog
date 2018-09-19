package ch.hearc.ig.odi.stringcalculatorlog;


import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StringCalculator {

    private static Logger LOGGER = Logger.getLogger(StringCalculator.class.getName());

    public static int add(final String numbers) {
        String delimiter = ",|\n";
        String numbersWithoutDelimiter = numbers;
        if (numbers.startsWith("//")) {
            LOGGER.log(Level.INFO, "Delimiter detected");
            int delimiterIndex = numbers.indexOf("//") + 2;
            delimiter = numbers.substring(delimiterIndex, delimiterIndex + 1);
            LOGGER.info(String.format("Delimiter is '%s'", delimiter));
            numbersWithoutDelimiter = numbers.substring(numbers.indexOf("\n") + 1);
        }
        return add(numbersWithoutDelimiter, delimiter);
    }

    private static int add(final String numbers, final String delimiter) {
        int returnValue = 0;
        List<Integer> negativeNumbers = new ArrayList();
        String[] numbersArray = numbers.split(delimiter);
        for (String number : numbersArray) {
            if (!(number.trim().length() == 0)) {
                Integer numberInt = Integer.parseInt(number);
                if (numberInt < 0){
                    negativeNumbers.add(numberInt);
                    LOGGER.warning("Negative number detected: " + numberInt);
                }
                else if (numberInt <= 1000)
                    returnValue += Integer.parseInt(number.trim());
            }
        }

        if (negativeNumbers.size() > 0) {
            LOGGER.log(Level.SEVERE, "Negative not allowed: " + negativeNumbers.toString());
            throw new RuntimeException("Negative not allowed: " + negativeNumbers.toString());
        }

        return returnValue;
    }

}
