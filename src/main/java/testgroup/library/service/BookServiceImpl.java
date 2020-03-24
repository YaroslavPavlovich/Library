package testgroup.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testgroup.library.dao.BookDAO;
import testgroup.library.dao.BookDAOImpl;
import testgroup.library.model.Book;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookDAO bookDAO = new BookDAOImpl();

    @Autowired
    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    @Transactional
    public List<Book> allBooks(int page) {
        return bookDAO.allBooks(page);
    }

    @Override
    @Transactional
    public List<Book> allBooksAuthor(int page) {
        return bookDAO.allBooksAuthor(page);
    }

    @Override
    @Transactional
    public List<Book> allBooksName(int page) {
        return bookDAO.allBooksName(page);
    }

    @Override
    @Transactional
    public List<Book> checkAuthor(String author, int page) {
        return bookDAO.checkAuthor(author,page);
    }

    @Override
    @Transactional
    public List<Book> checkSearch(String searching, int page) {
        return bookDAO.checkSearch(searching,page);
    }

    @Override
    @Transactional
    public List<Book> checkSearchAuthor(String searching, int page) {
        return bookDAO.checkSearchAuthor(searching,page);
    }

    @Override
    @Transactional
    public void add(Book book) {
        bookDAO.add(book);
    }

    @Override
    @Transactional
    public void delete(Book book) {
        bookDAO.delete(book);
    }

    @Override
    @Transactional
    public void edit(Book book) {
        bookDAO.edit(book);
    }

    @Override
    @Transactional
    public Book getById(int id) {
        return bookDAO.getById(id);
    }

    @Override
    @Transactional
    public int booksCount() {
        return bookDAO.booksCount();
    }

    @Override
    @Transactional
    public int authorCount() {
        return bookDAO.authorCount();
    }

    @Override
    @Transactional
    public int authorNameCount(String author) {
        return bookDAO.authorNameCount(author);
    }

    @Override
    @Transactional
    public int searchCount(String searching) {
        return bookDAO.searchCount(searching);
    }

    @Override
    @Transactional
    public int searchCountAuthor(String searching) {
        return bookDAO.searchCountAuthor(searching);
    }

    @Override
    @Transactional
    public boolean checkTitle(String title) {
        return bookDAO.checkTitle(title);
    }
}
