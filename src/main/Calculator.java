package main;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by joanna on 03.08.16.
 */
public class Calculator {
    public int add(String numbers) throws NegativesNotAllowedException {
        if(numbers.isEmpty()) {
            return 0;
        }
        String[] seperateNumbers = getSeperateNumbers(numbers);
        checkForNegatives(seperateNumbers);
        return getSum(seperateNumbers);
    }

    private void checkForNegatives(String[] nums) throws NegativesNotAllowedException {
        List<Integer> negatives = findNegatives(nums);
        if(!negatives.isEmpty()) {
            throw new NegativesNotAllowedException(createMessage(negatives));
        }
    }

    private String createMessage(List<Integer> negatives) {
        return negatives.stream()
                .map(i -> i.toString())
                .collect(Collectors.joining(","));
    }

    private int getSum(String[] numbers) {
        return Arrays.stream(numbers)
                .mapToInt(Integer::parseInt)
                .filter(n -> n <= 1000)
                .sum();
    }

    private List<Integer> findNegatives(String[] numbers) {
        return Arrays.stream(numbers)
                .map(Integer::parseInt)
                .filter(n -> n < 0)
                .collect(Collectors.toList());
    }

    private String[] getSeperateNumbers(String numbers) {
        String[] delimiters = getDelimiters(numbers);
        if(delimiters.length!=0) {
            numbers = numbers.split("\n",2)[1];
        }
        String regex = ",|\n" + Arrays
                .stream(delimiters)
                .map(d -> "|"+ Pattern.quote(d))
                .collect(Collectors.joining());
        return numbers.split(regex);
    }

    private String[] getDelimiters(String numbers) {
        String[] delimiters = {};
        if(numbers.startsWith("//")) {
            String expression = numbers.split("\n", 2)[0].substring(2);
            delimiters = checkForMultipleDelimiters(expression);
        }
        return delimiters;
    }

    private String[] checkForMultipleDelimiters(String expression) {
        if(expression.startsWith("[") && expression.endsWith("]")) {
            expression = (expression.substring(1, expression.length()-1));
        }
        String[] delimiters = expression.split(Pattern.quote("]["));
        return delimiters;
    }
}
