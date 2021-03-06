package ch.hearc.ig.odi.stringcalculatorlog;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTest {

    private StringCalculator sc;

    //Requirement 1

    //this test is deleted at requirement 4
//    @Test(expected = RuntimeException.class)
//    public void whenMoreThan2NumbersAreUsedThenExceptionIsThrown() {
//        sc.add("1,2,3");
//    }

    @Before
    public void setUp() {
        this.sc = new StringCalculator();
    }

    @Test
    public void when2NumbersAreUsedThenNoExceptionIsThrown() {
        sc.add("1,2");
        Assert.assertTrue("When 2 numbers are used, there is no exception", true);
    }
    @Test(expected = RuntimeException.class)
    public void whenNonNumberIsUsedThenExceptionIsThrown() {
        sc.add("1,X");
    }

    //Requirement 2
    @Test
    public void whenStringIsEmptyThenZero() {
        Assert.assertEquals("When the string is empty then zero is returned", 0, sc.add(""));
    }

    // Requirement 3
    @Test
    public void whenOneNumberIsUsedThenReturnValueIsThatSameNumber() {
        Assert.assertEquals("When a single number is used, the value returned is the number himself",4, sc.add("4"));
    }

    @Test
    public void whenTwoNumbersAreUsedThenReturnValueIsTheirSum() {
        Assert.assertEquals("When two number are used, the value returned is their sum", 7, sc.add("3,4"));
    }

    // Requirement 4
    @Test
    public void whenAnyNumberOfNumbersIsUsedThenReturnValuesAreTheirSums() {
        Assert.assertEquals("When multiple numbers are used, the value returned is their sum", 3+6+15+18+46+33, sc.add("3,6,15,18,46,33"));
    }

    // Requirement 5
    @Test
    public void whenNewLineIsUsedBetweenNumbersThenReturnValuesAreTheirSums() {
        Assert.assertEquals("When a new line is used between numbers, the value returned is their sum", 3+7+8, sc.add("3,7\n8"));
    }

    // Requirement 6
    @Test
    public void whenDelimiterIsSpecifiedThenItIsUsedToSeparateNumbers() {
        Assert.assertEquals("When a delimiter is specified, it is used to separate the numbers," +
                "the value returned is their sum",9+5+2, sc.add("//;\n9;5;2"));
    }

    // Requirement 7
    @Test(expected = RuntimeException.class)
    public void whenNegativeNumberIsUsedThenRuntimeExceptionIsThrown() {
        sc.add("9,3,-2,3");
    }
    @Test
    public void whenNegativeNumbersAreUsedThenRuntimeExceptionIsThrown() {
        RuntimeException exception = null;
        try {
            sc.add("3,4,-2,3,-5,2,-1");
        } catch (RuntimeException e) {
            exception = e;
        }
        Assert.assertEquals("When a negative number (or more) are used, an exception is thrown containing " +
                "all the negative numbers", "Negative not allowed: [-2, -5, -1]", exception.getMessage());
    }

    // Requirement 8
    @Test
    public void whenOneOrMoreNumbersAreGreaterThan1000IsUsedThenItIsNotIncludedInSum() {
        Assert.assertEquals("When one or more number are greater than 1000, they are not included" +
                "in the sum", 1003, sc.add("1000,1,1001,2,1203"));
    }
}
