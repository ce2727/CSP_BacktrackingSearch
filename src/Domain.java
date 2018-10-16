import java.util.HashSet;

public class Domain 
{
	public static HashSet<Integer> color()
	{
		HashSet<Integer> domain = new HashSet<Integer>();
		//Red = 0
		domain.add(0);
		//Green = 1
		domain.add(1);
		//Blue = 2
		domain.add(2);
		
		return domain;
	}
	
	public static HashSet<Integer> timeRange(int length)
	{
		HashSet<Integer> span = new HashSet<Integer>();
		for(Integer i = 0; i <= length; i++)
		{
			span.add(i);
		}
		return span;
	}
}
