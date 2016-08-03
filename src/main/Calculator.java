package main;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by joanna on 03.08.16.
 */
public class Calculator {
    public int add(String numbers) throws NegativesNotAllowedException {
        if(numbers.isEmpty()) {
            return 0;
        }
        String[] nums = getNumbers(numbers);
        checkForNegatives(nums);
        int sum = getSum(nums);
        return sum;
    }

    private void checkForNegatives(String[] nums) throws NegativesNotAllowedException {
        List<Integer> negatives = findNegatives(nums);
        if(!negatives.isEmpty()) {
            throw new NegativesNotAllowedException(createMessage(negatives));
        }
    }

    private String createMessage(List<Integer> negatives) {
        String msg = "";
        for(Integer n: negatives) {
            msg += n + ",";
        }
        msg = msg.substring(0, msg.length()-1);
        return msg;
    }

    private int getSum(String[] numbers) {
        int sum = 0;
        for(String num: numbers) {
            Integer n = Integer.parseInt(num);
            if(n <= 1000)
                sum+=n;
        }
        return sum;
    }

    private List<Integer> findNegatives(String[] nums) {
        List<Integer> negatives = new LinkedList<>();
        for(String num: nums) {
            Integer n = Integer.parseInt(num);
            if(n < 0) negatives.add(n);
        }
        return negatives;
    }

    private String[] getNumbers(String numbers) {
        String[] delimiters = getDelimiters(numbers);
        if(delimiters.length!=0) {
            numbers = numbers.split("\n",2)[1];
        }
        String regex = ",|\n";
        for(String d: delimiters) {
            regex += "|"+ Pattern.quote(d);
        }
        return numbers.split(regex);
    }

    private String[] getDelimiters(String numbers) {
        String[] delimiters = {};
        if(numbers.startsWith("//")) {
            String delimiter = numbers.split("\n", 2)[0].substring(2);
            delimiters = checkForMultipleDelimiters(delimiter);
        }
        return delimiters;
    }

    private String[] checkForMultipleDelimiters(String delimiter) {
        String[] delimiters = {};
        if(delimiter.startsWith("[") && delimiter.endsWith("]")) {
            delimiter = (delimiter.substring(1,delimiter.length()-1));
        }
        delimiters = delimiter.split(Pattern.quote("]["));
        return delimiters;
    }
}
