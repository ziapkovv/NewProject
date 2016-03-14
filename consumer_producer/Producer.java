package consumer_producer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Stack;

public class Producer implements Runnable {

	private Thread t;

	// storage for all producers
	private static Storage storage;

	// stack with text files for all producers
	private static Stack<File> fileContainer;

	// current number of written files
	private static int filesWritten = 0;

	// number of files
	public static int numberOFFiles = 0;

	public Producer(Storage _storage, Stack<File> _fileContainer) {

		storage = _storage;
		fileContainer = _fileContainer;
		t = new Thread(this, "Producer");
		t.start();

	}

	public void run() {

		File currentFile;

		while ((currentFile = fileContainer.empty() ? null : new File(fileContainer.pop().getAbsolutePath())) != null) {
			
			try (BufferedReader reader = new BufferedReader(new FileReader(currentFile))) {

				String line;

				int lineIterator = 1;

				while ((line = reader.readLine()) != null) {
					
					Product current = new Product(currentFile.getName(), line, lineIterator++);

					storage.put(current);

				}

				filesWritten++;

			} catch (Exception e) {

				System.out.println("Exception in Producer::run()");

			}
		}
		
		
		if (filesWritten == numberOFFiles) {
			storage.closeStorage();
		}
		
	}
	
	public Thread getT() {

		return t;

	}
}
