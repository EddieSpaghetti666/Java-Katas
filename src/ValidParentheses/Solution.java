package ValidParentheses;

import java.util.Stack;

class Solution{

    static boolean validParentheses(String parens)
    {
        Stack<Character> parenStack = new Stack<>();
        for(char c : parens.toCharArray()){
            if(c == '(') parenStack.push(c);
            else if(c == ')'){
                if(parenStack.empty()) return false;
                if(parenStack.peek() == '(') parenStack.pop();
                else parenStack.push(c);
            }
        }
        System.out.println(parenStack);
        return parenStack.empty();

    }
}