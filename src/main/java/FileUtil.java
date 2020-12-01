package main.java;

import java.io.File;

public class FileUtil {
	// Gets that path of the file excluding the file itself
	public static String getFilePath(File file) {
		String path = file.getAbsolutePath();
		String testPath = path.substring(0, path.lastIndexOf("\\"));
		return testPath;
	}
	// Checks if the directory the file is in is valid
	public static boolean validPath(File file) {
		return new File(getFilePath(file)).exists();
	}
	// Gets the file extension of a file returns an empty string is none is found
	public static String getFileExtension(File file) {
		String fileName = file.getName();
		if(fileName.lastIndexOf(".") != -1) {
			String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
			return extension;
		}
		return "";
	}
	// Compares the file extension of a file with an array of valid extensions
	public static boolean validExtension(File file, String[] extensions) {
		for (int i = 0; i < extensions.length; i++) {
			if(extensions[i].equals(getFileExtension(file))) {
				return true;
			}
		}
		return false;
	}
	// Gets the name of the file without the extension
	public static String getFileName(File file) {
		String fileName = file.getName();
		return fileName.substring(0, fileName.lastIndexOf("."));
	}
}
