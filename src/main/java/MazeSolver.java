package main.java;

import java.awt.Point;

public class MazeSolver {
	private static boolean[][] wasHere;
	private static boolean[][] correctPath;

	public static boolean[][] solveMaze(Maze maze) {
		wasHere = new boolean[maze.getHeight()][maze.getWidth()];
		correctPath = new boolean[maze.getHeight()][maze.getWidth()];
		solve(maze.getStartPoint().y, maze.getStartPoint().x, maze, correctPath, wasHere);
		correctPath[maze.getEndPoint().y][maze.getEndPoint().x] = true;
		return correctPath;
	}

	/* Recursive Solution found on wikipedia */

	private static boolean solve(int x, int y, Maze maze, boolean[][] correctPath, boolean[][] wasHere) {
		if (x == maze.getEndPoint().y && y == maze.getEndPoint().x) {
			return true;
		}
		if (!maze.getMaze()[x][y] || wasHere[x][y]) {
			return false;
		}
		wasHere[x][y] = true;
		if (x != 0) {
			if (solve(x - 1, y, maze, correctPath, wasHere)) {
				correctPath[x][y] = true;
				return true;
			}
		}
		if (x != maze.getHeight() - 1) {
			if (solve(x + 1, y, maze, correctPath, wasHere)) {
				correctPath[x][y] = true;
				return true;
			}
		}
		if (y != 0) {
			if (solve(x, y - 1, maze, correctPath, wasHere)) {
				correctPath[x][y] = true;
				return true;
			}
		}
		if (y != maze.getWidth() - 1) {
			if (solve(x, y + 1, maze, correctPath, wasHere)) {
				correctPath[x][y] = true;
				return true;
			}
		}
		return false;
	}

}
