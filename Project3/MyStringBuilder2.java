// CS 0445 Spring 2016
// Read this class and its comments very carefully to make sure you implement
// the class properly.  Note the items that are required and that cannot be
// altered!  Generally speaking you will implement your MyStringBuilder using
// a singly linked list of nodes.  See more comments below on the specific
// requirements for the class.

// For more details on the general functionality of most of these methods, 
// see the specifications of the similar method in the StringBuilder class.  
public class MyStringBuilder2
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
	public MyStringBuilder2(String s)
	{
	  if (s != null && s.length() > 0)
           makeBuilder(s, 0);
      else  // no String so initialize empty MyStringBuilder2
      {
            length = 0;
            firstC = null;
            lastC = null;
      }
	}

	// Create a new MyStringBuilder initialized with the chars in array s
	public MyStringBuilder2(char [] s)
	{
		if (s.length > 0)
		{
			String str = new String(s);
			makeBuilder(str, 0);
		}
		else
		{
			length = 0;
			firstC = null;
			lastC = null;
		}
	}

	// Create a new empty MyStringBuilder
	public MyStringBuilder2()
	{
		length = 0;
		firstC = null;
		lastC = null;
	}

	// Append MyStringBuilder b to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder2 append(MyStringBuilder2 b)
	{
		String str = b.toString();
		
		if (length > 0)
			appendHelperStr(str, 0);
		else //special case beggining isn't
		{
			CNode newNode = new CNode(str.charAt(0));
			firstC = newNode;
			lastC = newNode;
			length++;
			appendHelperStr(str, 1);
		}
		return this;
	}


	// Append String s to the end of the current MyStringBuilder, and return
	// the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder2 append(String s)
	{
		if (length > 0)
		{
			appendHelperStr(s, 0);
		}
		else
		{
			CNode newNode = new CNode(s.charAt(0));
			firstC = newNode;
			lastC = newNode;
			length++;
			appendHelperStr(s, 1);
		}
		return this;
	}

	// Append char array c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder2 append(char[] c)
	{
		if (length > 0)
		{
			appendHelperChar(c, 0);
		}
		else
		{
			CNode newNode = new CNode(c[0]);
			firstC = newNode;
			lastC = newNode;
			length++;
			appendHelperChar(c, 1);
		}
		return this;
	}

	// Append char c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder2 append(char c)
	{
		if (length > 0)
		{
			CNode newNode = new CNode(c);
			lastC.next = newNode;
			lastC = newNode;
			length++;
		}
		else //special case of empty MyStringBuilder2
		{
			CNode newNode = new CNode(c);
			firstC = newNode;
			lastC = newNode;
			length++;
		}
		return this;
			
	}

	// Return the character at location "index" in the current MyStringBuilder.
	// If index is invalid, throw an IndexOutOfBoundsException.
	public char charAt(int index)
	{
		if (index < 0 || index > (length - 1))
			throw new IndexOutOfBoundsException();
		else
			return charFind(index, 0, firstC);
	}
	
	private char charFind(int index, int count, CNode curr) //recursive method that charAt() uses
	{
		if (count == index) //base case
			return curr.data;
		else //recursive case
			return charFind(index, count + 1, curr.next);
	}

	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder, and return the current MyStringBuilder.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder as is).  If "end" is past the end of the MyStringBuilder, 
	// only remove up until the end of the MyStringBuilder. Be careful for 
	// special cases!
	public MyStringBuilder2 delete(int start, int end) //TODO: create recursive traverse method and recursive delete method
	{
		if (start < 0 || end <= start) //doesnt change list
			return this;
		else if (end > length)
		{
			//use recursive traverse method that returns the node before we want to start deleting
			CNode lastC = traverse(start, firstC, 0);
			lastC.next = null; //break rest of linked list
			length = start;
			return this;
		}
		else if (start == 0) //delete front node and then from there on out
		{
			//call traverse method and change firstC to "end"
			CNode temp = traverse(end, firstC, 0);
			firstC = temp.next;
			length = length - end;
			return this;
		}
		else//iterative version used two loops.  use recurseive traverrse method to get starting node, then use recursive delete method
		{
			CNode temp = traverse(start, firstC, 0);
			deleteHelper(end-start, temp, 0);
			return this;
		}
	}
	
	private CNode traverse (int index, CNode curr, int pos) //recursive method that will bring us to node before
	{
		if (index - 1 == pos)
			return curr;
		else
			return traverse(index, curr.next, pos + 1);
	}
	
	private void deleteHelper(int numNodesToDelete, CNode Beggining, int count)
	{
		if (count < numNodesToDelete)
		{
			CNode temp = Beggining;
			Beggining.next = Beggining.next.next;
			length--;
			Beggining = temp;
			deleteHelper(numNodesToDelete, Beggining, count+1);
		}
	}

	// Delete the character at location "index" from the current
	// MyStringBuilder and return the current MyStringBuilder.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder as is).
	// Be careful for special cases!
	public MyStringBuilder2 deleteCharAt(int index)
	{
		if (index < 0 || index >= length) //ivalid index
		{
			return this;
		}
		else if (index == 0) //special case--front node
		{
			firstC = firstC.next;
			length--;
			return this;
		}
		else //call recusive helper method
		{
			deleteCharAtRecur(index, firstC, 0);
		}
		
		return this;
			
	}
	
	private void deleteCharAtRecur(int index, CNode curr, int pos)
	{
		if (pos == (index - 1))
		{
			curr.next = curr.next.next;
			length--;
			if (index == (length - 1))
				lastC = curr;
		}
		else
			deleteCharAtRecur(index, curr.next, pos + 1);
	}
	

	// Find and return the index within the current MyStringBuilder where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder.  If str does not match any sequence of characters
	// within the current MyStringBuilder, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
	public int indexOf(String str)
	{
		int indexOf = recurseIndexOf(firstC, length, 0, -1, str);
		return indexOf;
	}
	
	private int recurseIndexOf(CNode curr, int length, int counter, int indexOf, String str) //recursive helper method that loops through
	{
		if (counter == (length - 1)) //base case
		{
			return indexOf;
		}
		else
		{
			if (curr.data == str.charAt(0))
			{
				indexOf = counter;
				CNode temp = curr; //save temp of current Node so we can continue 
				//call recursive String comparison (boolean type)
				boolean isString = compare(curr.next, str, 1); //start with index 1
				//pass index of first character into comparison method.  if method returns true, index is the initially passed in index, else, -1
				if (isString) //string found
				{					
					return indexOf;
				}
				else //string not found, but going to continue searching
				{
					return recurseIndexOf(temp.next, length, counter + 1, -1, str);
				}
					
				//if false, call recurseIndexOf() method again, else, terminate method
			}
			else
			{
				//call recurseIndexOf
				return recurseIndexOf(curr.next, length, counter + 1, -1, str);
			}
		}
	}
	
	private boolean compare(CNode curr, String str, int counter)
	{
		if (counter == str.length() - 1 && curr != null) //base case end of string
		{
			if (curr.data == str.charAt(counter))
			{
				return true;
			}
			else
				return false;
		}
		else if (curr == null || curr.data != str.charAt(counter)) //base case .data != charAt()
		{
			return false;
		}
		else //recursive case: end of string has not been reached and curr.data == str.charAt(counter)
		{
			return compare(curr.next, str, counter + 1);
		}
	}
		


	// Insert String str into the current MyStringBuilder starting at index
	// "offset" and return the current MyStringBuilder.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder2 insert(int offset, String str)
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
			insertFrontString(firstC, str, str.length() - 1);
			return this;
		}
		else
		{
			CNode begin = traverse(offset, firstC, 0);
			insertHelperString(begin, str, 0);
			return this;
		}
	}
	
	private void insertFrontString(CNode front, String str, int count)
	{
		if (count == 0) //base case
		{
			CNode newNode = new CNode(str.charAt(count));
			newNode.next = firstC;
			firstC = newNode;
			length++;
		}
		else
		{
			CNode newNode = new CNode(str.charAt(count));
			newNode.next = firstC;
			firstC = newNode;
			length++;
			insertFrontString(firstC, str, count-1); //recursive case
		}
	}
	
	private void insertHelperString(CNode curr, String str, int count)
	{
		if (count == str.length() - 1) //base case
		{
			CNode newNode = new CNode(str.charAt(count));
			newNode.next = curr.next;
			curr.next = newNode;
			length++;
		}
		else
		{
			CNode newNode = new CNode(str.charAt(count));
			newNode.next = curr.next;
			curr.next = newNode;
			length++;
			insertHelperString(curr.next, str, count + 1); //recursive call/case
		}
	}

	// Insert character c into the current MyStringBuilder at index
	// "offset" and return the current MyStringBuilder.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid, 
	// do nothing.
	public MyStringBuilder2 insert(int offset, char c)
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
			CNode newNode = new CNode(c);
			CNode curr = traverse(offset, firstC, 0);
			newNode.next = curr.next;
			curr.next = newNode;
			length++;
			return this;
		}
	}

	// Insert char array c into the current MyStringBuilder starting at index
	// index "offset" and return the current MyStringBuilder.  If "offset" is
	// invalid, do nothing.
	public MyStringBuilder2 insert(int offset, char [] c)
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
			insertFront(firstC, c, c.length - 1);
			return this;
		}
		else
		{
			CNode begin = traverse(offset, firstC, 0);
			insertHelper(begin, c, 0);
			return this;
		}
			
	}
	
	private void insertFront(CNode front, char[] c, int count)
	{
		if (count == 0) //base case
		{
			CNode newNode = new CNode(c[count]);
			newNode.next = firstC;
			firstC = newNode;
			length++;
		}
		else
		{
			CNode newNode = new CNode(c[count]);
			newNode.next = firstC;
			firstC = newNode;
			length++;
			insertFront(firstC, c, count-1); //recursive case
		}
	}
	
	private void insertHelper(CNode curr, char[] c, int count)
	{
		if (count == c.length - 1) //base case
		{
			CNode newNode = new CNode(c[count]);
			newNode.next = curr.next;
			curr.next = newNode;
			length++;
		}
		else
		{
			CNode newNode = new CNode(c[count]);
			newNode.next = curr.next;
			curr.next = newNode;
			length++;
			insertHelper(curr.next, c, count + 1); //recursive call/case
		}
	}

	//Return the length of the current MyStringBuilder
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
	public MyStringBuilder2 replace(int start, int end, String str)
	{
		if (start < 0 || end <= start || start > length)
			return this;
		else if (end > length)
		{
			CNode curr = traverse(start + 1, firstC, 0);
			replaceTillEnd((length - start), curr, str, 0);
			append(str.substring(length-start, str.length()));
			return this;
		}
		else  
		{
			CNode curr = traverse(start + 1, firstC, 0);
			replaceTillEnd((end-start), curr, str, 0); //replace characters
			insert(end, str.substring((end-start), str.length()));
			return this;
		}			
	}
	private void replaceTillEnd(int numCharReplaced, CNode begin, String str, int counter)
	{
		if (numCharReplaced == 1) //base case
		{
			begin.data = str.charAt(counter);
		}	
		else
		{
			begin.data = str.charAt(counter);
			replaceTillEnd(numCharReplaced - 1, begin.next, str, counter + 1);
		}
	}

	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder
	public String substring(int start, int end)
	{
		char[] a = new char[end - start];
		CNode curr = traverse(start + 1, firstC, 0);
		substringHelper(a, (end-start), 0, curr);
		return new String(a);
	}
	
	private void substringHelper(char[] a, int length, int count, CNode curr)
	{
		if (count == length - 1)
		{
			a[count] = curr.data;
		}
		else
		{
			a[count] = curr.data;
			substringHelper(a, length, count + 1, curr.next);
		}
	}

	// Return the entire contents of the current MyStringBuilder as a String
	public String toString()
	{
		char [] c = new char[length];
        getString(c, 0, firstC);
        return (new String(c));
	}
	 
	// Recursive method to set up a new MyStringBuilder2 from a String
	private void makeBuilder(String s, int pos)
	{
      // Recursive case – we have not finished going through the String
      if (pos < s.length()-1)
      {
            // Note how this is done – we make the recursive call FIRST, then
            // add the node before it.  In this way the LAST node we add is
            // the front node, and it enables us to avoid having to make a
            // special test for the front node.  However, many of your
            // methods will proceed in the normal front to back way.
            makeBuilder(s, pos+1);
            firstC = new CNode(s.charAt(pos), firstC);
            length++;
      }
      else if (pos == s.length()-1) // Special case for last char in String
      {                             // This is needed since lastC must be
                                    // set to point to this node
            firstC = new CNode(s.charAt(pos));
            lastC = firstC;
            length = 1;
      }
      else  // This case should never be reached, due to the way the
	  {	// constructor is set up.  However, I included it as a
          // safeguard (in case some other method calls this one)
            length = 0;
            firstC = null;
            lastC = null;
      }
	}
	private void getString(char [] c, int pos, CNode curr)
	{
      if (curr != null)
      {
            c[pos] = curr.data;
            getString(c, pos+1, curr.next);
      }
	}
	
	private void appendHelperStr(String str, int pos) //recursive method that appends string to stringbuilder object
	{
		if (pos == (str.length() - 1)) //base case
		{
			CNode newNode = new CNode(str.charAt(pos));
			lastC.next = newNode;
			lastC = newNode;
			length++;
		}
		else //recursive case
		{
			CNode newNode = new CNode(str.charAt(pos));
			lastC.next = newNode;
			lastC = newNode;
			length++;
			appendHelperStr(str, pos + 1); //update
		}
	}
	
	private void appendHelperChar(char[] c, int pos) //recursive method that appends characters to string builder object
	{
		if (pos == c.length - 1) //base case
		{
			CNode newNode = new CNode(c[pos]);
			lastC.next = newNode;
			lastC = newNode;
			length++;
		}
		else
		{
			CNode newNode = new CNode(c[pos]);
			lastC.next = newNode;
			lastC = newNode;
			length++;
			appendHelperChar(c, pos + 1);
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
