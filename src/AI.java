import java.util.Collections;
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
		Collections.sort(values);
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
		//Check that the constraint still holds
		for(Constraint c : csp.cons)
		{
			//If it's a NotEqualConstraint -- cast it to that
			if(c.getClass() == NotEqualConstraint.class)
			{
				NotEqualConstraint nec = (NotEqualConstraint) c;
				//If v1 is the unassigned
				if(nec.v1 == unassigned)
				{
					//check if i == v2
					if(i == a.assignments.get(nec.v2)) return false;
				} else if (nec.v2 == unassigned)
				{
					if(i == a.assignments.get(nec.v1)) return false;
				}
			}
			
			//If it's a NotEqualConstraint -- cast it to that
			if(c.getClass() == TimeConstraint.class)
			{
				TimeConstraint tc = (TimeConstraint) c;
				//If v1 is the unassigned
				if(tc.vSooner == unassigned && a.assignments.get(tc.vLater) != null)
				{
					//If setting the start time of a job would cause a conflict with a job that comes after, it's not consistent
					if(!((i + tc.duration) <= a.assignments.get(tc.vLater))) return false;
				} else if (tc.vLater == unassigned && a.assignments.get(tc.vSooner) != null)
				{
					//If setting the end time of a job conflicts with a job that comes before, it's not consistent
					if(!((a.assignments.get(tc.vSooner) + tc.duration) <= i)) return false;
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
