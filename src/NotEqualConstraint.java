
public class NotEqualConstraint extends Constraint
{
	public Variable v1;
	public Variable v2;
	
	public NotEqualConstraint(Variable v1, Variable v2)
	{
		this.v1 = v1;
		this.v2 = v2;
	}
	
}
