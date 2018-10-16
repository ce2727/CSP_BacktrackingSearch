import java.util.Date;
import java.util.HashSet;

/*
 * Setup code referenced: Sample code provided by George Ferguson for this assignment
 */
public class JobScheduling extends CSP
{
	private final Integer timeAXEL = 10;
	private final Integer timeWHEEL = 1;
	private final Integer timeNUTS = 2;
	private final Integer timeCAP = 1;
	private final Integer timeINSPECT = 3;


	public JobScheduling() {
		this.domain = Domain.timeRange(30-timeINSPECT);
		this.vars = new HashSet<Variable>();
		this.cons = new HashSet<Constraint>();
		
		Variable AXELf = new Variable("AXELf", this.domain);
		Variable AXELb = new Variable("AXELb", this.domain);
		Variable WHEELrf = new Variable("WHEELrf", this.domain);
		Variable WHEELlf = new Variable("WHEELlf", this.domain);
		Variable WHEELrb = new Variable("WHEELrb", this.domain);
		Variable WHEELlb = new Variable("WHEELlb", this.domain);
		Variable NUTSrf = new Variable("NUTSrf", this.domain);
		Variable NUTSlf = new Variable("NUTSlf", this.domain);
		Variable NUTSrb = new Variable("NUTSrb", this.domain);
		Variable NUTSlb = new Variable("NUTSlb", this.domain);
		Variable CAPrf = new Variable("CAPrf", this.domain);
		Variable CAPlf = new Variable("CAPlf", this.domain);
		Variable CAPrb = new Variable("CAPrb", this.domain);
		Variable CAPlb = new Variable("CAPlb", this.domain);
		Variable INSPECT = new Variable("INSPECT", this.domain);

		this.vars.add(AXELf);
		this.vars.add(AXELb);
		this.vars.add(WHEELrf);
		this.vars.add(WHEELlf);
		this.vars.add(WHEELrb);
		this.vars.add(WHEELlb);
		this.vars.add(NUTSrf);
		this.vars.add(NUTSlf);
		this.vars.add(NUTSrb);
		this.vars.add(NUTSlb);
		this.vars.add(CAPrf);
		this.vars.add(CAPlf);
		this.vars.add(CAPrb);
		this.vars.add(CAPlb);
		this.vars.add(INSPECT);

		//Part order constraints
		this.cons.add(new TimeConstraint(AXELf, timeAXEL, WHEELrf));
		this.cons.add(new TimeConstraint(AXELf, timeAXEL, WHEELlf));
		this.cons.add(new TimeConstraint(AXELb, timeAXEL, WHEELrb));
		this.cons.add(new TimeConstraint(AXELb, timeAXEL, WHEELlb));
		this.cons.add(new TimeConstraint(WHEELrf, timeWHEEL, NUTSrf));
		this.cons.add(new TimeConstraint(WHEELlf, timeWHEEL, NUTSlf));
		this.cons.add(new TimeConstraint(WHEELrb, timeWHEEL, NUTSrb));
		this.cons.add(new TimeConstraint(WHEELlb, timeWHEEL, NUTSlb));
		this.cons.add(new TimeConstraint(NUTSrf, timeNUTS, CAPrf));
		this.cons.add(new TimeConstraint(NUTSlf, timeNUTS, CAPlf));
		this.cons.add(new TimeConstraint(NUTSrb, timeNUTS, CAPrb));
		this.cons.add(new TimeConstraint(NUTSlb, timeNUTS, CAPlb));
		
		//"Workers share the axel tool" so one comes before the other
		this.cons.add(new TimeConstraint(AXELf, 10, AXELb));
/*
		//Everything done before inspection
		this.cons.add(new TimeConstraint(AXELf, timeAXEL, INSPECT));
		this.cons.add(new TimeConstraint(AXELb, timeAXEL, INSPECT));
		this.cons.add(new TimeConstraint(WHEELrf, timeWHEEL, INSPECT));
		this.cons.add(new TimeConstraint(WHEELlf, timeWHEEL, INSPECT));
		this.cons.add(new TimeConstraint(WHEELrb, timeWHEEL, INSPECT));
		this.cons.add(new TimeConstraint(WHEELlb, timeWHEEL, INSPECT));
		this.cons.add(new TimeConstraint(NUTSrf, timeNUTS, INSPECT));
		this.cons.add(new TimeConstraint(NUTSlf, timeNUTS, INSPECT));
		this.cons.add(new TimeConstraint(NUTSrb, timeNUTS, INSPECT));
		this.cons.add(new TimeConstraint(NUTSlb, timeNUTS, INSPECT));
		*/
		this.cons.add(new TimeConstraint(CAPrf, timeCAP, INSPECT));
		this.cons.add(new TimeConstraint(CAPlf, timeCAP, INSPECT));
		this.cons.add(new TimeConstraint(CAPrb, timeCAP, INSPECT));
		this.cons.add(new TimeConstraint(CAPlb, timeCAP, INSPECT));
	}
	
	public static void main(String[] args) {
		System.out.println("Job Scheduling Problem (AIMA 6.1.2)");
		CSP csp = new JobScheduling();
		System.out.println("Backtracking search solver");
		long start = new Date().getTime();
		Assignment solution = AI.BacktrackingSearch(csp);
		long end = new Date().getTime();
		System.out.format("time: %.3f secs\n", (end-start)/1000.0);
		System.out.println("result=\n" + solution.toString());
	}
}
