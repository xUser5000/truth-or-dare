import tree.AbstractSyntaxTree;
import tree.algorithm.GetPropositions;
import tree.algorithm.GetTruthTable;
import validation.Validator;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static List< List<String> > generateTruthTable (String statement) throws RuntimeException {
        // Validation (Throws runtime exception if there are validation errors)
        String compoundProposition = new Validator().validate(statement);

        // Converting from infix to postfix format
        // Postfix is easier to use and useful for constructing the abstract syntax tree
        String postfix = new Parser().toPostfix(compoundProposition);

        // Abstract syntax tree
        AbstractSyntaxTree tree = AbstractSyntaxTree.fromPostfixString(postfix);
        List<Character> propositions = GetPropositions.run(tree);

        // Generate the truth table
        List< List<Boolean> > table = GetTruthTable.run(tree);

        List< List<String> > res = new ArrayList<>();

        // The head row
        List<String> head = new ArrayList<>();
        for (Character ch: propositions) head.add(String.valueOf(ch));
        head.add(statement);
        res.add(head);

        // The main content of the table
        for (List<Boolean> list: table) {
            List<String> tmp = new ArrayList<>();
            for (Boolean value: list) tmp.add(String.valueOf(value));
            res.add(tmp);
        }

        return res;
    }
}
