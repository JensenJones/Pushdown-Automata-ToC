import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ParsingTable {
    private final Map<String, List<List<Token>>> parsingTable = new HashMap<>();

    // I think a good approach would be to have this class have the entire parsing table
    // Each non-terminal symbol would be the key for the hash table which would give a list of the possible
    // rules (each object in the list being a list of tokens) e.g. for "bool op" it would be like (arrows around the tokens):
    // [[<<bool eq>>], [<<bool log>>]]
    // then the apply rule method searches for what rule is for the read token and applies it

    public ParsingTable() {
        ArrayList<List<Token>> progRuleList = new ArrayList<>();
        ArrayList<Token> progRule = new ArrayList<>();
        progRule.add(new Token(Token.TokenType.PUBLIC, "public"));
        parsingTable.put("prog", progRuleList);
    }

    /*
     * Returns the production rule or null
     */
    public List<Token> applyRule(String topOfStack, Token input) {
        List<List<Token>> productionRules = parsingTable.get(topOfStack);
        for (List<Token> rule : productionRules) {
            for (Token token : rule) {
                if (token.equals(input)) {
                    return rule;
                }
            }
        }
        return null;
    }
}
