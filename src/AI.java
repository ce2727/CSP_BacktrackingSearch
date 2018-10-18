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
			
			if(c.getClass() == QueenConstraint.class)
			{
				QueenConstraint qc = (QueenConstraint) c;
				//Check which queen is unassigned
				
				//If Q1
				if(qc.Q1 == unassigned && a.assignments.get(qc.Q2) != null)
				{
					//Get row/column info
					Integer Q1r = i;
					Integer Q2r = a.assignments.get(qc.Q2);
					Integer Q1c = Integer.parseInt(qc.Q1.name);
					Integer Q2c = Integer.parseInt(qc.Q2.name);

					if(Q1r == Q2r) 	return false;

					//If Q2 is diagonally reachable from Q1
					if(diagReachable(Q2c, Q2r, Q1c, Q1r, csp.queensN)) 	return false;
					
				}else if (qc.Q2 == unassigned && a.assignments.get(qc.Q1) != null)
				{
					//Get row/column info
					Integer Q1r = a.assignments.get(qc.Q1);
					Integer Q2r = i;
					Integer Q1c = Integer.parseInt(qc.Q1.name);
					Integer Q2c = Integer.parseInt(qc.Q2.name);

					if(Q1r == Q2r) 	return false;

					//If Q1 is diagonally reachable from Q2
					if(diagReachable(Q1c, Q1r, Q2c, Q2r, csp.queensN)) 	return false;
				}				
			}
		}	
		return true;
	}

	public static boolean diagReachable(Integer x1, Integer y1, Integer x2, Integer y2, Integer n)
	{
		int[][] directions = {{1,0}, {1,-1}, {1,1}, {0,1}};
	    for (int[] d : directions) 
	    {
	        int dx = d[1];
	        int dy = d[0];

			int i = 0;
			int xCheck = (i * dx) + x1;
			int yCheck = (i * dy) + y1;
			//So long as we're in bounds
			
			while(((xCheck) < n) && ((yCheck) < n) && ((xCheck) > 0) && ((yCheck) > 0))
			{
				//If we find Q2
				if (xCheck == x2 && yCheck == y2) 
				{
					return true;
				}
				i++;
			}
	    }
		return false;
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
			//For each item in the variable's domain
			for(Integer i : csp.domain)
			{
				retList.add(i);
			}
			return retList;
	}
}
