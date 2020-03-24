package testgroup.library.service;

import org.springframework.transaction.annotation.Transactional;
import testgroup.library.model.Book;

import java.util.List;

public interface BookService {
    List<Book> allBooks(int page);//список всех книг
    List<Book> allBooksAuthor(int page);//список всех авторов
    List<Book> allBooksName(int page);//список книг по алфавиту
    List<Book> checkAuthor(String author, int page);//получение списка книг автора
    List<Book> checkSearchAuthor(String author, int page);//получение авторов, удовлетворяющих поиску
    List<Book> checkSearch(String searching, int page);//получение книг, удовлетворяющих поиску

    void add(Book book);//добавление книги в БД
    void delete(Book book);//удаление книги
    void edit(Book book);//редактирование книги
    Book getById(int id);//получение книги по её номеру

    int booksCount();//подсчёт всех книг
    int authorCount();//подсчёт всех авторов
    int authorNameCount(String author);//подсчёт книг данного автора
    int searchCount(String searching);//подсчёт книг, удовлетворяющих поиску
    int searchCountAuthor(String author);//подсчёт авторов, удовлетворяющих поиску

    boolean checkTitle(String title);//проверка названия на уникальность
}
