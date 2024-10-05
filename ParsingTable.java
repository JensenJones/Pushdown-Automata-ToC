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
        parsingTable.put(new Pair<>(TreeNode.Label.forstart, Token.TokenType.SEMICOLON), Collections.singletonList(new Pair<>(
                TreeNode.Label.epsilon, null)));


        List<Pair<Symbol, Token.TokenType>> forArithRuleA = Collections.singletonList(new Pair<>(TreeNode.Label.arithexpr, null));
        parsingTable.put(new Pair<>(TreeNode.Label.forarith, Token.TokenType.ID), forArithRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.forarith, Token.TokenType.NUM), forArithRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.forarith, Token.TokenType.LPAREN), forArithRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.forarith, Token.TokenType.RPAREN), Collections.singletonList(
                new Pair<>(TreeNode.Label.epsilon, null)));


        parsingTable.put(new Pair<>(TreeNode.Label.forstat, Token.TokenType.FOR), Arrays.asList(
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.IF),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.LPAREN),
                new Pair<>(TreeNode.Label.relexpr, null),
                new Pair<>(TreeNode.Label.boolexpr, null),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.RPAREN),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.LBRACE),
                new Pair<>(TreeNode.Label.los, null),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.RBRACE),
                new Pair<>(TreeNode.Label.elseifstat, null)));

        List<Pair<Symbol, Token.TokenType>> elseIfRuleA = Collections.singletonList(new Pair<>(TreeNode.Label.epsilon, null));
        parsingTable.put(new Pair<>(TreeNode.Label.elseifstat, Token.TokenType.ID), elseIfRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.elseifstat, Token.TokenType.IF), elseIfRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.elseifstat, Token.TokenType.FOR), elseIfRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.elseifstat, Token.TokenType.WHILE), elseIfRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.elseifstat, Token.TokenType.PRINT), elseIfRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.elseifstat, Token.TokenType.TYPE), elseIfRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.elseifstat, Token.TokenType.SEMICOLON), elseIfRuleA);

        List<Pair<Symbol, Token.TokenType>> elseIfRuleB = Arrays.asList(
                new Pair<>(TreeNode.Label.elseorelseif, Token.TokenType.ELSE),
                new Pair<>(TreeNode.Label.possif, null),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.LBRACE),
                new Pair<>(TreeNode.Label.los, null),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.RBRACE),
                new Pair<>(TreeNode.Label.elseifstat, null)
        );
        parsingTable.put(new Pair<>(TreeNode.Label.elseifstat, Token.TokenType.ELSE), elseIfRuleB);

        List<Pair<Symbol, Token.TokenType>> ifRuleA = Arrays.asList(
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.IF),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.LPAREN),
                new Pair<>(TreeNode.Label.relexpr, null),
                new Pair<>(TreeNode.Label.boolexpr, null),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.RPAREN),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.LBRACE),
                new Pair<>(TreeNode.Label.los, null),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.RBRACE),
                new Pair<>(TreeNode.Label.elseifstat, null));
        parsingTable.put(new Pair<>(TreeNode.Label.ifstat, Token.TokenType.IF), ifRuleA);

        List<Pair<Symbol, Token.TokenType>> elseorelseifRuleA = Arrays.asList(
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.ELSE),
                new Pair<>(TreeNode.Label.possif, null));
        parsingTable.put(new Pair<>(TreeNode.Label.elseorelseif, Token.TokenType.ELSE), elseorelseifRuleA);

        List<Pair<Symbol, Token.TokenType>> possIfRuleA = Arrays.asList(
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.IF),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.LPAREN),
                new Pair<>(TreeNode.Label.relexpr, null),
                new Pair<>(TreeNode.Label.boolexpr, null),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.RPAREN),
                new Pair<>(TreeNode.Label.epsilon, null));
        parsingTable.put(new Pair<>(TreeNode.Label.possif, Token.TokenType.IF), possIfRuleA);

        List<Pair<Symbol,Token.TokenType>> possifRuelB = Collections.singletonList(new Pair<>(TreeNode.Label.epsilon, null));
        parsingTable.put(new Pair<>(TreeNode.Label.possif, Token.TokenType.LBRACE), possifRuelB);

        List<Pair<Symbol, Token.TokenType>> assignRuleA = Arrays.asList(
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.ID),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.EQUAL),
                new Pair<>(TreeNode.Label.expr, null));
        parsingTable.put(new Pair<>(TreeNode.Label.assign, Token.TokenType.ID), assignRuleA);

        List<Pair<Symbol, Token.TokenType>> declRuleA = Arrays.asList(
                new Pair<>(TreeNode.Label.type, null),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.ID),
                new Pair<>(TreeNode.Label.possassign, null));
        parsingTable.put(new Pair<>(TreeNode.Label.decl, Token.TokenType.TYPE), declRuleA);

        List<Pair<Symbol, Token.TokenType>> possAssignRuleA = Arrays.asList(
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.ASSIGN),
                new Pair<>(TreeNode.Label.expr, null));
        parsingTable.put(new Pair<>(TreeNode.Label.possassign, Token.TokenType.ASSIGN), possAssignRuleA);

        List<Pair<Symbol, Token.TokenType>> possAssignRuleB = Collections.singletonList(new Pair<>(TreeNode.Label.epsilon, null));
        parsingTable.put(new Pair<>(TreeNode.Label.possassign, Token.TokenType.SEMICOLON), possAssignRuleB);

        List<Pair<Symbol, Token.TokenType>> printRuleA = Arrays.asList(
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.PRINT),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.LBRACE),
                new Pair<>(TreeNode.Label.print, null),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.RBRACE));
        parsingTable.put(new Pair<>(TreeNode.Label.print, Token.TokenType.PRINT), printRuleA);

        List<Pair<Symbol, Token.TokenType>> typeRuleA = Collections.singletonList(
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.TYPE)
        );
        parsingTable.put(new Pair<>(TreeNode.Label.type, Token.TokenType.TYPE), typeRuleA);

        List<Pair<Symbol, Token.TokenType>> exprRuleA = Arrays.asList(
                new Pair<>(TreeNode.Label.relexpr,null),
                new Pair<>(TreeNode.Label.boolexpr, null));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.NUM), exprRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.ID), exprRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.LBRACE), exprRuleA);

        List<Pair<Symbol, Token.TokenType>> exprRuleB = Arrays.asList(new Pair<>(TreeNode.Label.charexpr, null));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.SQUOTE), exprRuleB);

        List<Pair<Symbol, Token.TokenType>> charexprRuleA = Arrays.asList(new Pair<>(TreeNode.Label.terminal, Token.TokenType.SQUOTE),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.CHARLIT),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.SQUOTE));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.SQUOTE), charexprRuleA);

        List<Pair<Symbol, Token.TokenType>> boolexprRuleA = Arrays.asList(new Pair<>(TreeNode.Label.boolop, null),
                new Pair<>(TreeNode.Label.relexpr, null),
                new Pair<>(TreeNode.Label.boolexpr, null));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.EQUAL), boolexprRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.NEQUAL), boolexprRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.AND), boolexprRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.OR), boolexprRuleA);

        List<Pair<Symbol, Token.TokenType>> boolexprRuleB = Collections.singletonList(new Pair<>(TreeNode.Label.epsilon, null));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.RBRACE), boolexprRuleB);
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.SEMICOLON), boolexprRuleB);

        List<Pair<Symbol, Token.TokenType>> boolopRuleA = Collections.singletonList( new Pair<>(TreeNode.Label.booleq, null));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.EQUAL), boolopRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.NEQUAL), boolopRuleA);

        List<Pair<Symbol, Token.TokenType>> boolopRuleB = Collections.singletonList( new Pair<>(TreeNode.Label.boollog, null));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.AND), boolopRuleB);
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.OR), boolopRuleB);

        List<Pair<Symbol, Token.TokenType>> booleqRuleA = Collections.singletonList(new Pair<>(TreeNode.Label.terminal, Token.TokenType.EQUAL));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.EQUAL), booleqRuleA);

        List<Pair<Symbol, Token.TokenType>> booleqRuleB = Collections.singletonList(new Pair<>(TreeNode.Label.terminal, Token.TokenType.NEQUAL));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.NEQUAL), booleqRuleB);

        List<Pair<Symbol, Token.TokenType>> boollogRuleA = Collections.singletonList(new Pair<>(TreeNode.Label.terminal, Token.TokenType.EQUAL));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.EQUAL), booleqRuleA);

        List<Pair<Symbol, Token.TokenType>> boollogRuleB = Collections.singletonList(new Pair<>(TreeNode.Label.terminal, Token.TokenType.NEQUAL));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.NEQUAL), booleqRuleB);

        List<Pair<Symbol, Token.TokenType>> relexprRuleA = Arrays.asList(new Pair<>(TreeNode.Label.arithexpr, null),
                new Pair<>(TreeNode.Label.relexprprime, null));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.RBRACE), relexprRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.ID), relexprRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.NUM), relexprRuleA);

        List<Pair<Symbol, Token.TokenType>> relexprRuleB = Collections.singletonList(new Pair<>(TreeNode.Label.terminal, Token.TokenType.TRUE));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.TRUE), relexprRuleB);

        List<Pair<Symbol, Token.TokenType>> relexprRuleC = Collections.singletonList(new Pair<>(TreeNode.Label.terminal, Token.TokenType.FALSE));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.FALSE), relexprRuleC);

        List<Pair<Symbol, Token.TokenType>> relexprprimeRuleA = Arrays.asList(new Pair<>(TreeNode.Label.relop, null),
                new Pair<>(TreeNode.Label.arithexpr, null));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.GT), relexprprimeRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.LT), relexprprimeRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.GE), relexprprimeRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.LE), relexprprimeRuleA);

        List<Pair<Symbol, Token.TokenType>> relexprprimeRuleB = Arrays.asList(new Pair<>(TreeNode.Label.epsilon, null));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.RBRACE), relexprprimeRuleB);
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.SEMICOLON), relexprprimeRuleB);

        List<Pair<Symbol, Token.TokenType>> relopRuleA = Collections.singletonList(new Pair<>(TreeNode.Label.terminal, Token.TokenType.LT));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.LT), relopRuleA);

        List<Pair<Symbol, Token.TokenType>> relopRuleB = Collections.singletonList(new Pair<>(TreeNode.Label.terminal, Token.TokenType.GT));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.GT), relopRuleB);

        List<Pair<Symbol, Token.TokenType>> relopRuleC = Collections.singletonList(new Pair<>(TreeNode.Label.terminal, Token.TokenType.GE));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.GE), relopRuleC);

        List<Pair<Symbol, Token.TokenType>> relopRuleD = Collections.singletonList(new Pair<>(TreeNode.Label.terminal, Token.TokenType.LE));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.LE), relopRuleD);

        List<Pair<Symbol, Token.TokenType>> arthiexprRuleA = Arrays.asList(new Pair<>(TreeNode.Label.term, null),
                new Pair<>(TreeNode.Label.arithexprprime, null));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.LBRACE), arthiexprRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.ID), arthiexprRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.NUM), arthiexprRuleA);

        List<Pair<Symbol, Token.TokenType>> arithexprprimeRuleA = Arrays.asList(new Pair<>(TreeNode.Label.terminal, Token.TokenType.PLUS),
                new Pair<>(TreeNode.Label.term, null),
                new Pair<>(TreeNode.Label.arithexprprime, null));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.PLUS), arithexprprimeRuleA);

        List<Pair<Symbol, Token.TokenType>> arithexprprimeRuleB = Arrays.asList(new Pair<>(TreeNode.Label.terminal, Token.TokenType.MINUS),
                new Pair<>(TreeNode.Label.term, null),
                new Pair<>(TreeNode.Label.arithexprprime, null));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.MINUS), arithexprprimeRuleB);

        List<Pair<Symbol, Token.TokenType>> arithexprprimeRuleC = Collections.singletonList(new Pair<>(TreeNode.Label.epsilon, null));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.DOLLAR), arithexprprimeRuleC);

        List<Pair<Symbol, Token.TokenType>> termRuleA = Arrays.asList(new Pair<>(TreeNode.Label.factor, null),
                new Pair<>(TreeNode.Label.termprime, null));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.LBRACE), termRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.ID), termRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.NUM), termRuleA);

        List<Pair<Symbol, Token.TokenType>> termprimeRuleA = Arrays.asList(new Pair<>(TreeNode.Label.terminal, Token.TokenType.TIMES),
                new Pair<>(TreeNode.Label.factor, null),
                new Pair<>(TreeNode.Label.termprime, null));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.TIMES), termprimeRuleA);

        List<Pair<Symbol, Token.TokenType>> termprimeRuleB = Arrays.asList(new Pair<>(TreeNode.Label.terminal, Token.TokenType.DIVIDE),
                new Pair<>(TreeNode.Label.factor, null),
                new Pair<>(TreeNode.Label.termprime, null));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.DIVIDE), termprimeRuleB);

        List<Pair<Symbol, Token.TokenType>> termprimeRuleC = Arrays.asList(new Pair<>(TreeNode.Label.terminal, Token.TokenType.MOD),
                new Pair<>(TreeNode.Label.factor, null),
                new Pair<>(TreeNode.Label.termprime, null));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.MOD), termprimeRuleC);

        List<Pair<Symbol, Token.TokenType>> termprimeRuleD = Collections.singletonList(new Pair<>(TreeNode.Label.epsilon, null));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.PLUS), termprimeRuleD);
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.MINUS), termprimeRuleD);

        List<Pair<Symbol, Token.TokenType>> factorRuleA = Arrays.asList(new Pair<>(TreeNode.Label.terminal, Token.TokenType.LBRACE),
                new Pair<>(TreeNode.Label.arithexpr, null),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.RBRACE));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.LBRACE), factorRuleA);

        List<Pair<Symbol, Token.TokenType>> factorRuleB = Collections.singletonList(new Pair<>(TreeNode.Label.terminal, Token.TokenType.NUM));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.ID), factorRuleB);

        List<Pair<Symbol, Token.TokenType>> factorRuleC = Collections.singletonList(new Pair<>(TreeNode.Label.terminal, Token.TokenType.NUM));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.NUM), factorRuleC);

        List<Pair<Symbol, Token.TokenType>> printexprRuleA = Arrays.asList(new Pair<>(TreeNode.Label.relexpr, null),
                new Pair<>(TreeNode.Label.boolexpr, null));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.LBRACE), printexprRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.ID), printexprRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.NUM), printexprRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.TRUE), printexprRuleA);
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.FALSE), printexprRuleA);

        List<Pair<Symbol, Token.TokenType>> printexprRuleB = Arrays.asList(new Pair<>(TreeNode.Label.terminal, Token.TokenType.DQUOTE),
                new Pair<>(TreeNode.Label.boolexpr, null),
                new Pair<>(TreeNode.Label.terminal, Token.TokenType.DQUOTE));
        parsingTable.put(new Pair<>(TreeNode.Label.terminal, Token.TokenType.DQUOTE), printexprRuleB);

    }

    public List<Pair<Symbol, Token.TokenType>> applyRule(Pair<Symbol, Token.TokenType> key) {
        return parsingTable.get(key);
    }
}
