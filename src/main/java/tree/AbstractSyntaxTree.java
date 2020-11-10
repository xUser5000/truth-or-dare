package tree;

import tree.node.*;
import validation.Constants;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public final class AbstractSyntaxTree {

    private final Node root;
    private final String expression;

    private AbstractSyntaxTree (Node node, String expression) {
        this.expression = expression;
        this.root = node;
    }

    public Node getRoot() {
        return root;
    }

    public String getExpression() {
        return expression;
    }

    public boolean evaluate (Map<Character, Boolean> values) {
        return root.evaluate(values);
    }

    public static AbstractSyntaxTree fromPostfixString (String expression) {
        // Generating the set of all possible propositions
        Set<Character> propositions = new HashSet<>();
        String str = Constants.ALLOWED_PROPOSITIONS;
        for (int i = 0; i < str.length(); i++) propositions.add(str.charAt(i));

        Node root = null;
        Stack<Node> stack = new Stack<>();

        int n = expression.length();
        for (int i = n-1; i >= 0; i--) {
            char ch = expression.charAt(i);
            Node node = null;
            if (propositions.contains(ch)) node = new PropositionNode(ch);
            switch (ch) {
                case '~':
                    node = new NotNode();
                    break;
                case '^':
                    node = new AndNode();
                    break;
                case 'v':
                    node = new OrNode();
                    break;
            }

            if (root == null) root = node;

            if (!(node instanceof PropositionNode)) stack.push(node);
            else {
                while (!stack.isEmpty()) {
                    stack.peek().addChild(node);

                    if (stack.peek().isSaturated()) node = stack.pop();
                    else break;
                }
            }
        }

        return new AbstractSyntaxTree(root, expression);
    }
}
