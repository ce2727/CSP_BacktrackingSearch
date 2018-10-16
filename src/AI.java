import java.util.HashSet;
import java.util.LinkedList;

public class AI 
{
	public static Assignment BacktrackingSearch(CSP csp)
	{
		Assignment a = new Assignment(csp.vars);
		
		for(Variable v : csp.vars)
		{
			a.AddAssignment(v, null);
		}
		
		return r_BT(a, csp);
	}
	
	public static Assignment r_BT(Assignment a, CSP csp)
	{
		if (a.isComplete())
		{
			return a;
		}
		
		Variable unassigned = selectUnassignedVariable(a);
		LinkedList<Integer> values = orderDomainValues(unassigned, a, csp);
		for(Integer i : values)
		{
			if(isConsistent(i, unassigned, a, csp))
			{
				a.AddAssignment(unassigned, i);
				Assignment result = r_BT(a, csp);
				if(result != null)
				{
					return result;
				} else 
				{
					a.removeAssignment(unassigned);
				}
			}
		}
		return null;
	}
	
	private static boolean isConsistent(Integer i, Variable unassigned, Assignment a, CSP csp) 
	{
		//Find constraints that are applicable to the assignment
		HashSet<Constraint> applicable = new HashSet<Constraint>();
		for(Constraint c : csp.cons)
		{
			if((c.v1 == unassigned || c.v2 == unassigned) && c.v1 != null && c.v2 != null)
			{
				applicable.add(c);
			}
		}
		//Check that the constraint still holds
		for(Constraint c : applicable)
		{
			if(c.getClass() == NotEqualConstraint.class)
			{
				//If v1 is the unassigned
				if(c.v1 == unassigned)
				{
					//check if i == v2
					if(i == a.assignments.get(c.v2)) return false;
				} else if (c.v2 == unassigned)
				{
					if(i == a.assignments.get(c.v1)) return false;
				}
			}
		}	
		return true;
	}

	//Helper Methods
	public static Variable selectUnassignedVariable(Assignment a)
	{
		for(Variable v : a.toAssign)
		{
			if(!a.assignments.containsKey(v) || a.assignments.get(v) == null)
			{
				return v;
			}
		}
		
		//Shouldn't be reached so long as isComplete is run beforehand
		return null;
	}
	
	public static LinkedList<Integer> orderDomainValues(Variable var, Assignment assignment, CSP csp)
	{
			LinkedList<Integer> retList = new LinkedList<Integer>();
			
			for(Integer i : csp.domain)
			{
				retList.add(i);
			}
			return retList;
	}
}
