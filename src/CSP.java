import java.util.HashSet;

public abstract class CSP 
{
	public HashSet<Variable> vars;
	public HashSet<Constraint> cons;
	public HashSet<Integer> domain;
	
	//Just used to provide AI with how large board is
	public Integer queensN;
	
}
