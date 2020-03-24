package testgroup.library.dao;

import testgroup.library.model.Book;

import java.util.List;

public interface BookDAO {
    List<Book> allBooks(int page);//списко всех книг
    @SuppressWarnings("unchecked")
    List<Book> allBooksAuthor(int page);//список авторов по алфавиту
    @SuppressWarnings("unchecked")
    List<Book> allBooksName(int page);//списко книг по алфавиту
    @SuppressWarnings("unchecked")
    List<Book> checkAuthor(String author, int page);//список книг автора
    List<Book> checkSearch(String searching, int page);//список найденных книг по поиску названия
    List<Book> checkSearchAuthor(String searching, int page);//список авторов найденных поиском

    void add(Book book);//добавление книги в БД
    void delete(Book book);//удаление книги из БД
    void edit(Book book);//редактирование книги
    Book getById(int id);//получение книги по номеру

    int authorNameCount(String author);//подсчёт книг данного автора
    int booksCount();//подсчёт всех книг
    int authorCount();//посчёт авторов
    int searchCount(String searching);//подсчёт найденных книг поиском по названию
    boolean checkTitle(String title);//проверка уникального названия
    int searchCountAuthor(String searching);//подсчёт авторов найденных поиском по автору
}
