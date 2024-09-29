import java.util.*;

public class SyntacticAnalyser {

	public static ParseTree parse(List<Token> tokens) throws SyntaxException {

		ParsingTable parsingTable = new ParsingTable();
		ParseTree tree = new ParseTree();
		Deque<Pair<Symbol, TreeNode>> stack = new ArrayDeque<>();

		stack.push(new Pair<>(TreeNode.Label.prog, null));

		int position = 0;

		while (!stack.isEmpty() && position < tokens.size()) {
			Pair<Symbol, TreeNode> topOfStack = stack.pop();

			determineAction(tokens.get(position), topOfStack, tree, parsingTable, stack);

			position++;
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

	private static void determineAction(Token currentToken,
                                        Pair<Symbol, TreeNode> topOfStack,
                                        ParseTree tree,
                                        ParsingTable parsingTable,
                                        Deque<Pair<Symbol, TreeNode>> stack) throws Error{
		TreeNode newNode = new TreeNode(getLabel(topOfStack.fst()), currentToken, topOfStack.snd());

		if (topOfStack.snd() == null){
			tree.setRoot(newNode);
		} else {
			topOfStack.snd().addChild(newNode);
		}
		if (topOfStack.fst().equals(TreeNode.Label.terminal)) {
			if (topOfStack.fst() != currentToken.getType()) {
				System.out.println("Top of stack = " + topOfStack.fst()); // for debugging
				System.out.println("Current input token = " + currentToken.getType()); // for debugging
			}
		} else {
			List<Pair<Symbol, Token.TokenType>> producedRule = parsingTable.applyRule(new Pair<>(topOfStack.fst(), currentToken.getType()));
			if (producedRule == null) {
				System.out.println("Produced empty rule for:" + topOfStack.fst() + currentToken.getType()); // for debugging
				return;
			}

			ListIterator<Pair<Symbol, Token.TokenType>> iterator = producedRule.listIterator(producedRule.size());
			while (iterator.hasPrevious()) {
				Pair<Symbol, Token.TokenType> current = iterator.previous();
				TreeNode newNodeChild = new TreeNode(getLabel(current.fst()), new Token(current.snd()), newNode);
				stack.push(new Pair<>(getSymbol(current), newNode));
			}
		}
	}

	private static Symbol getSymbol(Pair<Symbol, Token.TokenType> current) {
		if (current.snd() == null) {
			return current.fst();
		}
		return current.snd();
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
