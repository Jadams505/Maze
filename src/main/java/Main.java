package main.java;

import java.io.File;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {
	public static void main(String[] args) {

	    Maze maze = new MazeGenerator(50, 50).generateMaze();
	    boolean[][] solution = MazeSolver.solveMaze(maze);
	    MazeDrawer md = new MazeDrawer(maze, solution);
	    boolean close = false;
	    int response;
		JFileChooser chooser;
		String currentDirectory = ".";
		do {
			chooser  = new JFileChooser(currentDirectory);
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			chooser.setDialogType(JFileChooser.SAVE_DIALOG);
			chooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG File", "png"));
			response = chooser.showSaveDialog(null);
			if(response == JFileChooser.APPROVE_OPTION) { // If the user selects save
				File file = chooser.getSelectedFile();
				currentDirectory = file.getAbsolutePath();
				if(FileUtil.validPath(file)) { // If the directory is valid
					if(FileUtil.validExtension(file, MazeDrawer.IMAGE_EXTENSIONS)) { // If the file is an image
						if(file.exists()) { // If the file already exists
							int confirm = JOptionPane.showConfirmDialog(null, "The file " + file.getName() + " already exists\nDo You want to overwrite it?", "Save File", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
							if(confirm == JOptionPane.YES_OPTION) {
								md.drawMaze(file);
								md.drawSolution(file);
								System.out.println("You overwrote a file");
								close = true;
							}
						}else { // If the file does not already exist
							md.drawMaze(file);
							md.drawSolution(file);
							System.out.println("File Successfully created");
							close = true;
						}
					}else { // If the file extension is wrong
						JOptionPane.showMessageDialog(null, "The file " + file.getName() + " is not an image", "Extension Mismatch", JOptionPane.ERROR_MESSAGE);
					}
				}else { // If the directory does not exist
					JOptionPane.showMessageDialog(null, "The directory " + FileUtil.getFilePath(file) +  " cannot be found", "Unidentified Directory", JOptionPane.ERROR_MESSAGE);
					System.out.println("Invalid Path");
				}
			}else { // If the user does not select save
				close = true;
			}
		}while(!close); 
		
	}
}
