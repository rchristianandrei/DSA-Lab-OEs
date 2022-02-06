
public class Cards {
	
	public String name;
	public String shape;
	
	public Cards (String name, String shape)
	{
		this.name = name;
		this.shape = shape;
	}
	
	public String toString()
	{
		return name + " of " + shape;
	}
}