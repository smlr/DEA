package vea.uebung05.a;

public class BookBean {
	private int bookid;
	private String name;
	private String isbn;
	private double price;

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String toString() {
		return "Buch nr. #" + this.bookid + " hat den Titel " + this.name
				+ " und ISBN " + this.isbn + " zu einem Preis von "
				+ this.price + ". ";
	}

}
