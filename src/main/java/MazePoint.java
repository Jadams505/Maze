package main.java;

import java.awt.Point;

public class MazePoint {
	private Point point;
	private int paths;

	public MazePoint(Point point, int paths) {
		this.point = point;
		this.paths = paths;
	}

	public Point getPoint() {
		return point;
	}

	public int getPathCount() {
		return paths;
	}
}
