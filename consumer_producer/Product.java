package consumer_producer;

public class Product {

	private String line;
	private String fileName;

	private long lineNumebr;

	public Product(String _fileName, String _line, long _lineNumber) {

		line = _line;
		fileName = _fileName;
		lineNumebr = _lineNumber;

	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getLineNumebr() {
		return lineNumebr;
	}

	public void setLineNumebr(long lineNumebr) {
		this.lineNumebr = lineNumebr;
	}

	public void print() {

		System.out.println(fileName + " " + lineNumebr + " " + line);

	}
}
