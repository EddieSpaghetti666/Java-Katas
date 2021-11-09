package Calculator;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

public class Calculator {



    public static Double evaluate(String expression) {
        if(expression.split(" ").length == 1) return Double.parseDouble(expression);
        String postfix = infixToPostfix(expression);
        System.out.println(postfix);
        //System.out.print(evaluatePostfixExpr(postfix));
        return evaluatePostfixExpr(postfix);
    }

    private static Double evaluatePostfixExpr(String expression){
        Stack<Double> operands = new Stack<>();
        for(String s : expression.split(" ")){
            if(isDigit(s)){
                operands.push(Double.parseDouble(s));
            }
            if(isOperator(s)){
                double operand2 = operands.pop();
                double operand1 = operands.pop();
                double result = evaluate(operand1, operand2, s.charAt(0));
                operands.push(result);
            }
        }
        return operands.peek();
    }

    private static Double evaluate(double op1, double op2, char operator){
        switch (operator){
            case '*': return op1 * op2;
            case '/': return op1 / op2;
            case '+': return op1 + op2;
            case '-': return op1 - op2;
            default: throw new RuntimeException("Invalid operator");
        }

    }

    private static String infixToPostfix(String expression){
        Stack<Character> operators = new Stack<>();
        Queue<String> output= new LinkedList<>();

        for(String s: expression.split(" ")){
            if(isDigit(s)) output.add(s);
            else if(isOperator(s)){
                while (!operators.empty() && operators.peek() != '('
                        && getPrecedence(operators.peek()) >= getPrecedence(s.charAt(0))){
                    output.add(operators.pop().toString());
                }
                operators.push(s.charAt(0));
            }
            else if(s.equals("(")) operators.push(s.charAt(0));
            else if(s.equals(")")){
                if(operators.empty()) return null;
                while(operators.peek() != '('){
                    output.add(operators.pop().toString());
                }
                if(operators.peek() != '(') throw new RuntimeException("Couldn't find left paren!");
                operators.pop();
            }
        }
        while(!operators.empty()){
            output.add(operators.pop().toString());
        }
        return output.stream().collect(Collectors.joining(" "));

    }

    private static boolean isDigit(String string){
        if(string.charAt(0) == '-' && string.length() > 1) string = string.substring(1);
        return string.chars().allMatch(c -> Character.isDigit(c) || c == '.');
    }

    private static int getPrecedence(char c){
        switch(c){
            case '*':
            case '/': {
                return 1;
            }
            case '+':
            case '-': {
                return 0;
            }
            default:
                throw new RuntimeException("Invalid Operator");
        }
    }


    private static boolean isOperator(String s){
        return s.equals("+") || s.equals("-") || s.equals("/") || s.equals("*");
    }

}