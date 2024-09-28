import java.util.*;

public class SyntacticAnalyser {

	public static ParseTree parse(List<Token> tokens) throws SyntaxException {

		ParsingTable parsingTable = new ParsingTable();
		ParseTree tree = new ParseTree();
		Deque<Pair<Symbol, TreeNode>> stack = new ArrayDeque<>();

		stack.push(new Pair<> (TreeNode.Label.prog,null));

		int position = 0;

		while (!stack.isEmpty() && position < tokens.size()) {
			Pair topOfStack = stack.pop();
			TreeNode newNode = new TreeNode(getLabel(topOfStack.fst()), tokens.get(position), topOfStack.snd());
			applyRule(topOfStack.fst(), tokens.get(position), stack, newNode);

			if (topOfStack.snd() == null){
				tree.setRoot(newNode);
			} else {
				topOfStack.snd().addChild(newNode);
			}

			if (!topOfStack.fst().isVariable()){
				position ++;
			}
		}

		return new ParseTree();
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
