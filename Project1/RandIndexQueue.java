
//Gerard mcglone
//cs 445
//project 1

import java.util.Random;

public class RandIndexQueue<T> implements MyQ<T>, Shufflable, Indexable<T>
{
	private int itemTotal = 0;
	private T[] list; //array that keeps items

	
	public RandIndexQueue(int size)
	{
		@SuppressWarnings("unchecked")
		T[] temp1 = (T[]) new Object[size];
		list = temp1;
	}
	
	public boolean addItem(T item)
	{
		if (!this.full()) //adds item to array if there is room for it.
		{
			list[itemTotal] = item;
			itemTotal++;
			return true;
		}
		else
			return false;	
	}
	
	public T removeItem() //shift down to the left instead of putting value in last spot in specific index
	{
		if (itemTotal > 0)
		{
			T temp1 = list[0];
			for (int i = 1; i < list.length; i++)
			{
				list[i-1] = list[i];
			}
			itemTotal--;
			return temp1;
		}
		else
			return null;
	}
	
	public boolean full()
	{
		if (itemTotal == (list.length)) //returns true if array is full
			return true;
		else
			return false;
	}
	
	public boolean empty()
	{
		if (itemTotal == 0) //returns true if array is empty
			return true;
		else
			return false;
	}
	
	public int size() //returns number of items
	{
		return itemTotal;
	}
	
	public void clear()
	{
		for (int i = 0; i < list.length; i++)
		{
			list[i] = null;
		}
		
		itemTotal = 0;
	}
	
	public void shuffle()
	{
		Random rand = new Random();
		
		for (int i = 0; i < list.length*20; i++) 
		{
			int num1 = rand.nextInt(itemTotal);
			int num2 = rand.nextInt(itemTotal); //assigning random values only up to the num of elements being used so null values arent being swapped
			int num3 = rand.nextInt(itemTotal); //and data is kept contiguous
			
			T temp1 = list[num1];
			T temp2 = list[num2];
			list[num1] = temp2; //swapping
			list[num2] = list[num3];
			list[num3] = temp1;
		}
	}
	
	public T get (int i)
	{
		if (itemTotal < i+1)
			throw new IndexOutOfBoundsException();
		else
			return list[i];
	}
	
	public void set (int i, T item)
	{
		if (itemTotal < i+1)
			throw new IndexOutOfBoundsException();
		else
		{
			list[i] = item;
		}
	}
	public String toString() //return all elements in array in the order of one row
	{
		String temp = "";
		for (int i = 0; i < itemTotal; i++)
		{
			temp += list[i];
			temp += " ";
		}
		return temp;
	}
		
}
