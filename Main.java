import java.io.File;
import java.util.Stack;

import consumer_producer.Consumer;
import consumer_producer.Producer;
import consumer_producer.Storage;

public class Main {

	// get all text files in current directory and push them
	// into Stack<File> files
	private static void getTextFiles(File directory, Stack<File> files) {

		if (directory.isDirectory()) {

			// store all folders and files
			String foldersAndFiles[] = directory.list();

			for (String current : foldersAndFiles) {

				File f = new File(directory.getAbsolutePath() + File.separator + current);

				if (f.isDirectory()) {

					getTextFiles(f, files);

				} else {

					if (f.getName().endsWith(".txt")) {
						files.push(f);
					}

				}

			}

		} else {

			if (directory.getName().toLowerCase().endsWith(".txt")) {
				files.push(directory);
			}

		}

	}

	public static void main(String[] args) {

		// create object directory from type FIle

		File directory = new File("D:\\DOWNLOAD\\Eclipse\\workspace\\JavaProject\\Project\\folder");

		// create stack in which text files are stored

		Stack<File> filesStack = new Stack<File>();

		// push all text files from directory into filesStack

		getTextFiles(directory, filesStack);

		String searchedSubstring = "substring"; // string to be found

		// create storage

		Storage myStorage = new Storage();

		Producer.numberOFFiles = filesStack.size();

		// number of producers and consumers

		int numberProducers = 4;
		int numberConsumers = 4;

		// stack with producers

		Stack<Producer> producers = new Stack<Producer>();

		long t = System.currentTimeMillis(); // store current time in variable t

		// create producer threads

		for (int i = 0; i < numberProducers; i++) {

			producers.push(new Producer(myStorage, filesStack));

		}

		// create consumer threads

		Stack<Consumer> consumer = new Stack<Consumer>();

		for (int i = 0; i < numberConsumers; i++) {

			String name = Integer.toString(i + 1);
			consumer.push(new Consumer(myStorage, searchedSubstring, name));

		}

		try {

			for (int i = 0; i < numberConsumers; i++) {
				consumer.elementAt(i).getT().join();
			}

			for (int i = 0; i < numberProducers; i++) {
				producers.elementAt(i).getT().join();
			}

		} catch (Exception e) {
			System.out.println("Exception");
		}

		t = System.currentTimeMillis() - t;

		System.out.println();
		System.out.println("Lines Found: " + myStorage.getSubstringOccurences());

		System.out.println("Time: " + t);

	}
}