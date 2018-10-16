import java.util.HashMap;
import java.util.HashSet;

public class Assignment 
{
	public HashMap<Variable, Integer> assignments;
	public HashSet<Variable> toAssign;
	public boolean isFailure = false;
	
	public Assignment(HashSet<Variable> toAssign)
	{
		this.toAssign = toAssign;
		this.assignments = new HashMap<>();
	}
	
	public void AddAssignment(Variable v, Integer i)
	{
		assignments.put(v, i);
	}
	
	public void removeAssignment(Variable v)
	{
		assignments.remove(v);
	}
	
	public boolean isComplete()
	{
		for(Variable a : toAssign)
		{
			if(!assignments.containsKey(a) || assignments.get(a) == null)
			{
				return false;
			}
		}
		return true;
	}
	
	public String toString() 
	{
		String ret = "";
		
		for (Variable v : toAssign)
		{
			if (assignments.get(v) == null)
			{
				ret += v.name + " : " + "null \n";
			} else
			{
				ret += v.name + " : " + assignments.get(v) + "\n";
			}
		}
		return ret;
	}
}
