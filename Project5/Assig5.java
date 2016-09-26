//Gerard McGlone
//CS 445

import java.io.*;
import java.util.Scanner;

public class Assig5
{
	public static void main (String[] args) throws IOException
	{
		File file = new File (args[0]);
		Scanner fr = new Scanner(file);
		
		HuffmanTree tree = new HuffmanTree(); //dummy variable that allows us to perform operations given a root node.
		BinaryNode root = tree.build(fr, fr.nextLine()); //build tree from text file
		System.out.println("\nThe Tree has been restored\n");

		StringBuilder[] table = new StringBuilder[tree.getNumLetters()];
		StringBuilder str = new StringBuilder();
		
		tree.buildTable(root, str, table); //constructs huffman table
			
		int numLetters = tree.getNumLetters();
		
		int choice = 1;
		Scanner scanInt = new Scanner(System.in);
		Scanner scanText = new Scanner(System.in);
		
		
		
		while (choice > 0)
		{
			System.out.println("\nPlease choose from the following:\n1) Encode a text string\n2) Decode a Huffman String\n3) Quit");
			choice = scanInt.nextInt();
			
			
			
			if (choice == 1) //encode option 1
			{
				if (numLetters == 6)
					System.out.println("Enter a String from the following characters:\nABCDEF");
				else
					System.out.println("Enter a String from the following characters:\nABCDEFGHI");
				
					String input = scanText.nextLine();
					StringBuilder sb = tree.encode(table, input, numLetters); //encode to a huffman string
					if (sb.length() > 0)
					{
						System.out.println("Huffman String:\n");
						System.out.println(sb);
					}
					else
					{
						System.out.println("There was an error in your text string\n");
					}	
			}
			else if (choice == 2) //Decode option 3
			{
				System.out.println("Here is the encoding table:");
				if (numLetters == 6)
				{
					System.out.println("A: " + table[0]);
					System.out.println("B: " + table[1]);
					System.out.println("C: " + table[2]);
					System.out.println("D: " + table[3]);
					System.out.println("E: " + table[4]);
					System.out.println("F: " + table[5]);
				}
				else
				{
					System.out.println("A: " + table[0]);
					System.out.println("B: " + table[1]);
					System.out.println("C: " + table[2]);
					System.out.println("D: " + table[3]);
					System.out.println("E: " + table[4]);
					System.out.println("F: " + table[5]);
					System.out.println("G: " + table[6]);
					System.out.println("H: " + table[7]);
					System.out.println("I: " + table[8]);
				}
				
				System.out.println("Please enter a Huffman string (one line, no spaces)");
				String input = scanText.nextLine();
				StringBuilder sb = tree.decode(root, input); //decode huffman string
				
				if (sb.length() == 0)
					System.out.println("There was an error in your Huffman string");
				else
					System.out.println(sb);
			}
			else //exit option 3
			{
				System.out.println("Goodbye");
				break;
			}
			
			
		}
	}
}
