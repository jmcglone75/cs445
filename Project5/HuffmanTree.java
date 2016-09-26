//Gerard McGlone
//CS 445
/* This class provides a client with methods needed to
 * perform specific operations that deal with Huffman trees.
 * The class itself relies on BinaryNode from the author as the
 * fundemental tree representation.  What this class does is,
 * create a huffman tree from a text file, create a table from a huffman
 * tree, and methods to both decode and encode huffman strings.*/

import java.util.Scanner;
import java.lang.StringBuilder;

public class HuffmanTree<T>
{
	
	int numLetters = 0;

	public BinaryNode<T> build(Scanner fr, String str) //reads in Huffman tree from text file
	{	
		//recursive case:  if I is charAt(0), establish new root, and recursivley build
		//left and right subtrees
		if (str.charAt(0) == 'I') 
		{
			BinaryNode root = new BinaryNode('\0'); //build new root with dummy value
			str = fr.nextLine(); //advance scanner
			root.setLeftChild(build(fr, str)); //build left
			str = fr.nextLine();
			root.setRightChild(build(fr, str)); //build right
			return root;
		}
		else //base case, build leaf node
		{
			BinaryNode leaf = new BinaryNode(str.charAt(2));
			numLetters++;
			return leaf;
		}
	}
	
	//recursive method to build huffman table given a huffman tree
	//passes root node of huffman tree,
	//passes empty StringBuilder for creating table values
	//passes table
	public void buildTable (BinaryNode root, StringBuilder str, StringBuilder[] table)
	{
		if (root.isLeaf()) //base case, add current String Builder to table
		{
			StringBuilder temp = new StringBuilder(str);
			
			char a = (char) root.getData();
			int index = ((int) a) - 65;
			table[index] = temp;
			
		}
		else //recursive case
		{
			buildTable(root.getLeftChild(), str.append('0'), table); //build left
			str.deleteCharAt(str.length() - 1); //backtrack
			buildTable(root.getRightChild(), str.append('1'), table); //build right
			str.deleteCharAt(str.length() - 1); //backtrack
		}
	}
	
	//simply goes through string iterativley and appends huffman code 
	//depending on letter
	public StringBuilder encode (StringBuilder[] table, String str, int numLetters)
	{
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < str.length(); i++)
		{
			char temp = str.charAt(i);
			int index = ((int) temp) - 65;
			
			if (index >= numLetters || index < 0) //letter not in huffman tree
			{
				sb.delete(0, sb.length());
				return sb; //return empty StringBuilder object
			}
			sb.append(table[index]);
			sb.append('\n');
		
		}
		return sb;
	}
	
	//iterativley goes through each character in huffman string
	//if a leaf is found, append the character value to the StringBuilder
	//once a leaf is found, start at root node and try again
	public StringBuilder decode (BinaryNode root, String str)
	{
		BinaryNode temp = root;
		StringBuilder sb = new StringBuilder();
		boolean isFound = false;
		
		
		
		
		for (int i = 0; i < str.length(); i++)
		{
			char c = str.charAt(i);
			isFound = false;
			
			if (c == '0') //change node to left child
			{
				temp = temp.getLeftChild();
			}
			else if (c == '1') //change node to right child
			{
				temp = temp.getRightChild();
			}
			
			if (temp == null) //error in huffman string -- has us go to node that doesnt exist
			{
				return new StringBuilder("");
			}
			
			else if (temp.getData() != '\0')
			{
				 sb.append(temp.getData());
				 temp = root; //restore current node back to root for more searching
				 isFound = true;
			}
			
		} //end for
		
		//this is necessary in the case that the string ends and we are still not on a leaf node. it will be false in that case
		if (!isFound) 
		{
			return new StringBuilder("");
		}
		
		return sb;
		
	}	
	
	public int getNumLetters() //returns number of letters (or leafs) in the huffman tree
	{
		return numLetters;
	}
	
			

		
}
