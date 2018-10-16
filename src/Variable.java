import java.util.HashSet;

public class Variable 
{
	public String name;
	public HashSet<Integer> domain;
	
	public Variable(String name, HashSet<Integer> domain)
	{
		this.name = name;
		this.domain = domain;
	}
}
