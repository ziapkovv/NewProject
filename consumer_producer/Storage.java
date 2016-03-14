package consumer_producer;
import java.util.Vector;

public class Storage {

	private static long substringOccurence;
	private static final int CAPACITY = 1000;
	private static Vector<Product> storage;

	private boolean isOpen;

	public Storage() {
		
		substringOccurence = 0;
		storage = new Vector<Product>();
		isOpen = true;

	}
	


	private Product found(String substring) {
		
		int first = 0;
		
		while (!storage.isEmpty() && !storage.elementAt(first).getLine().contains(substring)) {
			
			storage.remove(first);

		}

		if (storage.isEmpty()) {
			
			return null;
			
		} else {
			
			substringOccurence++;
			return storage.remove(first);
			
		}
	}

	public synchronized Product get(String substring) {

		while(storage.isEmpty() && isOpen) {
			try {
				
				wait();

			} catch (InterruptedException e) {
				System.out.println("InterruptedException caught");
			}
		}

		Product temp = found(substring);

		notify();
		return temp;
	}

	public synchronized void put(Product nextProduct) {

		while (storage.size() == CAPACITY) {
			try {
				
				wait();

			} catch (InterruptedException e) {
				System.out.println("InterruptedException caught");
			}
		}

		storage.addElement(nextProduct);	
		
		notify();

	}

	public synchronized void printStorage() {

		if (storage.isEmpty()) {
			System.out.println("Storage is empty!");
		} else {
			System.out.print(storage.size() + " STORAGE : ");
			for (int i = 0; i < storage.size(); i++) {
				System.out.print(storage.elementAt(i) + ", ");
			}

			System.out.println();
		}
	}

	public synchronized void closeStorage() {

		isOpen = false;
		// awakens next thread
		notify();
		
	}
	
	// open storage
	public void openStorage(){
		
		isOpen = true;
		
	}

	// check if storage is open
	public boolean isOpen() {

		return isOpen;

	}

	
	public long getSubstringOccurences() {

		return substringOccurence;

	}

	public boolean isEmpty() {

		return storage.size() == 0;
		
	}
	
	public Vector<Product> getStorage(){
		
		return storage;
		
	}
	
}