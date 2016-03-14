package UnitTests;

import org.junit.Test;

import consumer_producer.*;

import static org.junit.Assert.*;

public class StorageTest {

	// Test put method
	@Test
	public void testPut() {

		Storage storage = new Storage();

		storage.openStorage();

		storage.put(new Product("file1.txt", "Hi Java", 12));
		storage.put(new Product("file2.txt", "Hello Fmi", 10));

		// test if String "Hi Java" is equals to first product's line in Storage
		assertEquals("Hi Java", storage.getStorage().elementAt(0).getLine());

		// test if line of first Product is equal to 12
		assertEquals(12, storage.getStorage().elementAt(0).getLineNumebr());

		// test if file name of the first object put in storage is equals to
		// file1.txt
		assertEquals("file1.txt", storage.getStorage().elementAt(0).getFileName());

		// test if substring occurences are equal to 0
		assertEquals(storage.getSubstringOccurences(), 0);

	}

	@Test
	public void testGet() {

		Storage storage = new Storage();

		storage.openStorage();

		storage.put(new Product("file1.txt", "Hi Java", 12));

		Product expected = storage.get("Hi");

		assertEquals("file1.txt 12 Hi Java",
				expected.getFileName() + " " + expected.getLineNumebr() + " " + expected.getLine());

	}

	@Test
	public void testOpen() {

		Storage storage = new Storage();

		storage.openStorage();

		// test if storage is openned
		assertTrue(storage.isOpen());

	}

}
