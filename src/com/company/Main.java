package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        RPNCalculator calculator = new RPNCalculator();

        String str1 = "A=(B*6^7-C+7)^V^4^4";
        List<String> expr1 = RPNCalculator.splitLine(str1);
        List<String> res1 = calculator.convertToRPN(expr1);
        System.out.println("Input: " + str1);
        System.out.println("Res: " + calculator.listToString(res1));
        System.out.println();

        String str2 = "A=2+M[1,j,j+3]^5";
        List<String> expr2 = RPNCalculator.splitLine(str2);
        List<String> res2 = calculator.convertToRPN(expr2);
        System.out.println("Input: " + str2);
        System.out.println("Res: " + calculator.listToString(res2));
        System.out.println();

        String str3 = "B=M[i,j+1,k]";
        List<String> expr3 = RPNCalculator.splitLine(str3);
        List<String> res3 = calculator.convertToRPN(expr3);
        System.out.println("Input: " + str2);
        System.out.println("Res: " + calculator.listToString(res3));
        System.out.println();

        String str4 = "50/(2*5)+sin(45+45)";
        List<String> expr4 = RPNCalculator.splitLine(str4);
        List<String> res4 = calculator.convertToRPN(expr4);
        System.out.println("Input: " + str4);
        System.out.println("Res: " + calculator.listToString(res4));
        System.out.println("Result: " + calculator.RPNCalculate(res4));
        System.out.println();

        String str5 = "12+2*((3*4)+(10/5))";
        List<String> expr5 = RPNCalculator.splitLine(str5);
        List<String> res5 = calculator.convertToRPN(expr5);
        System.out.println("Input: " + str5);
        System.out.println("Res: " + calculator.listToString(res5));
        System.out.println("Result: " + calculator.RPNCalculate(res5));
        System.out.println();

//        String str6 = "A=2+f(j,j+1,j+3)^8";
//        List<String> expr6 = RPNCalculator.splitLine(str6);
//        List<String> res6 = calculator.convertToRPN(expr6);
//        System.out.println("Input: " + str6);
//        System.out.println("Res: " + calculator.listToString(res6));
//        System.out.println();

    }
}
