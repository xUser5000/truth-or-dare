import validation.Constants;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Parser {
    public String toPostfix (String expression) {
        StringBuilder res = new StringBuilder();

        Set<Character> propositions = new HashSet<>();

        String str = Constants.ALLOWED_PROPOSITIONS;
        for (int i = 0; i < str.length(); i++) propositions.add(str.charAt(i));

        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            // If it's a proposition, insert it in the result
            if (propositions.contains(ch)) res.append(ch);

            // Push any '(' to the stack
            else if (ch == '(') operators.push(ch);

            // If you encounter a ')', insert every element included in the parentheses
            else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(')
                    res.append(operators.pop());
                operators.pop();
            }

            // A connective
            else {

                if (!operators.isEmpty() && operators.peek() != '(' && precedence(ch) <= precedence(operators.peek())) {
                    while (!operators.isEmpty() && precedence(ch) <= precedence(operators.peek()))
                        res.append(operators.pop());
                }
                operators.push(ch);
            }
        }

        // Insert all the remaining elements in the stack to the result
        while (!operators.isEmpty()) res.append(operators.pop());

        return res.toString();
    }

    int precedence (char operator) {
        if (operator == '~') return 2;
        return 1;
    }
}
