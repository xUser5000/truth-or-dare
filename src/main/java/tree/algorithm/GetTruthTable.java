package tree.algorithm;

import tree.AbstractSyntaxTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Uses a recursive function to populate the table with all possible combinations
 * of truth values (The number of rows = 2^n, where n = number of propositions)
 * Then evaluates every row at a time
 */
public class GetTruthTable {

    private static List< List<Boolean> > res;
    private static AbstractSyntaxTree tree;

    public static List< List<Boolean> > run (AbstractSyntaxTree tree) {
        res = new ArrayList<>();
        GetTruthTable.tree = tree;

        List<Character> propositions = GetPropositions.run(tree);

        populate(0, propositions, new HashMap<>());

        return res;
    }

    private static void populate (int i, List<Character> propositions, Map<Character, Boolean> map) {
        if (i == propositions.size()) {
            // Evaluates the entire row
            List<Boolean> list = new ArrayList<>();
            for (Character ch: propositions) list.add(map.get(ch));
            list.add(tree.evaluate(map));
            res.add(list);
            return;
        }

        map.put(propositions.get(i), true);
        populate(i+1, propositions, map);
        map.put(propositions.get(i), false);
        populate(i+1, propositions, map);
    }
}
