package validation.filter;

import validation.Constants;
import validation.exception.SpecialCharactersException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpecialCharactersFilter implements Filter {

    @Override
    public void filtrate(String expression) {
        List<Character> errors = new ArrayList<>();
        Set<Character> allowed = new HashSet<>();

        String str = Constants.ALLOWED_CONNECTIVES + Constants.ALLOWED_PROPOSITIONS;
        for (int i = 0; i < str.length(); i++) {
            char character = str.charAt(i);
            allowed.add(character);
        }

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (!allowed.contains(ch) && ch != '~' && ch != ')' && ch != '(') errors.add(ch);
        }

        if (errors.size() != 0) {
            StringBuilder res = new StringBuilder("You are not allowed to enter these characters: ");
            for (Character ch: errors) res.append(ch).append(" ");
            throw new SpecialCharactersException(res.toString());
        }
    }
}
