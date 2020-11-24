package com.company;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.Collections.unmodifiableMap;

public class RPNCalculator {

    public static final String EXPRESSION = "-?[0-9.]+|[A-Za-z]+|[-+*/()^]";
    public static Map<String, Integer> precedence;

    public double RPNCalculate(Queue<String> expressions) {
        Stack<Double> stack = new Stack<>();
        double op1;//operand
        double op2;
        double currVal;
        for (String exp : expressions) {
            if (isNumber(exp)) stack.push(Double.parseDouble(exp));
            else if (exp.length() == 1) {
                switch (exp.charAt(0)) {
                    case '+' -> {
                        op1 = stack.pop();
                        op2 = stack.pop();
                        currVal = op1 + op2;
                        stack.push(currVal);
                    }
                    case '-' -> {
                        op1 = stack.pop();
                        op2 = stack.pop();
                        currVal = op1 - op2;
                        stack.push(currVal);
                    }
                    case '^' -> {
                        op1 = stack.pop();
                        op2 = stack.pop();
                        currVal = Math.pow(op2, op1);
                        stack.push(currVal);
                    }
                    case '*' -> {
                        op1 = stack.pop();
                        op2 = stack.pop();
                        currVal = op1 * op2;
                        stack.push(currVal);
                    }
                    case '/' -> {
                        op1 = stack.pop();
                        op2 = stack.pop();
                        currVal = op1 / op2;
                        stack.push(currVal);
                    }
                }
            } else if (exp.length() == 3) {
                double convertToRadians = Math.toRadians(stack.pop());
                switch (exp) {
                    case "sin" -> {
                        currVal = Math.sin(convertToRadians);
                        stack.push(currVal);
                    }
                    case "cos" -> {
                        currVal = Math.cos(convertToRadians);
                        stack.push(currVal);
                    }
                    case "tan" -> {
                        currVal = Math.tan(convertToRadians);
                        stack.push(currVal);
                    }
                    case "ctg" -> {
                        currVal = 1.0 / Math.tan(convertToRadians);
                        stack.push(currVal);
                    }
                }
            }
            System.out.println("current: " + exp + ", stack: " + stack);

        }

        return stack.peek();

    }

    public Queue<String> convertToRPN(List<String> exp) {
        Queue<String> result = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        for (String token : exp) {
            if ("(".equals(token)) {
                stack.push(token);
                continue;
            }
            if (")".equals(token)) {
                while (!"(".equals(stack.peek()))
                    result.add(stack.pop());
                stack.pop();
                continue;
            }

            if (precedence.containsKey(token)) {
                while (!stack.empty() && precedence.get(token) <= precedence.get(stack.peek())) {
                    result.add(stack.pop());
                }
                stack.push(token);
                continue;
            }
            result.add(token);
        }

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    static {
        Map<String, Integer> temp = new HashMap<>();
        temp.put("sin", 5);
        temp.put("cos", 5);
        temp.put("tan", 5);
        temp.put("ctg", 5);
        temp.put("^", 4);
        temp.put("/", 3);
        temp.put("*", 3);
        temp.put("+", 2);
        temp.put("-", 2);
        temp.put(")", 1);
        temp.put("(", 0);
        precedence = unmodifiableMap(temp);
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
