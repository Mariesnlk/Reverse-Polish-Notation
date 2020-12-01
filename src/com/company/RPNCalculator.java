package com.company;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RPNCalculator {

    public static final String EXPRESSION = "-?[0-9.]+|[A-Za-z]+|[-+*/()\\[\\]^=,]";
    public static Map<String, Integer> precedence;

    public static  Map<String, Integer> presLoad(Map<String, Integer> precedence){
        Map<String, Integer> tablePrior = new HashMap<>();
        tablePrior.put("sin", 5);
        tablePrior.put("cos", 5);
        tablePrior.put("tan", 5);
        tablePrior.put("ctg", 5);
        tablePrior.put("^", 4);
        tablePrior.put("/", 3);
        tablePrior.put("*", 3);
        tablePrior.put("+", 2);
        tablePrior.put("-", 2);
        tablePrior.put(",", 1);
        tablePrior.put("=", 1);
        tablePrior.put("]", 1);
        tablePrior.put(")", 1);
        tablePrior.put("[", 0);
        tablePrior.put("(", 0);
        precedence = tablePrior;
        return precedence;
    }

    public double RPNCalculate(List<String> expressions) {
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
            System.out.println("current: " + exp + ", stack: " + stack.toString());
        }
        return stack.peek();
    }

    public List<String> convertToRPN(List<String> expr) {
        List<String> res = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        Map<String, Integer> pres = presLoad(precedence);
        int counter = 0;
        for (String symbol : expr) {
            if ("(".equals(symbol)) {
                counter = 2;
//                stack.push("Fn");
                stack.push(symbol);
                continue;
            }
            if (")".equals(symbol)) {
                while (!"(".equals(stack.peek()))
                    res.add(stack.pop());
//                res.add("Fn");
//                res.add(Integer.toString(counter));
//                res.add(symbol);
                stack.pop();
                continue;
            }
            if ("[".equals(symbol)) {
                counter = 2;
                stack.push(symbol);
                continue;
            }
            if (",".equals(symbol)) {
                counter++;
                while (!"[".equals(stack.peek())) {
                    res.add(stack.pop());
                }
//                while (!"(".equals(stack.peek())) {
//                    res.add(stack.pop());
//                }
                continue;
            }
            if ("]".equals(symbol)) {
                while (!"[".equals(stack.peek())) {
                    res.add(stack.pop());
                }
                res.add(Integer.toString(counter));
                res.add(symbol);
                stack.pop();
                continue;
            }
            if (pres.containsKey(symbol)) {
                while (!stack.empty() && pres.get(symbol) <= pres.get(stack.peek())) {
                    res.add(stack.pop());
                }
                stack.push(symbol);
                continue;
            }
            res.add(symbol);
        }
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
        return res;
    }

//    static {
//        temp.put("sin", 5);
//        temp.put("cos", 5);
//        temp.put("tan", 5);
//        temp.put("ctg", 5);
//        temp.put("^", 4);
//        temp.put("/", 3);
//        temp.put("*", 3);
//        temp.put("+", 2);
//        temp.put("-", 2);
//        temp.put(",", 1);
//        temp.put("=", 1);
//        temp.put("]", 1);
//        temp.put(")", 1);
//        temp.put("[", 0);
//        temp.put("(", 0);
//        precedence = temp;
//    }

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
