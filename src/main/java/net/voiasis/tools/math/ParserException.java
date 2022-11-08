package net.voiasis.tools.math;

public class ParserException extends RuntimeException {

    private RuntimeException ex;
    private int line;

    public ParserException(RuntimeException ex, int line) {
        super(ex.toString() + " occurred at parsing line " + (line + 1));
        this.ex = ex;
        this.line = line;
    }

    public RuntimeException getException() {
        return ex;
    }

    public int getLineOccurred() {
        return line;
    }

}