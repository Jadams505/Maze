package main.java;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

	    System.out.println("How big would would like your maze?");
	    System.out.println("Enter width: ");
	    int width = input.nextInt();
	    System.out.println("Enter height: ");
	    int height = input.nextInt();
	    String answer = "n";
	    Maze maze = new MazeGenerator(width, height).generateMaze();
	    boolean[][] solution = MazeSolver.solveMaze(maze);
	    MazeDrawer md = new MazeDrawer(maze, solution);
	    md.drawMaze();
	    md.toImage();
	    while(!answer.equals("exit")){
	      System.out.println("Show Solution? y/n/exit ");
	      answer = input.next();
	      if(answer.equals("y")){
	        md.drawSolution();
	        md.toImage();
	      }else if (answer.equals("n")){
	        md.hideSolution();
	        md.toImage();
	      }
	    }
	    input.close();
	}
}
