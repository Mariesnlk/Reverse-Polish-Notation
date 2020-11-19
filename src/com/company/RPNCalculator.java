package com.company;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RPNCalculator {

    final static String EXPRESSION = "-?[0-9.]+|[A-Za-z]+|[-+*/()^]";

    public double RPNCalculate(Queue<String> expressions) {
        Stack<Double> stack = new Stack<>();
        double op1;//operand
        double op2;
        double currVal = 0.0;
        double convertToRadians = Math.toRadians(stack.pop());
        for (String exp : expressions) {
            if (isNumber(exp)) stack.push(Double.parseDouble(exp));
            else if (exp.length() == 1) {
                switch (exp.charAt(0)) {
                    case '+':
                        op1 = stack.pop();
                        op2 = stack.pop();
                        currVal = op1 + op2;
                        stack.push(currVal);
                        break;
                    case '-':
                        op1 = stack.pop();
                        op2 = stack.pop();
                        currVal = op1 - op2;
                        stack.push(currVal);
                        break;
                    case '^':
                        op1 = stack.pop();
                        op2 = stack.pop();
                        currVal = Math.pow(op2, op1);
                        stack.push(currVal);
                        break;
                    case '*':
                        op1 = stack.pop();
                        op2 = stack.pop();
                        currVal = op1 * op2;
                        stack.push(currVal);
                        break;
                    case '/':
                        op1 = stack.pop();
                        op2 = stack.pop();
                        currVal = op1 / op2;
                        stack.push(currVal);
                }
            } else if (exp.length() == 3) {
                switch (exp) {
                    case "sin":
                        currVal = Math.sin(convertToRadians);
                        stack.push(currVal);
                        break;
                    case "cos":
                        currVal = Math.cos(convertToRadians);
                        stack.push(currVal);
                        break;
                    case "tg":
                        currVal = Math.tan(convertToRadians);
                        stack.push(currVal);
                        break;
                    case "ctg":
                        currVal = 1.0 / Math.tan(convertToRadians);
                        stack.push(currVal);
                        break;
                }
            }
            System.out.println("current: " + exp + ", stack: " + stack);

        }

        return stack.peek();

    }

    public Queue<String> convertToRPN(List<String> exp){
        Queue<String> result = new LinkedList<>();
        Stack<String> stack = new Stack<>();

        return result;
    }


    public static List<String> splitLine(String line) {
        Pattern pattern = Pattern.compile(EXPRESSION);
        Matcher match = pattern.matcher(line);
        List<String> result = new ArrayList<>();
        while (match.find()) {
            result.add(match.group());
        }
        return result;
    }

    private boolean isNumber(String num) {
        return num.matches("-?[0-9.]+");
    }
}
