import java.util.*;

public class SyntacticAnalyser {

	public static ParseTree parse(List<Token> tokens) throws SyntaxException {

		ParsingTable parsingTable = new ParsingTable();
		ParseTree tree = new ParseTree();
		Deque<Pair<Symbol, TreeNode>> stack = new ArrayDeque<>();

		stack.push(new Pair<>(TreeNode.Label.prog, null));

		int position = 0;

		while (!stack.isEmpty() && position < tokens.size()) {
            position += determineAction(tokens.get(position), stack.pop(), tree, parsingTable, stack);
		}

		if (tree.getRoot() == null || !stack.isEmpty()) {
			throw new SyntaxException("Syntax Error");
		}

		return tree;
	}

	private static TreeNode.Label getLabel(Symbol fst) {
		try {
			return TreeNode.Label.valueOf(fst.toString());
		} catch (IllegalArgumentException e) {
			return TreeNode.Label.terminal;
		}
	}

	private static Symbol getSymbol(Pair<Symbol, Token.TokenType> current) {
		if (current.snd() == null) {
			return current.fst();
		}
		return current.snd();
	}

	private static int determineAction(Token currentToken,
										Pair<Symbol, TreeNode> topOfStack,
										ParseTree tree,
										ParsingTable parsingTable,
										Deque<Pair<Symbol, TreeNode>> stack) throws SyntaxException {
		TreeNode newNode = new TreeNode(getLabel(topOfStack.fst()), currentToken, topOfStack.snd());

		if (topOfStack.snd() == null){
			tree.setRoot(newNode);
		} else {
			topOfStack.snd().addChild(newNode);
		}
		if (topOfStack.fst() instanceof Token.TokenType) {
			if (topOfStack.fst() != currentToken.getType()) {
				throw new SyntaxException("Syntax Exception");
			}
			return 1;
		} else {
			List<Pair<Symbol, Token.TokenType>> producedRule = parsingTable.applyRule(new Pair<>(topOfStack.fst(), currentToken.getType()));
			if (producedRule == null) {
				System.out.println("DEBUGGING NULL RULE PRODUCED:");
				System.out.println("Rule: " + topOfStack.fst() + " --> " + currentToken.getType() + " = NULL");
				throw new SyntaxException("Syntax Exception");
			}

			ListIterator<Pair<Symbol, Token.TokenType>> iterator = producedRule.listIterator(producedRule.size());
			while (iterator.hasPrevious()) {
				Pair<Symbol, Token.TokenType> current = iterator.previous();
				stack.push(new Pair<>(getSymbol(current), newNode));
			}
			return 0;
		}
	}
}

// The following class may be helpful.


class Pair<A, B> {
	private final A a;
	private final B b;

	public Pair(A a, B b) {
		this.a = a;
		this.b = b;
	}

	public A fst() {
		return a;
	}

	public B snd() {
		return b;
	}

	@Override
	public int hashCode() {
		return 3 * a.hashCode() + 7 * b.hashCode();
	}

	@Override
	public String toString() {
		return "{" + a + ", " + b + "}";
	}

	@Override
	public boolean equals(Object o) {
		if ((o instanceof Pair<?, ?>)) {
			Pair<?, ?> other = (Pair<?, ?>) o;
			return other.fst().equals(a) && other.snd().equals(b);
		}

		return false;
	}

}
