package assign03;
import java.io.FileWriter;
import java.io.IOException;
/*	FileHandler
	A wrapper for a FileWriter that Java provides. Handles the same/most operations
	and their exceptions. */
public class FileHandler {
	private FileWriter writer; 
	
	// Creates a new file with the given name
	public FileHandler(String fileName) {
		try {
			writer = new FileWriter(fileName);
		} catch (IOException e) {
			System.out.println("The File could not be opened: " + e.getLocalizedMessage());
		}
	}
	
	// Writes the given string of data to the file
	// Appends a newline char to the end of all data
	public void Write(String data) {
		if(writer == null) return;
		try {
			writer.write(data);
		} catch (IOException e) {
			System.out.println("Could not write: " + data + " to the file - " + e.getLocalizedMessage());
		}
	}
	
	// Closes the file 
	public void Done() {
		if(writer == null) return;
		try {
			writer.close();
		} catch (IOException e) {
			System.out.println("Exception arose when closing file - " + e.getLocalizedMessage());
		}
	}
	
	// Pushes buffered data to file
	public void Flush() {
		if(writer == null) return;
		try {
			writer.flush();
		} catch (IOException e) {
			System.out.println("Exception occured during flush - " + e.getLocalizedMessage());
		}
	}
} // End of FileHandler
