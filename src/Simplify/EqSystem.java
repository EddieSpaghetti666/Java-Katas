package Simplify;

import java.util.*;
import java.util.stream.Collectors;

class EqSystem {

    interface Symbol {
    }

    private static class Factor implements Symbol {
        Character symbol;
        int coefficient;

        Factor(Character symbol, int coeff) {
            this.symbol = symbol;
            this.coefficient = coeff;
        }

        @Override
        public String toString() {
            return Integer.toString(coefficient) + symbol;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Factor)) return false;
            Factor o = (Factor) obj;
            return this.symbol == o.symbol && this.coefficient == o.coefficient;
        }

        @Override
        public int hashCode() {
            return symbol + coefficient;
        }
    }

    private static class Operator implements Symbol {
        Character operator;

        Operator(Character operator) {
            this.operator = operator;
        }

        @Override
        public String toString() {
            return operator.toString();
        }
    }

    static String simplify(String[] examples, String formula) {
        Map<Factor, List<Symbol>> equations = buildEquationMap(examples);
        formula = formula.replaceAll(" ", "");
        List<Symbol> equation = stringToSymbolList(formula);
        System.out.println(equation);
        return null;
    }


    private static Map<Factor, List<Symbol>> buildEquationMap(String[] examples) {
        Map<Factor, List<Symbol>> equations = new HashMap<>();
        for (String example : examples) {
            example = example.replaceAll(" ", "");
            String[] sides = example.split("=");
            List<Symbol> lhsSymbols = stringToSymbolList(sides[0]);
            equations.put(new Factor(sides[1].charAt(0), 1), lhsSymbols);
        }
        return equations;

    }

    private static List<Symbol> stringToSymbolList(String str) {
        List<Symbol> symbols = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);
            if (current == '+' || current == '-') {
                symbols.add(new Operator(current));
            } else if (Character.isDigit(current)) {
                int coefficient = Character.getNumericValue(current);
                Character symbol = str.charAt(++i);
                symbols.add(new Factor(symbol, coefficient));
            } else {
                Factor factor = new Factor(current, 1);
                symbols.add(new Factor(current, 1));
            }

        }
        return symbols;
    }
}