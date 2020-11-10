package tree.algorithm;

import tree.AbstractSyntaxTree;
import tree.node.Node;
import tree.node.PropositionNode;

import java.util.*;

/**
 * Traverse AST using depth-first search and finds all the proposition nodes
 */
public final class GetPropositions {

    private static Set<Character> res;

    public static List<Character> run (AbstractSyntaxTree tree) {
        res = new HashSet<>();
        dfs(tree.getRoot());
        return new ArrayList<>(res);
    }

    private static void dfs (Node node) {
        if (node instanceof PropositionNode) {
            res.add(node.toString().charAt(0));
            return;
        }

        List<Node> children = node.getChildren();
        if (children != null) for (Node child: children) dfs(child);
    }
}
