//Gerard McGlone
// CS 0445 Spring 2016
// Read this class and its comments very carefully to make sure you implement
// the class properly.  Note the items that are required and that cannot be
// altered!  Generally speaking you will implement your MyStringBuilder using
// a singly linked list of nodes.  See more comments below on the specific
// requirements for the class.

// For more details on the general functionality of most of these methods, 
// see the specifications of the similar method in the StringBuilder class.  
public class MyStringBuilder
{
	// These are the only three instance variables you are allowed to have.
	// See details of CNode class below.  In other words, you MAY NOT add
	// any additional instance variables to this class.  However, you may
	// use any method variables that you need within individual methods.
	// But remember that you may NOT use any variables of any other
	// linked list class or of the predefined StringBuilder or 
	// or StringBuffer class in any place in your code.  You may only use the
	// String class where it is an argument or return type in a method.
	private CNode firstC;	// reference to front of list.  This reference is necessary
							// to keep track of the list
	private CNode lastC; 	// reference to last node of list.  This reference is
							// necessary to improve the efficiency of the append()
							// method
	private int length;  	// number of characters in the list

	// You may also add any additional private methods that you need to
	// help with your implementation of the public methods.

	// Create a new MyStringBuilder initialized with the chars in String s
	public MyStringBuilder(String s)
	{
		if (s == null || s.length() == 0) // Special case for empty String
		{					 			  // or null reference
			firstC = null;
			lastC = null;
			length = 0;
		}
		else
		{
			// Create front node to get started
			firstC = new CNode(s.charAt(0));
			length = 1;
			CNode currNode = firstC;
			// Create remaining nodes, copying from String.  Note
			// how each new node is simply added to the end of the
			// previous one.  Trace this to see what is going on.
			for (int i = 1; i < s.length(); i++)
			{
				CNode newNode = new CNode(s.charAt(i));
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			lastC = currNode;
		}
	}

	// Create a new MyStringBuilder initialized with the chars in array s
	public MyStringBuilder(char [] s)
	{
		if (s.length == 0) //case with an empty character array
		{
			firstC = null;
			lastC = null;
			length = 0;
		}
		else //normal case
		{
			firstC = new CNode(s[0]);
			length = 1;
			CNode currNode = firstC;
			for (int i = 1; i < s.length; i++)
			{
				CNode newNode = new CNode(s[i]);
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			lastC = currNode;
		}
	}

	// Create a new empty MyStringBuilder
	public MyStringBuilder()
	{
		firstC = null;
		lastC = null;
		length = 0;
	}

	// Append MyStringBuilder b to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(MyStringBuilder b)
	{
		append(b.toString());
		
		return this;
	}


	// Append String s to the end of the current MyStringBuilder, and return
	// the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(String s)
	{
		if (length < 1)
		{
			firstC = new CNode(s.charAt(0));
			CNode currNode = firstC;
			length = 1;
			
			for (int i = 1; i < s.length(); i++)
			{
				CNode newNode = new CNode(s.charAt(i));
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			lastC = currNode;
		}
		else
		{
			for (int i = 0; i < s.length(); i++)
			{
				CNode newNode = new CNode(s.charAt(i));
				lastC.next = newNode;
				lastC = newNode;
				length++;
			}
		}
		
		return this;
	}

	// Append char array c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char [] c)
	{
		if (length  < 1) //if StringBuilder object is empty
		{
			firstC = new CNode(c[0]);
			CNode currNode = firstC;
			length = 1;
			
			for (int i = 1; i < c.length; i++)
			{
				CNode newNode = new CNode(c[i]);
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			lastC = currNode;
		}
		else
		{
			for (int i = 0; i < c.length; i++)
			{
				CNode newNode = new CNode(c[i]);
				lastC.next = newNode;
				lastC = newNode;
				length++;
			}
		}
		
		return this;
	}

	// Append char c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char c)
	{
		if (length > 0) //assumes MyStringBuilder isn't empty
		{
			CNode newNode = new CNode(c);
			lastC.next = newNode;
			lastC = newNode;
			length++;
		}
		else //case with empty MyStringBuilder
		{
			CNode newNode = new CNode(c);
			firstC = newNode;
			lastC = newNode;
			length++;
		}
			
		
		return this; //returns reference to MyStringBuilder oject
	}

	// Return the character at location "index" in the current MyStringBuilder.
	// If index is invalid, throw an IndexOutOfBoundsException.
	public char charAt(int index)
	{
		char temp = firstC.data;
		
		if (index < 0 || index > length)
		{
			throw new IndexOutOfBoundsException();
		}
		else
		{
			CNode currNode = firstC;
			for (int i = 0; i < length; i++)
			{
				if (i == index)
				{
					temp = currNode.data;
				}
				currNode = currNode.next;
			}
		}
		return temp;
	}

	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder, and return the current MyStringBuilder.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder as is).  If "end" is past the end of the MyStringBuilder, 
	// only remove up until the end of the MyStringBuilder. Be careful for 
	// special cases!
	public MyStringBuilder delete(int start, int end)
	{
		if (start < 0 || end <= start) //doesnt change list
			return this;
		else if (end > length) //deletes rest of list from start
		{
			//traverse to start, set to null
			CNode currNode = firstC;
			
			for (int i = 0; i < length; i++)
			{
				if (i == start)
				{
					currNode = null;
					length = start;
					break;
				}
				currNode = currNode.next;
			}
			
		}
		else if (start == 0 && end == 1) //delete front node
		{
			CNode currNode = firstC;
			currNode = currNode.next;
			firstC = currNode;
			length--;
		}
		else if (start == 0) //changes list to start at end
		{
			CNode currNode = firstC;
			
			for (int i = 0; i < length; i++)
			{
				if (i == end)
				{
					firstC = currNode; //changes firstC to specified end
					length = length - end;
					break;
				}
				currNode = currNode.next;		
			}
		}
		else //firstC wont change because its not starting at 0
		{
			CNode currNode = firstC;
			int diff = end - start;
			
			for (int i = 0; i < length; i++)
			{
				if (i == start - 1)
				{
					CNode beginDelete = currNode;
					for (int j = 0; j < diff; j++)
					{
						currNode.next = currNode.next.next;
						currNode = beginDelete;
						length--;
					}
					break;
				}
				currNode = currNode.next;
			}
			
		}
		
		CNode currNode = firstC;
		
		for (int i = 0; i < length; i++) //reinitialize lastC
		{
			if (i == length - 1)
			{
				lastC = currNode;
			}
			currNode = currNode.next;
		}
		
		return this;
	}

	// Delete the character at location "index" from the current
	// MyStringBuilder and return the current MyStringBuilder.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder as is).
	// Be careful for special cases!
	public MyStringBuilder deleteCharAt(int index)
	{
		if (index < 0 || index >= length)
		{
			return this;
		}
		else if (index == 0)
		{
			firstC = firstC.next;
			length--;
			return this;
		}
		else
		{
			CNode currNode = firstC;
			
			for (int i = 0; i < length; i++)
			{
				if (i == (index - 1))
				{
					currNode.next = currNode.next.next;
					length--;
					break;
				}
				currNode = currNode.next;
			}
			return this;
		}
	}

	// Find and return the index within the current MyStringBuilder where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder.  If str does not match any sequence of characters
	// within the current MyStringBuilder, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
	public int indexOf(String str)
	{
		int index = -1;
		boolean wholeStringFound = false;
		
		CNode nodeBeforeSearch = firstC;
		
		for (int i = 0; i < length; i++)
		{
			CNode currNode = nodeBeforeSearch;
			for (int j = 0; j < str.length(); j++)
			{
				if (currNode != null && str.charAt(j) == currNode.data)
				{
					index = i;
					wholeStringFound = true;
				}
				else
				{
					wholeStringFound = false;
					index = -1;
					break;
				}
				currNode = currNode.next;
			}
			if (wholeStringFound)
				break;
			nodeBeforeSearch = nodeBeforeSearch.next;
		}
		return index;
	}

	// Insert String str into the current MyStringBuilder starting at index
	// "offset" and return the current MyStringBuilder.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder insert(int offset, String str)
	{
		if (offset < 0 || offset > length)
		{
			return this;
		}
		else if (offset == length)
		{
			this.append(str);
			return this;
		}
		else if (offset == 0)
		{
			for (int i = str.length() - 1; i >= 0; i--)
			{
				CNode newNode = new CNode(str.charAt(i));
				newNode.next = firstC;
				firstC = newNode;
				length++;
			}
			return this;	
		}
		else
		{
			CNode curr = firstC;
			
			for (int i = 0; i < length; i++)
			{
				if (i == offset - 1)
				{
					for (int j = 0; j < str.length(); j++)
					{
						CNode newNode = new CNode(str.charAt(j));
						newNode.next = curr.next;
						curr.next = newNode;
						length++;
						curr = curr.next;
					}
					break;
				}
				curr = curr.next;
			}
			
			return this;
		}
	}

	// Insert character c into the current MyStringBuilder at index
	// "offset" and return the current MyStringBuilder.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid, 
	// do nothing.
	public MyStringBuilder insert(int offset, char c)
	{
		if (offset < 0 || offset > length)
		{
			return this;
		}
		else if (offset == length)
		{
			this.append(c);
			return this;
		}
		else if (offset == 0)
		{
			CNode newNode = new CNode(c);
			newNode.next = firstC;
			firstC = newNode;
			length++;
			return this;
		}
		else
		{
			CNode curr = firstC;
			CNode newNode = new CNode(c);
			
			for (int i = 0; i < length; i++)
			{
				if (i == offset - 1)
				{
					newNode.next = curr.next;
					curr.next = newNode;
					length++;
					break;
				}
				curr = curr.next;
			}
			return this;
		}
				
	}

	// Insert char array c into the current MyStringBuilder starting at index
	// index "offset" and return the current MyStringBuilder.  If "offset" is
	// invalid, do nothing.
	public MyStringBuilder insert(int offset, char [] c)
	{
		if (offset < 0 || offset > length)
		{
			return this;
		}
		else if (offset == length)
		{
			this.append(c);
			return this;
		}
		else if (offset == 0)
		{
			for (int i = c.length - 1; i >= 0; i--)
			{
				CNode newNode = new CNode(c[i]);
				newNode.next = firstC;
				firstC = newNode;
				length++;
			}
			return this;	
		}
		else
		{
			CNode curr = firstC;
			
			for (int i = 0; i < length; i++)
			{
				if (i == offset - 1)
				{
					for (int j = 0; j < c.length; j++)
					{
						CNode newNode = new CNode(c[j]);
						newNode.next = curr.next;
						curr.next = newNode;
						length++;
						curr = curr.next;
					}
					break;
				}
				curr = curr.next;
			}
			return this;
		}
						
	}

	// Return the length of the current MyStringBuilder
	public int length()
	{
		return length;
	}


	// Delete the substring from "start" to "end" - 1 in the current
	// MyStringBuilder, then insert String "str" into the current
	// MyStringBuilder starting at index "start", then return the current
	// MyStringBuilder.  If "start" is invalid or "end" <= "start", do nothing.
	// If "end" is past the end of the MyStringBuilder, only delete until the
	// end of the MyStringBuilder, then insert.
	public MyStringBuilder replace(int start, int end, String str)
	{
		if (start < 0 || end <= start || start > length)
		{
			return this;
		}
		else if (end > length)
		{
			this.delete(start, length);
			this.insert(start, str);
			return this;
		}
		else //normal case 
		{
			this.delete(start, end);
			this.insert(start, str);
			return this;
		}
		
	}

	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder
	//CAN USE ARRAY OF CHARACTERS
	public String substring(int start, int end)
	{
		char[] a = new char[end - start];
		CNode curr = firstC;
		
		for (int i = 0; i < length; i++)
		{
			if (i == start)
			{
				for (int j = 0; j < (end - start); j++)
				{
					a[j] = curr.data;
					curr = curr.next;
				}
				break;
			}
			curr = curr.next;
		}
		String output = new String(a);
		return output;
	}

	// Return the entire contents of the current MyStringBuilder as a String
	public String toString()
	{
		char[] charArray = new char[length];
		
		if (length > 0)
		{
			CNode currNode = firstC;
			charArray[0] = currNode.data;
			currNode = currNode.next;
			
			for (int i = 1; i < length; i++)
			{
				charArray[i] = currNode.data;
				currNode = currNode.next;
			}
			String output = new String(charArray);
			return output;
		}
		else
		{
			return "";
		}
	}

	// You must use this inner class exactly as specified below.  Note that
	// since it is an inner class, the MyStringBuilder class MAY access the
	// data and next fields directly.
	private class CNode
	{
		private char data;
		private CNode next;

		public CNode(char c)
		{
			data = c;
			next = null;
		}

		public CNode(char c, CNode n)
		{
			data = c;
			next = n;
		}
	}
}
