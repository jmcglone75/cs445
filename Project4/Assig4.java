//Gerard McGlone
//Assig 4
//CS 445 - 9:30 TH

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Assig4
{
	static long numRecurse = 0;
	static ArrayList<Point> shortestPath = new ArrayList<Point>();
	static int numSolutions = 0;
	static int x_start;
	static int y_start;
	static int row;
	static int col;
	static char[][] shortestSolution;
	
	public static void main (String[] args) throws IOException
	{
		File file = new File(args[0]); //read in file
		Scanner fr = new Scanner(file);
		int mazeRow, mazeCol;
		String rowString;
		ArrayList<Point> path;
		boolean anotherMaze = true;
		char[][] maze;
		
		while (anotherMaze)
		{

			path = new ArrayList<Point>(); //data structure holding path
			
			//get dimensions of maze
			mazeRow = fr.nextInt(); //get num of rows
			mazeCol = fr.nextInt(); //get num of cols
			row = mazeRow;
			col = mazeCol;
		
			maze = new char[mazeRow][mazeCol]; //create 2D character array that functions as the maze
			shortestSolution = new char[mazeRow][mazeCol];
		
			//get starting point
			int start_x = fr.nextInt();
			int start_y = fr.nextInt();
			x_start = start_x;
			y_start = start_y;
		
		
			rowString = fr.nextLine();
		
			//construct maze
			for (int i = 0; i < mazeRow; i++)
			{
				rowString = fr.nextLine();
				rowString = rowString.replaceAll(" ", "");
				for (int x = 0; x < rowString.length(); x++)
				{
					maze[i][x] = rowString.charAt(x);
				}
			}
			System.out.println("---------------------------------------\n");
			System.out.println("\nThe new board is:");
			
				for (int i = 0; i < row; i++)
				{
					for (int j = 0; j < col; j++)
					{
						System.out.print(maze[i][j] + " ");
					}
					System.out.println();
				}	
				
			System.out.println("\nSearching for solutions starting at (" + start_x + ", " + start_y + ")\n");
			boolean solved = solveMaze(maze, start_x, start_y, path);
			System.out.println("\nThere were a total of " + numSolutions + " solutions found");
			System.out.println("A total of " + numRecurse + " recursive calls were made");
			if (numSolutions > 0)
			{
				System.out.println("The shortest solution had " + shortestPath.size() + " elements");
				for (int i = 0; i < row; i++)
				{
					for (int j = 0; j < col; j++)
					{
						System.out.print(shortestSolution[i][j] + " ");
					}
					System.out.println();
				}
				System.out.print("path: ");
				for (int i = 0; i < shortestPath.size(); i++)
					System.out.print(shortestPath.get(i) + " ");
				System.out.println("\n\n");
			}
		
			anotherMaze = fr.hasNextLine();
			if (anotherMaze)
			{
				numSolutions = 0;
				numRecurse = 0;
				shortestPath.clear();
			}
		}//end while	
		
		
	}
	
	public static boolean solveMaze(char[][] maze, int x, int y, ArrayList<Point> path)
	{	
		if (x < 0 || x >= maze.length || y < 0 || y >= maze[0].length)  //position in maze is out of bounds
			return false;
		else if (maze[x][y] == '1' || maze[x][y] == 'X') //position in maze is an obstacle
		{
			return false;
		}
		else //position in maze is valid
		{
			path.add(new Point(x,y)); //add point to path because its valid
			
			boolean solution;
			if (maze[x][y] == '2') //end goal found, solution is true
			{
				
				System.out.println("solution found with " + path.size() + " segments");
				for (int i = 0; i < row; i++)
				{
					for (int j = 0; j < col; j++)
					{
						System.out.print(maze[i][j] + " ");
					}
					System.out.println();
				}	
				
				System.out.print("path: ");
				for (int i = 0; i < path.size(); i++)
				{
					System.out.print(path.get(i) + " ");
				}
				
				if (numSolutions == 0)
				{
					//shortestPath = path;
					//create shortest path
					for (int i = 0; i < path.size(); i++)
					{
						shortestPath.add(path.get(i));
					}
					//create maze of shortest path
					for (int i = 0; i < row; i++)
					{
						for (int j = 0; j < col; j++)
						{
							char temp = maze[i][j];
							shortestSolution[i][j] = temp;
						}
					}
					
				}
				else
				{
					if (path.size() < shortestPath.size())
					{
						shortestPath.clear();
						for (int i = 0; i < path.size(); i++)
						{
							shortestPath.add(path.get(i));
						}
						//create maze of shortest path
						for (int i = 0; i < row; i++)
						{
							for (int j = 0; j < col; j++)
							{
								char temp = maze[i][j];
								shortestSolution[i][j] = temp;
							}
						}	
					}
				}
				System.out.println("\n");
				
				numSolutions++; //increment number of solutions
				
				//Backtrack when solution has been found to point before solution;
				path.remove(path.size() - 1);  //remove most recent point from path
				solution = false;
				
			}
			else  //recurse
			{
				maze[x][y] = 'X';
				solution = solveMaze(maze, x, y+1, path); //try moving right
				numRecurse++;
				if (!solution)
				{
					solution = solveMaze(maze, x+1, y, path); //try moving down
					numRecurse++;
				}
				if (!solution)
				{
					solution = solveMaze(maze, x, y-1, path); //try moving left
					numRecurse++;
				}
				if (!solution)
				{
					solution = solveMaze(maze, x-1, y, path); //try moving up
					numRecurse++;
				}
				if (!solution) //BACKTRACK
				{
					maze[x][y] = '0';
					path.remove(path.size() - 1);  //remove most recent point from path
					
					//the function has backtracked all the way to the starting point,
					//base case -- done recursing
					if (x == x_start && y == y_start) 
					{
						return true;
					}
					
				}
			}
			
			return solution;
		}

	}
		
}
