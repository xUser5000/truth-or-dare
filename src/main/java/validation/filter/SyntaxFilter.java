package validation.filter;

import validation.Constants;
import validation.exception.InvalidSyntaxException;

import java.util.HashSet;
import java.util.Set;

public class SyntaxFilter implements Filter {
    @Override
    public void filtrate(String expression) {
        Set<Character> propositions = new HashSet<>();
        Set<Character> connectives = new HashSet<>();

        String str = Constants.ALLOWED_PROPOSITIONS;
        for (int i = 0; i < str.length(); i++) propositions.add(str.charAt(i));
        str = Constants.ALLOWED_CONNECTIVES;
        for (int i = 0; i < str.length(); i++) connectives.add(str.charAt(i));

        // Assert that the first character is always a proposition or a ~ connective
        if (
                expression.charAt(0) != '~' &&
                !propositions.contains(expression.charAt(0)) &&
                expression.charAt(0) != '('
        ) {
            throw new InvalidSyntaxException("Syntax Error: The first character is invalid");
        }

        // Assert that the last character is always a proposition or a ")"
        char lastChar = expression.charAt(expression.length()-1);
        if (!propositions.contains(lastChar) && lastChar != ')') {
            throw new InvalidSyntaxException("Syntax error: The last character must be a proposition or a \")\"");
        }

        // There should not be any two consecutive propositions, or two consecutive connectives
        int n = expression.length();
        for (int i = 1; i < n; i++) {
            char curr = expression.charAt(i);
            char prev = expression.charAt(i-1);
            if (propositions.contains(curr) && propositions.contains(prev)) {
                throw new InvalidSyntaxException("Syntax error: There should not be any two consecutive propositions");
            }
            if (connectives.contains(curr) && connectives.contains(prev)) {
                throw new InvalidSyntaxException("Syntax error: There should not be any two consecutive connectives");
            }
        }

        // Every non-leading "~" should be preceded by a connective
        for (int i = 1; i < n; i++) {
            char curr = expression.charAt(i);
            char prev = expression.charAt(i-1);
            if (curr == '~' && (!connectives.contains(prev) || prev == ')')) {
                throw new InvalidSyntaxException("Syntax error: Every non-leading \"~\" should be preceded by a connective");
            }
        }

        // Parentheses are well-formatted
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            char ch = expression.charAt(i);
            if (ch == '(') cnt++;
            if (ch == ')') cnt--;

            if (cnt < 0) {
                throw new InvalidSyntaxException("Syntax error: Parentheses are ill-formatted");
            }
        }
        if (cnt != 0) {
            throw new InvalidSyntaxException("Syntax error: Parentheses are ill-formatted");
        }

        // The content of parentheses is not empty
        for (int i = 0; i < n-1; i++) {
            char curr = expression.charAt(i);
            char next = expression.charAt(i+1);
            if (curr == '(' && next == ')') {
                throw new InvalidSyntaxException("Syntax error: Parentheses content is empty");
            }
        }
    }
}
