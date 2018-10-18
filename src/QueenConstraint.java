
public class QueenConstraint extends Constraint
{
	Variable Q1;
	Variable Q2;
	
	public QueenConstraint(Variable Q1, Variable Q2)
	{
		this.Q1 = Q1;
		this.Q2 = Q2;
	}
}
