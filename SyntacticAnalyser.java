import java.util.*;

public class SyntacticAnalyser {

	public static ParseTree parse(List<Token> tokens) throws SyntaxException {

		var tree = new ParseTree();
		var stack = new ArrayDeque<Pair<Symbol, TreeNode>>();

		stack.push(new Pair<> (TreeNode.Label.prog,null));

		var position = 0;

		while (!stack.isEmpty() && position < tokens.size()) {
			var s = stack.pop();
			var newNode = new TreeNode(getLabel(s.fst()), tokens.get(position), s.snd());
			applyRule(s.fst(), tokens.get(position), stack, newNode);

			if (s.snd() == null){
				tree.setRoot(newNode);
			} else {
				s.snd().addChild(newNode);
			}

			if (!s.fst().isVariable()){
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
