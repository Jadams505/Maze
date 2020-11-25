package main.java;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class MazeDrawer {
	private Maze maze;
	private boolean[][] solution;
	private BufferedImage image;
	private final int WALL = 483990, PATH = 16777215, SOLUTION = 167215;
	private int scale = 20;

	public MazeDrawer(Maze maze, boolean[][] solution) {
		this.maze = maze;
		this.solution = solution;
		if (maze.getWidth() + maze.getHeight() > 400) {
			scale = 1;
		}
		this.image = new BufferedImage(maze.getWidth() * scale, maze.getHeight() * scale, BufferedImage.TYPE_INT_RGB);
	}

	public void drawMaze() {
		int color = 0;
		for (int i = 0; i < maze.getHeight(); i++) {
			for (int j = 0; j < maze.getWidth(); j++) {
				if (maze.getMaze()[i][j]) {
					color = PATH;
				} else {
					color = WALL;
				}
				for (int k = 0; k < scale; k++) {
					for (int l = 0; l < scale; l++) {
						image.setRGB(j * scale + k, i * scale + l, color);
					}
				}
			}
		}
	}

	public void drawSolution() {
		for (int i = 0; i < maze.getHeight(); i++) {
			for (int j = 0; j < maze.getWidth(); j++) {
				if (solution[i][j]) {
					for (int k = 0; k < scale; k++) {
						for (int l = 0; l < scale; l++) {
							image.setRGB(j * scale + k, i * scale + l, SOLUTION);
						}
					}
				}
			}
		}
	}

	public void hideSolution() {
		for (int i = 0; i < maze.getHeight(); i++) {
			for (int j = 0; j < maze.getWidth(); j++) {
				if (solution[i][j]) {
					for (int k = 0; k < scale; k++) {
						for (int l = 0; l < scale; l++) {
							image.setRGB(j * scale + k, i * scale + l, PATH);
						}
					}
				}
			}
		}
	}

	public void toImage() {
		String fileName = "maze.png";
		File file = new File(fileName);
		try {
			ImageIO.write(image, "png", file);
			System.out.println("Maze drawn in " + fileName);
		} catch (Exception e) {
			System.out.println("File Fail");
		}
	}
}
