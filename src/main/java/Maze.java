package main.java;

import java.awt.Point;

public class Maze {
	private boolean[][] maze;
	private Point startPoint, endPoint;

	public Maze(boolean[][] maze, Point startPoint, Point endPoint) {
		this.maze = maze;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	public boolean[][] getMaze() {
		return maze;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public int getWidth() {
		return maze[0].length;
	}

	public int getHeight() {
		return maze.length;
	}

	public void updateMaze(int row, int col) {
		maze[col][row] = true;
	}
}
