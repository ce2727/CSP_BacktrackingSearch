
public class TimeConstraint extends Constraint
{
	Variable vSooner;
	Variable vLater;
	Integer duration;
	
	public TimeConstraint(Variable vSooner, Integer duration, Variable vLater)
	{
		this.vSooner = vSooner;
		this.vLater = vLater;
		this.duration = duration;
	}
}
