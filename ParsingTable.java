import java.util.*;

class ParsingTable {
    private final Map<Pair<Symbol, Token.TokenType>, List<Pair<Symbol, Token.TokenType>>> parsingTable = new HashMap<>();

    // Key:
    // Value:

    public ParsingTable() {
        List<Pair<Symbol, Token.TokenType>> progRuleA = Arrays.asList(
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.PUBLIC),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.CLASS),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.ID),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.LBRACE),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.PUBLIC),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.STATIC),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.VOID),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.MAIN),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.LPAREN),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.STRINGARR),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.ARGS),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.LBRACE),
                new Pair<>(TreeNode.Label.los, null),  // Non-terminal with no TokenType
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.RBRACE),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.RBRACE));
        parsingTable.put(new Pair<>(TreeNode.Label.prog, Token.TokenType.PUBLIC), progRuleA);

        List<Pair<Symbol, Token.TokenType>> losRuleA = Arrays.asList(
                new Pair<>(TreeNode.Label.stat, null),
                new Pair<>(TreeNode.Label.los, null));
        parsingTable.put(new Pair<>(TreeNode.Label.los, Token.TokenType.SEMICOLON), losRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.los, Token.TokenType.TYPE), losRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.los, Token.TokenType.PRINT), losRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.los, Token.TokenType.WHILE), losRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.los, Token.TokenType.FOR), losRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.los, Token.TokenType.IF), losRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.los, Token.TokenType.ID), losRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.los, Token.TokenType.RBRACE), Collections.singletonList(
                new Pair<>(TreeNode.Label.epsilon, null)));


        parsingTable.put(new Pair<>(TreeNode.Label.stat, Token.TokenType.SEMICOLON), Collections.singletonList(
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.SEMICOLON)));
        parsingTable.put(new Pair<>(TreeNode.Label.stat, Token.TokenType.ID), Collections.singletonList(
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.ID)));
        parsingTable.put(new Pair<>(TreeNode.Label.stat, Token.TokenType.IF), Collections.singletonList(
                new Pair<>(TreeNode.Label.ifstat, null)));
        parsingTable.put(new Pair<>(TreeNode.Label.stat, Token.TokenType.FOR), Collections.singletonList(
                new Pair<>(TreeNode.Label.forstat, null)));
        parsingTable.put(new Pair<>(TreeNode.Label.stat, Token.TokenType.WHILE), Collections.singletonList(
                new Pair<>(TreeNode.Label.whilestat, null)));
        parsingTable.put(new Pair<>(TreeNode.Label.stat, Token.TokenType.PRINT), Arrays.asList(
                new Pair<>(TreeNode.Label.print, null),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.SEMICOLON)));
        parsingTable.put(new Pair<>(TreeNode.Label.stat, Token.TokenType.TYPE), Arrays.asList(
                new Pair<>(TreeNode.Label.decl, null),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.SEMICOLON)));

        parsingTable.put(new Pair<>(TreeNode.Label.whilestat, Token.TokenType.WHILE), Arrays.asList(
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.WHILE),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.LPAREN),
                new Pair<>(TreeNode.Label.relexpr, null),
                new Pair<>(TreeNode.Label.boolexpr, null),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.RPAREN),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.LBRACE),
                new Pair<>(TreeNode.Label.los, null),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.RBRACE)));

        parsingTable.put(new Pair<>(TreeNode.Label.forstat, Token.TokenType.FOR), Arrays.asList(
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.FOR),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.LPAREN),
                new Pair<>(TreeNode.Label.forstart, null),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.SEMICOLON),
                new Pair<>(TreeNode.Label.relexpr, null),
                new Pair<>(TreeNode.Label.boolexpr, null),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.SEMICOLON),
                new Pair<>(TreeNode.Label.forarith, null),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.RPAREN),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.LBRACE),
                new Pair<>(TreeNode.Label.los, null),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.RBRACE)));

        parsingTable.put(new Pair<>(TreeNode.Label.forstart, Token.TokenType.TYPE), Collections.singletonList(new Pair<>(
                TreeNode.Label.decl, null)));
        parsingTable.put(new Pair<>(TreeNode.Label.forstart, Token.TokenType.ID), Collections.singletonList(new Pair<>(
                TreeNode.Label.assign, null)));
    }

    public List<Pair<Symbol, Token.TokenType>> applyRule(Pair<Symbol, Token.TokenType> key) {
        return parsingTable.get(key);
    }
}
