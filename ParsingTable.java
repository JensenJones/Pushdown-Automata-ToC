import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ParsingTable {
    private final Map<String, List<Token>> parsingTable = new HashMap<>();

    public ParsingTable() {
        ArrayList<Token> progList = new ArrayList<>();
        progList.add(new Token(Token.TokenType.PUBLIC, "public"));
        parsingTable.put("prog", progList);
    }

    public List<Token> getPossibleNext(String current) {
        return parsingTable.get(current);
    }
}
