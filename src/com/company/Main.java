package com.company;

import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        RPNCalculator calculator = new RPNCalculator();

        List<String> expression = calculator.splitLine(line);
        Queue<String> result = calculator.convertToRPN(expression);
        System.out.println(result);
        System.out.println(calculator.RPNCalculate(result));

    }
}
