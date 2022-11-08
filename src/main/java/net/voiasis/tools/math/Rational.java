package net.voiasis.tools.math;

import java.io.Serializable;
import java.math.BigInteger;

public class Rational implements Serializable {

    private BigInteger numerator;
    private BigInteger denominator;

    public Rational(BigInteger numerator, BigInteger denominator) {
        BigInteger gcd = numerator.gcd(denominator);
        this.numerator = numerator.divide(gcd);
        this.denominator = denominator.divide(gcd);
        if (this.denominator.compareTo(new BigInteger("0")) < 0) {
            this.numerator = this.numerator.negate();
            this.denominator = this.denominator.negate();
        }
    }

    public Rational add(Rational num2) {
        return new Rational(numerator.multiply(num2.denominator).add(denominator.multiply(num2.numerator)), denominator.multiply(num2.denominator));
    }

    public Rational subtract(Rational num2) {
        return new Rational(numerator.multiply(num2.denominator).subtract(denominator.multiply(num2.numerator)), denominator.multiply(num2.denominator));
    }

    public Rational multiply(Rational num2) {
        return new Rational(numerator.multiply(num2.numerator), denominator.multiply(num2.denominator));
    }

    public Rational divide(Rational num2) {
        if (num2.numerator.equals(new BigInteger("0"))) {
            throw new ArithmeticException("/ by 0");
        }
        return new Rational(numerator.multiply(num2.denominator), denominator.multiply(num2.numerator));
    }

    public boolean equals(long l) {
        return l == 0 ? numerator.equals(new BigInteger("0")) : numerator.equals(new BigInteger("" + l)) && denominator.equals(new BigInteger("1"));
    }

    public BigInteger getNumerator() {
        return numerator;
    }

    public BigInteger getDenominator() {
        return denominator;
    }

    public Rational negate() {
        return new Rational(numerator.negate(), denominator);
    }

    public String toString() {
        return denominator.equals(new BigInteger("1")) ? numerator + "" : numerator + "/" + denominator;
    }

}
