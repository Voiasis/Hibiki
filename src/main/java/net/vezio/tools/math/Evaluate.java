package net.vezio.tools.math;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluate implements Serializable {

    private String expression;
    private Stack<Coefficient> operandStack;
    private Stack<Character> operatorStack;
    private StringTokenizer tokens;
    private char[] unknownChars;

    public Evaluate(String expression, char[] unknownChars) {
        this.expression = expression;
        operandStack = new Stack<Coefficient>();
        operatorStack = new Stack<Character>();
        this.unknownChars = unknownChars;
    }

    private boolean isUnknown(char c) {
        for (int j = 0; j < unknownChars.length; j++) {
            if (c == unknownChars[j]) {
                return true;
            }
        }
        return false;
    }

    public boolean isNumber(char c) {
        return '0' <= c && '9' >= c;
    }

    public Coefficient evaluateExpression() {
        expression = expression.replace(" ", "");
        expression = expression.replace(")(", ")*(");
        expression = expression.replace("(+", "(");
        expression = expression.replace("(-", "(0-");
        if (expression.startsWith("+")) {
            expression = new StringBuilder(expression).delete(0, 1).toString();
        }
        if (expression.startsWith("-")) {
            expression = new StringBuilder(expression).insert(0, "0").toString();
        }
        for (int i = 0; i < expression.length() - 1; i++) {
            if (isNumber(expression.charAt(i))) {
                if (isUnknown(expression.charAt(i + 1))) {
                    expression = new StringBuilder(expression).insert(i + 1, '*').toString();
                    i += 1;
                }
                if (expression.charAt(i + 1) == '(') {
                    expression = new StringBuilder(expression).insert(i + 1, '*').toString();
                    i += 1;
                }
            } else if (isUnknown(expression.charAt(i))) {
                if (expression.charAt(i + 1) == '(') {
                    expression = new StringBuilder(expression).insert(i + 1, '*').toString();
                    i += 1;
                }
            } else if (expression.charAt(i) == ')') {
                if (isUnknown(expression.charAt(i + 1))) {
                    expression = new StringBuilder(expression).insert(i + 1, '*').toString();
                    i += 1;
                }
                if (isNumber(expression.charAt(i + 1))) {
                    expression = new StringBuilder(expression).insert(i + 1, '*').toString();
                    i += 1;
                }
            }
        }
        tokens = new StringTokenizer(expression, "()+-/*", true);
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            if (token.length() == 0)
                continue;
            else if (token.charAt(0) == '+' || token.charAt(0) == '-') {
                while (!operatorStack.isEmpty() &&
                        (operatorStack.peek() == '+' ||
                                operatorStack.peek() == '-' ||
                                operatorStack.peek() == '*' ||
                                operatorStack.peek() == '/')) {
                    processAnOperator();
                }
                operatorStack.push(token.charAt(0));
            } else if (token.charAt(0) == '*' || token.charAt(0) == '/') {
                while (!operatorStack.isEmpty() &&
                        (operatorStack.peek() == '*' ||
                                operatorStack.peek() == '/')) {
                    processAnOperator();
                }
                operatorStack.push(token.charAt(0));
            } else if (token.charAt(0) == '(') {
                operatorStack.push('(');
            } else if (token.charAt(0) == ')') {
                while (operatorStack.peek() != '(') {
                    processAnOperator();
                }
                operatorStack.pop();
            } else {
                if ('0' <= token.charAt(0) && '9' >= token.charAt(0)) {
                    if (token.contains(".")) {
                        operandStack.push(new Coefficient(toRational(token), unknownChars));
                    } else {
                        operandStack.push(new Coefficient(new Rational(new BigInteger(token), new BigInteger("1")), unknownChars));
                    }
                } else {
                    operandStack.push(new Coefficient(new Rational(new BigInteger("1"), new BigInteger("1")), token.charAt(0), unknownChars));
                }
            }
        }
        while (!operatorStack.isEmpty()) {
            processAnOperator();
        }
        return operandStack.pop();
    }

    public void processAnOperator() {
        char op = operatorStack.pop();
        Coefficient op1 = operandStack.pop();
        Coefficient op2 = operandStack.pop();
        if (op == '+') {
            operandStack.push(op2.add(op1));
        } else if (op == '-') {
            operandStack.push(op2.subtract(op1));
        } else if (op == '*') {
            operandStack.push(op2.multiply(op1));
        } else if (op == '/') {
            operandStack.push(op2.divide(op1));
        }
    }

    public static Rational toRational(String token) {
        String decimal = token.trim();
        while (decimal.endsWith("0")) {
            decimal = decimal.substring(0, decimal.length() - 1);
        }
        int decimalPoint = decimal.length() - decimal.indexOf('.');
        String decimals = "1";
        for (int i = 1; i < decimalPoint; i++) {
            decimals += "0";
        }
        return new Rational(new BigInteger(token.replace(".", "")), new BigInteger(decimals));
    }

}