import db.DBHelper;
import models.Book;
import models.Author;

import java.util.List;

public class Runner {

    public static void main(String[] args) {
        Author author1 = new Author("William", "Shakespeare");
        DBHelper.save(author1);

        Author author2 = new Author("Suzanne", "Collins");
        DBHelper.save(author2);

        Book book1 = new Book("Henry V", 1599, author1);
        DBHelper.save(book1);

        Book book2 = new Book("Coriolanus", 1605, author1);
        DBHelper.save(book2);

        Book book3 = new Book("The Merry Wives of Windsor", 1602, author1);
        DBHelper.save(book3);

        Book book4 = new Book("The Hunger Games", 2008, author2);
        DBHelper.save(book4);

        Book book5 = new Book("Catching Fire", 2009, author2);
        DBHelper.save(book5);

        Book book6 = new Book("Mockingjay", 2010, author2);
        DBHelper.save(book6);

        book2.setPublished(1604);
        DBHelper.update(book2);

        List<Book> books = DBHelper.getAll("Book");

        List<Author> authors = DBHelper.getAll("Author");

        List<Book> booksOfAuthor1 = DBHelper.getBooks(author1.getId());
    }

}
