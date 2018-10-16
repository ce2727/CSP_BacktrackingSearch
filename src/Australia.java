import java.util.Date;
import java.util.HashSet;

/*
 * Setup code referenced: Sample code provided by George Ferguson for this assignment
 */
public class Australia extends CSP
{
	public Australia() {
		this.domain = Domain.color();
		this.vars = new HashSet<Variable>();
		this.cons = new HashSet<Constraint>();
		
		Variable WA = new Variable("WA", this.domain);
		Variable NT = new Variable("NT", this.domain);
		Variable Q = new Variable("Q", this.domain);
		Variable NSW = new Variable("NSW", this.domain);
		Variable V = new Variable("V", this.domain);
		Variable SA = new Variable("SA", this.domain);
		Variable T = new Variable("T", this.domain);
		this.vars.add(WA);
		this.vars.add(NT);
		this.vars.add(Q);
		this.vars.add(NSW);
		this.vars.add(V);
		this.vars.add(SA);
		this.vars.add(T);
		this.cons.add(new NotEqualConstraint(SA, WA));
		this.cons.add(new NotEqualConstraint(SA, NT));
		this.cons.add(new NotEqualConstraint(SA, Q));
		this.cons.add(new NotEqualConstraint(SA, NSW));
		this.cons.add(new NotEqualConstraint(SA, V));
		this.cons.add(new NotEqualConstraint(WA, NT));
		this.cons.add(new NotEqualConstraint(NT, Q));
		this.cons.add(new NotEqualConstraint(Q, NSW));
		this.cons.add(new NotEqualConstraint(NSW, V));
	}
	
	public static void main(String[] args) {
		System.out.println("Australia Map Coloring Problem (AIMA 6.1.1)");
		CSP csp = new Australia();
		System.out.println("Backtracking search solver");
		long start = new Date().getTime();
		Assignment solution = AI.BacktrackingSearch(csp);
		long end = new Date().getTime();
		System.out.format("time: %.3f secs\n", (end-start)/1000.0);
		
		System.out.println("Result: (0 = red, 1 = green, 2 = blue)\n" + solution.toString());
	}
}
