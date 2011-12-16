package vea.uebung05.a;

public class Tester {

	public static void main(String[] args) {
		CustomerDAO customerDAO = new CustomerDAO();
		BookDAO bookDAO = new BookDAO();
		System.out.println("# Anzahl Customer:" + customerDAO.count());

		System.out.println("# Lade Buch mit bookid=1");
		BookBean book = bookDAO.load(1);
		System.out.println(book);
		System.out.println("# Schreibe Buch mit bookid=1 zurück in Datenbank");
		bookDAO.save(book);
		System.out.println("Das Buch wurde mir der neuen bookid "
				+ (bookDAO.generateBookId() - 1) + " gespeicehrt. ");
		System.out.println("Kontrolle: "
				+ bookDAO.load(bookDAO.generateBookId() - 1));

	}

}
