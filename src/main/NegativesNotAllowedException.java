package main;

/**
 * Created by joanna on 03.08.16.
 */
public class NegativesNotAllowedException extends Exception {
    public NegativesNotAllowedException(String message) {
        super(message);
    }
}
