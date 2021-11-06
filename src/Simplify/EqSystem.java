package Simplify;

import java.util.*;
import java.util.stream.Collectors;

class EqSystem {

    static String simplify(String[] examples, String formula) {
        Map<Character, String> equations = buildEquationMap(examples);
        System.out.println(equations);
        String expandedEq = expandEquation(formula, equations).replaceAll(" ", "");
        System.out.println(expandedEq);

        return null;
    }

    private static String getFinalTerm(String expandedEq){
        char symbol = '0';
        int coefficient = 0;
        for(char c : expandedEq.toCharArray()){
            if(Character.isAlphabetic(c)){
                symbol = c;
                coefficient++;
            }
            if(Character.isDigit(c)){
                coefficient += Character.getNumericValue(c) - 1;
            }
        }
        return Integer.toString(coefficient) + symbol;



    }

    private static String expandEquation(String equation, Map<Character, String> substitutions){
        for(char c : equation.toCharArray()){
            if(substitutions.containsKey(c)){
                equation = equation.replaceAll(Character.toString(c),substitutions.get(c));
                return expandEquation(equation, substitutions);
            }
        }
        return equation;
    }

    private static Map<Character, String> buildEquationMap(String[] examples) {
        Map<Character, String> equations = new HashMap<>();
        for (String example : examples) {
            example = example.replaceAll(" ", "");
            String[] sides = example.split("=");
            equations.put(sides[1].charAt(0), "(" + sides[0] + ")");
        }
        return equations;

    }


}