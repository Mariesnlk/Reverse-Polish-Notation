package com.company;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        RPNCalculator calculator = new RPNCalculator();

        List<String> expression = RPNCalculator.splitLine(line);
        List<String> result = calculator.convertToRPN(expression);
        System.out.println(result);
        System.out.println(calculator.RPNCalculate(result));
        //A=2+M[1,j,j+3]^5
        //B=2+f(j,j+1,j+3)^8
    }
}
