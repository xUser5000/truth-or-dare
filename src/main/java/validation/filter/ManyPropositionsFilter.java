package validation.filter;

import validation.Constants;
import validation.exception.ManyPropositionsException;

import java.util.HashSet;
import java.util.Set;

public class ManyPropositionsFilter implements Filter {

    @Override
    public void filtrate(String expression) {
        Set<Character> allowed = new HashSet<>();
        Set<Character> set = new HashSet<>();

        String str = Constants.ALLOWED_PROPOSITIONS;
        for (int i = 0; i < str.length(); i++) {
            Character ch = str.charAt(i);
            allowed.add(ch);
        }

        for (int i = 0; i < expression.length(); i++) {
            Character ch = expression.charAt(i);
            if (allowed.contains(ch)) set.add(ch);
        }

        if (set.size() < 2) {
            throw new ManyPropositionsException("Your statement must contain at least two propositions.");
        }
    }
}
