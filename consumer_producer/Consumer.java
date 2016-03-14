package consumer_producer;

public class Consumer implements Runnable {

	private Thread t;
	private String substring;
	private static Storage storage;
	private String name;

	public Consumer(Storage _storage, String _substring, String _name) {

		name = _name;
		storage = _storage;
		this.substring = _substring;

		t = new Thread(this, name);
		t.start();
	}

	public void run() {

		while (!storage.isEmpty() || (storage.isOpen())) {

			Product product = storage.get(substring);

			if (product != null) {
				
				product.print();

			}
		}
	}

	public Thread getT() {

		return t;

	}

}
