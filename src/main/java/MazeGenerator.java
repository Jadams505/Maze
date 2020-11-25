package main.java;

import java.awt.Point;
import java.util.ArrayList;

public class MazeGenerator {
	private final Point OUT_OF_BOUNDS = new Point(-1, -1);
	private boolean[][] maze; // false = wall, true = path
	private Point startPoint, endPoint;
	private ArrayList<MazePoint> pointsInMaze = new ArrayList<MazePoint>();
	private int width, height;

	public MazeGenerator(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public Maze generateMaze() {
		initilizeMaze();
		generatePaths();
		return new Maze(maze, startPoint, endPoint);
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean[][] getMaze() {
		return maze;
	}

	/* Set all maze values to false and calculates a random startPoint */
	private void initilizeMaze() {
		maze = new boolean[height][width];
		System.out.println(height);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				maze[i][j] = false;
			}
		}
		this.startPoint = new Point(2 + (int) (Math.random() * (width - 4)), 0);
	}

	/* Path Generation */

	private void generatePath(MazePoint start) {
		updateMaze(start.getPoint());
		MazePoint nextPos = randomNextPos(start.getPoint());
		if (start.getPathCount() < 1) {
			pointsInMaze.remove(start);
		} else {
			pointsInMaze.set(pointsInMaze.indexOf(start), new MazePoint(start.getPoint(), start.getPathCount() - 1));
		}
		while (nextPos != null) {
			updateMaze(nextPos.getPoint());
			if (nextPos.getPathCount() >= 1) {
				pointsInMaze.add(nextPos);
			}
			nextPos = randomNextPos(nextPos.getPoint());
		}
	}

	private void generatePaths() {
		pointsInMaze.add(new MazePoint(startPoint, 1));
		while (!pointsInMaze.isEmpty()) {
			MazePoint random = pointsInMaze.get((int) (Math.random() * pointsInMaze.size()));
			generatePath(random);
		}
		makeEndPoint();
	}

	/* The end point is always on the bottom */

	private void makeEndPoint() {
		ArrayList<Point> ends = new ArrayList<Point>();
		for (int i = 0; i < width; i++) { // bottom
			if (maze[height - 2][i]) {
				Point b = new Point(i, height - 1);
				ends.add(b);
			}
		}
		int random = (int) (Math.random() * ends.size());
		Point end = ends.get(random);
		endPoint = end;
		updateMaze(end);
	}

	private void updateMaze(Point p) {
		maze[p.y][p.x] = true;
	}

	private boolean validSpace(Point p) {
		return !p.equals(OUT_OF_BOUNDS) && !isBorder(p) && !isEmpty(p);
	}

	private boolean isEmpty(Point p) {
		return maze[p.y][p.x];
	}

	private boolean isBorder(Point p) {
		return p.x == 0 || p.x == width - 1 || p.y == 0 || p.y == height - 1;
	}

	/* Returns adjacent point if it's valid */

	private Point up(Point p) {
		int y = p.y - 1;
		if (y >= 0) {
			return new Point(p.x, y);
		}
		return OUT_OF_BOUNDS;
	}

	private Point right(Point p) {
		int x = p.x + 1;
		if (x < width) {
			return new Point(x, p.y);
		}
		return OUT_OF_BOUNDS;
	}

	private Point down(Point p) {
		int y = p.y + 1;
		if (y < height) {
			return new Point(p.x, y);
		}
		return OUT_OF_BOUNDS;
	}

	private Point left(Point p) {
		int x = p.x - 1;
		if (x >= 0) {
			return new Point(x, p.y);
		}
		return OUT_OF_BOUNDS;
	}

	private boolean canMoveLeft(Point p) {
		Point l = left(p);
		return validSpace(l) && (isBorder(left(l)) || validSpace(left(l))) && validSpace(up(l)) && validSpace(down(l));
	}

	private boolean canMoveRight(Point p) {
		Point r = right(p);
		return validSpace(r) && (isBorder(right(r)) || validSpace(right(r))) && validSpace(up(r))
				&& validSpace(down(r));
	}

	private boolean canMoveUp(Point p) {
		Point u = up(p);
		return validSpace(u) && (isBorder(up(u)) || validSpace(up(u))) && validSpace(left(u)) && validSpace(right(u));
	}

	private boolean canMoveDown(Point p) {
		Point d = down(p);
		return validSpace(d) && validSpace(left(d)) && (isBorder(down(d)) || validSpace(down(d)))
				&& validSpace(right(d));
	}

	private MazePoint randomNextPos(Point p) {
		ArrayList<Point> points = new ArrayList<Point>();
		if (canMoveRight(p)) {
			points.add(right(p));
		}
		if (canMoveLeft(p)) {
			points.add(left(p));
		}
		if (canMoveUp(p)) {
			points.add(up(p));
		}
		if (canMoveDown(p)) {
			points.add(down(p));
		}
		if (!points.isEmpty()) {
			int random = (int) (Math.random() * points.size());
			return new MazePoint(points.get(random), points.size());
		}
		return null;
	}
}
