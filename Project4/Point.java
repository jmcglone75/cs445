//Gerard McGlone
//T TH -- 9:30
//class used to create point objects, which are then kept track of in the 
//Path and shortest path ArrayLists

public class Point
{
	int x, y;
	
	public Point (int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public Point()
	{
		x = 0;
		y = 0;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int x)
	{
		this.y = y;
	}
	
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}
}
