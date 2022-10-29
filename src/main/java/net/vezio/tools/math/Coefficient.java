package net.vezio.tools.math;

import java.io.Serializable;
import java.math.BigInteger;

public class Coefficient implements Serializable {

    char unknownChars[];
    Rational number;
    Rational[] unknown;

    public Coefficient(Rational num, Rational[] unknown, char[] chars) {
        unknownChars = chars;
        this.unknown = new Rational[unknownChars.length];
        this.number = num;
        this.unknown = unknown;
    }

    public Coefficient(Rational num, char[] chars) {
        unknownChars = chars;
        unknown = new Rational[unknownChars.length];
        number = num;
        for (int i = 0; i < unknownChars.length; i++) {
            unknown[i] = new Rational(new BigInteger("0"), new BigInteger("1"));
        }
    }

    public Coefficient(Rational num, Rational unk, char unkchar, char[] chars) {
        unknownChars = chars;
        unknown = new Rational[unknownChars.length];
        number = num;
        for (int i = 0; i < unknownChars.length; i++) {
            if (unknownChars[i] == unkchar)
                unknown[i] = unk;
            else
                unknown[i] = new Rational(new BigInteger("0"), new BigInteger("1"));
        }
    }

    public Coefficient(Rational unk, char unkchar, char[] chars) {
        unknownChars = chars;
        unknown = new Rational[unknownChars.length];
        number = new Rational(new BigInteger("0"), new BigInteger("1"));
        for (int i = 0; i < unknownChars.length; i++) {
            if (unknownChars[i] == unkchar)
                unknown[i] = unk;
            else
                unknown[i] = new Rational(new BigInteger("0"), new BigInteger("1"));
        }
    }

    public Coefficient add(Coefficient coefficient) {
        Rational[] unk = new Rational[unknown.length];
        for (int i = 0; i < unknown.length; i++) {
            unk[i] = unknown[i].add(coefficient.unknown[i]);
        }
        return new Coefficient(number.add(coefficient.number), unk, unknownChars);
    }

    public Coefficient subtract(Coefficient coefficient) {
        Rational[] unk = new Rational[unknown.length];
        for (int i = 0; i < unknown.length; i++) {
            unk[i] = unknown[i].subtract(coefficient.unknown[i]);
        }
        return new Coefficient(number.subtract(coefficient.number), unk, unknownChars);
    }

    public Coefficient multiply(Coefficient coefficient) {
        if (coefficient.isPureNumber()) {
            Rational[] unk = new Rational[unknown.length];
            for (int i = 0; i < unknown.length; i++) {
                unk[i] = unknown[i].multiply(coefficient.number);
            }
            return new Coefficient(number.multiply(coefficient.number), unk, unknownChars);
        } else if (isPureNumber()) {
            Rational[] unk = new Rational[unknown.length];
            for (int i = 0; i < unknown.length; i++) {
                unk[i] = coefficient.unknown[i].multiply(number);
            }
            return new Coefficient(coefficient.number.multiply(number), unk, unknownChars);
        } else {
            throw new ArithmeticException("Not a valid linear equation");
        }
    }

    public Coefficient divide(Coefficient coefficient) {
        if (!coefficient.isPureNumber()) {
            throw new ArithmeticException("Not a valid linear equation");
        }
        Rational[] unk = new Rational[unknown.length];
        for (int i = 0; i < unknown.length; i++) {
            unk[i] = unknown[i].divide(coefficient.number);
        }
        return new Coefficient(number.divide(coefficient.number), unk, unknownChars);
    }

    public boolean isPureNumber() {
        for (int i = 0; i < unknown.length; i++) {
            if (!unknown[i].equals(0)) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        String s = "" + number;
        for (int i = 0; i < unknown.length; i++) {
            s += " + " + unknown[i] + unknownChars[i];
        }
        return s;
    }

}