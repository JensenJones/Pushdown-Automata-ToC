import java.util.*;

class ParsingTable {
    private final Map<Pair<TreeNode, Token>, List<TreeNode>> parsingTable = new HashMap<>();

    // Key:
    // Value:

    public ParsingTable() {
        TreeNode progNode = new TreeNode(TreeNode.Label.prog, null);
        List<TreeNode> progRuleA = Arrays.asList(new TreeNode(TreeNode.Label.valueOf("public"), new Token(Token.TokenType.PUBLIC), progNode),
                                                 new TreeNode(TreeNode.Label.valueOf("class"), new Token(Token.TokenType.CLASS) ,progNode),
                                                 new TreeNode(TreeNode.Label.valueOf("ID"), new Token(Token.TokenType.ID) ,progNode),
                                                 new TreeNode(TreeNode.Label.valueOf("{"), new Token(Token.TokenType.LBRACE) ,progNode),
                                                 new TreeNode(TreeNode.Label.valueOf("public"), new Token(Token.TokenType.PUBLIC), progNode),
                                                 new TreeNode(TreeNode.Label.valueOf("static"), new Token(Token.TokenType.STATIC) ,progNode),
                                                 new TreeNode(TreeNode.Label.valueOf("void"), new Token(Token.TokenType.VOID) ,progNode),
                                                 new TreeNode(TreeNode.Label.valueOf("main"), new Token(Token.TokenType.MAIN) ,progNode),
                                                 new TreeNode(TreeNode.Label.valueOf("("), new Token(Token.TokenType.LPAREN) ,progNode),
                                                 new TreeNode(TreeNode.Label.valueOf("string[]"), new Token(Token.TokenType.STRINGARR) ,progNode),
                                                 new TreeNode(TreeNode.Label.valueOf("args"), new Token(Token.TokenType.ARGS) ,progNode),
                                                 new TreeNode(TreeNode.Label.valueOf("{"), new Token(Token.TokenType.LBRACE) ,progNode),
                                                 new TreeNode(TreeNode.Label.los, progNode),
                                                 new TreeNode(TreeNode.Label.valueOf("}"), new Token(Token.TokenType.RBRACE) ,progNode),
                                                 new TreeNode(TreeNode.Label.valueOf("}"), new Token(Token.TokenType.RBRACE) ,progNode));
        parsingTable.put(new Pair<>(progNode, new Token(Token.TokenType.PUBLIC)), progRuleA);
    }

    /*
     * Returns the production rule or null
     */
    public List<TreeNode> applyRule(Pair<TreeNode, Token> key) {
        return parsingTable.get(key);
    }
}
