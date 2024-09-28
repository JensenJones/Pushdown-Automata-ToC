import java.util.*;

class ParsingTable {
    private final Map<Pair<TreeNode, Token>, List<TreeNode>> parsingTable = new HashMap<>();

    // Key:
    // Value:

    public ParsingTable() {
        TreeNode progNode = new TreeNode(TreeNode.Label.prog, null);


        List<TreeNode> progRuleA = Arrays.asList(
                new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.PUBLIC), null),  // Pass `null` as the parent
                new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.CLASS), null),
                new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.ID), null),
                new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.LBRACE), null),
                new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.PUBLIC), null),
                new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.STATIC), null),
                new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.VOID), null),
                new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.MAIN), null),
                new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.LPAREN), null),
                new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.STRINGARR), null),
                new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.ARGS), null),
                new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.LBRACE), null),
                new TreeNode(TreeNode.Label.los, null),  // Pass `null` as the parent
                new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.RBRACE), null),
                new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.RBRACE), null)
        );
        parsingTable.put(new Pair<>(progNode, new Token(Token.TokenType.PUBLIC)), progRuleA);
    }

    /*
     * Returns the production rule or null
     */
    public List<TreeNode> applyRule(Pair<TreeNode, Token> key) {
        return parsingTable.get(key);
    }
}
