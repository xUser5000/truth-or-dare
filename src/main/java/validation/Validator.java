package validation;

import validation.filter.ManyPropositionsFilter;
import validation.filter.SpecialCharactersFilter;
import validation.filter.SyntaxFilter;

public class Validator {

    private final SpecialCharactersFilter specialCharactersFilter = new SpecialCharactersFilter();
    private final ManyPropositionsFilter manyPropositionsFilter = new ManyPropositionsFilter();
    private final SyntaxFilter syntaxFilter = new SyntaxFilter();

    private String preprocess (String statement) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < statement.length(); i++) {
            char ch = statement.charAt(i);
            if (ch != ' ') res.append(Character.toLowerCase(ch));
        }
        return res.toString();
    }

    public String validate (String expression) throws RuntimeException {
        expression = preprocess(expression);
        specialCharactersFilter.filtrate(expression);
        manyPropositionsFilter.filtrate(expression);
        syntaxFilter.filtrate(expression);
        return expression;
    }

}
