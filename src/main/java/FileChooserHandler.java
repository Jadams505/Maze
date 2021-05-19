package main.java;

import java.io.File;
import java.io.ObjectInputStream.GetField;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import main.gui.ImageViewerFrame;

public class FileChooserHandler {
	
	private JFileChooser chooser;
	private MazeDrawer md;
	
	public FileChooserHandler(MazeDrawer md) {
		this.md = md;
	}
	// Opens a generic save file chooser
	private void initializeChooser() {
		this.chooser = new JFileChooser(".");
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setDialogType(JFileChooser.SAVE_DIALOG);
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", MazeDrawer.IMAGE_EXTENSIONS));
	}
	private int getUserResponse() {
		return chooser.showSaveDialog(null);
	}
	// Returns true if the JFileChooser should open back up
	private boolean handleUserResponse() {
		if(getUserResponse() == JFileChooser.APPROVE_OPTION) { // If the user selects save
			File file = chooser.getSelectedFile();
			if(FileUtil.validPath(file)) { // If the directory is valid
				if(FileUtil.validExtension(file, MazeDrawer.IMAGE_EXTENSIONS)) { // If the file is an image
					if(file.exists()) { // If the file already exists
						int confirm = JOptionPane.showConfirmDialog(null, "The file " + file.getName() + " already exists\nDo You want to overwrite it?", "Save File", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
						if(confirm == JOptionPane.YES_OPTION) {
							md.drawMaze(file);
							md.drawSolution(file);
							int choice = JOptionPane.showOptionDialog(null, "File " + file.getName() + " has been overwritten", "File Overwritten", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[] {"Ok", "View"}, 0);
							if(choice == 1) {
								new ImageViewerFrame(new ImageIcon(file.getAbsolutePath()).getImage(), md.getMaze().getWidth(), md.getMaze().getHeight(), md.getScale());
							}
							return false;
						}
					}else { // If the file does not already exist
						md.drawMaze(file);
						md.drawSolution(file);
						int choice = JOptionPane.showOptionDialog(null, "File " + file.getName() + " has successfully been created", "File Saved", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[] {"Ok", "View"}, 0);
						if(choice == 1) {
							new ImageViewerFrame(new ImageIcon(file.getAbsolutePath()).getImage(), md.getMaze().getWidth(), md.getMaze().getHeight(), md.getScale());
						}
						return false;
					}
				}else { // If the file extension is wrong
					JOptionPane.showMessageDialog(null, "The file " + file.getName() + " is not an image", "Extension Mismatch", JOptionPane.ERROR_MESSAGE);
					return true;
				}
			}else { // If the directory does not exist
				JOptionPane.showMessageDialog(null, "The directory " + FileUtil.getFilePath(file) +  " cannot be found", "Unidentified Directory", JOptionPane.ERROR_MESSAGE);
				System.out.println("Invalid Path");
				return true;
			}
		}else { // If the user does not select save
			return false;
		}
		return true;
	} 
	public void handle() {
		boolean handling;
		do { // Keep opening back up the JFileChooser until the user exits or saves a file
			initializeChooser();
			handling = handleUserResponse();
		}while(handling);
	}
}
