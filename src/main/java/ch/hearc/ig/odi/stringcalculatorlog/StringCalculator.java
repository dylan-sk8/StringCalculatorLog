package ch.hearc.ig.odi.stringcalculatorlog;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

    private static Logger LOGGER = LogManager.getLogger(StringCalculator.class.getName());

    // Question 1.
    public StringCalculator() {
    }

    // Question 2.
//    public StringCalculator() {
//
//    }

    //Question 3.
//    public StringCalculator() {
//        try {
//            FileHandler fl = new FileHandler("log.log", true);
//            fl.setFormatter(new SimpleFormatter());
//            LOGGER.addHandler(fl);
//        } catch (IOException e) {
//            throw new IllegalStateException("Could not add FileHandler", e);
//        }
//    }



    public int add(final String numbers) {
        String delimiter = ",|\n";
        String numbersWithoutDelimiter = numbers;
        if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf("//") + 2;
            delimiter = numbers.substring(delimiterIndex, delimiterIndex + 1);
            LOGGER.info(String.format("Delimiter is '%s'", delimiter));
            numbersWithoutDelimiter = numbers.substring(numbers.indexOf("\n") + 1);
        }
        return add(numbersWithoutDelimiter, delimiter);
    }

    private int add(final String numbers, final String delimiter) {
        int returnValue = 0;
        List<Integer> negativeNumbers = new ArrayList();
        String[] numbersArray = numbers.split(delimiter);
        for (String number : numbersArray) {
            if (!(number.trim().length() == 0)) {
                Integer numberInt = Integer.parseInt(number);
                if (numberInt < 0){
                    negativeNumbers.add(numberInt);
                }
                else if (numberInt <= 1000)
                    returnValue += Integer.parseInt(number.trim());
                else
                    LOGGER.warn(String.format("Number greater than 1000 detected [%s], it'll not be taken into account.", numberInt));
            }
        }

        if (negativeNumbers.size() > 0) {
            LOGGER.fatal("Negative not allowed: " + negativeNumbers.toString());
            throw new RuntimeException("Negative not allowed: " + negativeNumbers.toString());
        }

        return returnValue;
    }

}
