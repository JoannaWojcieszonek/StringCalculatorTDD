package test;

import main.Calculator;
import main.NegativesNotAllowedException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by joanna on 03.08.16.
 */
public class CalculatorTest {
    private Calculator calculator;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        calculator = new Calculator();
    }

    @Test
    public void shouldReturnZeroForEmptyString() throws Exception {
        //given
        String numbers = "";
        //when
        int result = calculator.add(numbers);
        //then
        Assert.assertEquals(result, 0);
    }

    @Test
    public void oneNumber() throws Exception {
        //given
        String numbers = "5";
        //when
        int result = calculator.add(numbers);
        //then
        Assert.assertEquals(result, 5);
    }

    @Test
    public void twoNumbers() throws Exception{
        //given
        String numbers = "2,3";
        //when
        int result = calculator.add(numbers);
        //then
        Assert.assertEquals(result,5);
    }

    @Test
    public void allNumbers() throws Exception{
        //given
        String numbers = "1,2,3,4,5,5";
        //when
        int result = calculator.add(numbers);
        //then
        Assert.assertEquals(result, 20);
    }

    @Test
    public void allowNewLinesBetweenNumbers() throws Exception{
        //given
        String numbers = "1\n2\n4";
        //when
        int result = calculator.add(numbers);
        //then
        Assert.assertEquals(result, 7);
    }

    @Test
    public void supportDifferentDelimiters() throws Exception{
        //given
        String numbers = "//;\n1;2";
        //when
        int result = calculator.add(numbers);
        //then
        Assert.assertEquals(result, 3);
    }

    @Test
    public void negativesNotAllowed() throws Exception{
        //given
        String numbers = "-3,2,-5";

        //then
        expectedException.expect(NegativesNotAllowedException.class);
        expectedException.expectMessage("-3,-5");

        //when
        int result = calculator.add(numbers);
    }

    @Test
    public void ignoreBiggerNumbers() throws Exception {
        //given
        String numbers = "1000,1001,2";
        //when
        int result = calculator.add(numbers);
        //then
        Assert.assertEquals(result, 1002);
    }

    @Test
    public void anyLengthDelimiters() throws Exception {
        //given
        String numbers = "//[***]\n1***3***4";
        //when
        int result = calculator.add(numbers);
        //then
        Assert.assertEquals(result, 8);
    }

    @Test
    public void multipleDelimiters() throws Exception {
        //given
        String numbers = "//[*][%]\n1*2%3";
        //when
        int result = calculator.add(numbers);
        //then
        Assert.assertEquals(result, 6);
    }

    @Test
    public void multipleAnyLengthDelimiters() throws Exception {
        //given
        String numbers = "//[***][//][a][#]\n1***2//3a4#5";
        //when
        int result = calculator.add(numbers);
        //then
        Assert.assertEquals(result, 15);
    }
}
