import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;

public class NQueens extends CSP
{
	public NQueens() {
		System.out.println("What N would you like to use for the N-Queens problem?");
		Scanner s = new Scanner(System.in);
		String stringN = s.nextLine();
		s.close();
		Integer n = Integer.parseInt(stringN);
		
		this.queensN = n;
		this.domain = Domain.timeRange(n-1);
		this.vars = new HashSet<Variable>();
		this.cons = new HashSet<Constraint>();
		ArrayList<Variable> setupList = new ArrayList<Variable>();
		
		for (Integer i = 0; i < n; i++)
		{
			Variable toAdd = new Variable(i.toString(), this.domain);
			this.vars.add(toAdd);
			setupList.add(toAdd);
		}
		
		
		//Enumerating constraints
		//For each queen (in order 0->n)
		for(Variable q1 : setupList)
		{
			//Get column # of queen one by parsing name to int
			Integer cInt = Integer.parseInt(q1.name);
			
			//Each column queen after r, whose constraints have yet to be enumerated
			for(Integer iQ2 = cInt+1; iQ2 < n; iQ2++)
			{
				//Get other queen in constraint from index
				Variable q2 = setupList.get(iQ2);
				this.cons.add(new QueenConstraint(q1,q2));
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("N-Queens Problem (AIMA 6.1.1)");
		CSP csp = new NQueens();
		System.out.println("Backtracking search solver");
		long start = new Date().getTime();
		Assignment solution = AI.BacktrackingSearch(csp);
		long end = new Date().getTime();
		System.out.format("time: %.3f secs\n", (end-start)/1000.0);
		
		System.out.println("Result: \n" + solution.toString());
	}
}
